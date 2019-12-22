import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Dimension;

public class Panneau1 extends JPanel{
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    Button boutonPlay,boutonSettings,boutonCredits;
    Image fond;
    Dimension dimension = getToolkit().getScreenSize();
    int width,height;
    
    public Panneau1() {
		width=(int)dimension.getWidth();
		height=(int)dimension.getHeight();
		setSize(width,height);
		setLayout(null);
		try {
		    fond = ImageIO.read(new File("Ressources/fondmenu.jpg").getAbsoluteFile());
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
		boutonPlay = new Button("PLAY");
		boutonPlay.setBounds((width/10), 5*(height/12),width/6,height/6); 
		this.add(boutonPlay);
		
		boutonSettings= new Button("SETTINGS");
		boutonSettings.setBounds((width/10), 7*(height/10), width/4,height/6);
		this.add(boutonSettings);
    }
    
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(fond,0,0,width,height,this);
    }
    // setBounds(xPosition, yPosition, width, height);
}
