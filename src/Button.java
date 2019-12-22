import javax.swing.JButton;
import java.awt.Font;

public class Button extends JButton {
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public Button(String str) {
    	super(str);
    	this.setFont(new Font("Molot",Font.BOLD,100));
    }
}
