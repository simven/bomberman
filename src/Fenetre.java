import javax.swing.JFrame;
import java.util.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class Fenetre extends JFrame {
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    static PlayMusic sonBoutons,musiqueMenu,BGM;
    Timer timer=new Timer();
    Dimension dimension = getToolkit().getScreenSize();
    
    Panneau1 pan1 = new Panneau1();
    static PanneauSettings panS = new PanneauSettings();
    static PanneauSelectionPerso panSP = new PanneauSelectionPerso();

    public Fenetre() {
		super("BOMBERMAN");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize((int)dimension.getWidth(),(int)dimension.getHeight());
		setLocationRelativeTo(null);
		
		musiqueMenu = new PlayMusic("Ressources/musiqueMenu.wav");
		musiqueMenu.setVol(-10);
		musiqueMenu.playMusic();
	
		this.pan1.boutonPlay.addActionListener(new ListenerBoutonSolo());
		this.pan1.boutonSettings.addActionListener(new ListenerBoutonSettings());
		
		this.panS.boutonAugmVolMusique.addActionListener(new ListenerBoutonAugmVolMusique());
		this.panS.boutonDiminVolMusique.addActionListener(new ListenerBoutonDiminVolMusique());
		this.panS.boutonRetour.addActionListener(new ListenerBoutonGoToPan1());
		this.panS.boutonAugmVolBoutons.addActionListener(new ListenerBoutonAugmVolBoutons());
		this.panS.boutonDiminVolBoutons.addActionListener(new ListenerBoutonDiminVolBoutons());
		
		this.panSP.boutonStart.addActionListener(new ListenerBoutonStartAGame());
		this.panSP.boutonRetour.addActionListener(new ListenerBoutonGoToPan1());
	
		timer.scheduleAtFixedRate(new AnimPersoChoix(),0,500);
		
		setContentPane(this.pan1);
		
		sonBoutons = new PlayMusic("Ressources/son_clic_bouton.wav");
		sonBoutons.setVol(-20);
	
    }
    
    public void changeToPan1() {
		this.setContentPane(this.pan1);
		this.revalidate();
    }
    
    public void changeToPanSettings(){
		this.setContentPane(this.panS);
		this.revalidate();
    }
    
    public void changeToPanSelectionPerso(){
		this.setContentPane(this.panSP);
		this.revalidate();
    }
    
    public void startAGame(){
		musiqueMenu.stopMusic();
		this.dispose();
		String command = "java -cp bin Start";
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec(command);
		}
		catch(Exception e) {}
    }
    
    public static void augmenterVolMusique() {
    	musiqueMenu.augmenterVol();
    }
    
    public static void diminuerVolMusique() {
    	musiqueMenu.diminuerVol();
    }
    
    public static void augmenterVolBoutons() {
    	sonBoutons.augmenterVol();
    }
    
    public static void diminuerVolBoutons() {
    	sonBoutons.diminuerVol();
    }
    
    public class ListenerBoutonGoToPan1 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent clic) {
		    sonBoutons.sound();
		    Fenetre.this.changeToPan1();
		}
    }

    public class ListenerBoutonSettings implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent clic) {
		    sonBoutons.sound();
		    Fenetre.this.changeToPanSettings();
		}
    }
    public class ListenerBoutonSolo implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent clic) {
			sonBoutons.sound();
		    Fenetre.this.changeToPanSelectionPerso();
		}
    }
    public class ListenerBoutonStartAGame implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent clic) {
		    sonBoutons.sound();
		    Fenetre.this.startAGame();
		}
    }
    public class ListenerBoutonAugmVolMusique implements ActionListener{
    	@Override
    	public void actionPerformed(ActionEvent clic) {
	    sonBoutons.sound();
    	    Fenetre.augmenterVolMusique();
    	}
    }
    public class ListenerBoutonDiminVolMusique implements ActionListener{
    	@Override
    	public void actionPerformed(ActionEvent clic) {
	    sonBoutons.sound();
    	    Fenetre.diminuerVolMusique();
    	}
    }
    public class ListenerBoutonDiminVolBoutons implements ActionListener{
    	@Override
    	public void actionPerformed(ActionEvent clic) {
	    sonBoutons.sound();
    	    Fenetre.diminuerVolBoutons();
    	}
    }
    public class ListenerBoutonAugmVolBoutons implements ActionListener{
    	@Override
    	public void actionPerformed(ActionEvent clic) {
	    sonBoutons.sound();
    	    Fenetre.augmenterVolBoutons();
    	}
    }
}
