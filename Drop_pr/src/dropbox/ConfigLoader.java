package dropbox;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

	int iloscWatkow=0;
	String sciezka;
	String token;
	
//	String nazwa = "config.properties";
	
	public ConfigLoader(){
	}
	
	public String odczytParam(String nazwa) throws IOException{
		String result = "";
		InputStream inputStream;
		try {
			Properties prop = new Properties();
//			String nazwa = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(nazwa);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + nazwa + "' not found in the classpath");
			}
 
			iloscWatkow = Integer.parseInt(prop.getProperty("Threads"));
			sciezka = prop.getProperty("Path");
			token = prop.getProperty("AccessToken");
 
			result = iloscWatkow + " " + sciezka + " " + token;
			inputStream.close();
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} 
		return result;
	}
	
	
	public int getIloscWatkow() {
		return iloscWatkow;
	}

	public void setIloscWatkow(int iloscWatkow) {
		this.iloscWatkow = iloscWatkow;
	}

	public String getSciezka() {
		return sciezka;
	}

	public void setSciezka(String sciezka) {
		this.sciezka = sciezka;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
