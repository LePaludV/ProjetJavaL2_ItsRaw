import javafx.scene.image.Image;

public class Recette {
		
	String nom, description;
	Ingr�dient[] ingr�dients;
	int temps,difficult�,note;
	Image photo;
	String[] �tapes, cat�gories;
	
	public Recette(String nom, Ingr�dient[] ingr�dients, int temps, int difficult�, int note, Image photo, String description, String[] �tapes) {
		this.nom = nom;
		this.ingr�dients = ingr�dients;
		this.temps = temps;
		this.difficult� = difficult�;
		this.note = note;
		this.photo = photo;
		this.description = description;
		this.�tapes = �tapes;
	}
	

}
