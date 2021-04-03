package Panier;

import Main.Ingrédient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class PanierController {
	ModèlePanier mdl;

    @FXML
    private Button btnRetour;

    @FXML
    private ScrollPane scrollPanier;
    
    @FXML
    private ImageView poubelle;

    public PanierController(ModèlePanier mdlPanier) {
		this.mdl=mdlPanier;
	}

	@FXML
    void goToAccueil(ActionEvent event) {
		this.mdl.goToAccueil();
    	
    }

	public void supprimerIngrédient(Ingrédient ingrédient) {
		this.mdl.supprimerIngr(ingrédient);
		
		
	}
	@FXML
	void viderPanier(MouseEvent event) {
		this.mdl.viderPanier();
	    }

	

}





