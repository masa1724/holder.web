package holder.domain.repository.common;

import java.io.IOException;
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
		return _sqlProperties.getProperty(sqlId);
	}
}
