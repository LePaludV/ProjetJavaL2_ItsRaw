import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Vue extends Application {
	Stage primaryStage;
	Accueil acc=new Accueil();
	GridPane rootLayout;
	  
	private enum typeInterface {ACCUEIL, AJOUT_RECETTE};
	private typeInterface currentInterface = typeInterface.AJOUT_RECETTE;
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("It's Raw");
        this.primaryStage.setWidth(800);
        this.primaryStage.setHeight(640);
        this.primaryStage.setResizable(false);
        
        if (this.currentInterface == typeInterface.AJOUT_RECETTE) {
            ModèleAjoutRecette mdlAjout = new ModèleAjoutRecette();
            AjoutRecetteController ctrlAjout = new AjoutRecetteController(mdlAjout);
            InterfaceAjouterRecette vueAjout = new InterfaceAjouterRecette(ctrlAjout);
            mdlAjout.addObserver(vueAjout);
            Scene scene=new Scene(vueAjout.getRoot());
            primaryStage.setScene(scene);
            
        }
      
        primaryStage.show();
        	  
           
        	  

	}

	
}
