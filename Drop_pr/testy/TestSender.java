import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.sql.DriverPropertyInfo;

import org.junit.Test;

import com.dropbox.core.DbxException;

import dropbox.ConfigLoader;
import dropbox.Dropbox;
import dropbox.Keys;
import sender.Sender;
import sender.SenderService;
import skaner.Skaner;
import skaner.SkanerService;
import timer.Timer;

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
			config.odczytParam(keys.plikKonfiguracyjny);
			Dropbox dropbox = new Dropbox(config.getToken());
			try {
				dropbox.polacz();
			} catch (IOException | DbxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			SkanerService skanerService = new SkanerService(config.getSciezka());
			skanerService.inicjujSkaner();
			
			Timer timerService = new Timer();
			timerService.inicjujTimer();
		
			
			SenderService senderService = new SenderService(dropbox, config.getIloscWatkow(), skanerService.getSkaner(), timerService);
			
			Sender sender = new Sender(dropbox, senderService);
		
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
			config.odczytParam(keys.plikKonfiguracyjny);
			Dropbox dropbox = new Dropbox(config.getToken());
			try {
				dropbox.polacz();
			} catch (IOException | DbxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			SkanerService skanerService = new SkanerService(config.getSciezka());
//			skanerService.inicjujSkaner();
//			
//			Timer timerService = new Timer();
//			timerService.inicjujTimer();
//		
//			SenderService senderService = new SenderService(dropbox, config.getIloscWatkow(), skanerService.getSkaner(), timerService);
//			
//			Sender sender = new Sender(dropbox, senderService);
			
			boolean wynik = dropbox.wyslij(file[0]);
			boolean przewidywany = true;
		
			assertEquals(przewidywany, wynik);
		}
	}

}
