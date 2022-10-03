package services.helpers;

import java.util.Calendar;
import java.util.Date;

public class TimeHelper {
	private long date;
	private int field;
	private int difference;
	private long dateStart;
	private long dateEnd;

	public TimeHelper(long date, int field, int difference) {
		this.date = date;
		this.field = field;
		this.difference = difference > 0 ? difference * -1 : difference;
	}

	public long getDateStart() {
		return dateStart;
	}

	public long getDateEnd() {
		return dateEnd;
	}

	public TimeHelper invoke() {
		Calendar cal = addToCalendar(this.difference);
		dateStart = cal.getTime().getTime();
		cal = addToCalendar(this.difference * -1);
		dateEnd = cal.getTime().getTime();
		return this;
	}

	private Calendar addToCalendar(int difference) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(this.date));
		switch (this.field) {
		case Calendar.MILLISECOND:
			cal.add(Calendar.MILLISECOND, difference);
			break;
		case Calendar.SECOND:
			cal.add(Calendar.SECOND, difference);
			break;
		case Calendar.MINUTE:
			cal.add(Calendar.MINUTE, difference);
			break;
		case Calendar.HOUR:
			cal.add(Calendar.HOUR, difference);
			break;
		}
		return cal;
	}
}
