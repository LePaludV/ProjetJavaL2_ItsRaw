package Etapes;
import Accueil.*;
import AccueilRecette.*;
import AjoutRecette.*;
import Main.*;
public class ModèleEtapes {
	Vue vue;

	public ModèleEtapes(Vue v) {
		this.vue = v;
	}

	public void goToAccueil() {
		this.vue.currentInterface = this.vue.currentInterface.ACCUEIL;
		this.vue.changeWindow(Vue.typeInterface.ACCUEIL);
		// TODO Auto-generated method stub

	}

}
