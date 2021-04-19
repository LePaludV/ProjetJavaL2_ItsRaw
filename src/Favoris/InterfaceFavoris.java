package Favoris;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import Main.*;
import Accueil.*;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class InterfaceFavoris implements Observer {
	static SplitPane rootLayout;
	static FavorisController ctrlFav;
	static ModèleFavoris mdlFav;
	HashMap<String, Recette> listeRecettes;
	ArrayList<String> listFavoris;
	static ModèleAccueil mdl;
	Image CV,CF;
		
	
	
	public InterfaceFavoris(FavorisController ctrlFav, ModèleFavoris mdlFav,ModèleAccueil mdlAcc) {
		this.ctrlFav=ctrlFav;
		this.mdlFav=mdlFav;
		this.mdl=mdlAcc;
		try {
			this.CV = new Image(new FileInputStream("imgs/loveAcc.png"));
			this.CF = new Image(new FileInputStream("imgs/loveRed.png"));
			
		} catch (FileNotFoundException e) {
			System.out.println("Image non trouvée !");
		}
		

	}

	

	

	public static Parent getRoot() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Vue.class.getResource("../favori.fxml"));
		loader.setController(ctrlFav);

		try {
			rootLayout = (SplitPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return rootLayout;
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		ArrayList<Recette> lstRecettes= new ArrayList<Recette>();
		this.listFavoris=this.mdlFav.loadFavoris();
		this.listeRecettes=this.mdl.getListeRecettes();

		for(String nom : listFavoris) {
			if(this.listeRecettes.containsKey(nom)) {
				Recette rct = this.listeRecettes.get(nom);
				lstRecettes.add(rct);
				
			}
		}
		
		
		VBox recettes = ctrlFav.listeIngr;
		recettes.getChildren().clear();
	
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
					ctrlFav.openRecette(lstRecettes.get(Integer.parseInt(btn.getId())));
				});
				try {
					FileInputStream fis = new FileInputStream("imagesRecette/"+nom+".png");
					ImageView imgView = new ImageView(new Image(fis));
					double largeurScroll = ctrlFav.scrollFav.getMinWidth();
					double largeurPhoto = recette_courante.getPhoto().getWidth();
					double coeff = (largeurScroll/largeurPhoto);
					imgView.setFitHeight((recette_courante.getPhoto().getHeight()*coeff)/3);
					imgView.setFitWidth((recette_courante.getPhoto().getWidth()*coeff)/3);
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
				   
						this.ctrlFav.modifRct(nom);
				        
				        
				    }
				);
				Coeur.setFitHeight(50);
				Coeur.setFitWidth(50);
				
				vbox.getChildren().addAll(btn, lblRecette,Coeur);
				hb.getChildren().add(vbox);		
			}
		}
		ctrlFav.listeIngr.getChildren().add(hb);
	}
	ScrollPane sp = new ScrollPane();
	sp.setContent(ctrlFav.listeIngr);
	ctrlFav.scrollFav.setContent(recettes);

	
	}
}
