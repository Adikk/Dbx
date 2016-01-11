package timer;

public class TimerService implements Runnable{

	private Timer parent;
	
	
	public TimerService(Timer parent){
		setParent(parent);
	}
	
	void wystartujTimer(){
		Thread timer = new Thread(this);
		timer.start();
	}
	
	@Override
	public void run() {

		while(true){
			
			try {
				Thread.sleep(1000);		        
				parent.zwiekszSekundy();			
			} catch(InterruptedException ie) {}
			
			parent.setSrednia((double)parent.getIloscPl()/(double)parent.getSekundy());
			parent.setSredniaIB((double)parent.getRozmiarPl()/(double)parent.getSekundy());
			java.text.DecimalFormat df=new java.text.DecimalFormat(); 
			df.setMaximumFractionDigits(4); 
			df.setMinimumFractionDigits(2); 
		}
	}

	public Timer getParent() {
		return parent;
	}

	public void setParent(Timer parent) {
		this.parent = parent;
	}
}
