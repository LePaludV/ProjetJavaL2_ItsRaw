package Favoris;
import AccueilRecette.ModèleAccueilRecette;
import Main.Recette;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class FavorisController {

    @FXML
    private Button btnRetour;

    @FXML
    private ImageView poubelle;

    @FXML 
    public ScrollPane scrollFav;

    @FXML
    public VBox listeIngr;

    @FXML
    void goToAccueil(ActionEvent event) {
    	this.mdlFav.goToAccueil();
    }

    @FXML
    void viderPanier(MouseEvent event) {
    	this.mdlFav.viderFav();
    }



	ModèleFavoris mdlFav;
	ModèleAccueilRecette mdlRecette;
	public FavorisController(ModèleFavoris mdlFav,ModèleAccueilRecette mdlRecette) {
		this.mdlFav=mdlFav;
		this.mdlRecette=mdlRecette;
	}

	public void openRecette(Recette rct) {
		this.mdlFav.goToAccueilRecette();
		this.mdlRecette.setRecette(rct);
	}

}
