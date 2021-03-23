import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;

public class AccueilController {
	@FXML
	private Button btnAjoutRecette;
	@FXML
	public VBox recettes;
	
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
}
