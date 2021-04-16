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
import Accueil.*;
import AccueilRecette.*;
import AjoutRecette.*;
import Favoris.*;
import Main.*;

public class InterfaceAccueil implements Observer
{
	static SplitPane rootLayout;
	static AccueilController ctrlAccueil;
	InputStream CV,CF;
	ImageView CoeurVide,CoeurFull;
	ArrayList listFavoris;
	ModèleFavoris mdlFav;
	

	public InterfaceAccueil(AccueilController ctrl,ModèleFavoris mdlFav)
	{
		this.mdlFav=mdlFav;
		ctrlAccueil = ctrl;
		try {
			CV = new FileInputStream("imgs/loveAcc.png");
			CF= new FileInputStream("imgs/loveRed.png");
			CoeurVide = new ImageView(new Image(CV));
			CoeurFull = new ImageView(new Image(CF));
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
			ArrayList<Recette> lstRecettes = (ArrayList<Recette>) arg;
			for(int i = 0; i < (int) (lstRecettes.size()/3)+lstRecettes.size()%3; i++)
			{
				HBox hb = new HBox();
				hb.setSpacing(20);
				for(int j = 0; j<3; j++)
				{
					if((i+2)*i+j<lstRecettes.size())
					{
						Recette recette_courante = lstRecettes.get((i+2)*i+j);
						String nom  = recette_courante.getNom();
						VBox vbox = new VBox();
						vbox.setAlignment(Pos.CENTER);
						vbox.getStyleClass().add("box");
						Button btn = new Button();
						Label lblRecette = new Label(nom);
						lblRecette.getStyleClass().add("label3");
						btn.setId(Integer.toString((i+2)*i+j));
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
							imgView.setFitHeight((recette_courante.getPhoto().getHeight()*coeff)/3);
							imgView.setFitWidth((recette_courante.getPhoto().getWidth()*coeff)/3);
							btn.setGraphic(imgView);
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						
						ImageView Coeur=new ImageView();
						Coeur=CoeurVide;
						if(this.listFavoris.contains(nom)) {
							Coeur=CoeurFull;
						}
						
						Coeur.setOnMouseClicked(e -> {
						   ModèleFavoris mdlFav=new ModèleFavoris(null);
						   
								this.ctrlAccueil.modifRct(nom);
						        System.out.println("Ajout au fav");
						        
						    }
						);
						Coeur.setFitHeight(50);
						Coeur.setFitWidth(50);
						
						
						HBox txtbtn=new HBox();
						
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
