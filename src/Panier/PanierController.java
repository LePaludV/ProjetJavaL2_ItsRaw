package Panier;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class PanierController {
	ModèlePanier mdl;

    @FXML
    private Button btnRetour;

    @FXML
    private ScrollPane scrollPanier;

    @FXML
    private VBox listeIngrédient;

    public PanierController(ModèlePanier mdlPanier) {
		this.mdl=mdlPanier;
	}

	@FXML
    void goToAccueil(ActionEvent event) {
		this.mdl.goToAccueil();
    	
    }

}





