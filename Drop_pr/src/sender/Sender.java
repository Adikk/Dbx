package sender;

import java.io.File;
import java.io.IOException;
import com.dropbox.core.DbxException;

import dropbox.Dropbox;
import dropbox.Plik;

public class Sender implements Runnable{

	
	private Dropbox dropbox;
	private SenderService parent;
	
	public Sender(Dropbox dropbox, SenderService parent){
		this.dropbox = dropbox;
		this.parent = parent;
	}
	
	@Override
	public void run() {
		while(true){
				Plik f = null;
			
				synchronized (parent.getSkaner().getListaPlikow()) {
					f = dajZListy();
				}
				
				try {
					if(f != null){
						long rozmiarPliku = f.getPlik().length();
						dropbox.wyslij(f.getPlik());
						aktualizujStaty(f.getPlik(), rozmiarPliku);
						parent.getSkaner().usunZListy(f);
					}
				} catch (IOException | DbxException e) {
					System.out.println("Nie mo¿na wys³aæ pliku");
				}
		}
	}

	public synchronized Plik dajZListy(){	
		Plik f = null;
		f = parent.getSkaner().dajZListy();
		return f;
	}
	
	public void aktualizujStaty(File plik, long rozmiarPliku){
        parent.getTimer().getStaty().incrementAndGet();
        parent.getTimer().statystyka(parent.getTimer().getStaty().get()+"  "+plik.getName()+"\n", parent.getTimer().getStaty().get(), rozmiarPliku);
        
        System.out.println(plik.length());
	}
}
