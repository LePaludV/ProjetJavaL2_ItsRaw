package Main;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.image.Image;
import Accueil.*;
import AccueilRecette.*;
import AjoutRecette.*;
import Main.*;
public class Recette implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public String nom;
	public String description;
	public Image photo;
	public ArrayList<Ingrédient> ingrédients;
	public boolean[] note = {false,false,false,false,false};
	public boolean saved;
	public boolean[] difficulté = {false,false,false,false,false};
	public ArrayList<String> étapes, catégories;
	public int nbrPersonne;

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
	
	public void setNom(String nom) throws FileNotFoundException {
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
