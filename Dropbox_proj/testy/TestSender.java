import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.dropbox.core.DbxException;

import dropbox.ConfigLoader;
import dropbox.Dropbox;
import dropbox.Keys;
import sender.Sender;
import skaner.Skaner;
import skaner.SkanerService;
import timer.TimerService;

public class TestSender {

	@Test
	public void testDajZListy() throws IOException{
		String sciezka = "C:/Users/Marian/Desktop/test/";
		File f = new File (sciezka);
		File[] file = f.listFiles();
		
		if(file.length > 0){
			String przewidywany = file[0].getAbsolutePath();
		
			Keys keys = new Keys();
			ConfigLoader config = new ConfigLoader();
			config.odczytParam(keys.getPlikKonfiguracyjny());
			Dropbox dropbox = new Dropbox(config.getToken());
			try {
				dropbox.polacz();
			} catch (IOException | DbxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			SkanerService skanerService = new SkanerService(config.getSciezka());
			skanerService.inicjujSkaner();
			
			TimerService timerService = new TimerService(null);
			timerService.inicjujTimer();
		
			Sender sender = new Sender(skanerService.getSkaner(), dropbox.getClient(), timerService.getTimer());
		
			String wynik = sender.dajZListy().getPlik().getAbsolutePath();
		
			assertEquals(przewidywany, wynik);
		}
	}
	
	@Test
	public void testWyslij() throws IOException, DbxException{
		String sciezka = "C:/Users/Marian/Desktop/test/";
		File f = new File (sciezka);
		File[] file = f.listFiles();
		
		if(file.length > 0){
			Skaner skaner = new Skaner(f);

			Keys keys = new Keys();
			ConfigLoader config = new ConfigLoader();
			config.odczytParam(keys.getPlikKonfiguracyjny());
			Dropbox dropbox = new Dropbox(config.getToken());
			try {
				dropbox.polacz();
			} catch (IOException | DbxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			SkanerService skanerService = new SkanerService(config.getSciezka());
			skanerService.inicjujSkaner();
			
			TimerService timerService = new TimerService(null);
			timerService.inicjujTimer();
		
			Sender sender = new Sender(skanerService.getSkaner(), dropbox.getClient(), timerService.getTimer());
			
			boolean wynik = sender.wyslij(file[0]);
			boolean przewidywany = true;
		
			assertEquals(przewidywany, wynik);
		}
	}

}
