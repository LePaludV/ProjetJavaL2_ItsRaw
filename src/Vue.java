import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Vue extends Application {
	Stage primaryStage;
	Accueil acc=new Accueil();
	GridPane rootLayout;

	private enum InterfaceType {
			AJOUT_RECETTE, ACCUEIL, PRESENTATION_RECETTE, ETAPE_RECETTE
	};

	private InterfaceType currentInterface = InterfaceType.AJOUT_RECETTE;

	@Override
	public void start(Stage primaryStage) throws Exception {

		if (currentInterface == InterfaceType.AJOUT_RECETTE) {
			this.primaryStage = primaryStage;
	        this.primaryStage.setTitle("It's Raw");
	        this.primaryStage.setWidth(600);
	        this.primaryStage.setHeight(600);

	        this.primaryStage.sizeToScene();

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
		} else if (currentInterface == InterfaceType.ACCUEIL) {

		} else if (currentInterface == InterfaceType.PRESENTATION_RECETTE) {

		} else if (currentInterface == InterfaceType.ETAPE_RECETTE) {

		}
	}

}
