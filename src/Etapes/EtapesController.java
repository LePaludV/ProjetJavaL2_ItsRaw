package Etapes;


import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import AccueilRecette.ModèleAccueilRecette;
import Main.Recette;
import javafx.event.ActionEvent;

public class EtapesController {
	@FXML
	public Button precedent;
	@FXML
	public Button suivant;
	@FXML
	public Button retour;
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
	ModèleAccueilRecette mdlAcc;

	public EtapesController(ModèleEtapes mdl, ModèleAccueilRecette mdlAcc) {
		this.mdl = mdl;
		this.mdlAcc = mdlAcc;
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

	public void backToAccueilRecette(Recette rct) {
		this.mdl.goToAccueil();
		this.mdlAcc.setRecette(rct);
	}
	
	public void finRecette()
	{
		this.mdl.indexEtape -= 1;
	}

}
