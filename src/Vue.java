import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Vue extends Application {
	Stage primaryStage;
	InterfaceAccueil acc=new InterfaceAccueil(null);
	GridPane rootLayout;
	ModèleAjoutRecette mdlAjout;
	ModèleAccueil mdlAccueil;

	public enum typeInterface {ACCUEIL, AJOUT_RECETTE, ACCUEIL_RECETTE, ETAPE_RECETTE};
	public typeInterface currentInterface = typeInterface.ACCUEIL;
	@Override
	public void start(Stage primaryStage) throws Exception {
        this.mdlAccueil = new ModèleAccueil(this);
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("It's Raw");

        this.primaryStage.setResizable(false);

        this.changeWindow(this.currentInterface);
        primaryStage.show();
	}

	public void ingredientWindow() {

		Stage secondStage = new Stage();

		HBox parent = new HBox();
		Scene scene = new Scene(parent);

		TextField txtNom = new TextField();
		TextField txtQuantité = new TextField();

		MenuButton menu = new MenuButton("Mesure");
		String[] mesures = {"g", "kg", "l", "cl", "cuil. café", "cuil. soupe", "pincé(s)"};
		ArrayList<RadioMenuItem> listeItems = new ArrayList<RadioMenuItem>();
		ToggleGroup grp = new ToggleGroup();
		
		for (String s : mesures) {
			RadioMenuItem item = new RadioMenuItem(s);
			item.setToggleGroup(grp);
			menu.getItems().add(item);
		}
		
		Label lblNom = new Label("Nom :");
		Label lblQuantité = new Label("Quantité :");
		Label lblMesure = new Label("Mesure :");

		Button btnValider = new Button("Valider !");

		btnValider.setOnAction(e -> {
			String q = txtQuantité.getText();
			System.out.println(q);
			Integer quantité = Integer.parseInt(q);
			System.out.println(menu.getText());
			RadioMenuItem currentItem = (RadioMenuItem) grp.getSelectedToggle();
			this.mdlAjout.validerIngrédient(txtNom.getText(), quantité, currentItem.getText() );
			secondStage.close();
		});

		parent.getChildren().add(lblNom);
		parent.getChildren().add(txtNom);
		parent.getChildren().add(lblQuantité);
		parent.getChildren().add(txtQuantité);
		parent.getChildren().add(lblMesure);
		parent.getChildren().addAll(menu);
		parent.getChildren().add(btnValider);

		secondStage.setScene(scene);
		secondStage.show();
	}

	public void changeWindow(typeInterface inter) {
		this.currentInterface = inter;
        if (this.currentInterface == typeInterface.AJOUT_RECETTE) {
            this.mdlAjout = new ModèleAjoutRecette(this);
            AjoutRecetteController ctrlAjout = new AjoutRecetteController(mdlAjout, mdlAccueil);
            InterfaceAjouterRecette vueAjout = new InterfaceAjouterRecette(ctrlAjout);
            this.mdlAjout.addObserver(vueAjout);
            Scene scene=new Scene(InterfaceAjouterRecette.getRoot());
            primaryStage.setScene(scene);
            this.primaryStage.sizeToScene();
        } else if (this.currentInterface == typeInterface.ACCUEIL)
        {
        	this.mdlAccueil = new ModèleAccueil(this);
            AccueilController ctrlAccueil = new AccueilController(this.mdlAccueil);
            InterfaceAccueil vueAccueil = new InterfaceAccueil(ctrlAccueil);
            this.mdlAccueil.addObserver(vueAccueil);
            Scene scene=new Scene(InterfaceAccueil.getRoot());
            primaryStage.setScene(scene);
            this.primaryStage.sizeToScene();

        } else if (this.currentInterface == typeInterface.ACCUEIL_RECETTE) {

        } else if (this.currentInterface == typeInterface.ETAPE_RECETTE) {

        }
	}
}
