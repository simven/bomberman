public class Mur {
	private int x;
	private int y;
	private StateMur etat; // etat du mur

	public Mur(int x, int y,StateMur etat) {
		this.x=x;
		this.y=y;
		this.etat = etat;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public StateMur getEtat() {
		return etat;
	}

	public void setEtat(StateMur etat) {
		this.etat = etat;
	}
}
