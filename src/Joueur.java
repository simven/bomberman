import java.awt.Dimension;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Joueur extends Personnages implements Bomberman{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int range, xSprite, x2Sprite;
	private int nbMines = 3;
	private int x2, y2;
	private Image face, dos, droite, gauche;
	private static final int MAXMINES = 5;

	public Joueur(int hp, double v) { 
		super(hp, v);
		range=50;
			try {
				face = ImageIO.read(new File("Ressources/Face.png").getAbsoluteFile());
				dos = ImageIO.read(new File("Ressources/Dos.png").getAbsoluteFile());
				droite = ImageIO.read(new File("Ressources/Droite.png").getAbsoluteFile());
				gauche = ImageIO.read(new File("Ressources/Gauche.png").getAbsoluteFile());
			}
			catch (IOException e) {}
	}
	
	public int getRange() {
		return range;
	}
	
	public void setX2(int x2) {
		this.x2 = x2;
	}
	
	public void setY2(int y2) {
		this.y2 = y2;
	}	
	
	public Rectangle cac() {
		return new Rectangle(getX()+5,getY()+5,32,8);
	}
	
    public void poserMine() throws ExceptionPlusDeMines  {
		  if(nbMines != 0){
			  Mine m = new Mine(getX(),getY());
			  Chrono chr = new Chrono();
			  @SuppressWarnings("unused")
			  boolean time = false;
			  m.setX(getX());
			  m.setY(getY());
			  switch(getDirection()){
			  case HAUT:
				  m.setX(getX()+1);
			      chr.start();
			      while(time = false)
				      if(chr.getDureeSec(chr.stop())==3)
				    	  time = true;
			      m.setStateM(StateMine.ACTIVE);
			      break;
			  case BAS:
			      m.setX(getX()-1);
			      chr.start();
			      while(time = false)
				      if(chr.getDureeSec(chr.stop())==3)
				    	  time = true;
			      m.setStateM(StateMine.ACTIVE);
			      break;
			  case GAUCHE:
			      m.setY(getY()-1);
			      chr.start();
			      while(time = false)
				      if(chr.getDureeSec(chr.stop())==3)
				    	  time = true;
			      m.setStateM(StateMine.ACTIVE);
			      break;
			  case DROITE:
			      m.setY(getY()+1);
			      chr.start();
			      while(time = false)
			    	  if(chr.getDureeSec(chr.stop())==3)
			    		  time = true;
			      m.setStateM(StateMine.ACTIVE);
			      break;
			  }
		  }
		  else
			  throw new ExceptionPlusDeMines("Plus de mines !");
	}
    
	public void paintComponent(Graphics g) {
		this.setSize(new Dimension(getToolkit().getScreenSize().width, getToolkit().getScreenSize().height));
		switch(getDirection()){
			case HAUT:
				g.drawImage(dos, getX(), getY(),getX()+32,getY()+32, xSprite+32%32,0, x2Sprite+32%32,32,this);
				xSprite+=32;
				x2Sprite+=32;
				if (x2Sprite==160 || y2==getY()){
					xSprite=0;
					x2Sprite=32;
				}
				if (y2==getY()) {
					xSprite=32;
					x2Sprite=64;
					}
				break;
			case BAS:
				g.drawImage(face, getX(), getY(),getX()+32,getY()+32, xSprite+32%32,0, x2Sprite+32%32,32,this);
				xSprite+=32;
				x2Sprite+=32;
				if (x2Sprite==160){
					xSprite=0;
					x2Sprite=32;
				}
				if (y2==getY()) {
				xSprite=32;
				x2Sprite=64;
				}
				break;
			case GAUCHE:
				g.drawImage(gauche, getX(), getY(),getX()+32,getY()+32, xSprite+32%32,0, x2Sprite+32%32,32,this);
				xSprite+=32;
				x2Sprite+=32;
				if (x2Sprite==160 || x2==getX()){
					xSprite=0;
					x2Sprite=32;
				}
				if (x2==getX()) {
					xSprite=32;
					x2Sprite=64;
					}
				break;
			case DROITE:
				g.drawImage(droite, getX(), getY(),getX()+32,getY()+32, xSprite+32%32,0, x2Sprite+32%32,32,this);
				xSprite+=32;
				x2Sprite+=32;
				if (x2Sprite==160 || x2==getX()){
					xSprite=0;
					x2Sprite=32;
				}
				if (x2==getX()) {
					xSprite=32;
					x2Sprite=64;
					}
				break;
		}
	}
}


