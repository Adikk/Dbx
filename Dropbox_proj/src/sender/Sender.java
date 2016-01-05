package sender;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxWriteMode;

import dropbox.Plik;
import skaner.Skaner;
import timer.Timer;

public class Sender implements Runnable{

	Skaner skaner;
	DbxClient client;
	Timer timer;
	
	public Sender(Skaner skaner, DbxClient client, Timer timer){
		this.skaner = skaner;
		this.client = client;
		this.timer = timer;
	}
	
	@Override
	public void run() {
		while(true){
				Plik f = null;
			
				synchronized (skaner.getListaPlikow()) {
					f = dajZListy();
				}
				
				try {
					if(f != null){
						wyslij(f.getPlik());
						aktualizujStaty(f.getPlik());
						skaner.usunZListy(f);
					}
				} catch (IOException | DbxException e) {
					System.out.println("Nie mo¿na wys³aæ pliku");
				}
		}
	}

	public synchronized Plik dajZListy(){	
		Plik f = null;
		f = skaner.dajZListy();
		return f;
	}
	
	public boolean wyslij(File plik) throws IOException, DbxException {	
   
		
    	System.out.println("Wysy³am: "+plik.getName());
        
        File inputFile = plik;
        FileInputStream inputStream = new FileInputStream(plik);
        try {
            DbxEntry.File uploadedFile = client.uploadFile(
            		"/"+plik.getName(),
                DbxWriteMode.add(), inputFile.length(), inputStream);
            System.out.println("Uploaded: " + uploadedFile.toString());
        } finally {
            inputStream.close();
        }

        plik.delete();       
        
        return true;
    }
	
	public void aktualizujStaty(File plik){
        timer.getStaty().incrementAndGet();
        timer.statystyka(timer.getStaty().get()+"  "+plik.getName()+"\n",timer.getStaty().get(),plik.length());
	}
}
