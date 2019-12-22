import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sortie {
	private Image sortie;
	private int x, y;
	private boolean spawn;
	
	public Sortie(int x, int y) {
		this.x=x;
		this.y=y;
		try {
			sortie = ImageIO.read(new File("Ressources/sortie.png").getAbsoluteFile());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isSpawn() {
		return spawn;
	}

	public void setSpawn(boolean spawn) {
		this.spawn = spawn;
	}
	
	public Image getImage() {
		return sortie;
	}
}