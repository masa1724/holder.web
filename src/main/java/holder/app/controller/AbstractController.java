package holder.app.controller;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;

import holder.app.util.Message;
import holder.domain.repository.common.CounterRepository;

public class AbstractController {
	@Autowired
	private Properties templateUrlProperties;
	
	@Autowired
	private Message msg;
	
	@Autowired
	private CounterRepository counterRepo;
	
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
	/*
	public String getTemplateUrl(String key){
		return templateUrlProperties.getProperty(key);
	}
	
	
	public String getRedirectUrl(String key){
		return templateUrlProperties.getProperty("rd_" + key);
	}
	*/
	
	public String getOperationNo() throws DataAccessException, IOException{
		return "O" + counterRepo.getCounter(CounterRepository.COUNTER_ID_OPERATION);
	}
}
