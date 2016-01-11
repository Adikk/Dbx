package timer;

import java.util.concurrent.atomic.AtomicInteger;

import dropbox.MainFrame;

public class Timer implements Runnable{

	MainFrame okno;
	
	String stat = "";
	int sekundy = 0;
	int iloscPl = 0;
	long rozmiarPl = 0;
	
	AtomicInteger staty = new AtomicInteger(0);
	
	public Timer(MainFrame okno){
		this.okno = okno;
	}
	
	void wystartujTimer(){
		Thread timer = new Thread(this);
		timer.start();
	}
	
	public void statystyka(String str,int l,long s){	
		stat = stat + str;
		iloscPl=l;
		rozmiarPl=rozmiarPl+s;
		
		okno.aktualizujStatystyki(stat);
	}
	
	@Override
	public void run() {

		while(true){

			try {
				Thread.sleep(1000);		        
				sekundy++;
				//System.out.println("Odliczanie sekund: "+sekundy);			
			} catch(InterruptedException ie) {}
			double srednia = (double)iloscPl/(double)sekundy;
			double sredniaIB = rozmiarPl/sekundy;
			java.text.DecimalFormat df=new java.text.DecimalFormat(); 
			df.setMaximumFractionDigits(4); 
			df.setMinimumFractionDigits(2); 
			
			okno.aktualizujStatystyki(df.format(sredniaIB), df.format(srednia));
		}
	}

	public MainFrame getOkno() {
		return okno;
	}

	public void setOkno(MainFrame okno) {
		this.okno = okno;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public int getSekundy() {
		return sekundy;
	}

	public void setSekundy(int sekundy) {
		this.sekundy = sekundy;
	}

	public int getIloscPl() {
		return iloscPl;
	}

	public void setIloscPl(int iloscPl) {
		this.iloscPl = iloscPl;
	}

	public long getRozmiarPl() {
		return rozmiarPl;
	}

	public void setRozmiarPl(long rozmiarPl) {
		this.rozmiarPl = rozmiarPl;
	}

	public AtomicInteger getStaty() {
		return staty;
	}

	public void setStaty(AtomicInteger staty) {
		this.staty = staty;
	}
	
	
}
