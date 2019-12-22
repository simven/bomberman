import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class PanneauCaracPerso extends JPanel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Dimension dimension = getToolkit().getScreenSize();
    Font myfont;
    int width,height;
    Image fond;
    
    public PanneauCaracPerso(){
    	System.setProperty( "file.encoding", "ASCII" );
    	width=(int)dimension.getWidth();
    	height=(int)dimension.getHeight();
    	myfont = new Font("Noto Sans",Font.BOLD,30);
    	this.setFont(myfont);
    	this.setSize(width/3,height/4);
    	this.setBackground(Color.white);
    	this.setBorder(BorderFactory.createLineBorder(Color.white));
    	
    	try {
    		fond = ImageIO.read(new File("Ressources/fond-degrade.jpg").getAbsoluteFile());
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void paintComponent(Graphics g){
    	g.drawImage(fond,0,0,width/3,height/4,this);
    	g.setColor(Color.black);
    	g.setFont(myfont);
    	g.drawString("Personnage de base du jeu.",50,50);
    	g.drawString("Un petit squelette egare dans un",50,100);
    	g.drawString("sombre labyrinthe.",50,150);
    }
}
