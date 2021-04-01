package Main;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class Recette implements Serializable {
	
	private static final long serialVersionUID = 1L;
	String nom;
	String description;
	Image photo;
	ArrayList<Ingrédient> ingrédients;
	boolean[] note = {false,false,false,false,false};
	boolean saved;
	boolean[] difficulté = {false,false,false,false,false};
	ArrayList<String> étapes, catégories;
	int nbrPersonne;

	public Recette() {
		this.nom="";
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
	
	public Image getPhoto() {
		return this.photo;
	}
	
	public void setPhotoByImage(Image img) {
		System.out.println("setPhoto !");
		this.photo = img;
	}
	
	public void setPhoto() {
		this.photo = new Image("imagesRecette/"+this.nom+".png");
	}
	
	public void setNom(String nom){
		this.nom = nom;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String s) {
		this.description = s;
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
