package AccueilRecette;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

import Accueil.*;
import AccueilRecette.*;
import AjoutRecette.*;
import Main.*;

public class AccueilRecetteController {

	    @FXML
	    private Button btnSuivieRecette;
	    
	    @FXML
	    public ToggleGroup note;

	    @FXML
	    public ToggleButton note0;

	    @FXML
	    public ToggleButton note1;

	    @FXML
	    public ToggleButton note2;

	    @FXML
	    public ToggleButton note3;

	    @FXML
	    public ToggleButton note4;
	    
	    @FXML
	    public ToggleGroup difficulté;

	    @FXML
	    public ToggleButton difficluté0;

	    @FXML
	    public ToggleButton difficluté1;

	    @FXML
	    public ToggleButton difficluté2;

	    @FXML
	    public ToggleButton difficluté3;

	    @FXML
	    public ToggleButton difficluté4;

	    @FXML
	    public Label TexteIngrédient;

	    @FXML
	    public ImageView ImageRecette;

	    @FXML
	    public Label TexteDescription;
	    
	    @FXML
	    public Label Nom;
	    
	    @FXML
	    public Label nbrPersonne;
	    
	    @FXML
	    private Button btnRetour;

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
	    void btnSuivieRecette(ActionEvent event) {
	    	this.mdl.goToEtapes();
	    }
	    
	    @FXML
	    void RetourAccueil(ActionEvent event) {
	    	this.mdl.goToAccueil();

	    }
	    
	   
		ModèleAccueilRecette mdl;
	

		public AccueilRecetteController(ModèleAccueilRecette mdl) {
		
			this.mdl=mdl;
			

		}
		
		

		
	}


