package au.com.corexin.oanda.v2.bo;

import au.com.corexin.oanda.v2.bo.type.GranularityType;
import au.com.corexin.oanda.v2.util.Utils;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Candle<T extends CandleMid> extends AbstractOandaBo {

	public String instrument;
	public GranularityType granularity;
	public List<T> candles = new ArrayList<T>();

	public List<T> getCandlesBetween(DateTime start, DateTime end) {
		DateTime localStart = Utils.utcToLocalTime(start);
		DateTime localEnd = Utils.utcToLocalTime(end);
		List<T> result = new ArrayList<T>();
		for (T candle : candles) {
			if (candle.dateTime.equals(localStart) || (candle.dateTime.isAfter(localStart) && candle.dateTime.isBefore(localEnd))) {
				result.add(candle);
			}
		}
		return result;
	}

	public List<T> getCandlesBetween(int start, int end) {
		List<T> result = new ArrayList<T>();
		for (int i = start; i <= end; i++) {
			result.add(candles.get(i));
		}
		return result;
	}

	public List<T> getCandlesLatest(int n) {
		List<T> result = new ArrayList<T>();
		for (int i = candles.size() - n; i < candles.size(); i++) {
			result.add(candles.get(i));
		}
		return result;
	}

	public List<CandleMid> getCandles()
	{
		return (List<CandleMid>) candles;
	}

	@Override
	public String toString() {
		return
		// "\nCandle [instrument=" + instrument + ", granularity=" + granularity
		// + ", candle count:" + candles.size() + "\n Candles="
		"\n" + Utils.generateToString(candles);
	}

}
