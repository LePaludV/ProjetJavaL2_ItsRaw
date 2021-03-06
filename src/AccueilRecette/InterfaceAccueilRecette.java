package AccueilRecette;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import Accueil.*;
import AccueilRecette.*;
import AjoutRecette.*;
import Favoris.*;
import Main.*;
public class InterfaceAccueilRecette  implements Observer {
	static BorderPane rootLayout;
	static AccueilRecetteController ctrlAccueilRct;
	InputStream is1,is2;
	Image etoileJaune,etoileNoire;
	InputStream it1,it2,CV,CF;
	Image ToqueGrise,ToqueNoire;
	Image CoeurVide,CoeurFull;
	ArrayList<String> listeFav;
	ModèleFavoris mdlFav;

	public InterfaceAccueilRecette(AccueilRecetteController ctrl,ModèleFavoris mdlFav) {
		ctrlAccueilRct = ctrl;
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
		
		try {
			CV = new FileInputStream("imgs/love.png");
			CF= new FileInputStream("imgs/loveRed.png");
			CoeurVide = new Image(CV);
			CoeurFull = new Image(CF);
		} catch (FileNotFoundException e) {
			System.out.println("Image non trouvée !");
		}
		this.mdlFav=mdlFav;
		
	}

	public static BorderPane getRoot() {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Vue.class.getResource("../accueilRecette.fxml"));
		loader.setController(ctrlAccueilRct);

		try {
			rootLayout = (BorderPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return rootLayout;
	}

	public void update(Observable arg0, Object rct) { 
		Recette recette = (Recette) rct;
		ObservableList<Toggle> note = ctrlAccueilRct.note.getToggles();
		for (int i=0;i<note.size();i++) {
			if (note.get(i) instanceof ToggleButton) {
				ToggleButton tb = (ToggleButton) note.get(i);
				tb.setBackground(null);
				if (recette.getNote()[i]) {
					tb.setGraphic(new ImageView(this.etoileJaune));
				} else {
					tb.setGraphic(new ImageView(this.etoileNoire));
				}
			}
		}

		ObservableList<Toggle> difficulté = ctrlAccueilRct.difficulté.getToggles();
		for (int i=0;i<difficulté.size();i++) {
			if (difficulté.get(i) instanceof ToggleButton) {
				ToggleButton tb = (ToggleButton) difficulté.get(i);
				tb.setBackground(null);

				if (recette.getDifficulté()[i]) {
					tb.setGraphic(new ImageView(this.ToqueNoire));
				} else {
					tb.setGraphic(new ImageView(this.ToqueGrise));
				}
			}
		}
		loadRecette(recette);
		this.listeFav=this.mdlFav.loadFavoris();
		if(this.listeFav.contains(recette.getNom())) {
			this.ctrlAccueilRct.imgFav.setImage(CoeurFull);
		}
		else {
			this.ctrlAccueilRct.imgFav.setImage(CoeurVide);
		}
		
	}
	
	public void loadRecette(Recette rct) {
		if(rct==null) {
			ctrlAccueilRct.TexteDescription.setText("Problème chargement de la recette séléctionner dans l'accueil "
					+ "\nOU interface lancé par défaut (cf ''currentInterface'' dans Vue.java)");
		}
		else {
			ctrlAccueilRct.TexteDescription.setText(rct.getDescription());
			String ingr = null;
			for(Ingrédient s: rct.getIngrédients()) {
				ingr+="- "+s.quantité+s.mesure+" "+s.nom+"\n";
			}

			ctrlAccueilRct.TexteIngrédient.setText("ingr");
			System.out.println("nom de la recette "+rct.getNom());
			System.out.println("photo de la recette "+rct.getPhoto());
			
			ImageView imgView = new ImageView(rct.getPhoto());
			double hauteurVbox = rct.getPhoto().getHeight();
			double hauteurPhoto = rct.getPhoto().getHeight();
			double coeff = (hauteurVbox/hauteurPhoto);
			//ctrlAccueilRct.ImageRecette.setFitHeight(hauteurPhoto);
			//ctrlAccueilRct.ImageRecette.setFitWidth(rct.getPhoto().getWidth()/coeff);
			ctrlAccueilRct.ImageRecette.setImage(rct.getPhoto());
			
			
			ctrlAccueilRct.Nom.setText(rct.getNom());
			ctrlAccueilRct.nbrPersonne.setText("Pour "+rct.getNbrPersonne()+" Personne(s).");
			ctrlAccueilRct.TexteIngrédient.setText(rct.getIngrédients().toString());
			StringBuilder Ingrédient = new StringBuilder();
			for(int i = 0;i<rct.getIngrédients().size();i++) {
				Ingrédient.append(rct.getIngrédients().get(i).nom+" "+(int)rct.getIngrédients().get(i).quantité+" "+rct.getIngrédients().get(i).mesure+"\n");
			}
				ctrlAccueilRct.TexteIngrédient.setText(Ingrédient.toString());
			};
			
	}
}
