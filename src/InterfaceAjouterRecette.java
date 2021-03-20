import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

	
public class InterfaceAjouterRecette implements Observer {
	

	public static BorderPane getRoot() {
		 FXMLLoader loader = new FXMLLoader();
         loader.setLocation(Vue.class.getResource("ajoutRecette.fxml"));
         BorderPane rootLayout = null;
		try {
			rootLayout = (BorderPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 return rootLayout;
	}




	   

	    @FXML
	    void AddCatégorie(ActionEvent event) {
	    	System.out.println("Catégorie add");
	    }

	    @FXML
	    void AddEtape(ActionEvent event) {

	    }

	    @FXML
	    void AddIngrédient(ActionEvent event) {

	    }

	    @FXML
	    void Catégorie(MouseEvent event) {
	    	//Affichage des catégorie ajouté

	    }

	    @FXML
	    void Déscription(MouseEvent event) {
	    	//champ de texte avec la description 

	    }

	    @FXML
	    void ImportationImage(DragEvent event) {
	    	// test de l'importation d'une image jsp si ça marche 
	    	System.out.println(Image.toString());
	    }

	    @FXML
	    void Ingrédient(MouseEvent event) {
	    	//Affichage des Ingrédients ajouté
	    }

	    @FXML
	    void NomRecette(ActionEvent event) {
	    	//champ de texte pour le nom de la recette
	    }

	    @FXML
	    void NoteDifficulté(ActionEvent event) {
	    	//group de boutton radio qui vont de NoteDiff0 à NoteDiff4
	    }

	    @FXML
	    void NoteRecette(ActionEvent event) {
	    	////group de boutton radio qui vont de Note0 à Note4

	    }

	    @FXML
	    void Sauvegarder(ActionEvent event) {
	    	//Bouttons sauvegarder

	    }

	    @FXML
	    void TexteEtape(ActionEvent event) {
	    	//champ de texte pour ecrire une étape
	    }

	}

}


