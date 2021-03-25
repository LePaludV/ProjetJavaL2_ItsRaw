import java.util.Observable;

public class ModèleAccueilRecette extends Observable{
	Recette recette_courante;
	Vue vue;
	
	
	public ModèleAccueilRecette(Vue v) {
		this.vue = v;
	}
	public void ajoutDifficulté(int difficulté) {
		
		for (int i=0;i<5;i++) {
			if (i <= difficulté) {
				this.recette_courante.difficulté[i] = true;
			} else {
				this.recette_courante.difficulté[i] = false;
			}
		}
		this.setChanged();
		this.notifyObservers(this.recette_courante);		
	}
	
	public void ajoutNote(int note) {
		System.out.println("fonction note recette modèle ");
		for (int i=0;i<5;i++) {
			if (i <= note) {
				this.recette_courante.note[i] = true;
			} else {
				this.recette_courante.note[i] = false;
			}
		}
		this.setChanged();
		this.notifyObservers(this.recette_courante);
	}
	
	public void setRecette(Recette rct) {
		this.recette_courante = rct;
		this.setChanged();
		this.notifyObservers(this.recette_courante);
		
		
	}
}
