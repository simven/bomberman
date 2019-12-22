
public class Mine extends Bombes{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4798981339099917836L;
	private StateMine etatM = StateMine.INACTIVE;
	
	public Mine(int x,int y){
		super(x,y);
	}
	
	public StateMine getStateM() {
		return etatM;
	}
	
	public void setStateM(StateMine etatM) {
		this.etatM=etatM;
	}
	
	public void explosion(){
		if(this.getStateM() == StateMine.ACTIVE){
			for(int i = 0;i<this.getFlammes();i++){
				
			}
		}
	}
}