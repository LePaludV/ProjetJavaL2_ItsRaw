import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Vue extends Application {
	Accueil acc=new Accueil();
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("It's Raw");
		StackPane root= new StackPane();
//		root.getChildren().add(acc);
		Scene scene = new Scene(root, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

	
}
