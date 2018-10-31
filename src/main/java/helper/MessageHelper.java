package helper;
import java.util.Iterator;
import java.util.List;

import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import com.google.api.services.gmail.model.MessagePartHeader;

public class MessageHelper {
	private Message message;
	public static final String MIMETYPE_TEXT_PLAIN = "text/plain";
	public static final String MIMETYPE_TEXT_HTML = "text/html";
	public static final String MIMETYPE_MULTIPART_ALTERNATIVE = "multipart/alternative";
	public static final String MIMETYPE_MULTIPART_MIXED = "multipart/mixed";
	public static final String MIMETYPE_MULTIPART_RELATED = "multipart/related";
	
	public MessageHelper() { }

	public MessageHelper(Message message) {
		this.message = message;
	}
	
	public Message getMessage() {
		return message;
	}
	
	public void setMessage(Message message) {
		this.message = message;
	}
		
	public MessagePart getPayload() {
		return message.getPayload();
	}
	
	public String getMimeType() {
		return getPayload().getMimeType();
	}
	
	public List<MessagePartHeader> getHeaders() {
		return getPayload().getHeaders();
	}
	
	public String getSubject() {		
		Iterator<MessagePartHeader> iterator = getHeaders().iterator();
		while (iterator.hasNext()) {
			MessagePartHeader header = iterator.next();
			if (header.getName().equalsIgnoreCase("subject")) {
				return header.getValue();
			}
		}
		return null;
	}
	
	public Long getInternalDate() {
		return message.getInternalDate();
	}
}
