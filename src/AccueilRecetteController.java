	import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.ToggleButton;
	import javafx.scene.image.ImageView;
public class AccueilRecetteController {

	    @FXML
	    private Button btnSuivieRecette;

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
	    void NoteDifficulté(ActionEvent event) {

	    }

	    @FXML
	    void NoteRecette(ActionEvent event) {

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
			
		}

		
	}


