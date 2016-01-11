package timer;

import java.util.concurrent.atomic.AtomicInteger;

public class Timer {

	private TimerService timer;
	
	private String stat = "";
	private int sekundy = 0;
	private int iloscPlikow = 0;
	private long rozmiarPliku = 0;
	
	private double srednia = 0;
	private double sredniaIB = 0;
	
	private AtomicInteger staty = new AtomicInteger(0);
	
	public Timer(){
	}
	
	public void inicjujTimer(){
		timer = new TimerService(this);
		timer.wystartujTimer();
	}

	public TimerService getTimer() {
		return timer;
	}

	public void setTimer(TimerService timer) {
		this.timer = timer;
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
	
	public void zwiekszSekundy(){
		this.sekundy++;
	}

	public int getIloscPl() {
		return iloscPlikow;
	}

	public void setIloscPl(int iloscPl) {
		this.iloscPlikow = iloscPl;
	}

	public long getRozmiarPl() {
		return rozmiarPliku;
	}

	public void setRozmiarPl(long rozmiarPl) {
		this.rozmiarPliku = rozmiarPl;
	}

	public AtomicInteger getStaty() {
		return staty;
	}

	public void setStaty(AtomicInteger staty) {
		this.staty = staty;
	}

	public double getSrednia() {
		return srednia;
	}

	public void setSrednia(double srednia) {
		this.srednia = srednia;
	}

	public double getSredniaIB() {
		return sredniaIB;
	}

	public void setSredniaIB(double sredniaIB) {
		this.sredniaIB = sredniaIB;
	}

	public void statystyka(String str,int l,long s){	
		
		stat += str;
		iloscPlikow = l;
		rozmiarPliku += s;
	}
	
}
