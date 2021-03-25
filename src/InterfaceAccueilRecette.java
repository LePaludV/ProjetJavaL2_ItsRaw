import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class InterfaceAccueilRecette  implements Observer {
	static BorderPane rootLayout;
	static AccueilRecetteController ctrlAjout;
	InputStream is1,is2;
	Image etoileJaune,etoileNoire;
	InputStream it1,it2;
	Image ToqueGrise,ToqueNoire;

	public InterfaceAccueilRecette(AccueilRecetteController ctrl) {
		ctrlAjout = ctrl;
		try {
			is1 = new FileInputStream("imgs/etoileJaune.png");
			is2 = new FileInputStream("imgs/etoileNoire.png");
			etoileJaune = new Image(is1);
			etoileNoire = new Image(is2);
		} catch (FileNotFoundException e) {
			System.out.println("Image non trouvée !");
		}

		try {
			it1 = new FileInputStream("imgs/toqueGrise.png");
			it2 = new FileInputStream("imgs/toqueNoire.png");
			ToqueGrise = new Image(it1);
			ToqueNoire = new Image(it2);
		} catch (FileNotFoundException e) {
			System.out.println("Image non trouvée !");
		}
	
	}

	public static BorderPane getRoot() {
		
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Vue.class.getResource("accueilRecette.fxml"));
        loader.setController(ctrlAjout);

		try {
			rootLayout = (BorderPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		 return rootLayout;
	}
	
	public void update(Observable arg0, Object rct) {
		//Pb sur cette uptade qui s'acitve pas, peut être à cause de l'update dans interfaceAjoutRecette avec des package
		//+ besoin de savoir l'état (avec un if )peut-être .
		//Quand on va modifier la note / difficulté le uptade dois faire la sauvegarder automatique (ce que faisait le btn sauvegarder de ajout recette).
		// quand on update faut créer un nouveau fichier xml a partir de l'acnien avec la nouvel note et supprimer l'ancien ensuite ?
	Recette recette = (Recette) rct;
	System.out.println("test");
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
	}
	
}
