import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class InterfaceAccueilRecette {
	static BorderPane rootLayout;
	static AccueilRecetteController ctrlAjout;

	public InterfaceAccueilRecette(AccueilRecetteController ctrl) {
		ctrlAjout = ctrl;
	}

	public static BorderPane getRoot() {
		
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Vue.class.getResource("accueilRecette.fxml"));
        loader.setController(ctrlAjout);

		try {
			rootLayout = (BorderPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		 return rootLayout;
	}
}
