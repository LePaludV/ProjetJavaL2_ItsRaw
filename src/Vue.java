import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Vue extends Application {
	Accueil acc=new Accueil();
<<<<<<< Updated upstream
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("It's Raw");
		StackPane root= new StackPane();
//		root.getChildren().add(acc);
		Scene scene = new Scene(root, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
=======
	GridPane rootLayout;
	
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
>>>>>>> Stashed changes
	}
	
}
