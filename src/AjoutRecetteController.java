import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    public ToggleGroup note;
    
    @FXML
    private ToggleButton note0;

    @FXML
    private ToggleButton note1;

    @FXML
    private ToggleButton note2;

    @FXML
    private ToggleButton note3;

    @FXML
    private ToggleButton note4;
    
    @FXML
    public ToggleGroup difficulté;

    @FXML
    private ToggleButton difficluté0;
    
    @FXML
    private ToggleButton difficluté1;

    @FXML
    private ToggleButton difficluté2;

    @FXML
    private ToggleButton difficluté3;

    @FXML
    private ToggleButton difficluté4;
    
    @FXML
    private TextField TexteEtape;
    
	@FXML
	private ImageView imageView;
	
    @FXML
    private Spinner<?> nbrPersonne;
	
	@FXML
	private void dragOver(DragEvent event) {
		if (event.getDragboard().hasFiles()) {
			event.acceptTransferModes(TransferMode.ANY);
		}
	}
	
	
	
	@FXML
	private void dragDropped(DragEvent event) throws FileNotFoundException {
		List<File> files = event.getDragboard().getFiles();
		Image img = new Image(new FileInputStream(files.get(0)));
		this.mdl.ajoutPhoto(img);
	}

    @FXML
    void AddCatégorie(ActionEvent event) {
    	System.out.println(NomCategorie.getText());
    	this.mdl.ajoutCatégorie(NomCategorie.getText());
    	this.NomCategorie.setText(null);
    }

    @FXML
    void AddEtape(ActionEvent event) {
    	System.out.println(TexteEtape.getText());
    	this.nombreEtape++;
    	this.mdl.ajoutEtape(this.nombreEtape+". "+this.TexteEtape.getText());
    	this.TexteEtape.setText("");
    }

    @FXML
    void AddIngrédient(ActionEvent event) {
    	this.mdl.ajoutIngrédient();
    }

    @FXML
    void ImportationImage(DragEvent event) {

    }

    @FXML
    void NoteDifficulté(ActionEvent event) {
    	if (((ToggleButton) this.difficulté.getSelectedToggle()) != null) {
        	String s = ((ToggleButton) this.difficulté.getSelectedToggle()).getId();
        	System.out.println(Character.getNumericValue(s.charAt(s.length()-1)));
        	this.mdl.ajoutDifficulté(Character.getNumericValue(s.charAt(s.length()-1)));    		
    	}
    }

    @FXML
    void NoteRecette(ActionEvent event) {
    	if (((ToggleButton) this.note.getSelectedToggle()) != null) {
        	String s = ((ToggleButton) this.note.getSelectedToggle()).getId();
        	this.mdl.ajoutNote(Character.getNumericValue(s.charAt(s.length()-1)));    		
    	}
    }

    @FXML
    void Sauvegarder(ActionEvent event) {
    	System.out.println("Nom de la recette : "+NomRecette.getText());
    	System.out.println("Description de la recette : "+Description.getText());
    	System.out.println("Note :"+this.note.getSelectedToggle());
    	this.mdl.sauvegarder();
    }
	
	ModèleAjoutRecette mdl;
	ModèleAccueil mdlAcc;
	int nombreEtape;
	public AjoutRecetteController(ModèleAjoutRecette mdl, ModèleAccueil mdl2) {
		this.mdl = mdl;
		this.mdlAcc = mdl2;
		this.nombreEtape=0;
		SpinnerValueFactory<Integer> nbr =new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 100, 4,2);

		//this.nbrPersonne.setValueFactory((SpinnerValueFactory<?>) nbr);
		
	}
	
	public void sauvegarderRecette(Recette rct) {
		this.mdlAcc.ajouterRecette(rct);
	}
	
}
