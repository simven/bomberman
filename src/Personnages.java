import javax.swing.JPanel;

public class Personnages extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int hpMax;
	private int hp=hpMax;
	private int x, y;// position du perso
	private double v; // vitesse du personnage
	private Direction direction=Direction.BAS; // direction ou le perso regarde (haut,droite,gauche,bas)
	private StatePerso etat = StatePerso.VIVANT; // etat du personnage (mort ou vivant)
	
	public Personnages(int hpMax, double v) {
		this.setLocation(x, y);
		this.hpMax = hpMax;
		this.v = v;
	}
	
	public int getHP() {
		return hp;
	}
	
	public void setHP(int hp) {
		this.hp = hp;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x=x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y=y;;
	}
	
	public double getV() {
		return v;
	}
	
	public void setV(double v) {
		this.v = v;
	}
	
	public Direction getDirection(){
		return direction;
	}
	
	public void setDirection(Direction d){
		direction=d;
	}
	
	public StatePerso getStatePerso(){
		return etat;
	}
	
	public void setStatePerso(StatePerso etatPerso){
		etat=etatPerso;
	}
	
	public void perteHP(int hp){
			this.hp-=hp;
			if (hp==0)
				etat = StatePerso.MORT;
	}
}