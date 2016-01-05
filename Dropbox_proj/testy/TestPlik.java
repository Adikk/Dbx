import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import dropbox.Plik;

public class TestPlik {

	@Test
	public void testGetDostepnosc(){
		String sciezka = "C:/Users/Marian/Desktop/test/";
		File f = new File (sciezka);
		File[] file = f.listFiles();
		
		if(file.length > 0){
			Plik plik = new Plik(file[0], true);
			boolean wynik = plik.getDostepnosc();
			boolean przewidywany = true;
			
			assertEquals(przewidywany, wynik);
		}
	}
	
	@Test
	public void testGetPlik(){
		String sciezka = "C:/Users/Marian/Desktop/test/";
		File f = new File (sciezka);
		File[] file = f.listFiles();
		
		if(file.length > 0){
			Plik plik = new Plik(file[0], true);
			String przewidywany = file[0].getAbsolutePath();
			String wynik = plik.getPlik().getAbsolutePath();
			
			assertEquals(przewidywany, wynik);
		}
	}
}
