package Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Croix extends Button {
	
	private static final String  PATH = "imgs/red_cross.png";
	private ImageView imgView;
		
	public Croix() {
		super();
		FileInputStream fis;
		try {
			fis = new FileInputStream(PATH);
			this.imgView = new ImageView(new Image(fis));
			this.setGraphic(this.imgView);
			this.imgView.setFitHeight(20);
			this.imgView.setFitWidth(20);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.getStyleClass().add("cross_button");
		this.setBackground(null);
	}
}
