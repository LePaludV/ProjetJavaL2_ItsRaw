import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class AccueilController {
	@FXML
	private Button btnAjoutRecette;
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
