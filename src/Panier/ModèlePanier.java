package Panier;

import Main.*;

public class ModèlePanier {
	Vue vue;
	public ModèlePanier(Vue vue) {
		this.vue = vue;
	}
	public void goToAccueil() {
		this.vue.currentInterface = this.vue.currentInterface.ACCUEIL;
		this.vue.changeWindow(Vue.typeInterface.ACCUEIL);
		
	}

}
