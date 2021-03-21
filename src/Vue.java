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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Vue extends Application {
	Stage primaryStage;
	InterfaceAccueil acc=new InterfaceAccueil(null);
	GridPane rootLayout;
	ModèleAjoutRecette mdlAjout;
	ModèleAccueil mdlAccueil;

	enum typeInterface {ACCUEIL, AJOUT_RECETTE, ACCUEIL_RECETTE, ETAPE_RECETTE};
	public typeInterface currentInterface = typeInterface.ACCUEIL;
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("It's Raw");

        this.primaryStage.setResizable(false);

        this.changeWindow();
        primaryStage.show();
	}

	public void ingredientWindow() {

		Stage secondStage = new Stage();

		HBox parent = new HBox();
		Scene scene = new Scene(parent);

		TextField txtNom = new TextField();
		TextField txtQuantité = new TextField();
		TextField txtMesure = new TextField();

		Label lblNom = new Label("Nom :");
		Label lblQuantité = new Label("Quantité :");
		Label lblMesure = new Label("Mesure :");

		Button btnValider = new Button("Valider !");

		btnValider.setOnAction(e -> {
			String q = txtQuantité.getText();
			System.out.println(q);
			Integer quantité = Integer.parseInt(q);
			this.mdlAjout.validerIngrédient(txtNom.getText(), quantité, txtMesure.getText());
			secondStage.close();
		});

		parent.getChildren().add(lblNom);
		parent.getChildren().add(txtNom);
		parent.getChildren().add(lblQuantité);
		parent.getChildren().add(txtQuantité);
		parent.getChildren().add(lblMesure);
		parent.getChildren().add(txtMesure);
		parent.getChildren().add(btnValider);

		secondStage.setScene(scene);
		secondStage.show();
	}

	public void changeWindow() {
        if (this.currentInterface == typeInterface.AJOUT_RECETTE) {
            this.mdlAjout = new ModèleAjoutRecette(this);
            AjoutRecetteController ctrlAjout = new AjoutRecetteController(mdlAjout);
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
            Recette r1 = new Recette();
            Recette r2 = new Recette();
            Recette r3 = new Recette();
            try {
    			r1.photo = new Image(new FileInputStream("imgs/lasagnes.jpg"));
    			r2.photo = new Image(new FileInputStream("imgs/oeuf_a_la_tomate.jpg"));
    			r3.photo = new Image(new FileInputStream("imgs/pave_de_saumon_aux_cocos.jpg"));
    			ArrayList<Recette> lstRecettes = new ArrayList<>();
                lstRecettes.add(r1);
                // lstRecettes.add(r2);
                // lstRecettes.add(r3);
                vueAccueil.AfficherLesRecettes(lstRecettes);
    		} catch (FileNotFoundException e) {
    			System.out.println("Image non trouvée !");
    		}
            Scene scene=new Scene(InterfaceAccueil.getRoot());
            primaryStage.setScene(scene);
            this.primaryStage.sizeToScene();
         
        } else if (this.currentInterface == typeInterface.ACCUEIL_RECETTE) {

        } else if (this.currentInterface == typeInterface.ETAPE_RECETTE) {

        }
	}
}
