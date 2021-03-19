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
	  
	  @FXML 
	  Button ajoutEtape;
	@Override
	
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("It's Raw");
        this.primaryStage.setWidth(600);
        this.primaryStage.setHeight(600);
		
        this.primaryStage.sizeToScene();
        
        // Show the scene containing the root layout.
    
        	  try {	
        		  FXMLLoader loader = new FXMLLoader();
                  loader.setLocation(Vue.class.getResource("ajoutRecette.fxml"));
                  rootLayout = (GridPane) loader.load();
                  
                  Scene scene=new Scene(rootLayout);
                  primaryStage.setScene(scene);
                  primaryStage.show();
        	  }
        	  catch(IOException e) {
        		  e.printStackTrace();
        	  }
              
            private void Bouton(ActionEvent event) {
            	System.out.println("test");
            }
        	  

	}

	
}
