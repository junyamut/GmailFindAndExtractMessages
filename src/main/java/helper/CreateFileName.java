package helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CreateFileName {
	private String fileName;
	private String mimeType;
	private String assignedfileExtension;	
	private String originalSubjectLine;
	private String sanitizedSubjectLine;	
	private String formattedDate;
	private Long internalDate;

	public CreateFileName(String subjectLine, Long internalDate, String mimeType) { 
		this.originalSubjectLine = subjectLine;
		this.internalDate = internalDate;
		this.mimeType = mimeType;
	}
	
	public void create() {		
		sanitizeSubject();
		formatDate();
		setFileExtension();		
		fileName = new StringBuilder()
				.append(sanitizedSubjectLine)
				.append(" -- ")
				.append(formattedDate)
				.append(assignedfileExtension)
				.toString(); 
	}
	
	public String getFileName() {
		return fileName;
	}
	
	private void sanitizeSubject() {
		sanitizedSubjectLine = originalSubjectLine.replaceAll("[^a-zA-Z0-9-_\\.]", "-");
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
				assignedfileExtension = ".txt";
				break;
			case MessageHelper.MIMETYPE_TEXT_HTML:
			case MessageHelper.MIMETYPE_MULTIPART_ALTERNATIVE:
			case MessageHelper.MIMETYPE_MULTIPART_MIXED:
			case MessageHelper.MIMETYPE_MULTIPART_RELATED:
			default:
				assignedfileExtension = ".html";
		}
	}
}
