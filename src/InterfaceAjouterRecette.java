import java.io.IOException;
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

	
public class InterfaceAjouterRecette {

	@FXML
	private Button AjoutIngrédient;
	
	@FXML
	private Label Ingrédient;
	
	@FXML
	private Button AjoutCatégorie;
	
	@FXML
	private Label Catégorie;
	
	@FXML
	private Button AjoutEtape;
	
	@FXML
	private TextArea Déscription;
	
	@FXML
	private ImageView Image;
	
	@FXML
	private TextField NomRecette;
	
	@FXML
	private Button Sauvegarder;
	
	@FXML
	private RadioButton Note0;
	
	@FXML
	private ToggleGroup Note;
	
	@FXML
	private RadioButton Note1;
	
	@FXML
	private RadioButton Note2;
	
	@FXML
	private RadioButton Note3;
	
	@FXML
	private RadioButton Note4;
	
	@FXML
	private RadioButton Diff0;
	
	@FXML
	private ToggleGroup Difficulté1;
	
	@FXML
	private RadioButton Diff1;
	
	@FXML
	private RadioButton NoteDiff4;
	
	@FXML
	private TextField TexteEtape;
	
	
	public InterfaceAjouterRecette() {
		
	}
	
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

	    }

	    @FXML
	    void Déscription(MouseEvent event) {

	    }

	    @FXML
	    void ImportationImage(DragEvent event) {

	    }

	    @FXML
	    void Ingrédient(MouseEvent event) {

	    }

	    @FXML
	    void NomRecette(ActionEvent event) {

	    }

	    @FXML
	    void NoteDifficulté(ActionEvent event) {

	    }

	    @FXML
	    void NoteRecette(ActionEvent event) {

	    }

	    @FXML
	    void Sauvegarder(ActionEvent event) {

	    }

	    @FXML
	    void TexteEtape(ActionEvent event) {

	    }

	}


