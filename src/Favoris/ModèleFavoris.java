package Favoris;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Observable;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import Main.Vue;

public class ModèleFavoris extends Observable {

	ArrayList<String> listeFav;
	Vue vue;
	File favoris;

	public ModèleFavoris(Vue vue) {
		this.vue=vue;
		this.favoris=new File("favoris.xml");
	
	
	}
	


	public void addRct(String nom) {
		this.listeFav=loadFavoris();
		
		if(this.listeFav.contains(nom)) {
			int e=this.listeFav.indexOf(nom);
			this.listeFav.remove(e);
		}
		else {
			this.listeFav.add(nom);
		}
		saveFavoris(this.listeFav);
		
		
		
	}

	

	public void goToAccueil() {
		this.vue.currentInterface = this.vue.currentInterface.ACCUEIL;
		this.vue.changeWindow(Vue.typeInterface.ACCUEIL);
		
	}
	
	public ArrayList<String> loadFavoris() {
		System.out.println("Début chargement fav");
		XMLDecoder decoder = null;
		try {
			FileInputStream fis=new FileInputStream(this.favoris);
			BufferedInputStream bis=new BufferedInputStream(fis);
			decoder = new XMLDecoder(bis);
			
			this.listeFav=(ArrayList<String>) decoder.readObject();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (decoder !=null) decoder.close();
		}
		return this.listeFav;
	}
	
	private void saveFavoris(ArrayList<String> lst) {
		System.out.println(lst);
		XMLEncoder encoder = null;
		try {
			FileOutputStream fos = new FileOutputStream(this.favoris);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			encoder = new XMLEncoder(bos);
			
			encoder.writeObject(this.listeFav);
			encoder.flush();
		}catch(final java.io.IOException e) {
			throw new RuntimeException("Ecriture des données impossible");
			
		} finally {
			if(encoder !=null) encoder.close();
		}
		
		
	}


	public void AfficherFavoris(){
		this.setChanged();
		this.notifyObservers();
	}



	public void goToAccueilRecette() {
		this.vue.currentInterface = this.vue.currentInterface.ACCUEIL_RECETTE;
		this.vue.changeWindow(Vue.typeInterface.ACCUEIL_RECETTE);
		
		
	}



	public void viderFav() {
		this.listeFav.clear();
		saveFavoris(this.listeFav);
		this.setChanged();
		this.notifyObservers();
		
		
	}



	

	
}
