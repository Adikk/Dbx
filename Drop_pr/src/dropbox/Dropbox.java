package dropbox;

import java.io.IOException;
import java.util.Locale;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;

public class Dropbox {

	String token = null;
	DbxClient client = null;
	
	public Dropbox(String token){
		setToken(token);
	}
	
	public boolean polacz()throws IOException, DbxException{	
		String code = token;
	
		DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
		String myAccessTokenREMEMBERME = code;
		//System.out.println(myAccessTokenREMEMBERME);
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
	
	
}
