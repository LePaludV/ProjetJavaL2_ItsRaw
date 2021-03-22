import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class ModèleAccueil extends Observable {
	
	ArrayList<Recette> recettes;
	HashMap<Ingrédient, ArrayList<Recette>> classeIng;
	HashMap<String, ArrayList<Recette>> catégories;
	
	public ModèleAccueil() {
		this.recettes = new ArrayList<Recette>();
	}
	
	public void ajouterRecette(Recette rct) {
		recettes.add(rct);
		for (Ingrédient i : rct.ingrédients) {
			if (this.classeIng.get(i) == null) {
				this.classeIng.put(i, new ArrayList<Recette>());
			}
			this.classeIng.get(i).add(rct);
		}
		for (String s : rct.catégories) {
			if (this.catégories.get(s) == null) {
				this.catégories.put(s, new ArrayList<Recette>());
			}
			this.catégories.get(s).add(rct);
		}
	}
	
	private void saveData() {
		XMLEncoder encoder = null;
		try {
			FileOutputStream fos = new FileOutputStream("donnéeRecette.xml");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			encoder = new XMLEncoder(bos);
			
			encoder.writeObject(this.recettes);
			encoder.writeObject(this.classeIng);
			encoder.writeObject(this.catégories);
			encoder.flush();
		} catch (final java.io.IOException e) {
			throw new RuntimeException("Ecriture des données impossible");
		} finally {
			if (encoder != null) encoder.close();
		}
	}
	
	private void loadData() {
		XMLDecoder decoder = null;
		try {
			FileInputStream fis = new FileInputStream("donnéeRecette.xml");
			BufferedInputStream bis = new BufferedInputStream(fis);
			decoder = new XMLDecoder(bis);
			
			this.recettes = (ArrayList<Recette>) decoder.readObject();
			this.classeIng = (HashMap<Ingrédient, ArrayList<Recette>>) decoder.readObject();
			this.catégories = (HashMap<String, ArrayList<Recette>>) decoder.readObject();
		} catch (Exception e) {
			throw new RuntimeException("Chargement des données impossible");
		} finally {
			if (decoder != null) decoder.close();
		}
	}
	
	public void afficherParCatègories(String catègorie) {
		if (this.catégories.get(catègorie) != null) {
			this.notifyObservers(this.catégories.get(catègorie));
		}
	}
	
	public void afficherParIngrèdients(Ingrédient ing) {
		if (this.catégories.get(ing) != null) {
			this.notifyObservers(this.classeIng.get(ing));
		}
	}
	
	public void afficherRecettes() {
		this.notifyObservers(this.recettes);
	}
	
	public void changerNote(Recette rct, int note) {
		this.notifyObservers(rct);
	}
}
