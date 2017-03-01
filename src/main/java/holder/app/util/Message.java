package holder.app.util;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Message {
	@Autowired
	private Properties messageProperties;
	
	public String getMessage(String msgId) throws IOException {
		return _getMessage(msgId);
	}
	
	public String getMessage(String msgId, String param) throws IOException {
		String[] params = {param};
		return getMessage(msgId, params);
	}
	
	public String getMessage(String msgId, String[] params) throws IOException {
		return MessageFormat.format(_getMessage(msgId), params);
	}
			
	private String _getMessage(String msgId) throws IOException {
		System.out.println("msgId=" + msgId + ", msg=" + messageProperties.getProperty(msgId));
		return messageProperties.getProperty(msgId);
	}
}
