import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControleurAjout implements ActionListener, MouseListener
{
	private ModèleAjoutRecette modl;
	
	public ControleurAjout(String nom) {
		this.modl = new ModèleAjoutRecette(nom);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Button btn = (Button) e.getSource(); 
		if(btn.getName().equals(new String("ajouter nom")))
		{
			
		}
		else if(btn.getName() == "Ajouter image")
		{
			
		}
		else if(btn.getName() == "Ajouter description")
		{
			
		}
		else if(btn.getName() == "Ajouter ingrédient")
		{
			
		}
		else if(btn.getName() == "Ajouter note")
		{
			
		}
		else if(btn.getName() == "Ajouter difficulté")
		{
			
		}
		else if(btn.getName() == "Ajouter catégorie")
		{
			
		}
		else if(btn.getName() == "Ajouter étape")
		{
			
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}