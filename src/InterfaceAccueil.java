
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InterfaceAccueil implements Observer
{
	static BorderPane rootLayout;
	static AccueilController ctrlAccueil;
	
	public InterfaceAccueil(AccueilController ctrl)
	{
		ctrlAccueil = ctrl;
	}
	
	public static BorderPane getRoot() 
	{
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Vue.class.getResource("accueil.fxml"));
        loader.setController(ctrlAccueil);
        
		try {
			rootLayout = (BorderPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 return rootLayout;
	}

	@Override
	public void update(Observable o, Object arg) {
		VBox recettes = (VBox) rootLayout.lookup("#recettes");
		ArrayList<Recette> lstRecettes = (ArrayList<Recette>) arg;
		System.out.println((lstRecettes.size()/3)+lstRecettes.size()%3);
		for(int i = 0; i < (int) (lstRecettes.size()/3)+lstRecettes.size()%3; i++)
		{
			HBox hb = new HBox();
			hb.setSpacing(20);
			for(int j = 0; j<3; j++)
			{
				if(i+j<lstRecettes.size())
				{
					Button btn = new Button();
					/*ImageView imageCourante = new ImageView(lstRecettes.get(i+j).photo);
					imageCourante.setFitHeight(100);
					imageCourante.setFitWidth(100);
					System.out.println("rec" + recettes);
					System.out.println(lstRecettes.get(i+j).photo);*/
					try {
						btn.backgroundProperty().setValue(new Background((new BackgroundImage(new Image(new FileInputStream("imgs/lasagnes.jpg")), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, null))));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//btn.setOnAction(ctrlAccueil.ajouterRecette(new ActionEvent());
					hb.getChildren().add(btn);
					//hb.getChildren().add(imageCourante);
				}
			}
			this.ctrlAccueil.recettes.getChildren().add(hb);
		}
		ScrollPane sp = new ScrollPane();
		sp.setContent(this.ctrlAccueil.recettes);
		
	}
}
