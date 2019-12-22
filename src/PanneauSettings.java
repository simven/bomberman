import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanneauSettings extends JPanel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    Image fond;
    Button boutonRetour,boutonAugmVolMusique,boutonDiminVolMusique,boutonAugmVolBoutons,boutonDiminVolBoutons;
    Dimension dimension = getToolkit().getScreenSize();
    int width,height;
    
    public PanneauSettings(){
		try{
			fond = ImageIO.read(new File("Ressources/fondmenu.jpg").getAbsoluteFile());
		}
	    catch (IOException e) {
	    	e.printStackTrace();
	    }
		width=(int)dimension.getWidth();
		height=(int)dimension.getHeight();
		setSize(width,height);
	    	
    	this.setLayout(null);
    	
    	boutonRetour = new Button("BACK");
    	boutonRetour.setBounds(4*(width/5),9*(height/12),width/6,height/6);
    	this.add(boutonRetour);
	
		boutonAugmVolBoutons = new Button("+");
    	boutonAugmVolBoutons.setBounds(2*(width/9),7*(height/13),width/12,height/8);
    	this.add(boutonAugmVolBoutons);
    	
    	boutonDiminVolBoutons = new Button("-");
    	boutonDiminVolBoutons.setBounds(3*(width/9),7*(height/13),width/12,height/8);
    	this.add(boutonDiminVolBoutons);
	
		boutonAugmVolMusique = new Button("+");
    	boutonAugmVolMusique.setBounds(width/3+(width/15),7*(height/10),width/12,height/8);
    	this.add(boutonAugmVolMusique);
    	
    	boutonDiminVolMusique = new Button("-");
    	boutonDiminVolMusique.setBounds(width/2,7*(height/10),width/12,height/8);
    	this.add(boutonDiminVolMusique);
    }
    
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Molot",Font.BOLD,130));
		g.drawImage(fond,0,0,width,height,this);
		g.drawString("Sound interface :",width/14,5*(height/10));
		g.drawString("Music :",width/12,7*(height/10)+height/8);
    }
}