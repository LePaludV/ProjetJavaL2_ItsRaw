import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class InterfaceAjouterRecette {
	
	public InterfaceAjouterRecette() {
		
	}
	
	public static StackPane getScene() {
		StackPane root = new StackPane();
		root.getChildren().add(new Label("Bordel ça fonctionne !"));
		
		
		
		return root;
	}
}
