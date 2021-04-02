<<<<<<< Updated upstream:src/Recette.java
=======
package Main;
import java.awt.image.BufferedImage;
import java.io.File;
>>>>>>> Stashed changes:src/Main/Recette.java
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class Recette implements Serializable {
	
	private static final long serialVersionUID = 1L;
<<<<<<< Updated upstream:src/Recette.java
	String nom, description;
	Image photo;
=======
	String nom;
	String description;
	transient Image photo;
>>>>>>> Stashed changes:src/Main/Recette.java
	ArrayList<Ingrédient> ingrédients;
	boolean[] note = {false,false,false,false,false};
	boolean saved;
	boolean[] difficulté = {false,false,false,false,false};
	ArrayList<String> étapes, catégories;
	int nbrPersonne;

	public Recette() {
		this.ingrédients = new ArrayList<Ingrédient>();
		this.étapes = new ArrayList<String>();
		this.catégories = new ArrayList<String>();
		this.saved=false;
		this.description = "";
		this.photo = null;
	}
	
	public int getNbrPersonne() {
		return this.nbrPersonne;
	}
	
	public void setNbrPersonne(int i) {
		this.nbrPersonne=i;
	}
	
	public String getNom() {
		return this.nom;
	}
<<<<<<< Updated upstream:src/Recette.java
	
	public void setNom(String nom) throws FileNotFoundException {
=======

	public Image getPhoto() {
		try {
            File file = new File("imagesRecette/"+this.getNom()+".png");
			BufferedImage bufferedImage = SwingFXUtils.fromFXImage(this.photo, null);
			ImageIO.write(bufferedImage, "png", file);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		return this.photo;
	}

	public Image getPhotoImage() {
		return this.photo;
	}
	
	public void setPhotoByImage(Image img) {
		this.photo = img;
	}

	public void setPhoto() throws FileNotFoundException {
		String s = "imagesRecette/"+this.nom+".png";
		FileInputStream fis = new FileInputStream(s);
		this.photo = new Image(fis);
	}

	public void setNom(String nom){
>>>>>>> Stashed changes:src/Main/Recette.java
		this.nom = nom;
		this.photo = new Image(new FileInputStream("imagesRecette/"+nom+".png"));
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String s) {
		this.description = s;
	}
	
	public String getNomImage() {
		return this.nom;
	}
	
	public void setNomImage(String s) {
		this.photo = new Image(s);
		System.out.println("photo chargé :"+this.photo);
	}
	
	public ArrayList<Ingrédient> getIngrédients(){
		return this.ingrédients;
	}
	
	public void setIngrédients(ArrayList<Ingrédient> ing) {
		this.ingrédients = ing;
	}
	
	public boolean getSave() {
		return this.saved;
	}
	
	public void setSave(boolean b) {
		this.saved = b;
	}
	
	
	public boolean[] getNote() {
		return this.note;
	}
	
	public void setNote(boolean[] lstB) {
		this.note = lstB;
	}
	
	public boolean[] getDifficulté() {
		return this.difficulté;
	}
	
	public void setDifficulté(boolean[] lstB) {
		this.difficulté = lstB;
	}
	
	public ArrayList<String> getEtapes(){
		return this.étapes;
	}
	
	public void setEtapes(ArrayList<String> lstEtape) {
		this.étapes = lstEtape;
	}
	
	public ArrayList<String> getCatégories(){
		return this.étapes;
	}
	
	public void setCatégories(ArrayList<String> lstCatégories) {
		this.catégories = lstCatégories;
	}
	
	/*
	@Override
	public String toString() {
		return "Recette [nom="+this.nom+
						 ", description="+this.description+
						 ", photo="+this.photo+
						 ", ingrédients="+this.ingrédients+
						 ", difficulté="+this.difficulté+
						 ", note="+this.note+
						 ", étapes="+this.étapes.toString()+
						 ", catégories="+this.catégories+"]";
	}
	*/
}
