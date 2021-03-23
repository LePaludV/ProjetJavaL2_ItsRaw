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

	public enum InterfaceType {
			AJOUT_RECETTE, ACCUEIL, PRESENTATION_RECETTE, ETAPE_RECETTE
	};

	public InterfaceType currentInterface = InterfaceType.AJOUT_RECETTE;

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

	public void changeWindow() {
        if (this.currentInterface == typeInterface.AJOUT_RECETTE) {
            this.mdlAjout = new ModèleAjoutRecette(this);
            AjoutRecetteController ctrlAjout = new AjoutRecetteController(mdlAjout, this.mdlAccueil);
            InterfaceAjouterRecette vueAjout = new InterfaceAjouterRecette(ctrlAjout);
            this.mdlAjout.addObserver(vueAjout);
            Scene scene=new Scene(InterfaceAjouterRecette.getRoot());
            primaryStage.setScene(scene);
            this.primaryStage.sizeToScene();
        } else if (this.currentInterface == typeInterface.ACCUEIL)
        {
        	this.mdlAccueil = new ModèleAccueil(this);
            AccueilController ctrlAccueil = new AccueilController(this.mdlAccueil);
            InterfaceAccueil vueAccueil = new InterfaceAccueil(ctrlAccueil);
            this.mdlAccueil.addObserver(vueAccueil);
            Scene scene=new Scene(InterfaceAccueil.getRoot());
            Recette r1 = new Recette();
            Recette r2 = new Recette();
            Recette r3 = new Recette();
            try {
    			r1.photo = new Image(new FileInputStream("imgs/lasagnes.jpg"));
    			r2.photo = new Image(new FileInputStream("imgs/oeuf_a_la_tomate.jpg"));
    			r3.photo = new Image(new FileInputStream("imgs/pave_de_saumon_aux_cocos.jpg"));
    			ArrayList<Recette> lstRecettes = new ArrayList<>();
                lstRecettes.add(r1);
                lstRecettes.add(r2);
                lstRecettes.add(r3);
                vueAccueil.AfficherLesRecettes(lstRecettes);
    		} catch (FileNotFoundException e) {
    			System.out.println("Image non trouvée !");
    		}
            primaryStage.setScene(scene);
            this.primaryStage.sizeToScene();

        } else if (this.currentInterface == typeInterface.ACCUEIL_RECETTE) {

        } else if (this.currentInterface == typeInterface.ETAPE_RECETTE) {

        }
	}
}
