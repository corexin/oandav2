package au.com.corexin.oanda.v2.util;

import au.com.corexin.oanda.v2.bo.AbstractOandaBo;
import au.com.corexin.oanda.v2.bo.Price;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

public class Utils {

	private static final Logger logger = Logger.getLogger(Utils.class);

	public static final String YYYY_MMM_DD_HH_MM_SS = "yyyy MMM dd hh:mm:ss";
	public static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance(YYYY_MMM_DD_HH_MM_SS);

	public static final int DEFAULT_FRACTION_DIGITALS = 2;

	public static DateTime createExpiryDateTime(int expiryInManyDays) {
		DateTime d = new DateTime(DateTimeZone.UTC);
		return d.plusDays(expiryInManyDays);
	}

	public static DateTime createExpiryDateTimeMin(int expiryInMins) {
		DateTime d = new DateTime(DateTimeZone.UTC);
		return d.plusMinutes(expiryInMins);
	}

	public static DateTime createDateTime(int year, int month, int day, int hour, int minute, int second) {
		DateTime result = new DateTime(DateTimeZone.forOffsetHours(10));
		result = result.withYear(year).withMonthOfYear(month).withDayOfMonth(day).withHourOfDay(hour).withMinuteOfHour(minute).withSecondOfMinute(second).withMillisOfSecond(0).withZone(DateTimeZone.forOffsetHours(10))
				.toDateTime(DateTimeZone.UTC);

		return result;
	}

	public static DateTime timeAsUTC(DateTime dateTime) {
		return dateTime.withMillisOfSecond(0).withZone(DateTimeZone.forOffsetHours(10))
				.toDateTime(DateTimeZone.UTC);
	}

	public static DateTime fromUTCStringToLocalTime(String utcTime) {
		return ISODateTimeFormat.dateTime().withZone(DateTimeZone.UTC).parseDateTime(utcTime).withZone(DateTimeZone.forID("Australia/Brisbane"));
	}

	public static DateTime utcToLocalTime(DateTime utcTime) {
		return utcTime.toDateTime(new DateTime().getZone());
	}

	public static DateTime getDateTimeUpToSeconds(DateTime dt) {
		DateTime result = new DateTime();
		result.withYear(dt.getYear());
		result.withMonthOfYear(dt.getMonthOfYear());
		result.withDayOfMonth(dt.getDayOfMonth());
		result.withHourOfDay(dt.getHourOfDay());
		result.withMinuteOfHour(dt.getMinuteOfHour());
		result.withSecondOfMinute(dt.getSecondOfMinute());
		return result;
	}

	public static boolean isMonday() {
		return Calendar.MONDAY == Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	}

	public static String formatFloat(float f) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(DEFAULT_FRACTION_DIGITALS);
		return df.format(f);
	}

	public static String formatDouble(double f) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(DEFAULT_FRACTION_DIGITALS);
		return df.format(f);
	}

	public static String formatFloat(float f, int maxFractionDigitals) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(maxFractionDigitals);
		return df.format(f);
	}

	public static float formatFloatToFloat(double f) {
		return new BigDecimal(f).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	public static float formatFloatToFloat(double f, int decimal) {
		return new BigDecimal(f).setScale(decimal, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	public static String generateToString(Object o) {
		String result = "";
		try {
			if (o instanceof AbstractOandaBo) {
				result += o.toString() + "\n";
			} else if (o.getClass().isAssignableFrom(Float.class)) {
				result += o + ", ";
			} else {
				Field[] declaredFields = o.getClass().getDeclaredFields();
				for (Field f : declaredFields) {
					result += f.getName() + ":" + generateToString(f.get(o)) + ", ";
				}
				result += "\n";
			}
		} catch (Exception e) {
			logger.error("Error generate toString for " + o);
		}
		return result;
	}

	public static String generateToString(Class<?> clazz, Object... obj) {
		String result = "\n";
		Field[] declaredFields = clazz.getDeclaredFields();
		int i = 0;
		for (Field f : declaredFields) {
			result += f.getName() + ":" + obj[i] + ", ";
			i++;
		}

		return result;
	}

	public static String generateToString(List<?> obj) {
		String result = "";
		for (Object o : obj) {
			result += generateToString(o);
		}

		return result;
	}

	public static Float sum(List<Float> list) {
		float result = 0f;
		if (list != null) {
			for (Float f : list) {
				result += f;
			}
		}
		return result;
	}

	public static void sleep(int milSec) {
		try {
			Thread.sleep(milSec);
		} catch (InterruptedException e) {

			logger.error("Error sleep for " + milSec + "milseconds" + e);
		}
	}

	public static int pipsModeBy5(double bigger, double smaller, int pipsInOneDollar) {
		return (new Double(Math.round(bigger * pipsInOneDollar) - Math.round(smaller * pipsInOneDollar)).intValue()) / 5;
	}

	public static DateTime reportStartDateTime() {
		DateTime start = null;
		DateTime now = DateTime.now();
		if (now.getDayOfWeek() == DateTimeConstants.SATURDAY) {
			start = now.minusDays(2);
		} else if (now.getDayOfWeek() == DateTimeConstants.SUNDAY) {
			start = now.minusDays(3);
		} else if (now.getDayOfWeek() == DateTimeConstants.MONDAY) {
			start = now.minusDays(4);
		} else {
			start = now.minusDays(1);
		}
		return start;
	}

	public static float priceDiff(Price high, Price low) {
		return Math.abs(high.bid - low.bid);
	}

	public static float priceDiff(float high, float low) {
		return Math.abs(high - low);
	}

	public static boolean instHalted(Price high, Price low) {
		if (OandaConstants.HALTED_STATUS.equalsIgnoreCase(high.status) || OandaConstants.HALTED_STATUS.equalsIgnoreCase(low.status)) {
			logger.info("Instrument halt:" + high + ":" + low);
			return true;
		} else {
			return false;
		}
	}

	public static String jsonErrorCode(JSONObject json) {
		return json.getString(OandaConstants.ERROR_CODE_KEY);
	}

	public static String jsonErrorMessage(JSONObject json) {
		return json.getString(OandaConstants.ERROR_MESSAGE_KEY);
	}
}
