import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class PlayMusic {
	float v;
	AudioInputStream bgm;
	Clip clip;

	public PlayMusic(String filepath) {
		try {
			bgm =AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());;
			clip = AudioSystem.getClip();
			clip.open(bgm);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void playMusic() {
		try {
	        clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY); // pour faire une loop à "l'infini"
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sound() {
		try {
	        clip.start();
	        clip.loop(1);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stopMusic() {
		clip.stop();
		clip.flush();
	}
	
    public float getV(){
    	return v;
    }
    
	public void setVol(float v) {
		FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		volume.setValue(v);
		this.v=v;
	}
	
	public void augmenterVol() {
	    try{
	    	this.setVol(this.v+5);
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	}
	
	public void diminuerVol() {
	    try{
	    	this.setVol(this.v-5);
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	}
}