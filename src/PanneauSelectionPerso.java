import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanneauSelectionPerso extends JPanel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Button boutonRetour,boutonDroit,boutonGauche,boutonStart;
    PanneauChoixPerso panCP;
    PanneauCaracPerso panCarac;
    Image fond;
    Dimension dimension = getToolkit().getScreenSize();
    int width,height;
    
    public PanneauSelectionPerso(){
		try {
		    fond = ImageIO.read(new File("Ressources/fondmenu.jpg").getAbsoluteFile());
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
		width=(int)dimension.getWidth();
		height=(int)dimension.getHeight();
		setSize(width,height);
		this.setLayout(null);
		this.setBackground(Color.black);
		
		boutonRetour=new Button("BACK");
		boutonRetour.setBounds(5*(width/7), 9*(height/12),width/5,height/8);
		this.add(boutonRetour);
		
		boutonStart = new Button("START");
		boutonStart.setBounds(2*(width/5), 9*(height/12),width/5,height/8);
		this.add(boutonStart);
		
		panCP = new PanneauChoixPerso();
		panCP.setLocation(width/7,4*(height/10));
		this.add(panCP);
	
		panCarac=new PanneauCaracPerso();
		panCarac.setLocation(2*(width/4),4*(height/10));
		this.add(panCarac);
    }
    
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(fond,0,0,width,height,this);
    }
}