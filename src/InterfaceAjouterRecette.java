import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class InterfaceAjouterRecette {
	
	public InterfaceAjouterRecette() {
		
	}
	
	public static StackPane getStackPane() {
		StackPane root = new StackPane();
		TextField  nomRecette = new TextField ();
		root.getChildren().add(nomRecette);
		
		
<<<<<<< Updated upstream
		
		return root;
=======
	}

	@Override
	public void update(Observable arg0, Object rct) {

		Recette recette = (Recette) rct;
		VBox étapes = (VBox) rootLayout.lookup("#affEtape");
		étapes.getChildren().clear();
		for(String i: recette.étapes) {
			Label etape = new Label(i);
			etape.setWrapText(true);
			étapes.getChildren().add(etape);
		}

		VBox catégorie = (VBox) rootLayout.lookup("#affCat");
		catégorie.getChildren().clear();
		for(String i: recette.catégories) {
			catégorie.getChildren().add(new Label(i));
		}

		VBox ingrédient = (VBox) rootLayout.lookup("#affIngr");
		ingrédient.getChildren().clear();
		for(Ingrédient i: recette.ingrédients) {
			ingrédient.getChildren().add(new Label(i.nom+" : "+i.quantité+i.mesure));
		}


		if (recette.photo != null) {
			ImageView imgView = (ImageView) rootLayout.lookup("#affImg");
			imgView.setImage(recette.photo);
			imgView.setFitHeight(recette.photo.getHeight()/5);
			imgView.setFitWidth(recette.photo.getWidth()/5);
			
		}

		ObservableList<Toggle> note = ctrlAjout.note.getToggles();
		for (int i=0;i<note.size();i++) {
			if (note.get(i) instanceof ToggleButton) {
				ToggleButton tb = (ToggleButton) note.get(i);
				tb.setBackground(null);
				if (recette.note[i]) {
					tb.setGraphic(new ImageView(this.etoileJaune));
				} else {
					tb.setGraphic(new ImageView(this.etoileNoire));
				}
			}
		}

		ObservableList<Toggle> difficulté = ctrlAjout.difficulté.getToggles();
		for (int i=0;i<difficulté.size();i++) {
			if (difficulté.get(i) instanceof ToggleButton) {
				ToggleButton tb = (ToggleButton) difficulté.get(i);
				tb.setBackground(null);

				if (recette.difficulté[i]) {
					tb.setGraphic(new ImageView(this.ToqueNoire));
				} else {
					tb.setGraphic(new ImageView(this.ToqueGrise));
				}
			}
		}

		if (recette.saved) {
			this.validerSauvegarde(recette);
		}
>>>>>>> Stashed changes
	}
}
