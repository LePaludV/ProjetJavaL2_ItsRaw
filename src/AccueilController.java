import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashSet;

import javafx.event.ActionEvent;

public class AccueilController {
	@FXML
	private Button btnAjoutRecette;
	@FXML
	public VBox recettes;
	
<<<<<<< Updated upstream
=======
	@FXML
    public ScrollPane scrollRecettes; 
	
	@FXML
	private ComboBox<String> searchBar;
	
	@FXML
	private void rechercherIngrédients(ActionEvent e) {
		
	}
	
>>>>>>> Stashed changes
	ModèleAccueil mdl;
	
	public AccueilController(ModèleAccueil mod)
	{
		this.mdl = mod;
	}

	// Event Listener on Button[#btnAjoutRecette].onAction
	@FXML
	public void ajouterRecette(ActionEvent event) {
		this.mdl.goToAjouterRecette();
	}
<<<<<<< Updated upstream
=======
	
	public void openRecette(Recette rct) {
		this.mdl.goToAjouterAccueilRecette();
		System.out.println("reçu");
		this.mdlRecette.setRecette(rct);
	}
	
	private void compléterComboBox(HashSet<Ingrédient> liste) {
		for (Ingrédient ing : liste) {
			this.searchBar.getItems().add(ing.nom);
		}
	}
>>>>>>> Stashed changes
}
