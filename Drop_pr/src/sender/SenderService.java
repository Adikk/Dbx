package sender;

import dropbox.Dropbox;
import skaner.Skaner;
import timer.Timer;

public class SenderService {

	private int iloscWatkow;
	private Skaner skaner;
	private Dropbox dropbox;
	private Timer timer;
	
	
	public SenderService(Dropbox dropbox, int iloscWatkow, Skaner skaner, Timer timer){
		this.dropbox = dropbox;
		this.iloscWatkow = iloscWatkow;
		this.skaner = skaner;
		this.timer = timer;
	}
	
	public void rozpocznijWysylanie(){
		for(int k = 0; k < iloscWatkow; k++){
			Thread sender = new Thread(new Sender(dropbox, this));
			sender.start();
		}
	}

	public int getIloscWatkow() {
		return iloscWatkow;
	}

	public void setIloscWatkow(int iloscWatkow) {
		this.iloscWatkow = iloscWatkow;
	}

	public Skaner getSkaner() {
		return skaner;
	}

	public void setSkaner(Skaner skaner) {
		this.skaner = skaner;
	}

	public Dropbox getDropbox() {
		return dropbox;
	}

	public void setDropbox(Dropbox dropbox) {
		this.dropbox = dropbox;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	
}
