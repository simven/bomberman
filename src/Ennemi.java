import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ennemi extends Personnages{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean aggroed;
	private int x2, y2;
	private int xSprite, x2Sprite;
	private Image dos,face,droite,gauche;

	public Ennemi(int hp, double v) {
		super(hp, v);
		try {
			face = ImageIO.read(new File("Ressources/FaceE.png").getAbsoluteFile());
			dos = ImageIO.read(new File("Ressources/DosE.png").getAbsoluteFile());
			droite = ImageIO.read(new File("Ressources/DroiteE.png").getAbsoluteFile());
			gauche = ImageIO.read(new File("Ressources/GaucheE.png").getAbsoluteFile());
		}
		catch (IOException e) {}
	}
	
	public void setX2(int x2) {
		this.x2 = x2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public boolean isAggroed() {
		return aggroed;
	}

	public void paintComponent(Graphics g) {
		this.setSize(new Dimension(1366,768));
		if (getStatePerso()!=StatePerso.MORT)
			switch(getDirection()){
				case HAUT:
					g.drawImage(dos, this.getX(), this.getY(),this.getX()+32,this.getY()+32, xSprite+32%32,0, x2Sprite+32%32,32,this);
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
					g.drawImage(face, this.getX(), this.getY(),this.getX()+32,this.getY()+32, xSprite+32%32,0, x2Sprite+32%32,32,this);
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
				case GAUCHE:
					g.drawImage(gauche, this.getX(), this.getY(),this.getX()+32,this.getY()+32, xSprite+32%32,0, x2Sprite+32%32,32,this);
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
					g.drawImage(droite, this.getX(), this.getY(),this.getX()+32,this.getY()+32, xSprite+32%32,0, x2Sprite+32%32,32,this);
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
