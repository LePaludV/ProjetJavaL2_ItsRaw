
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
import javafx.geometry.Pos;
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
	private static final float DIVISION_RATION = 1.8f;
	
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
			e.printStackTrace();
		}
		 return rootLayout;
	}

	@Override
	public void update(Observable o, Object arg) {
		VBox recettes = (VBox) rootLayout.lookup("#recettes");
		recettes.getChildren().clear();
		ArrayList<Recette> lstRecettes = (ArrayList<Recette>) arg;
		System.out.println(lstRecettes);
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
					btn.setId(Character.getName(i));
					btn.setOnAction(e -> {
						this.ctrlAccueil.openRecette(lstRecettes.get(Integer.parseInt(btn.getId())));
					});
					
					try {
						String nom  = lstRecettes.get(i+j).nom;
						Image img = new Image(new FileInputStream("imagesRecette/"+nom+".png"));
						ImageView imgView = new ImageView(img);
						imgView.setFitHeight(img.getHeight()/DIVISION_RATION);
						imgView.setFitWidth(img.getWidth()/DIVISION_RATION);
						btn.setText(nom);
						btn.setAlignment(Pos.BOTTOM_RIGHT);
						btn.setGraphic(imgView);
						btn.setBackground(null);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					hb.getChildren().add(btn);
				}
			}
			ctrlAccueil.recettes.getChildren().add(hb);
		}
		ScrollPane sp = new ScrollPane();
		sp.setContent(ctrlAccueil.recettes);
		
	}
}
