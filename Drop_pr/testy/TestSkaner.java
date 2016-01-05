import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import dropbox.Plik;
import skaner.Skaner;

public class TestSkaner {

	@Test
	public void testDajZListy(){
		String sciezka = "C:/Users/Marian/Desktop/test/";
		File f = new File (sciezka);
		File[] file = f.listFiles();
		
		if(file.length > 0){
			String przewidywany = file[0].getAbsolutePath();
		
			Skaner skaner = new Skaner(f);
			Thread watekSkanera = new Thread(skaner);
			watekSkanera.start();
						
			String wynik = "";
			Plik plik;
			
			while((plik = skaner.dajZListy()) == null){
			}
			
			wynik = plik.getPlik().getAbsolutePath();
		
			assertEquals(przewidywany, wynik);
		}
	}

	
	@Test
	public void testUsunZListy(){
		String sciezka = "C:/Users/Marian/Desktop/test/";
		File f = new File (sciezka);
		File[] file = f.listFiles();
		
		if(file.length > 0){		
			Skaner skaner = new Skaner(f);
			Thread watekSkanera = new Thread(skaner);
			watekSkanera.start();
						
			boolean wynik, przewidywany = true;
			Plik plik;
			
			while((plik = skaner.dajZListy()) == null){
			}
			
			wynik = skaner.usunZListy(plik);
		
			assertEquals(przewidywany, wynik);
		}
	}

	@Test
	public void testPlikDoWyslaniaTrue(){
		String sciezka = "C:/Users/Marian/Desktop/test/";
		File f = new File (sciezka);
		
		Skaner skaner = new Skaner(f);
						
		boolean wynik, przewidywany = true;
		
		wynik = skaner.plikDoWyslania(f);
			
		assertEquals(przewidywany, wynik);
	}
	
	@Test
	public void testPlikDoWyslaniaFalse(){
		File f = null;
		
		Skaner skaner = new Skaner(f);						
		boolean wynik, przewidywany = false;

		wynik = skaner.plikDoWyslania(f);
		
		assertEquals(przewidywany, wynik);
	}
}
