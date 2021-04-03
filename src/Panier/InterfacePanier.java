package Panier;

import java.io.IOException;

import Main.Vue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;

public class InterfacePanier {
	static SplitPane rootLayout;
	static PanierController ctrlPanier;

	public InterfacePanier(PanierController ctrlPanier) {
		this.ctrlPanier=ctrlPanier;
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

}
