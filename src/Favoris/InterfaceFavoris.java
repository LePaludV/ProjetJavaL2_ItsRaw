package Favoris;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import Main.*;
import Accueil.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;


public class InterfaceFavoris implements Observer {
	static SplitPane rootLayout;
	static FavorisController ctrlFav;
	static ModèleFavoris mdlFav;
	HashMap<String, Recette> listeRecettes;
	ArrayList<String> listFavoris;
	static ModèleAccueil mdl;

	public InterfaceFavoris(FavorisController ctrlFav, ModèleFavoris mdlFav,ModèleAccueil mdlAcc) {
		this.ctrlFav=ctrlFav;
		this.mdlFav=mdlFav;
		this.mdl=mdlAcc;

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
		this.listFavoris=this.mdlFav.loadFavoris();
		this.listeRecettes=this.mdl.getListeRecettes();

		for(String nom : listFavoris) {
			if(this.listeRecettes.containsKey(nom)) {
				Recette rct = this.listeRecettes.get(nom);
				System.out.println(rct.getNom());
			}
		}
		
		
	}


	

}
