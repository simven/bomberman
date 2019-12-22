import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class EcranWin extends JPanel {
	private static final long serialVersionUID = 1L;
    Button boutonRetry,boutonLeave;
    Image fond;
    Dimension dimension = getToolkit().getScreenSize();
    int width,height;
    
    public EcranWin() {
		width=(int)dimension.getWidth();
		height=(int)dimension.getHeight();
		setSize(width,height);
		setLayout(null);
		try {
		    fond = ImageIO.read(new File("Ressources/victoire.jpg").getAbsoluteFile());
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
		boutonRetry = new Button("RETRY");
		boutonRetry.setBounds(14*(width/20), 7*(height/10), width/5, height/6);
		this.add(boutonRetry);
		
		boutonLeave = new Button("LEAVE");
		boutonLeave.setBounds(width/8, 7*(height/10), width/5, height/6);
		this.add(boutonLeave);
    }
    
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(fond,0,0,width,height,this);
    }
}
