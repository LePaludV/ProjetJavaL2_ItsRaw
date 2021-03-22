import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

	
public class InterfaceAjouterRecette implements Observer {
	static BorderPane rootLayout;
	static AjoutRecetteController ctrlAjout;
	InputStream is1,is2;
	Image etoileJaune,etoileNoire;
	
	public InterfaceAjouterRecette(AjoutRecetteController ctrl) {
		ctrlAjout = ctrl;
		try {
			is1 = new FileInputStream("imgs/etoileJaune.png");
			is2 = new FileInputStream("imgs/etoileNoire.png");
			etoileJaune = new Image(is1);
			etoileNoire = new Image(is2);
		} catch (FileNotFoundException e) {
			System.out.println("Image non trouvée !");
		}
		
	}

	public static BorderPane getRoot() {
		 FXMLLoader loader = new FXMLLoader();
         loader.setLocation(Vue.class.getResource("ajoutRecette.fxml"));
         loader.setController(ctrlAjout);
         
		try {
			rootLayout = (BorderPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		 return rootLayout;
	}

	@Override
	public void update(Observable arg0, Object rct) {
		
		Recette recette = (Recette) rct;
		VBox étapes = (VBox) rootLayout.lookup("#affEtape");
		étapes.getChildren().clear();
		for(String i: recette.étapes) {
			étapes.getChildren().add(new Label(i));
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
	}	
}


