package skaner;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import dropbox.Plik;

public class Skaner implements Runnable{

	private ArrayList<Plik> listaPlikow = new ArrayList<Plik>();
	private File folder = null;
	
	public Skaner(File wejsciowy){
		folder=wejsciowy;
		listaPlikow=new ArrayList<Plik>();
	}
	
	@Override
	public void run() {
		if(folder.isDirectory()){   
        	while(true){
        		plikDoWyslania(folder);									
            	
            	try {
            		Thread.sleep(5000);
            	} catch (InterruptedException e) {
            		e.printStackTrace();
            	}
        	}
        	
        } else{System.out.println("\nPlik o podanej sciezce nie jest katalogiem.");}
	}

	public void ustawFolder(File folder){
		this.folder = folder;
	}
	
	public synchronized boolean plikDoWyslania(final File folder) {	
		
    	if(folder!=null){
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
            	System.out.println("\nPlik o podanej œciezce jest katalogiem. Jeœli jest pusty zostanie usuniêty.");
            	fileEntry.delete();
            } else {
            	boolean czyWstawic=true;
            	Iterator<Plik> iterator=listaPlikow.iterator();
            	
            	while(iterator.hasNext()){
            		Plik list=iterator.next();
        
            		if(list.getPlik().getName().equals(fileEntry.getName())){
            			czyWstawic=false;
            		}
            	}
            	
            	if(czyWstawic==true){
            		Plik l = new Plik(fileEntry, true);
            		listaPlikow.add(l);
            		}
            	}
        	}
        
        	return true;
    	}
    	
    	return false;
	}
	
	public synchronized Plik dajZListy(){	
		Plik fi = null;
		
		if(listaPlikow.size() > 0){
			for(Plik p : listaPlikow){
				if(p.getDostepnosc()){
					p.setDostepnosc(false);
					System.out.println("Zwracam plik: " + p.getPlik().getName() + " - " + p.getDostepnosc());
					return p;
				}
			}
		}
		
		return fi;
	}
		
	public synchronized boolean usunZListy(Plik f){
		if(listaPlikow.remove(f)) return true;
		return false;
	}

	public ArrayList<Plik> getListaPlikow() {
		return listaPlikow;
	}

	public void setListaPlikow(ArrayList<Plik> listaPlikow) {
		this.listaPlikow = listaPlikow;
	}

	public File getFolder() {
		return folder;
	}

	public void setFolder(File folder) {
		this.folder = folder;
	}
	
	
}
