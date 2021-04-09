package Favoris;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import Main.Vue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;


public class InterfaceFavoris implements Observer {
	static SplitPane rootLayout;
	static FavorisController ctrlFav;
	static ModèleFavoris mdlFav;

	public InterfaceFavoris(FavorisController ctrlFav, ModèleFavoris mdlFav) {
		this.ctrlFav=ctrlFav;
		this.mdlFav=mdlFav;
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
		// TODO Auto-generated method stub
		
	}


	

}
