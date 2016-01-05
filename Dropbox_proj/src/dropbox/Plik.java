package dropbox;
import java.io.File;


public class Plik {

	File f;
	boolean dostepny;
	
	public Plik (File f,boolean dostepny){
		this.f=f;
		this.dostepny=dostepny;
	}
	
	public File getPlik(){
		return f;
	}
	
	public boolean getDostepnosc(){
		return dostepny;
	}
	
	public void setDostepnosc(boolean d){
		dostepny=d;
	}
	
	
}
