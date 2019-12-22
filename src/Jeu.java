

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;

public class Jeu extends JFrame{
	/**
	 *
	 */
	private static final long serialVersionUID = 8521926420216612722L;
	private boolean ingame=true;
	private PlayMusic bgm;
	private Labyrinth lvl1;
	private InputKeyboard input;
	private boolean cooldown;
	private PlayMusic victoire;
	private PlayMusic xp;
	private PlayMusic YOUDIED;
    EcranLoose loose = new EcranLoose();
    EcranWin win = new EcranWin();

	public Jeu() {
		super("BOMBERMAN");
		bgm = new PlayMusic("Ressources/BGM.wav");
		xp = new PlayMusic("Ressources/bombe.wav");
		YOUDIED = new PlayMusic("Ressources/YOUDIED.wav");
		victoire = new PlayMusic("Ressources/musiqueVictoire.wav");
		bgm.setVol(-10);
		bgm.playMusic();
		victoire.setVol(-10);
		lvl1 = new Labyrinth(17,15);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);
		setLocationRelativeTo(null);
		setFocusable(true);
		setContentPane(lvl1);
		input=new InputKeyboard();
    	loose.boutonLeave.addActionListener(new ListenerBoutonLeave());
    	loose.boutonRetry.addActionListener(new ListenerBoutonRetry());
    	win.boutonLeave.addActionListener(new ListenerBoutonLeave());
    	win.boutonRetry.addActionListener(new ListenerBoutonRetry());
	}
	
	public boolean canMove(Personnages p) {
		Rectangle perso=hitbox(p);
		for (int i=0;i<lvl1.getNbMurs();i++) {
			Rectangle hitbox= new Rectangle(lvl1.getMurs()[i].getX()/2,lvl1.getMurs()[i].getY()/2,25,25);
			if (perso.intersects(hitbox) && lvl1.getMurs()[i].getEtat()!=StateMur.CASSE)
				return false;
		}
		return true;
	}
	
	public void sortie() {
		Rectangle Hsortie = new Rectangle(lvl1.getSortie().getX()/2+10,lvl1.getSortie().getY()/2+10,16,16);
		Rectangle joueur = new Rectangle(lvl1.getJ1().getX(),lvl1.getJ1().getY(),16,16);
		if(Hsortie.intersects(joueur) && lvl1.getSortie().isSpawn()) 
			lvl1.setFin(true);
	}
	
	public void collisionEnnemis() {
		Rectangle joueur = new Rectangle(lvl1.getJ1().getX()+1,lvl1.getJ1().getY(),16,16);
		for (int i=0;i<lvl1.getNbE();i++) {
			Rectangle ennemi = new Rectangle(lvl1.getEnnemis()[i].getX()+1,lvl1.getEnnemis()[i].getY(),16,16);
			if(ennemi.intersects(joueur) && lvl1.getEnnemis()[i].getStatePerso()==StatePerso.VIVANT && lvl1.getJ1().getStatePerso()==StatePerso.VIVANT) {
				lvl1.getJ1().setStatePerso(StatePerso.MORT);
				bgm.stopMusic();
		    	YOUDIED.clip.start();
			}
		}
			
	}
	
	public Rectangle hitbox(Personnages p) {
		if (p.getDirection()==Direction.DROITE)
			return new Rectangle(p.getX()+1,p.getY(),16,16);
		else if (p.getDirection()==Direction.GAUCHE)
			return new Rectangle(p.getX()-1,p.getY(),16,16);
		else if (p.getDirection()==Direction.BAS)
			return new Rectangle(p.getX(),p.getY()+1,16,16);
		else
			return new Rectangle(p.getX(),p.getY()-1,16,16);
	}
	
	public void IAEnnemisLancerBombe(){
		for (int i=0;i<lvl1.getNbE();i++){
		    int x = lvl1.getEnnemis()[i].getX();
		    int y = lvl1.getEnnemis()[i].getY();
		    if (lvl1.getEnnemis()[i].getStatePerso()==StatePerso.VIVANT){
			switch (lvl1.getEnnemis()[i].getDirection()){
			case DROITE:
			    Rectangle vision = new Rectangle(lvl1.getEnnemis()[i].getX(),lvl1.getEnnemis()[i].getY(),x,16);
			    for (int j=0;i<lvl1.getNbMurs();j++) {
					Rectangle hitbox= new Rectangle(lvl1.getMurs()[j].getX()/2,lvl1.getMurs()[j].getY()/2,25,25);
					Rectangle joueur = new Rectangle(lvl1.getJ1().getX(),lvl1.getJ1().getY(),16,16);
					if (vision.intersects(hitbox))
					    break;
					else if (vision.intersects(joueur))
					    Ennemilancerbombe(lvl1.getEnnemis()[i]);
					else{
					    x++;
					    vision = new Rectangle(lvl1.getEnnemis()[i].getX(),lvl1.getEnnemis()[i].getY(),x,16);
					}
			    }
			    break;
			case GAUCHE:
			    vision = new Rectangle(lvl1.getEnnemis()[i].getX(),lvl1.getEnnemis()[i].getY(),x,16);
			    for (int j=0;i<lvl1.getNbMurs();j++) {
					Rectangle hitbox= new Rectangle(lvl1.getMurs()[j].getX()/2,lvl1.getMurs()[j].getY()/2,25,25);
					Rectangle joueur = new Rectangle(lvl1.getJ1().getX(),lvl1.getJ1().getY(),16,16);
					if (vision.intersects(hitbox))
					    break;
					else if (vision.intersects(joueur))
					    Ennemilancerbombe(lvl1.getEnnemis()[i]);
					else{
					    x--;
					    vision = new Rectangle(lvl1.getEnnemis()[i].getX(),lvl1.getEnnemis()[i].getY(),x,16);
					}
			    }
			    break;
			case HAUT:
			    vision = new Rectangle(lvl1.getEnnemis()[i].getX(),lvl1.getEnnemis()[i].getY(),16,y);
			    for (int j=0;i<lvl1.getNbMurs();j++) {
					Rectangle hitbox= new Rectangle(lvl1.getMurs()[j].getX()/2,lvl1.getMurs()[j].getY()/2,25,25);
					Rectangle joueur = new Rectangle(lvl1.getJ1().getX(),lvl1.getJ1().getY(),16,16);
					if (vision.intersects(hitbox))
					    break;
					else if (vision.intersects(joueur))
					    Ennemilancerbombe(lvl1.getEnnemis()[i]);
					else{
					    y--;
					    vision = new Rectangle(lvl1.getEnnemis()[i].getX(),lvl1.getEnnemis()[i].getY(),x,16);
					}
			    }
			    break;
			case BAS:
			    vision = new Rectangle(lvl1.getEnnemis()[i].getX(),lvl1.getEnnemis()[i].getY(),16,y);
			    for (int j=0;i<lvl1.getNbMurs();j++) {
					Rectangle hitbox= new Rectangle(lvl1.getMurs()[j].getX()/2,lvl1.getMurs()[j].getY()/2,25,25);
					Rectangle joueur = new Rectangle(lvl1.getJ1().getX(),lvl1.getJ1().getY(),16,16);
					if (vision.intersects(hitbox))
					    break;
					else if (vision.intersects(joueur))
					    Ennemilancerbombe(lvl1.getEnnemis()[i]);
					else{
					    y++;
					    vision = new Rectangle(lvl1.getEnnemis()[i].getX(),lvl1.getEnnemis()[i].getY(),x,16);
					}
			    }
			    break;
			}
		    }
		}
	}
	
	private void Ennemilancerbombe(Ennemi ennemi) {
		// TODO Stub de la méthode généré automatiquement
		
	}

	public void ennemis() {
		for (int i=0;i<lvl1.getNbE();i++)
			patrol(lvl1.getEnnemis()[i]);
	}
	
	public void cac() {
		Rectangle hit=lvl1.getJ1().cac();
		for (int i=0;i<lvl1.getNbE();i++) {
			Rectangle hitbox= new Rectangle(lvl1.getEnnemis()[i].getX(),lvl1.getEnnemis()[i].getY(),16,16);
			if (hit.intersects(hitbox))
				lvl1.getEnnemis()[i].setStatePerso(StatePerso.MORT);
		}
	}
	
	public void lancerBombe(Graphics g) throws InterruptedException{
		lvl1.setB(new Bombes(lvl1.getJ1().getX()*2,lvl1.getJ1().getY()*2));
		cooldown = true;
		switch(lvl1.getJ1().getDirection()) {
		case DROITE:
			lvl1.getB().setX(lvl1.getB().getX()+16);
			for(int i=0;i<lvl1.getJ1().getRange();i++) {
				play();
				ennemis();
				lvl1.getB().setX(lvl1.getB().getX()+3);
				if (lvl1.collisionBombes()!=666) {
					break;
				}
				g.drawImage(lvl1.getB().getSprite(),lvl1.getB().getX(),lvl1.getB().getY(),20, 20, this);
				lvl1.repaint();
				Thread.sleep(16);
			}
			lvl1.getB().setState(1);
			xp.sound();
			g.drawImage(lvl1.getB().getSprite(),lvl1.getB().getX(),lvl1.getB().getY(),30, 30, this);
			break;
		case GAUCHE:
			lvl1.getB().setX(lvl1.getB().getX()-16);
			for(int i=0;i<lvl1.getJ1().getRange();i++) {
				play();
				ennemis();
				lvl1.getB().setX(lvl1.getB().getX()-3);
				if (lvl1.collisionBombes()!=666) {
					break;
				}
				g.drawImage(lvl1.getB().getSprite(),lvl1.getB().getX(),lvl1.getB().getY(),20, 20, this);
				lvl1.repaint();
				Thread.sleep(16);
			}
			lvl1.getB().setState(1);
			xp.sound();
			g.drawImage(lvl1.getB().getSprite(),lvl1.getB().getX(),lvl1.getB().getY(),30, 30, this);
			break;
		case HAUT:
			lvl1.getB().setY(lvl1.getB().getY()-16);
			for(int i=0;i<lvl1.getJ1().getRange();i++) {
				play();
				ennemis();
				lvl1.getB().setY(lvl1.getB().getY()-3);
				if (lvl1.collisionBombes()!=666) {
					break;
				}
				g.drawImage(lvl1.getB().getSprite(),lvl1.getB().getX(),lvl1.getB().getY(),20, 20, this);
				lvl1.repaint();
				Thread.sleep(16);
			}
			lvl1.getB().setState(1);
			xp.sound();
			g.drawImage(lvl1.getB().getSprite(),lvl1.getB().getX(),lvl1.getB().getY(),30, 30, this);
			break;
		case BAS:
			lvl1.getB().setY(lvl1.getB().getY()+16);
			for(int i=0;i<lvl1.getJ1().getRange();i++) {
				play();
				ennemis();
				lvl1.getB().setY(lvl1.getB().getY()+3);
				if (lvl1.collisionBombes()!=666) {
					break;
				}
				g.drawImage(lvl1.getB().getSprite(),lvl1.getB().getX(),lvl1.getB().getY(),20, 20, this);
				lvl1.repaint();
				Thread.sleep(16);
			}
			lvl1.getB().setState(1);
			xp.sound();
			g.drawImage(lvl1.getB().getSprite(),lvl1.getB().getX(),lvl1.getB().getY(),30, 30, this);
			break;
		}
		cooldown=false;
	}
	
	public void patrol(Ennemi e) {
		Random r = new Random();
		int x=e.getX();
		int y=e.getY();
		e.setX2(x);
		e.setY2(y);
		int nbA = r.nextInt(64);
		switch(nbA){
			case 0: 
				e.setDirection(Direction.DROITE);
				if (canMove(e))
					e.setX(x+1);
				break;

			case 1:
				e.setDirection(Direction.GAUCHE);
				if (canMove(e))
					e.setX(x-1);
				break;

			case 2: 
				e.setDirection(Direction.HAUT);
				if (canMove(e))
					e.setY(y-1);
				break;

			case 3: 
				e.setDirection(Direction.BAS);
				if (canMove(e))
					e.setY(y+1);
				break;
			default :
				switch(e.getDirection()) {
					case HAUT:
						if (canMove(e))
							e.setY(y-1);
						break;
					case BAS:
						if (canMove(e))
							e.setY(y+1);
						break;
					case DROITE:
						if (canMove(e))
							e.setX(x+1);
						break;
					case GAUCHE:
						if (canMove(e))
							e.setX(x-1);
						break;
				}
				break;
		}
	}
	
	public void play() {
		this.addKeyListener(input);
		int x=lvl1.getJ1().getX();
		int y=lvl1.getJ1().getY();
		lvl1.getJ1().setX2(x);
		lvl1.getJ1().setY2(y);
		if (input.haut) {
			lvl1.getJ1().setDirection(Direction.HAUT);
			if (canMove(lvl1.getJ1()))
				lvl1.getJ1().setY(y-1);
		}
		else if(input.bas) {
			lvl1.getJ1().setDirection(Direction.BAS);
			if (canMove(lvl1.getJ1()))
				lvl1.getJ1().setY(y+1);
		}
		else if(input.droite) {
			lvl1.getJ1().setDirection(Direction.DROITE);
			if (canMove(lvl1.getJ1()))
				lvl1.getJ1().setX(x+1);
		}
		else if(input.gauche) {
			lvl1.getJ1().setDirection(Direction.GAUCHE);
			if (canMove(lvl1.getJ1()))
				lvl1.getJ1().setX(x-1);
		}
		if(input.espace)
			if (!cooldown)
				try {
					lancerBombe(lvl1.getGraphics());
				} catch (InterruptedException e) {}
		if(input.spe)
			cac();
		else if(input.quit)
			System.out.println("pause");
	}
	
	  public void leave() {
	    	this.dispose();
	    	Fenetre newfen = new Fenetre();
	    	victoire.stopMusic();
	    }
	  public void retry() {
		  
	  }
	    
	  public class ListenerBoutonLeave implements ActionListener{

		  @Override
		  public void actionPerformed(ActionEvent e) {
			  Jeu.this.leave();
		  }
	    	
	  }
	  
	  public class ListenerBoutonRetry implements ActionListener{

		  @Override
		  public void actionPerformed(ActionEvent e) {
			  Jeu.this.retry();
		  }
	    	
	  }

	public void jeu() {
		while(ingame) {
			play();
			ennemis();
			lvl1.repaint();
			sortie();
			collisionEnnemis();
			if (lvl1.getJ1().getStatePerso()==StatePerso.MORT)
				this.setContentPane(loose);
			if (lvl1.isFin()==true) {
				this.setContentPane(win);
				bgm.stopMusic();
		    	victoire.clip.start();
			}
			try {
				Thread.sleep(16);
			}
			catch (InterruptedException e) {}
		}
		
	}
}
