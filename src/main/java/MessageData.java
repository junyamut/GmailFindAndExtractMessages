import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.google.api.client.util.Base64;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;

import helper.MessageHelper;

public class MessageData {	
	
	public MessageData() {	
		
	}
	
	public String getData(Message message, String mimeType) {
		String data = new String();
		StringBuilder dataBuilder = new StringBuilder();
		switch (mimeType) {
			case MessageHelper.MIMETYPE_TEXT_PLAIN:
			case MessageHelper.MIMETYPE_TEXT_HTML:
				dataBuilder.append(message.getPayload().getBody().getData());
				break;			
			case MessageHelper.MIMETYPE_MULTIPART_ALTERNATIVE:
			case MessageHelper.MIMETYPE_MULTIPART_MIXED:
			case MessageHelper.MIMETYPE_MULTIPART_RELATED:
			default:
				try {
					assembleDataFromMessageParts(message.getPayload().getParts(), dataBuilder);
				} catch (IOException e) {
					System.out.println("Cannot get message data: " + e.getMessage());
				}
				
		}
		try {
			data = decode(dataBuilder.toString());
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unsupported encoding: " + e.getMessage());
		}
		return data;
	}
	
	private void assembleDataFromMessageParts(List<MessagePart> messageParts, StringBuilder stringBuilder) throws IOException {
	    for (MessagePart messagePart : messageParts) {
	        if (messagePart.getMimeType().equals("text/html")) {
	        	stringBuilder.append(messagePart.getBody().getData());
	        }
	        if (messagePart.getParts() != null) {
	        	assembleDataFromMessageParts(
	        			messagePart.getParts(), 
	        			stringBuilder);
	        }
	    }
	}
	
	private String decode(String encodedData) throws UnsupportedEncodingException {
		byte[] decoded = Base64.decodeBase64(encodedData);
        return new String(decoded, "UTF-8");
	}
}
