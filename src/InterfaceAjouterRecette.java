import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

	
public class InterfaceAjouterRecette implements Observer {
	
	static AjoutRecetteController ctrlAjout;
	
	public InterfaceAjouterRecette(AjoutRecetteController ctrl) {
		ctrlAjout = ctrl;
	}

	public static BorderPane getRoot() {
		 FXMLLoader loader = new FXMLLoader();
         loader.setLocation(Vue.class.getResource("ajoutRecette.fxml"));
         loader.setController(ctrlAjout);
         BorderPane rootLayout = null;
		try {
			rootLayout = (BorderPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 return rootLayout;
	}

	@Override
	public void update(Observable arg0, Object rct) {
		System.out.println("hjbzefhj");
		Recette recette= (Recette) rct;
		VBox étapes = (VBox) rootLayout.lookup("#affEtape");
		for(String i: recette.étapes) {
			étapes.getChildren().add(new Label(i));
			System.out.println(i);
		}
		
		
		
		
	}

}


