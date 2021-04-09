package Favoris;

import java.util.ArrayList;

import Main.Vue;

public class ModèleFavoris {

	private ArrayList<String> listeFav;
	Vue vue;

	public ModèleFavoris(Vue vue) {
		this.vue=vue;
	}
	public ArrayList<String> loadFav() {
		// TODO Auto-generated method stub
		return this.listeFav;
	}

	public void addRct(String nom) {
		// TODO Auto-generated method stub
		
	}

	public void goToAccueil() {
		this.vue.currentInterface = this.vue.currentInterface.ACCUEIL;
		this.vue.changeWindow(Vue.typeInterface.ACCUEIL);
		
	}

}
