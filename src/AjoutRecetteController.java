import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.ToggleGroup;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.image.ImageView;

import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.input.DragEvent;

public class AjoutRecetteController {
	 @FXML
	    private Button AjoutIngrédient;

	    @FXML
	    private Label Ingrédient;

	    @FXML
	    private TextField NomIngredient;

	    @FXML
	    private Button AjoutCatégorie;

	    @FXML
	    private Label Catégorie;

	    @FXML
	    private TextField NomCategorie;

	    @FXML
	    private Button AjoutEtape;

	    @FXML
	    private TextArea Description;

	    @FXML
	    private Label LabelEtape;

	    @FXML
	    private Text Image;

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
	    private Spinner<?> SpinnerNum;

	    @FXML
	    private TextField TexteEtape;

	    @FXML
	    void AddCatégorie(ActionEvent event) {
	    	System.out.println(NomCategorie.getText());
	    	this.mdl.ajoutCatégorie(NomCategorie.getText());

	    }

	    @FXML
	    void AddEtape(ActionEvent event) {
	    	System.out.println(TexteEtape.getText());
	    	this.mdl.ajoutEtape(TexteEtape.getText());

	    }

	    @FXML
	    void AddIngrédient(ActionEvent event) {
	    	System.out.println(NomIngredient.getText());
	    	this.mdl.ajoutIngrédient(NomIngredient.getText(), 0, null);

	    }

	    @FXML
	    void ImportationImage(DragEvent event) {

	    }

	    @FXML
	    void NoteDifficulté(ActionEvent event) {

	    }

	    @FXML
	    void NoteRecette(ActionEvent event) {

	    }

	    @FXML
	    void Sauvegarder(ActionEvent event) {
	    	System.out.println("Nom de la recette : "+NomRecette.getText());
	    	System.out.println("Description de la recette : "+Description.getText());
	    	System.out.println(Note.getSelectedToggle());
	    	System.out.println(Difficulté1.getSelectedToggle());

	    }

	
	
	ModèleAjoutRecette mdl;
	public AjoutRecetteController(ModèleAjoutRecette mdl) {
		this.mdl = mdl;
	}
	
	
}
