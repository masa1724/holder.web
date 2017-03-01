package holder.app.util;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Component;

/** 日付共通クラス **/
@Component
public class DateTimeUtils {
	private DateTimeUtils() {}
	
	public final static String FORMAT_YYYYMMDD = "yyyyMMdd";
	public final static String FORMAT_YYYY_MM_DD = "yyyy/MM/dd";
	public final static String FORMAT_DB_YYYYMMDD = "yyyy-MM-dd";
	public final static String FORMAT_DB_DATETIME = "yyyy-MM-dd HH:MM:SS";
	
	public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static String formatDateTimeString(LocalDateTime dateTime){
		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
		return dateTime.format(fomatter);
	}
	
	/** 曜日の和名を取得
	 *  @param date : 日付文字列
	 *  @return 曜日和名 
	 */
	public static String getJapaneseWeekName(String date) {        
		LocalDateTime dateTime = toLocalDateTime(date);
		
		DayOfWeek week = dateTime.getDayOfWeek();
		switch(week) {
			case SUNDAY:    return "日";
			case MONDAY:    return "月";
			case TUESDAY:   return "火";
			case WEDNESDAY: return "水";
			case THURSDAY:  return "木";
			case FRIDAY:    return "金";
			case SATURDAY:  return "土";
		}
		return "";
	}
	
	/** 日付文字列をLocalDateTimeのインスタンスへ変換し、返却します
	 *  @param date : 日付文字列( "-"付き or "/"付き or 区切り文字無し)
	 *  @return LocalDateTimeのインスタンス
	 */
	public static LocalDateTime toLocalDateTime(String dateTimeStr) {        
        String year = null;
        String month = null;
        String day = null;
        
        if (dateTimeStr.indexOf("/") >= 0 || dateTimeStr.indexOf("-") >= 0) {
        	year = dateTimeStr.substring(0,4);
        	month = dateTimeStr.substring(5,7);
        	day = dateTimeStr.substring(8,10);
        } else {
        	year = dateTimeStr.substring(0,4);
        	month = dateTimeStr.substring(4,6);
        	day = dateTimeStr.substring(6,8);
        }
        
        LocalDateTime dateTime = LocalDateTime.now()
				.withYear(Integer.parseInt(year))
				.withMonth(Integer.parseInt(month))
				.withDayOfMonth(Integer.parseInt(day));
		
        return dateTime;
	}
	
	/** LocalDateTimeのインスタンスを日付文字列を変換し、返却します
	 *  @param dateTime : LocalDateTimeのインスタンス
	 *  @param format   : 日付フォーマット
	 *  @return 日付文字列
	 */
	public static String toDateString(LocalDateTime dateTime, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return dateTime.format(formatter);
	}
	
	/** 現在日付をDatabase用の日付文字列で返却する。<br>
	 *  フォーマット: yyyy-MM-dd
	 *  @return 日付文字列(Database用にフォーマットされたもの)
	 */
	public static String getNowDBDateString() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(DateTimeUtils.FORMAT_DB_YYYYMMDD);
		return dateTime.format(fomatter);
	}
	
	/** LocalDateTimeインスタンスをDatabase用の日時文字列で返却する。<br>
	 *  フォーマット: yyyy-MM-dd HH:MM:SS
	 *  @return 日時文字列(Database用にフォーマットされたもの)
	 */
	public static String toDBDateTimeString(LocalDateTime dateTime) {
		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(DateTimeUtils.FORMAT_DB_DATETIME);
		return dateTime.format(fomatter);
	}
	
	public static LocalDateTime toLocalDateTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}
	
	public static LocalDateTime toLocalDateTime(Timestamp ts) {
		return ts == null ? null : ts.toLocalDateTime();
	}
	
	public static Date toDate(LocalDateTime dateTime) {
		return Date.from(ZonedDateTime.of(dateTime, ZoneId.systemDefault()).toInstant());
	}


	
	/** 日付文字列へ区切り文字を付加し、返却する
	 *  @param date : 日付文字列(区切り文字無し)
	 *  @param delimiter : 年月日区切り文字
	 *  @return 区切り文字付きの日付文字列
	 */
	public static String addDateStringDelimiter(String date, String delimiter) {
		StringBuffer tmpDate = new StringBuffer()
			.append(date.substring(0,4))
			.append(delimiter)
			.append(date.substring(4,6))
			.append(delimiter)
			.append(date.substring(6,8));
		
        return tmpDate.toString();
	}
}