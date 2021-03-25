	import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
public class AccueilRecetteController {

	    @FXML
	    private Button btnSuivieRecette;
	    
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
	    private Label TexteIngrédient;

	    @FXML
	    private ImageView ImageRecette;

	    @FXML
	    private Label TexteDescription;
	    
	    @FXML
	    private Label Nom;
	    
	    @FXML
	    private Label nbrPersonne;

	    @FXML
	    void NoteDifficulté(ActionEvent event) {
	    	if (((ToggleButton) this.difficulté.getSelectedToggle()) != null) {
	        	String s = ((ToggleButton) this.difficulté.getSelectedToggle()).getId();
	        	this.mdl.ajoutDifficulté(Character.getNumericValue(s.charAt(s.length()-1)));    		
	    	}
	    }

	    @FXML
	    void NoteRecette(ActionEvent event) {
	    	System.out.println("fonction note recette controller ");
	    	if (((ToggleButton) this.note.getSelectedToggle()) != null) {
	        	String s = ((ToggleButton) this.note.getSelectedToggle()).getId();
	        	this.mdl.ajoutNote(Character.getNumericValue(s.charAt(s.length()-1)));    		
	    	}
	    }

	    @FXML
	    void btnSuivieRecette(ActionEvent event) {

	    }
	   
		ModèleAccueilRecette mdl;

		public AccueilRecetteController(ModèleAccueilRecette mdl) {
		
			this.mdl=mdl;

		}
		
		public void loadRecette(Recette rct) {
			TexteDescription.setText(rct.description);
			String ingr = null;
			for(Ingrédient s: rct.ingrédients) {
				ingr+="- "+s.quantité+s.mesure+" "+s.nom+"\n";
			}
			String cat = null;
			for(String s: rct.catégories) {
				ingr+="- "+s+"\n";
			}
			ImageRecette.setImage(rct.photo);
			Nom.setText(rct.nom);
			nbrPersonne.setText("Pour "+rct.nbrPersonne+" Personne(s).");
			
		}

		
	}


