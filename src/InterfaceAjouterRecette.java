import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class InterfaceAjouterRecette {
	
	public InterfaceAjouterRecette() {
		
	}
	
	public static StackPane getStackPane() {
		StackPane root = new StackPane();
		TextField  nomRecette = new TextField ();
		root.getChildren().add(nomRecette);
		
		
		
		return root;
	}
}
