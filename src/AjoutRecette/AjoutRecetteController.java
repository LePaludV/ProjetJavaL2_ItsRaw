package AjoutRecette;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.input.DragEvent;
import Accueil.*;
import AccueilRecette.*;
import AjoutRecette.*;
import Main.*;

public class AjoutRecetteController implements Observer {
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
	private Spinner<Integer> nbrPersonne;

	@FXML
	public VBox affImg;

	@FXML
	private TextField nomIngrédient;

	@FXML
	private Spinner<Integer> quantitéIngrédient;

	@FXML
	private MenuButton mesureIngrédient;

	@FXML
	public ToggleGroup mesures;

	@FXML
	private Button retour;

	@FXML
	private RadioMenuItem defaultRadiobutton;

	@FXML
	private ComboBox<String> searchCatégorie;
	
	@FXML
	public VBox affIngr;
	
	@FXML
	public ScrollPane scrollEtape;
	
    @FXML
    public AnchorPane anchorIngr;

	@FXML
	private void exit(ActionEvent event) {
		this.mdlAcc.changeWindow(false, null);
	}

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
	void AddEtape(ActionEvent event) {
		if (!(this.TexteEtape.getText().equals(""))) {
			this.mdl.ajoutEtape(this.TexteEtape.getText());
			this.TexteEtape.setText("");			
		}
	}

	@FXML
	void AddIngrédient(ActionEvent event) {
		String nom = this.nomIngrédient.getText();
		int quantité = this.quantitéIngrédient.getValue();
		String mesure = ((RadioMenuItem) this.mesures.getSelectedToggle()).getText();
		this.mdl.ajoutIngrédient(nom, quantité, mesure);
	}

	@FXML
	void ImportationImage(DragEvent event) {

	}

	@FXML
	void NoteDifficulté(ActionEvent event) {
		if (((ToggleButton) this.difficulté.getSelectedToggle()) != null) {
			String s = ((ToggleButton) this.difficulté.getSelectedToggle()).getId();
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
		if (this.affImg.getChildren().size()>1) {
			this.mdl.sauvegarder(NomRecette.getText(), this.Description.getText(),this.nbrPersonne.getValue());
		}
	}

	ModèleAjoutRecette mdl;
	ModèleAccueil mdlAcc;
	int nombreEtape;
	private BarreDeRecherche<String> barreCatégories;

	public AjoutRecetteController(ModèleAjoutRecette mdl, ModèleAccueil mdl2) {
		this.mdl = mdl;
		this.mdlAcc = mdl2;
		this.nombreEtape=0;

	}

	public void supprimerIngrédient(Ingrédient ing) {
		this.mdl.supprimerIng(ing);
	}

	public void supprimerCatégorie(String cat) {
		this.mdl.supprimerCat(cat);
	}

	public void supprimerEtape(String s) {
		this.mdl.supprimerEtape(s);
	}

	public void sauvegarderRecette(boolean save, Recette rct) {
		this.mdlAcc.changeWindow(save, rct);
	}

	public void personnesSpinner() {
		SpinnerValueFactory<Integer> nombre = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 4);
		this.nbrPersonne.setValueFactory(nombre);
		SpinnerValueFactory<Integer> nombre2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, 0);
		this.quantitéIngrédient.setValueFactory(nombre2);
		this.quantitéIngrédient.setEditable(true);
		this.mesures.selectToggle(this.defaultRadiobutton);
		this.affImg.setAlignment(Pos.TOP_CENTER);

		for (String s : this.mdlAcc.catégories.keySet()) {
			this.searchCatégorie.getItems().add(s);
		}

		this.barreCatégories = new BarreDeRecherche<String>(this.searchCatégorie);
		this.barreCatégories.addObserver(this);
	}

	@Override
	public void update(Observable o, Object texte) {
		String s = (String) texte;
		this.mdl.ajoutCatégorie(s.toLowerCase());
	}
}
