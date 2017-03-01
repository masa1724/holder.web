package holder.domain.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import holder.app.util.Message;

public class AbstractService {
	private Logger logeer = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Message msg;
	
	public String getMessage(String msgId) throws IOException{
		return msg.getMessage(msgId);
	}
	
	public String getMessage(String msgId, String[] parameter) throws IOException  {
		return msg.getMessage(msgId, parameter);
	}
	
	public void infoLog(String msgId) throws IOException{
		logeer.info(msg.getMessage(msgId));
	}

	public void infoLog(String msgId, String parameter) throws IOException{
		logeer.info(msg.getMessage(msgId, new String[]{parameter}));
	}
	
	public void infoLog(String msgId, String[] parameter) throws IOException{
		logeer.info(msg.getMessage(msgId, parameter));
	}
	
	public void errorLog(String msgId) throws IOException{
		logeer.error(msg.getMessage(msgId));
	}
	
	public void errorLog(String msgId, String parameter) throws IOException{
		logeer.error(msg.getMessage(msgId, new String[]{parameter}));
	}

	public void errorLog(String msgId, String[] parameter) throws IOException{
		logeer.error(msg.getMessage(msgId, parameter));
	}
	
	public void warnLog(String msgId) throws IOException{
		logeer.warn(msg.getMessage(msgId));
	}
	
	public void warnLog(String msgId, String parameter) throws IOException{
		logeer.warn(msg.getMessage(msgId, new String[]{parameter}));
	}
	
	public void warnLog(String msgId, String[] parameter) throws IOException{
		logeer.warn(msg.getMessage(msgId, parameter));
	}
}
