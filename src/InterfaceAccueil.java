
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class InterfaceAccueil implements Observer
{
	static BorderPane rootLayout;
	static AccueilController ctrlAccueil;
	
	public InterfaceAccueil(AccueilController ctrl)
	{
		ctrlAccueil = ctrl;
	}
	
	public static BorderPane getRoot() 
	{
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Vue.class.getResource("accueil.fxml"));
        loader.setController(ctrlAccueil);
        System.out.println(loader);
        
		try {
			rootLayout = (BorderPane) loader.load();
		} catch (IOException e) {
			System.out.println("salut");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 return rootLayout;
	}

	@Override
	public void update(Observable o, Object arg) {
		ArrayList<Recette> lstRecettes = (ArrayList<Recette>) arg;
		
		
	}
}
