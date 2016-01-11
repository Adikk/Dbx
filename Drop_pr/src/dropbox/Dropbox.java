package dropbox;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWriteMode;

public class Dropbox {

	private String token = null;
	private DbxClient client = null;
	
	public Dropbox(String token){
		setToken(token);
	}
	
	public boolean polacz()throws IOException, DbxException{	
		String code = token;
	
		DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
		String myAccessTokenREMEMBERME = code;
		client = new DbxClient(config, myAccessTokenREMEMBERME);
		
		return true;
	}
	
	public String getUsername() throws DbxException{
		System.out.println("Nazwa powi¹zanego konta: " + client.getAccountInfo().displayName);
		return client.getAccountInfo().displayName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public DbxClient getClient() {
		return client;
	}

	public void setClient(DbxClient client) {
		this.client = client;
	}
	
	public void wyslij(Plik plik){
		
	}
	
	public boolean wyslij(File plik) throws IOException, DbxException {	
   
		
    	System.out.println("Wysy³am: "+plik.getName());
        
        File inputFile = plik;
        FileInputStream inputStream = new FileInputStream(plik);
        try {
            DbxEntry.File uploadedFile = client.uploadFile(
            		"/"+plik.getName(),
                DbxWriteMode.add(), inputFile.length(), inputStream);
            System.out.println("Uploaded: " + uploadedFile.toString());
        } finally {
            inputStream.close();
        }

        plik.delete();       
        
        return true;
    }
	
	
}
