package Panier;

import java.io.IOException;
import java.util.ArrayList;

import Main.Ingrédient;
import Main.Vue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;

public class InterfacePanier {
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
		ArrayList<Ingrédient> Ingrédients=this.mdlPanier.ChargementPanier();
		StringBuilder strIngr = new StringBuilder();
		for(int i = 0;i<Ingrédients.size();i++) {
			strIngr.append(Ingrédients.get(i).nom+" "+(int)Ingrédients.get(i).quantité+" "+Ingrédients.get(i).mesure+"\n");
		}
			this.ctrlPanier.TextePanier.setText(strIngr.toString());
		};
		
	

}
