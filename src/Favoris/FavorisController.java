package Favoris;
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
    private ScrollPane scrollPanier;

    @FXML
    private VBox listeIngr;

    @FXML
    void goToAccueil(ActionEvent event) {
    	this.mdlFav.goToAccueil();
    }

    @FXML
    void viderPanier(MouseEvent event) {

    }



	ModèleFavoris mdlFav;
	public FavorisController(ModèleFavoris mdlFav) {
		this.mdlFav=mdlFav;
	}


}
