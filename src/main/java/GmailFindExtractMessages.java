import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.ListMessagesResponse;

import helper.CreateFileName;
import helper.MessageHelper;
import helper.WriteToFile;

public class GmailFindExtractMessages {
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();	
	
	public GmailFindExtractMessages() throws GeneralSecurityException, IOException {
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, OAuth2.authorize(HTTP_TRANSPORT))
				.setApplicationName(Properties.getProperties().getApp().getName())
				.build();
		getMessagesMatchingQuery(
				service, 
				Properties.getProperties().getOauth().getUserId(), 
				Properties.getProperties().getQuery());
	}
	  		 	
	public static void getMessagesMatchingQuery(Gmail service, String userId, String query) throws IOException {
		ListMessagesResponse response = service.users().messages().list(userId).setQ(query).execute();
		List<Message> messages = new ArrayList<Message>();
	    while (response.getMessages() != null) {
	    	messages.addAll(response.getMessages());
	    	if (response.getNextPageToken() != null) {
	    		String pageToken = response.getNextPageToken();
	    		response = service.users().messages()
	    				.list(userId)
	    				.setQ(query)
	    				.setPageToken(pageToken)
	    				.execute();
	    	} else {
	    		break;
	    	}
	    }	    
	    System.out.println("Total no. of messages retrieved: " + messages.size());
	    MessageHelper messageHelper;
	    MessageData messageData;
	    CreateFileName fileName;
	    WriteToFile writeToFile;
	    for (Message message : messages) {
	    	System.out.println("Retrieving fully-qualified message for Message ID: " + message.getId());	    	
			message = getFullMessage(service, 
					Properties.getProperties().getOauth().getUserId(), 
					message.getId());
			messageHelper = new MessageHelper(message);
			messageData = new MessageData();
			fileName = new CreateFileName(
					messageHelper.getSubject(), 
					messageHelper.getInternalDate(), 
					messageHelper.getMimeType());
			fileName.create();
			writeToFile = new WriteToFile.WriteToFileBuilder()
					.outputDir(Properties.getProperties().getApp().getOutputDir())
					.mimeType(messageHelper.getMimeType())
					.fileName(fileName.getFileName())
					.content(messageData.getData(messageHelper.getMessage(), messageHelper.getMimeType()))
					.build();
			writeToFile.write();
	    }
	}
	  
	private static Message getFullMessage(Gmail service, String userId, String messageId) throws IOException {
		return service.users().messages().get(userId, messageId).execute();		  
	}
	  
	public static void main(String[] args) throws IOException, GeneralSecurityException {
		try {
			new GmailFindExtractMessages();
		} catch (GeneralSecurityException | IOException e) {
			e.printStackTrace();
		}
	}
}
