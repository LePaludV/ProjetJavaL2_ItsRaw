package AjoutRecette;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Accueil.*;
import AccueilRecette.*;
import AjoutRecette.*;
import Main.*;

public class InterfaceAjouterRecette implements Observer {
	static BorderPane rootLayout;
	static AjoutRecetteController ctrlAjout;
	InputStream is1,is2;
	Image etoileJaune,etoileNoire;
	InputStream it1,it2;
	Image ToqueGrise,ToqueNoire;


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
         loader.setLocation(Vue.class.getResource("../ajoutRecette.fxml"));
         loader.setController(ctrlAjout);

		try {
			rootLayout = (BorderPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		 return rootLayout;
	}

	public void validerSauvegarde(Recette recette) {
		Stage secondStage = new Stage();
		VBox parent = new VBox();
		Scene scene = new Scene(parent);
		Label lbl = new Label("Voulez-vous valider cette recette ?");
		Button oui = new Button("Oui");
		Button non = new Button("Non");
		HBox listeBtns = new HBox();

		non.setAlignment(Pos.CENTER);
		oui.setAlignment(Pos.CENTER);

		oui.setOnAction(e -> {
			secondStage.close();
			this.ctrlAjout.sauvegarderRecette(true, recette);
		});

		non.setOnAction(e -> {
			secondStage.close();
			recette.setSave(false);
		});
		listeBtns.setAlignment(Pos.CENTER);
		listeBtns.getChildren().addAll(oui,non);

		parent.getChildren().add(lbl);
		parent.getChildren().add(listeBtns);

		secondStage.setScene(scene);
		secondStage.show();
	}

	public void exitRecette(Recette recette) {
		Stage secondStage = new Stage();
		VBox parent = new VBox();
		Scene scene = new Scene(parent);
		Label lbl = new Label("Voulez-vous sauvegarder cette recette ?");
		Button oui = new Button("Oui");
		Button non = new Button("Non");
		Button annuler = new Button("Annuler");
		HBox listeBtns = new HBox();

		listeBtns.setAlignment(Pos.CENTER);

		oui.setOnAction(e -> {
			secondStage.close();
			ctrlAjout.sauvegarderRecette(true, recette);
		});

		non.setOnAction(e -> {
			recette.setSave(false);
			ctrlAjout.sauvegarderRecette(false, recette);
			secondStage.close();
		});

		annuler.setOnAction(e -> {
			recette.setSave(false);
			secondStage.close();
		});

		listeBtns.getChildren().addAll(oui,non);

		parent.getChildren().add(lbl);
		parent.getChildren().add(listeBtns);

		secondStage.setScene(scene);
		secondStage.show();

	}

	@Override
	public void update(Observable arg0, Object rct) {
		//Affichage des étapes
		Recette recette = (Recette) rct;
		ctrlAjout.affEtape.getChildren().clear();
		for(int i=0;i<recette.getEtapes().size();i++) {
			String text = (i+1)+". "+recette.getEtapes().get(i);
			Label etape = new Label(text);
			etape.getStyleClass().add("label2");
			etape.setPrefWidth(ctrlAjout.affEtape.getWidth());
			etape.setPrefHeight(15);
			etape.setWrapText(true);
			Button btn = new Croix();
			btn.setOnAction(e -> {
				ctrlAjout.supprimerEtape(text.split(" ")[1]);
			});
			HBox hb = new HBox();
			hb.setAlignment(Pos.CENTER_LEFT);
			hb.getChildren().addAll(btn, etape);
			ctrlAjout.affEtape.getChildren().add(hb);
			ctrlAjout.anchorEtape.setPrefHeight(ctrlAjout.affEtape.getHeight());
		}

		//Affichage des catégories
		VBox catégorie = (VBox) rootLayout.lookup("#affCat");
		catégorie.getChildren().clear();
		for(String i: recette.getCatégories()) {
			Label cat = new Label(i);
			cat.getStyleClass().add("label3");
			Button btn = new Croix();
			btn.setOnAction(e -> {
				ctrlAjout.supprimerCatégorie(i);
			});
			HBox hb = new HBox();
			hb.setAlignment(Pos.CENTER_LEFT);
			hb.getChildren().addAll(btn, cat);
			catégorie.getChildren().add(hb);
		}

		//Affichage des ingrédients
		VBox ingrédient = (VBox) rootLayout.lookup("#affIngr");
		ingrédient.getChildren().clear();
		for(Ingrédient i: recette.getIngrédients()) {
			Button btn = new Croix();
			btn.setOnAction(e -> {
				ctrlAjout.supprimerIngrédient(i);
			});
			Label lbl;
			System.out.println("quantité : "+i.quantité);
			if (i.quantité != 0.0) {
				lbl = new Label((int)i.quantité+" "+i.mesure+" "+i.nom);
			} else {
				lbl = new Label(i.nom);
			}
			lbl.getStyleClass().add("label3");
			lbl.setWrapText(true);
			lbl.setPrefWidth(ctrlAjout.affEtape.getWidth());
			lbl.setPrefHeight(15);
			lbl.setWrapText(true);
			HBox hb = new HBox();
			hb.setAlignment(Pos.CENTER_LEFT);
			hb.getChildren().addAll(btn, lbl);
			ingrédient.getChildren().add(hb);
			ctrlAjout.anchorIngr.setPrefHeight(ctrlAjout.affIngr.getHeight());
		}


		if (recette.getPhoto() != null && ctrlAjout.affImg.getChildren().size() < 2) {
			ImageView imgView = new ImageView(recette.getPhoto());
			double hauteurVbox = ctrlAjout.affImg.getHeight();
			double hauteurPhoto = recette.getPhoto().getHeight();
			double coeff = (hauteurVbox/hauteurPhoto);
			imgView.setFitHeight(hauteurPhoto*coeff);
			imgView.setFitWidth(recette.getPhoto().getWidth()*coeff);
			ctrlAjout.affImg.getChildren().add(imgView);
			ctrlAjout.affImg.setSpacing(4);
		}

		ObservableList<Toggle> note = ctrlAjout.note.getToggles();
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

		ObservableList<Toggle> difficulté = ctrlAjout.difficulté.getToggles();
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

		if (recette.getSave()) {
			this.validerSauvegarde(recette);
		}
	}
}
