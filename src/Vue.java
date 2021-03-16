import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Vue extends Application {
	Stage primaryStage;
	Accueil acc=new Accueil();
	BorderPane rootLayout;
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("It's Raw");
        this.primaryStage.setWidth(600);
        this.primaryStage.setHeight(600);
		
        
        
        // Show the scene containing the root layout.
        Scene scene=new Scene(InterfaceAjouterRecette.getScene());
        primaryStage.setScene(scene);
        primaryStage.show();
        


	}

	
}
