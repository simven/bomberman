public class Chrono{
	private long tempsDepart=0;
	private long tempsFin=0;
	private long pauseD=0;
	private long pauseF=0;
	private long duree=0;
	
	public void start(){
		tempsDepart=System.currentTimeMillis();
		tempsFin=0;
		pauseD=0;
		pauseF=0;
		duree=0;
		}
	
	public void pause() {
		if(tempsDepart==0)
			return;
		pauseD=System.currentTimeMillis();
	}
	
	public void resume(){
		if(tempsDepart==0)
			return;
		if (pauseD==0)
			return;
		pauseF=System.currentTimeMillis();
		tempsDepart=tempsDepart+pauseF-pauseD;
		tempsFin=0;
		pauseD=0;
		pauseF=0;
		duree=0;
	}
	
	public long stop() {  
		if(tempsDepart==0) 
			return 0;
	    tempsFin=System.currentTimeMillis();
	    duree=(tempsFin-tempsDepart)-(pauseF-pauseD);
	    tempsDepart=0;
	    tempsFin=0;
	    pauseD=0;
	    pauseF=0;
	    return duree;
	}
	
	public long getDureeSec(long duree){
		return duree/1000;
	}
}
