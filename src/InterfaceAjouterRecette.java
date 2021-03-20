import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

	
public class InterfaceAjouterRecette implements Observer {
	static BorderPane rootLayout;
	static AjoutRecetteController ctrlAjout;
	
	public InterfaceAjouterRecette(AjoutRecetteController ctrl) {
		ctrlAjout = ctrl;
	}

	public static BorderPane getRoot() {
		 FXMLLoader loader = new FXMLLoader();
         loader.setLocation(Vue.class.getResource("ajoutRecette.fxml"));
         loader.setController(ctrlAjout);
         
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
		
		Recette recette = (Recette) rct;
		VBox étapes = (VBox) rootLayout.lookup("#affEtape");
		étapes.getChildren().clear();
		for(String i: recette.étapes) {
			étapes.getChildren().add(new Label(i));
		}
		
		VBox catégorie = (VBox) rootLayout.lookup("#affCat");
		catégorie.getChildren().clear();
		for(String i: recette.catégories) {
			catégorie.getChildren().add(new Label(i));
		}
		
		VBox ingrédient = (VBox) rootLayout.lookup("#affIngr");
		ingrédient.getChildren().clear();
		for(Ingrédient i: recette.ingrédients) {
			ingrédient.getChildren().add(new Label(i.nom+" : "+i.quantité+i.mesure));
		}
		

		
		
		
		
	}

}


