import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JPanel;;


public class Bombes extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5837976805666285257L;
	private boolean explose;
	private int px,py;
	private int longueur_flammes=1;
	//private StateBombe etatB = StateBombe.INACTIVE;
	private int state;
	
	public Bombes(int x,int y){
		this.explose=false;
		this.px=x;
		this.py=y;
		state = 0;
	}
	
	public int getX(){
		return px;
	}
	
	public void setX(int x){
		this.px=x;
	}
	
	public int getY(){
		return py;
	}
	
	public void setY(int y){
		this.py=y;
	}
	
	public int getFlammes(){
		return longueur_flammes;
	}
	
	public void setFlammes(int flammes){
		this.longueur_flammes=flammes;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public Image getSprite() {
		try {
			return ImageIO.read(new File(String.format("Ressources/bmb%d.png", state)).getAbsoluteFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void explosion(Graphics g){ //Ebauche fonction d'explosion, a completer via le panel
		
	}
	
	public void collision(){ //Collision entre le joueur et la bombe
		
	}
}