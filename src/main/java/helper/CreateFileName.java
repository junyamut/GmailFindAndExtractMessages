package helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CreateFileName {
	private String fileName;
	private String fileExtension;
	private String formattedDate;
	private String subject;
	private String mimeType;
	private Long internalDate;	

	public CreateFileName(String subject, Long internalDate, String mimeType) { 
		this.subject = subject;
		this.internalDate = internalDate;
		this.mimeType = mimeType;
	}
	
	public void create() {		
		sanitizeSubject(subject);
		formatDate();
		setFileExtension();		
		fileName = new StringBuilder().append(subject).append(" -- ").append(formattedDate).append(fileExtension).toString(); 
	}
	
	public String getFileName() {
		return fileName;
	}
	
	private void sanitizeSubject(String subject) {
		this.subject = subject.replaceAll("[^a-zA-Z0-9-_\\.]", "-");
	}
	
	private void formatDate() {
		Date date = new Date(internalDate);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		dateFormat.setTimeZone(TimeZone.getDefault());
		formattedDate = dateFormat.format(date);
	}
	
	private void setFileExtension() {		
		switch (mimeType) {
			case MessageHelper.MIMETYPE_TEXT_PLAIN:			
				fileExtension = ".txt";
				break;
			case MessageHelper.MIMETYPE_TEXT_HTML:
			case MessageHelper.MIMETYPE_MULTIPART_ALTERNATIVE:
			case MessageHelper.MIMETYPE_MULTIPART_MIXED:
			case MessageHelper.MIMETYPE_MULTIPART_RELATED:
			default:
				fileExtension = ".html";
		}
	}
}
