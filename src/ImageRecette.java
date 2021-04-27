package Main;

import javafx.scene.image.Image;

public class ImageRecette {
	
	Image img;
	String url;
	
	public ImageRecette(String url) {
		this.url = url;
		this.img = new Image(url);
	}
	
	public ImageRecette() {
		
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getImg() {
		return this.url;
	}
	
	public void setImg(String url) {
		this.img = new Image(url);
	}
}
