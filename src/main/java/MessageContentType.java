import java.util.Iterator;
import java.util.List;

import com.google.api.services.gmail.model.MessagePartHeader;

public class MessageContentType {

	public MessageContentType() { }
	
	public String getType(List<MessagePartHeader> headers) {		
		Iterator<MessagePartHeader> iterator = headers.iterator();
		while (iterator.hasNext()) {
			MessagePartHeader header = iterator.next();
			if (header.getName().equalsIgnoreCase("content-type")) {
				return header.getValue();
			}
		}
		return null;
	}

}
