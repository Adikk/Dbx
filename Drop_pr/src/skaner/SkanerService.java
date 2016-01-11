package skaner;

import java.io.File;

public class SkanerService {

	private String sciezka;
	private Skaner skaner;
	
	public SkanerService(String sciezka){
		this.sciezka = sciezka;
	}
	
	public void inicjujSkaner(){
		File f = new File (sciezka);

		skaner = new Skaner(f);
		Thread watekSkanera = new Thread(skaner);
		watekSkanera.start();
	}

	public String getSciezka() {
		return sciezka;
	}

	public void setSciezka(String sciezka) {
		this.sciezka = sciezka;
	}

	public Skaner getSkaner() {
		return skaner;
	}

	public void setSkaner(Skaner skaner) {
		this.skaner = skaner;
	}
	
	
}
