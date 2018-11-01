import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import model.Scopes;

public class OAuth2 {
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	
	public OAuth2() { }
	
	private static GoogleClientSecrets readClientSecrets() throws IOException {
		InputStream inputStream = OAuth2.class.getResourceAsStream(Properties.getProperties().getOauth().getCredentialsFilePath());
		return GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(inputStream));
	}
	
	public static Credential authorize(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		Scopes scopes = Properties.getProperties().getOauth().getScopes();		
		GoogleAuthorizationCodeFlow gacFlow = new GoogleAuthorizationCodeFlow.Builder(
					HTTP_TRANSPORT, 
					JSON_FACTORY, 
					readClientSecrets(), 
					scopes.getRequired())
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(Properties.getProperties().getOauth().getTokensDirectoryPath())))
				.setAccessType(Properties.getProperties().getOauth().getAccessType())
				.setApprovalPrompt(Properties.getProperties().getOauth().getApprovalPrompt())
				.build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(Properties.getProperties().getOauth().getPort()).build();
		return new AuthorizationCodeInstalledApp(gacFlow, receiver).authorize("user");
	}
}
