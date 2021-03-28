
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
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InterfaceAccueil implements Observer
{
	static SplitPane rootLayout;
	static AccueilController ctrlAccueil;
	
	public InterfaceAccueil(AccueilController ctrl)
	{
		ctrlAccueil = ctrl;
	}
	
	public static SplitPane getRoot() 
	{
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Vue.class.getResource("accueil.fxml"));
        loader.setController(ctrlAccueil);
        
		try {
			rootLayout = (SplitPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return rootLayout;
	}

	@Override
	public void update(Observable o, Object arg) {
		VBox recettes = ctrlAccueil.recettes;
		recettes.getChildren().clear();
		ArrayList<Recette> lstRecettes = (ArrayList<Recette>) arg;
		for(int i = 0; i < (int) (lstRecettes.size()/3)+lstRecettes.size()%3; i++)
		{
			HBox hb = new HBox();
			hb.setSpacing(20);
			for(int j = 0; j<3; j++)
			{
				if((i+2)*i+j<lstRecettes.size())
				{
					Button btn = new Button();
					btn.setId(Integer.toString((i+2)*i+j));
					
					btn.setOnAction(e -> {
						System.out.println(lstRecettes.get(Integer.parseInt(btn.getId())).nom);
						
						this.ctrlAccueil.openRecette(lstRecettes.get(Integer.parseInt(btn.getId())));
					});
					
					try {
						String nom  = lstRecettes.get((i+2)*i+j).nom;
						Image img = new Image(new FileInputStream("imagesRecette/"+nom+".png"));
						ImageView imgView = new ImageView(img);
						double largeurScroll = ctrlAccueil.scrollRecettes.getWidth();
						double largeurPhoto = img.getWidth();
						double coeff = (largeurScroll/largeurPhoto);
						imgView.setFitHeight((img.getHeight()*coeff)/3);
						imgView.setFitWidth((img.getWidth()*coeff)/3);
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
		ctrlAccueil.scrollRecettes.setContent(recettes);		
	}
}
