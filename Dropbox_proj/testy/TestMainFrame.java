import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.dropbox.core.DbxException;

import dropbox.ConfigLoader;
import dropbox.Dropbox;
import dropbox.Keys;

public class TestMainFrame {

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testOdczytParam() throws IOException{
		Keys keys = new Keys();
		ConfigLoader config = new ConfigLoader();
		String wynik = config.odczytParam(keys.getPlikKonfiguracyjny());
		
		String przewidywany = "5 C:/Users/Marian/Desktop/test yMV2wfed6gAAAAAAAAAABt_dN6oBaKQ7ZMc6nq2d6DN7nCfJY4vId7V-DiWXcOEf";
		
		assertEquals(przewidywany, wynik);
	}
	
	@Test
	public void testPolacz() throws IOException, DbxException{	
		
		Keys keys = new Keys();
		ConfigLoader config = new ConfigLoader();
		config.odczytParam(keys.getPlikKonfiguracyjny());

		Dropbox dropbox = new Dropbox(config.getToken());
		dropbox.polacz();
		String wynik = dropbox.getUsername();
		String przewidywany = "Adrian K";
		
		assertEquals(przewidywany, wynik);
//		System.out.println("Nazwa powi¹zanego konta: " + client.getAccountInfo().displayName);

	}
}
