import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.ToggleGroup;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.image.ImageView;

import javafx.scene.control.RadioButton;

import javafx.scene.input.MouseEvent;

import javafx.scene.input.DragEvent;

public class AjoutRecetteController {
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

	// Event Listener on Button[#AjoutIngrédient].onAction
	@FXML
	public void AddIngrédient(ActionEvent event) {
		
	}
	// Event Listener on Label[#Ingrédient].onDragDetected
	@FXML
	public void Ingrédient(MouseEvent event) {
		
	}
	// Event Listener on Button[#AjoutCatégorie].onAction
	@FXML
	public void AddCatégorie(ActionEvent event) {
		
	}
	// Event Listener on Label[#Catégorie].onDragDetected
	@FXML
	public void Catégorie(MouseEvent event) {
		
	}
	// Event Listener on Button[#AjoutEtape].onAction
	@FXML
	public void AddEtape(ActionEvent event) {
		
	}
	// Event Listener on TextArea[#Description].onDragDetected
	@FXML
	public void Description(MouseEvent event) {
		
	}
	// Event Listener on ImageView[#Image].onDragDropped
	@FXML
	public void ImportationImage(DragEvent event) {
		
	}
	// Event Listener on TextField[#NomRecette].onAction
	@FXML
	public void NomRecette(ActionEvent event) {
		this.mdl.ajoutNom(((TextField) event.getSource()).getText());
		
	}
	// Event Listener on Button[#Sauvegarder].onAction
	@FXML
	public void Sauvegarder(ActionEvent event) {
		
	}
	// Event Listener on RadioButton[#Note0].onAction
	@FXML
	public void NoteRecette(ActionEvent event) {
		
	}
	// Event Listener on RadioButton[#Diff0].onAction
	@FXML
	public void NoteDifficulté(ActionEvent event) {
		
	}
	// Event Listener on TextField[#TexteEtape].onAction
	@FXML
	public void TexteEtape(ActionEvent event) {
		
	}
	
	ModèleAjoutRecette mdl;
	public AjoutRecetteController(ModèleAjoutRecette mdl) {
		this.mdl = mdl;
	}
}
