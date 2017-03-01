package holder.domain.repository.common;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SQL {
	
	@Autowired
	private Properties sqlProperties;

	private static Properties _sqlProperties;
    @PostConstruct
    public void init() {
    	_sqlProperties = sqlProperties;
    }
    
	public static String getSQL(String sqlId) throws IOException {
		LocalDateTime dt = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddhhmmss");		
		System.out.println("[" + dt.format(formatter) + "]" + sqlId + "=" + _sqlProperties.getProperty(sqlId));
		return _sqlProperties.getProperty(sqlId);
	}
}
