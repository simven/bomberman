import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanneauChoixPerso extends JPanel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Image perso;
    private int choix;
    private int xSprite = 0;
    private int x2Sprite = 0;
    
    public PanneauChoixPerso(){
		this.setLayout(null);
		choix=1;
		this.setSize(300,400);
		try{
		    perso = ImageIO.read(new File("Ressources/Droite.png").getAbsoluteFile());
		}
		catch(IOException e){
		    e.printStackTrace();
		}
    }
    
    public int getChoix(){
    	return choix;
    }
    
    public void setChoix(int i){
    	choix=i;
    }
    
    public void paintComponent(Graphics g){
		choix=2;
		xSprite+=32;
		x2Sprite+=32;
		if (x2Sprite==160){
		    xSprite=0;
		    x2Sprite=32;
		}
		g.drawImage(perso,0,0,300,400,xSprite+32%32,0,x2Sprite+32%32,31,this);
    }
}