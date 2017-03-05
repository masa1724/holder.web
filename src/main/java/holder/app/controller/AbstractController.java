package holder.app.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import holder.app.utils.message.Message;

public class AbstractController {
	@Autowired
	private Message msg;
	
	@Autowired
	DefaultUrlResolver urlResolver;
	
	private Logger logeer = LoggerFactory.getLogger(this.getClass());
	
	public String getMessage(String messageId) throws IOException{
		return msg.getMessage(messageId);
	}
	
	public String getMessage(String messageId, String[] parameter) throws IOException  {
		return msg.getMessage(messageId, parameter);
	}

	public void infoLogDirectMessage(String message){
		logeer.info(message);
	}
	
	public void infoLog(String messageId) throws IOException{
		logeer.info(msg.getMessage(messageId));
	}

	public void infoLog(String messageId, String[] parameter) throws IOException{
		logeer.info(msg.getMessage(messageId, parameter));
	}
	
	public void errorLog(String messageId) throws IOException{
		logeer.info(msg.getMessage(messageId));
	}

	public void errorLog(String messageId, String[] parameter) throws IOException{
		logeer.info(msg.getMessage(messageId, parameter));
	}
	
	public void warnLog(String messageId) throws IOException{
		logeer.warn(msg.getMessage(messageId));
	}
	
	public void warnLog(String messageId, String[] parameter) throws IOException{
		logeer.warn(msg.getMessage(messageId, parameter));
	}
	
	public String getFlowURL() throws IOException {
		return urlResolver.getFlowURL(this.getClass());
	}
	
	public String getFlowURL(Class<?> clazz) throws IOException {
		return urlResolver.getFlowURL(clazz);
	}
}
