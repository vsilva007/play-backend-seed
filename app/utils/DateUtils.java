package utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private static Calendar cal = Calendar.getInstance();

	public static long getDayStart(long date) {
		cal.setTime(new Date(date));
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR, 0);
		return cal.getTime().getTime();
	}

	public static long getDayEnd(long date) {
		cal.setTime(new Date(date));
		cal.set(Calendar.MILLISECOND, 999);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.HOUR, 23);
		return cal.getTime().getTime();
	}
}
