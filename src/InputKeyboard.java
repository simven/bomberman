import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
// les inputs clavier
public class InputKeyboard implements KeyListener{
	protected boolean haut,bas,gauche,droite,espace,quit,spe,interact;

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP)
			haut = true;
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        	gauche = true;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        	droite = true;
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
        	bas = true;
        if(e.getKeyCode() == KeyEvent.VK_Z)
        	haut = true;
        if(e.getKeyCode() == KeyEvent.VK_Q)
        	gauche = true;
        if(e.getKeyCode() == KeyEvent.VK_D)
        	droite = true;
        if(e.getKeyCode() == KeyEvent.VK_S)
        	bas = true;
        if(e.getKeyCode() == KeyEvent.VK_C)
        	spe = true;
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        	espace = true;
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        	quit = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP)
			haut = false;
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        	gauche = false;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        	droite = false;
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
        	bas = false;
        if(e.getKeyCode() == KeyEvent.VK_Z)
        	haut = false;
        if(e.getKeyCode() == KeyEvent.VK_Q)
        	gauche = false;
        if(e.getKeyCode() == KeyEvent.VK_D)
        	droite = false;
        if(e.getKeyCode() == KeyEvent.VK_S)
        	bas = false;
        if(e.getKeyCode() == KeyEvent.VK_C)
        	spe = false;
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        	espace = false;
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        	quit = false;
	}
}
