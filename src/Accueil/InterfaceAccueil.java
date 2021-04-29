package Accueil;


import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Locale.Category;
import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import Accueil.*;
import AccueilRecette.*;
import AjoutRecette.*;
import Favoris.*;
import Main.*;

public class InterfaceAccueil implements Observer
{
	static SplitPane rootLayout;
	static AccueilController ctrlAccueil;
	Image CV,CF;
	int nb_col = 3;
	ArrayList listFavoris;
	ModèleFavoris mdlFav;

	public InterfaceAccueil(AccueilController ctrl,ModèleFavoris mdlFav)
	{
		this.mdlFav=mdlFav;
		ctrlAccueil = ctrl;
		try {
			this.CV = new Image(new FileInputStream("imgs/loveAcc.png"));
			this.CF = new Image(new FileInputStream("imgs/loveRed.png"));

		} catch (FileNotFoundException e) {
			System.out.println("Image non trouvée !");
		}
	}

	public static SplitPane getRoot()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Vue.class.getResource("../accueil.fxml"));
		loader.setController(ctrlAccueil);

		try {
			rootLayout = (SplitPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rootLayout;
	}

	@Override
	public void update(Observable o, Object arg) {
		this.listFavoris=this.mdlFav.loadFavoris();
		if(arg instanceof ArrayList<?>)
		{
			VBox recettes = ctrlAccueil.recettes;
			recettes.getChildren().clear();
			recettes.setSpacing(10);
			ArrayList<Recette> lstRecettes = (ArrayList<Recette>) arg;
			System.out.println("Taille des recettes : "+lstRecettes.size());
			int x = 0;
			if (lstRecettes.size()%nb_col != 0) {
				x = 1;
			}
			for(int i = 0; i < (int) (lstRecettes.size()/nb_col)+x; i++)
			{
				HBox hb = new HBox();
				hb.setAlignment(Pos.CENTER);
				hb.setSpacing(20);
				for(int j = 0; j<nb_col; j++)
				{
					if(i*nb_col+j<lstRecettes.size())
					{
						Recette recette_courante = lstRecettes.get(i*nb_col+j);
						String nom  = recette_courante.getNom();
						VBox vbox = new VBox();
						vbox.setAlignment(Pos.CENTER);
						vbox.getStyleClass().add("box");
						Button btn = new Button();
						Text lblRecette = new Text(nom);
						lblRecette.setTextAlignment(TextAlignment.CENTER);
						lblRecette.getStyleClass().add("label3");
						lblRecette.setWrappingWidth(250);
						btn.setId(Integer.toString(i*nb_col+j));
						btn.setBackground(null);
						btn.setOnAction(e -> {
							ctrlAccueil.openRecette(lstRecettes.get(Integer.parseInt(btn.getId())));
						});
						try {
							FileInputStream fis = new FileInputStream("imagesRecette/"+nom+".png");
							ImageView imgView = new ImageView(new Image(fis));
							double largeurScroll = ctrlAccueil.scrollRecettes.getMinWidth();
							double largeurPhoto = recette_courante.getPhoto().getWidth();
							double coeff = (largeurScroll/largeurPhoto);
							imgView.setFitHeight((recette_courante.getPhoto().getHeight()*coeff)/nb_col);
							imgView.setFitWidth((recette_courante.getPhoto().getWidth()*coeff)/nb_col);
							btn.setGraphic(imgView);
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}

						ImageView Coeur=new ImageView();

						if(this.listFavoris.contains(nom)) {

							Coeur.setImage(this.CF);

						}else {
							Coeur.setImage(this.CV);
						}
						Coeur.setOnMouseClicked(e -> {
							ModèleFavoris mdlFav=new ModèleFavoris(null);
							this.ctrlAccueil.modifRct(nom);
						});
						Coeur.setFitHeight(50);
						Coeur.setFitWidth(50);
						vbox.getChildren().addAll(btn, lblRecette,Coeur);
						hb.getChildren().add(vbox);		

					}
				}
				ctrlAccueil.recettes.getChildren().add(hb);
			}
			ScrollPane sp = new ScrollPane();
			sp.setContent(ctrlAccueil.recettes);
			ctrlAccueil.scrollRecettes.setContent(recettes);
		}
	}
}
