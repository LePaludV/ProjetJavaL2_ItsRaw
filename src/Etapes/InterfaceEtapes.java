package Etapes;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import Accueil.*;
import AccueilRecette.*;
import AjoutRecette.*;
import Main.*;
public class InterfaceEtapes implements Observer {

	static BorderPane rootLayout;
	static EtapesController ctrlEtapes;
	private static final float DIVISION_RATION = 3f;
	
	public InterfaceEtapes(EtapesController ctrl)
	{
		ctrlEtapes = ctrl;
	}
	
	public static BorderPane getRoot() 
	{
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Vue.class.getResource("etapes.fxml"));
        loader.setController(ctrlEtapes);
        
		try {
			rootLayout = (BorderPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return rootLayout;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
