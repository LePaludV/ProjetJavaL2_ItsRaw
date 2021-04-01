package Etapes;
import Main.Vue;

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
