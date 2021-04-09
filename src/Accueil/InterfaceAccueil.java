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
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import Main.*;

public class InterfaceAccueil implements Observer
{
	static SplitPane rootLayout;
	static AccueilController ctrlAccueil;

	public InterfaceAccueil(AccueilController ctrl)
	{
		ctrlAccueil = ctrl;
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
		System.out.println(arg);
		if(arg instanceof ArrayList<?>)
		{
			VBox recettes = ctrlAccueil.recettes;
			recettes.getChildren().clear();
			ArrayList<Recette> lstRecettes = (ArrayList<Recette>) arg;
			System.out.println("lstRecettes : "+lstRecettes);
			for(int i = 0; i < (int) (lstRecettes.size()/3)+lstRecettes.size()%3; i++)
			{
				HBox hb = new HBox();
				hb.setSpacing(20);
				for(int j = 0; j<3; j++)
				{
					if((i+2)*i+j<lstRecettes.size())
					{
						Button btn = new Button();
						btn.setId(Integer.toString((i+2)*i+j));

						btn.setOnAction(e -> {
							System.out.println(lstRecettes.get(Integer.parseInt(btn.getId())).getPhoto());
							ctrlAccueil.openRecette(lstRecettes.get(Integer.parseInt(btn.getId())));
						});

						Recette recette_courante = lstRecettes.get((i+2)*i+j);
						String nom  = recette_courante.getNom();
						System.out.println(nom);
						try {
							FileInputStream fis = new FileInputStream("imagesRecette/"+nom+".png");
							System.out.println(fis);
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
						btn.setBackground(null);
						hb.getChildren().add(btn);							
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
