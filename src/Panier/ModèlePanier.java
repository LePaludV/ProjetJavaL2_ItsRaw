package Panier;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import Main.*;

public class ModèlePanier {
	Vue vue;
	ArrayList<Ingrédient> Ingrédients;
	File panier;
	public ModèlePanier(Vue vue) {
		this.vue = vue;
		this.panier=new File("panier.xml");
	}
	public void goToAccueil() {
		this.vue.currentInterface = this.vue.currentInterface.ACCUEIL;
		this.vue.changeWindow(Vue.typeInterface.ACCUEIL);
		
	}
	public void addIngredient(ArrayList<Ingrédient> ingr) {
		this.Ingrédients=ChargementPanier();
		for(int i=0;i<ingr.size();i++) {
			this.Ingrédients.add(ingr.get(i));
		}
		
		SauvegardePanier();
		
	}
	private void SauvegardePanier() {
		
		XMLEncoder encoder = null;
		try {
			FileOutputStream fos = new FileOutputStream(this.panier);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			encoder = new XMLEncoder(bos);
			encoder.writeObject(this.Ingrédients);
			encoder.flush();
			
		}catch(final java.io.IOException e) {
			throw new RuntimeException("Ecriture des données impossible");
		}finally {
			if (encoder !=null) encoder.close();
		}
		System.out.println("Panier sauvegarder ! ");
	}
	
	public ArrayList<Ingrédient> ChargementPanier() {
		XMLDecoder decoder = null;
		try {
			FileInputStream fis = new FileInputStream(this.panier);
			BufferedInputStream bis = new BufferedInputStream(fis);
			decoder = new XMLDecoder(bis);
			this.Ingrédients=(ArrayList<Ingrédient>) decoder.readObject();
;		}catch (Exception e) {
	throw new RuntimeException("Chargement des données impossible");
} finally {
	if (decoder !=null) decoder.close();
}
		return this.Ingrédients;
	}
	

}
