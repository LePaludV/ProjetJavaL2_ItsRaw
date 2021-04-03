package Panier;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import Main.*;

public class ModèlePanier extends Observable{
	Vue vue;
	ArrayList<Ingrédient> Ingrédients;
	File panier;
	String[][] liquide =  {{"","l","0,01"},{"100","cl","0,5"},{"1.5","cuil. soupe","0,33"},{"3","cuil. café",""}};
	String[][] poids = {{"","kg","0,001"},{"1000","g","0,2"},{"5","pincé",""}};
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
		
		for(Ingrédient i: ingr) {
			for(int j =0;j<this.Ingrédients.size();j++) {
				
				if(i.nom.equals(this.Ingrédients.get(j).nom)) {
					System.out.println("Existe déjà"+i.nom);
					/*if(Arrays.toString(liquide).contains(i.mesure) & Arrays.toString(liquide).contains(this.Ingrédients.get(j).mesure)) {
						Ingrédient maj=majIngrédientLiquide(this.Ingrédients.get(j),i);
						System.out.println(i.mesure+"   "+this.Ingrédients.get(j).mesure);
						this.Ingrédients.remove(i);
						this.Ingrédients.add(maj);
					}
					else if (Arrays.toString(poids).contains(i.mesure) & Arrays.toString(poids).contains(this.Ingrédients.get(j).mesure)) {
						Ingrédient maj=majIngrédientPoids(this.Ingrédients.get(j),i);
						System.out.println(i.mesure+"   "+this.Ingrédients.get(j).mesure);
						this.Ingrédients.remove(i);
						this.Ingrédients.add(maj);
					}
					
					
					
				
				
					*/
				}
			}this.Ingrédients.add(i);
			
			
		}
		
		SauvegardePanier();
		
	}
	private Ingrédient majIngrédientLiquide(Ingrédient j, Ingrédient i) {
		//j = Ingrédient déjà dans la liste qu'il faut modifier 
		//i = Ingrédient qu'il faut comparer avec j pour additioner les quatité en fonction des mesures.
		if(j.mesure.equals(i.mesure)) {
			j.quantité+=i.quantité;
		}
		return null;
	}
	private Ingrédient majIngrédientPoids(Ingrédient j, Ingrédient i) {
		//j = Ingrédient déjà dans la liste qu'il faut modifier 
		//i = Ingrédient qu'il faut comparer avec j pour additioner les quatité en fonction des mesures.
		
		if(j.mesure.equals(i.mesure)) {
			j.quantité+=i.quantité;
		}
		
		return j ;
		
		
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
	
	public void AfficherPanier(){
		this.setChanged();
		this.notifyObservers();
	}
	public void supprimerIngr(Ingrédient ingrédient) {
		this.Ingrédients.remove(ingrédient);
		this.SauvegardePanier();
		this.setChanged();
		this.notifyObservers();
		
	}
	public void viderPanier() {
		this.Ingrédients.clear();
		this.SauvegardePanier();
		this.setChanged();
		this.notifyObservers();
		
	}

}
