package services.helpers;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class DateHelper {
	private static final int HOUR_OFFSET = 5;
	private long date;
	private long dateStart;
	private long dateEnd;

	public DateHelper(long date) {
		this.date = date;
	}

	public long getDateStart() {
		return dateStart;
	}

	public long getDateEnd() {
		return dateEnd;
	}

	public DateHelper invoke() {
		dateStart = atStartOfDay(new Date(this.date)).getTime();
		dateEnd = atEndOfDay(new Date(this.date)).getTime();
		return this;
	}

	private Date atStartOfDay(Date date) {
		LocalDateTime localDateTime = dateToLocalDateTime(date);
		LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		return localDateTimeToDate(startOfDay);
	}

	private Date atEndOfDay(Date date) {
		LocalDateTime localDateTime = dateToLocalDateTime(date);
		LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
		return localDateTimeToDate(endOfDay);
	}

	private LocalDateTime dateToLocalDateTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	private Date localDateTimeToDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public long getOffsetedStartDay(int hoursLess) {
		return this.dateStart - (hoursLess * 60 * 60 * 1000);
	}

	public long getOffsetedEndDay(int hoursMore) {
		return this.dateEnd + (hoursMore * 60 * 60 * 1000);
	}
}
