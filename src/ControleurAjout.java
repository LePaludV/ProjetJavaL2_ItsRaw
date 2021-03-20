public class ControleurAjout implements ActionListener, MouseListener
{
	private ModèleAjoutRecette modl;

	public ControleurAjout(String nom) {
		this.modl = new ModèleAjoutRecette(nom);
	}
}
