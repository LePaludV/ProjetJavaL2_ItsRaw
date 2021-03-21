
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
        
		try {
			rootLayout = (BorderPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 return rootLayout;
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
	
	void AfficherLesRecettes(Object arg)
	{
		ArrayList<Recette> lstRecettes = (ArrayList<Recette>) arg;
		for(int i = 0; i < (int) (lstRecettes.size()/3); i++) //le probleme vient de par là
		{
			// HBox hb = new HBox();
			for(int j = 0; j<3; j++)
			{
				// System.out.println(lstRecettes.get(0).photo);
				// this.ctrlAccueil.vboxRecette.getChildren().add(new ImageView(lstRecettes.get(i+j).photo));
				this.ctrlAccueil.vboxRecette.getChildren().add(new Button());
			}
		}
	}
}
