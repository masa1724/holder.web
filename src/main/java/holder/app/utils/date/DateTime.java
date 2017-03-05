package holder.app.utils.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/** 日付共通クラス **/
@Component
public class DateTime {
	private DateTime() {}
	
	public final String FORMAT_YYYYMMDD = "yyyyMMdd";
	public final String FORMAT_YYYY_MM_DD = "yyyy/MM/dd";
	
	private final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	@Autowired
	private Properties dateTimeProperties;
	
	public static LocalDateTime getDateTime() {
		return LocalDateTime.now();
	}
	
	/*
	/**
	 * システム日付を返却する
	 * @return LocalDateTime  
	 
	public LocalDateTime getDateTime() {	
		LocalDateTime dateTime = LocalDateTime.now();
	    String sysDate = dateTimeProperties.getProperty("DATE");
	    String sysTime = dateTimeProperties.getProperty("TIME");
	    
	    if(!StringUtils.isEmpty(sysDate)) {
	    	dateTime = dateTime.withYear(Integer.parseInt(sysDate.substring(0,4)))
	    						.withMonth(Integer.parseInt(sysDate.substring(5,7)))
	    						.withDayOfMonth(Integer.parseInt(sysDate.substring(8,10)));
	    }
	    
	    if(!StringUtils.isEmpty(sysTime)) {
	    	dateTime = dateTime.withHour(Integer.parseInt(sysTime.substring(0,2)))
	    						.withMinute(Integer.parseInt(sysTime.substring(3,5)))
	    						.withSecond(Integer.parseInt(sysTime.substring(6,8)));
	    }

		return dateTime;
	}
	*/
	
	public String getDateTimeString() {
		LocalDateTime dateTime = getDateTime();
		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
		return dateTime.format(fomatter);
	}
}