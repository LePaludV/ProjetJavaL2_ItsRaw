import java.util.Observable;

public class ModèleEtapes extends Observable{
	Vue vue;
	int indexEtape;
	Recette recette_courante;
	
	public ModèleEtapes(Vue v) {
		this.vue = v;
		this.indexEtape = 0;
	}
	
	public void setRecette(Recette rct) {
		this.recette_courante = rct;
	}
	
	public void setEtapes()
	{
		this.setChanged();
		this.notifyObservers(this.indexEtape);
	}
	
	public void goToAccueil() {
		this.vue.currentInterface = this.vue.currentInterface.ACCUEIL_RECETTE;
		this.vue.changeWindow(Vue.typeInterface.ACCUEIL_RECETTE);
	}
	
	public void  clickSuivant()
	{
		this.indexEtape +=1;
		this.setEtapes();
	}
	
	public void clickPrecedent()
	{
		this.indexEtape -=1;
		this.setEtapes();
	}

}
