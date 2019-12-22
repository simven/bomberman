import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Labyrinth extends JPanel{
	/**
	 *
	 */
	private static Labyrinth instance;
	private static final long serialVersionUID = 3207948338488656577L;
	private int x, y;   // largeur et hauteur du labyrinthe
	private int nbCases;   // nombre de cases
	private int nbMurs;   // le nombre de murs
	private Mur[] murs;   // la liste des murs du labyrinthe
	private int nbE;   // le nombre d'ennemis
	private Ennemi[] ennemis;	// la liste des ennemis
	private Bombes b;
	private PlayMusic oof;
	private PlayMusic secret;
	private Sortie sortie;
	private Image fond;
	private boolean fin;
	private Image murI, murC, sol;
	private Joueur j1;
	private boolean paint;


	public Labyrinth(int x, int y) {
		oof = new PlayMusic("Ressources/Douleur2.wav");
		secret = new PlayMusic("Ressources/secret.wav");
		secret.setVol(-12);
		this.x = x;
		this.y = y;
		murs = new Mur[(x*2+(y-2)*2)+((x/2-1)*(y/2-1))+255];
		try {
			murI = ImageIO.read(new File("Ressources/Mur.png").getAbsoluteFile());
			sol = ImageIO.read(new File("Ressources/Sol.png").getAbsoluteFile());
			murC = ImageIO.read(new File("Ressources/MurC.jpg").getAbsoluteFile());
			fond = ImageIO.read(new File("Ressources/fond2.jpg").getAbsoluteFile());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		sortie = new Sortie(x/2*sol.getWidth(this),y/2*sol.getHeight(this));
		j1 = new Joueur(100,1);
		add(j1);
		j1.setX(30);
		j1.setY(30);
		setPreferredSize(new Dimension(getToolkit().getScreenSize().width, getToolkit().getScreenSize().height));
		placerBordure();
		placerMurIncassableCentraux();
		spawnEnnemis(7);
		placerMurCassable();
	}

	public Bombes getB() {
		return b;
	}

	public void setB(Bombes b) {
		this.b = b;
	}

	public int getX() {
		return x;
	}
	
	public Sortie getSortie() {
		return sortie;
	}

	public int getY() {
		return y;
	}
	
	public Joueur getJ1() {
		return j1;
	}
	
	public Mur[] getMurs() {
		return murs;
	}

	public void setFin(boolean fin) {
		this.fin = fin;
	}

	public int getNbMurs() {
		return nbMurs;
	}
	
	public void setNbMurs(int nbMurs) {
		this.nbMurs = nbMurs;
	}
	
	public int getNbCases() {
		return nbCases;
	}
	
	public void setNbCases(int nbCases) {
		this.nbCases = nbCases;
	}
	
	public int getNbE() {
		return nbE;
	}
	
	public Ennemi[] getEnnemis() {
		return ennemis;
	}
	
	public boolean isFin() {
		return fin;
	}

	public void spawnEnnemis(int nbE) {
		this.nbE=nbE;
		ennemis = new Ennemi[nbE];
		int e=0;
		while(e<(nbE-1))
			for(int i=1;i<x/2;i++)
				for(int j=1;j<y/2;j++)
					if(Math.random()<0.25) {
						ennemis[e]= new Ennemi(1,100);
						add(ennemis[e]);
						ennemis[e].setX(i*sol.getWidth(this)+30);
						ennemis[e].setY(j*sol.getHeight(this)+30);
						e++;
						if (e==nbE)
							return;
						break;							
					}
	}
	
	public boolean libre(int x, int y) {
		boolean libre=true;
		for(int m=0; m<nbMurs;m++)
			if (murs[m].getX()==x && murs[m].getY()==y)
				libre=false;
		return libre;
	}
	
	// remplis le labyrinthe de murs
	public void placerBordure(){
		// Place les bordures
		for(int i=0;i<y;i++) {														//murs de droites
			murs[nbMurs]= new Mur((x-1)*sol.getWidth(this), i*sol.getHeight(this),StateMur.INCASSABLE);
			nbMurs++;
		}
		for(int i=1;i<(x-1);i++) {														// murs du bas
			murs[nbMurs]= new Mur(i*sol.getWidth(this), (y-1)*sol.getHeight(this),StateMur.INCASSABLE);
			nbMurs++;
		}
		for(int i=0;i<y;i++) {														// murs de gauches
			murs[nbMurs]= new Mur(0, i*sol.getHeight(this),StateMur.INCASSABLE);
			nbMurs++;
		}
		for(int i=1;i<(x-1);i++) {														// murs du haut
			murs[nbMurs]= new Mur(i*sol.getWidth(this), 0,StateMur.INCASSABLE);
			nbMurs++;
		}
	}

	public void placerMurIncassableCentraux(){   //Place les murs centraux
		for(int i=2;i<(y-1);i+=2)
			for(int j=2;j<(x-1);j+=2){
				murs[nbMurs]= new Mur(j*sol.getWidth(this),i*sol.getHeight(this),StateMur.INCASSABLE);
				nbMurs++;
			}
	}

	public void placerMurCassable() {
		int nbPlace= x*y-nbMurs;
		int nbMursC= nbPlace*2/3;
		int n=nbMurs;
		int i=0;
		while(i<nbMursC)
			for(int j=0; j<(y-1); j++)
				for(int k=0; k<(x-1); k++)
					if((j!=1 || k!=1) && (j!=2 || k!=1) && (j!=1 || k!=2))
						if (ennemIsNotHere((k*sol.getWidth(this))/2+5, (j*sol.getHeight(this))/2+5))
							if(Math.random()<0.65) {
								murs[i+n]= new Mur(k*sol.getWidth(this),j*sol.getHeight(this),StateMur.CASSABLE);
								nbMurs++;
								i++;
							}
	}
	
	public void aff() {
		for(int i=0;i<nbMurs;i++)
			if (murs[i].getEtat()==StateMur.CASSABLE)
				System.out.println(murs[i].getX() + " " + murs[i].getY());
		for(int i=0;i<nbE;i++)
			System.out.println(ennemis[i].getX()+ " "+ ennemis[i].getY());
	}

	// Vérifie si un mur est présent au coordonnées données en paramètres
	public int murPresent(int x, int y) throws ExceptionMurNotFound {
		for(int m=0; m<nbMurs;m++) {
			if (murs[m].getX()==x && murs[m].getY()==y && murs[m].getEtat()==StateMur.INCASSABLE)
				return 1;
			else if (murs[m].getX()==x && murs[m].getY()==y && murs[m].getEtat()==StateMur.CASSABLE)
				return 0;
			else if (murs[m].getX()==x && murs[m].getY()==y && murs[m].getEtat()==StateMur.CASSE)
				return 2;
		}
		throw new ExceptionMurNotFound();
	}
	
	public boolean ennemIsNotHere(int x, int y) {
		for(int i=0;i<nbE;i++)
			if (ennemis[i].getX()==x && ennemis[i].getY()==y)
				return false;
		return true;
	}
	
	public boolean noE() {
		for(int i=0; i<nbE;i++)
			if (ennemis[i].getStatePerso()==StatePerso.VIVANT)
				return false;
		for(int i=0;i<nbMurs;i++)
			if(murs[i].getX()==sortie.getX() && murs[i].getY()==sortie.getY())
				murs[i].setEtat(StateMur.CASSE);
		return true;
	}
	
	public int collisionBombes() { // collision mur=0 collision joueur=2 collision ennemis=1
		Rectangle bombe = new Rectangle(b.getX(),b.getY(),20,20);
		for (int i=0;i<nbMurs;i++) {
			Rectangle mur = new Rectangle(murs[i].getX(),murs[i].getY(),50,50);
			if (mur.intersects(bombe) && murs[i].getEtat()==StateMur.CASSABLE) {
				murs[i].setEtat(StateMur.CASSE);
				return 0;
			}
			else if (mur.intersects(bombe) && murs[i].getEtat()==StateMur.INCASSABLE)
				return 0;
		}
		for (int i=0;i<nbE;i++) {
			Rectangle ennemi = new Rectangle(ennemis[i].getX()+ennemis[i].getX(),ennemis[i].getY()+ennemis[i].getY(),32,32);
			if (ennemi.intersects(bombe) && ennemis[i].getStatePerso()==StatePerso.VIVANT) {
				ennemis[i].setStatePerso(StatePerso.MORT);
				oof.sound();
				return 1;
			}
		}
		return 666;
	}
	
	

	public void paintComponent(Graphics g) {
		if(paint)
			g.drawImage(fond,0,0,1920,1280,this);
			for(int i=0;i<x;i++)
				for(int j=0;j<y;j++) { // parcours le labyrinthe
					try {
						if (murPresent(i*sol.getWidth(this), j*sol.getHeight(this))==1) // vérifie si l'on doit placer un mur ou non
							g.drawImage(murI,i*sol.getWidth(this),j*sol.getHeight(this),this);// dessine les murs
						else if (murPresent(i*sol.getWidth(this), j*sol.getHeight(this))==0)
							g.drawImage(murC,i*sol.getWidth(this),j*sol.getHeight(this),this);
						else
							g.drawImage(sol, i*sol.getWidth(this),j*sol.getHeight(this),this);
					}
					catch (ExceptionMurNotFound e) {
						g.drawImage(sol,i*sol.getWidth(this),j*sol.getHeight(this),this); // dessine le sols
					}
				}
		if (noE()) {
			sortie.setSpawn(true);
			secret.clip.start();
			g.drawImage(sol, sortie.getX(), sortie.getY(), this);
			g.drawImage(sortie.getImage(), sortie.getX()+10, sortie.getY()+10, this);
		}
		paint=true;
	}
}
