package Panier;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Main.Ingrédient;
import Main.Vue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InterfacePanier implements Observer {
	static SplitPane rootLayout;
	static PanierController ctrlPanier;
	static ModèlePanier mdlPanier;

	public InterfacePanier(PanierController ctrlPanier,ModèlePanier mdlPanier) {
		this.ctrlPanier=ctrlPanier;
		this.mdlPanier=mdlPanier;
	}

	public static Parent getRoot() {
		 FXMLLoader loader = new FXMLLoader();
         loader.setLocation(Vue.class.getResource("../panier.fxml"));
         loader.setController(ctrlPanier);

		try {
			rootLayout = (SplitPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		 return rootLayout;
	}

	public void loadPanier() {
	
	
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		ArrayList<Ingrédient> Ingrédients=this.mdlPanier.ChargementPanier();
		VBox lstIngr = (VBox) rootLayout.lookup("#listeIngr");
		lstIngr.getChildren().clear();
		
		for(Ingrédient i: Ingrédients) {
			Button btn = new Button();
			btn.setOnAction(e -> {
				ctrlPanier.supprimerIngrédient(i);
			});
			
			Label lbl = new Label(i.nom+" "+(int)i.quantité+" "+i.mesure);
			HBox hb = new HBox();
			hb.setAlignment(Pos.CENTER);
			try {
				FileInputStream fis = new FileInputStream("imgs/red_cross.png");
				ImageView imgView = new ImageView(new Image(fis));
				imgView.setFitHeight(20);
				imgView.setFitWidth(20);
				btn.setGraphic(imgView);
				btn.setBackground(null);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			hb.getChildren().addAll(btn, lbl);
			
			lstIngr.getChildren().add(hb);
		}
		
	}
}
