package Etapes;


import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import AccueilRecette.ModèleAccueilRecette;
import javafx.event.ActionEvent;

public class EtapesController {
	@FXML
	public Button precedent;
	@FXML
	public Button suivant;
	@FXML
	private Button retour;
	@FXML
	public Label nom;
	@FXML
	public Label textDescription;
	@FXML
	public Label textEtape;
	@FXML
	public Label textIngredients;
	@FXML
	public ImageView image;

	ModèleEtapes mdl;

	public EtapesController(ModèleEtapes mdl) {
		this.mdl = mdl;
	}

	// Event Listener on Button[#precedent].onAction
	@FXML
	public void clickOnPrecedent(ActionEvent event) {
		this.mdl.clickPrecedent();
	}
	// Event Listener on Button[#suivant].onAction
	@FXML
	public void clickOnSuivant(ActionEvent event) {
		this.mdl.clickSuivant();
	}
	@FXML
	public void backToAccueilRecette(ActionEvent event) {
		this.mdl.goToAccueil();
	}
	
	public void finRecette()
	{
		this.mdl.indexEtape -= 1;
	}

}
