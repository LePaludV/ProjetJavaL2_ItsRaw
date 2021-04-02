package Accueil;
import javafx.fxml.FXML;


import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javafx.event.ActionEvent;

import Accueil.*;
import AccueilRecette.*;
import AjoutRecette.*;
import Main.*;

public class AccueilController {
	@FXML
	private Button btnAjoutRecette;

	@FXML
	public VBox recettes;

	@FXML
    public ScrollPane scrollRecettes;

	@FXML
	private ComboBox<String> searchBar;
	  
	@FXML
	    private ImageView panier;

	ModèleAccueil mdl;
	ModèleAccueilRecette mdlRecette;
	BarreDeRecherche<String> bdr;

	public AccueilController(ModèleAccueil mod, ModèleAccueilRecette mdlRecette)
	{
		this.mdl = mod;
		this.mdlRecette = mdlRecette;
	}

	// Event Listener on Button[#btnAjoutRecette].onAction
	@FXML
	public void ajouterRecette(ActionEvent event) {
		this.mdl.goToAjouterRecette();
	}

	public void openRecette(Recette rct) {
		this.mdl.goToAjouterAccueilRecette();
		this.mdlRecette.setRecette(rct);
	}

	public void compléterComboBox(Set<String> liste) {
		for (String s : liste) {
			this.searchBar.getItems().add(s);
		}
	}

	public void createSearchBar() {
		this.bdr = new BarreDeRecherche<String>(this.searchBar);
	}
	
	
	  
	@FXML
	void goToPanier(MouseEvent event) {
		this.mdl.goToPanier();
	}

	    
}
