package holder.app.controller;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.stereotype.Component;

@Component
public class DefaultUrlResolver {
	@Autowired
	private Properties defaultUrlProperties;
	
	private Logger logeer = LoggerFactory.getLogger(this.getClass());
	
	public String getFlowURL(Class<?> clazz) throws IOException {
		String clazzName = clazz.getName();
		String c = extractClassName(clazzName);
		System.out.println("default resolve className=" + clazzName + ", extract=" + c);
		System.out.println("properties load=" + (defaultUrlProperties!=null));
		String url = defaultUrlProperties.getProperty(c);
		System.out.println("default resolve url=" + url);
		
		if (url == null) {
			throw new IllegalMappingException("default resolve " + clazzName + "に紐づくURLが設定されていません。URL=" + url);
		}
		
		logeer.debug("default resolve className=" + clazzName + " url=" + url);
		return url;
	}
	
	private String extractClassName(String clazzName) {
		return clazzName.substring(clazzName.lastIndexOf(".")+1, clazzName.length());
	}
}
