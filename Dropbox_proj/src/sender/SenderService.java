package sender;

import com.dropbox.core.DbxClient;

import skaner.Skaner;
import timer.Timer;

public class SenderService {

	int iloscWatkow;
	Skaner skaner;
	DbxClient client;
	Timer timer;
	
	public SenderService(DbxClient client, int iloscWatkow, Skaner skaner, Timer timer){
		this.client = client;
		this.iloscWatkow = iloscWatkow;
		this.skaner = skaner;
		this.timer = timer;
	}
	
	public void rozpocznijWysylanie(){
		for(int k = 0; k < iloscWatkow; k++){
			Thread sender = new Thread(new Sender(skaner, client, timer));
			sender.start();
		}
	}
}
