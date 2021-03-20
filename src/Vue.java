import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Vue extends Application {
	Stage primaryStage;
	Accueil acc=new Accueil();
	GridPane rootLayout;
	ModèleAjoutRecette mdlAjout;
	
	private enum typeInterface {ACCUEIL, AJOUT_RECETTE};
	private typeInterface currentInterface = typeInterface.AJOUT_RECETTE;
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
        }
	}
}
