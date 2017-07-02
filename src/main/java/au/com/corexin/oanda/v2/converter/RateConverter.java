package au.com.corexin.oanda.v2.converter;

import au.com.corexin.oanda.v2.bo.Candle;
import au.com.corexin.oanda.v2.bo.CandleBidAsk;
import au.com.corexin.oanda.v2.bo.CandleMid;
import au.com.corexin.oanda.v2.bo.OandaTypes;
import au.com.corexin.oanda.v2.bo.type.GranularityType;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RateConverter extends AbstractOandaJsonConverter {

	public static Map<String, Object> createCandleQueryParams(
			String instrument, GranularityType granularity, Integer count,
			DateTime start, DateTime end, OandaTypes.CandleFormat candleFormat,
			Boolean includeFirst, Byte dailyAlignment,
			OandaTypes.WeeklyAlignment weeklyAlignment) throws Exception {

		Map<String, Object> fields = new HashMap<String, Object>();

		if (instrument == null || instrument.length() == 0)
			throw new Exception("The instrument param can't be empty or null");

		fields.put(INSTRUMENT, instrument);

		if (granularity == null)
			granularity = GranularityType.S5;
		fields.put(GRANULARITY, granularity.toString());

		if (start != null) {
			fields.put(FROM, start.toString());
		
			if (end != null) {
				fields.put(TO, end.toString());
			}
		}else{
			if (count == null)
				count = 500;
			fields.put(COUNT, count);			
		}

		if (candleFormat != null)
			fields.put(CANDLE_FORMAT, candleFormat.toString());

		if (includeFirst == null)
			includeFirst = true;

		if (start != null)
			fields.put(INCLUDE_FIRST, includeFirst);

		if (dailyAlignment == null) {
			dailyAlignment = 22;
		} else if (dailyAlignment > 23)
			throw new Exception("The dailyAlignment must be between 0 and 23");

		fields.put("dailyAlignment", dailyAlignment);

		if (weeklyAlignment == null)
			weeklyAlignment = OandaTypes.WeeklyAlignment.Friday;
		fields.put("weeklyAlignment", weeklyAlignment.toString());

		return fields;
	}

	public static void populateCandle(HttpResponse<JsonNode> jsonResponse,
									  OandaTypes.CandleFormat candleFormat, Candle candle) {
		JSONObject obj = jsonResponse.getBody().getObject();

		candle.granularity = GranularityType
				.valueOf(obj.getString(GRANULARITY));
		candle.instrument = obj.getString(INSTRUMENT);

		JSONArray array = obj.getJSONArray(CANDLES);

		if (candleFormat == OandaTypes.CandleFormat.bidask) {

			List candles = candle.candles;

			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				CandleBidAsk candleBidAsk = new CandleBidAsk(candle.granularity);

				candleBidAsk.closeAsk = (float) object.getDouble(CLOSE_ASK);
				candleBidAsk.closeBid = (float) object.getDouble(CLOSE_BID);
				candleBidAsk.highAsk = (float) object.getDouble(HIGH_ASK);
				candleBidAsk.highBid = (float) object.getDouble(HIGH_BID);
				candleBidAsk.lowAsk = (float) object.getDouble(LOW_ASK);
				candleBidAsk.lowBid = (float) object.getDouble(LOW_BID);
				candleBidAsk.openAsk = (float) object.getDouble(OPEN_ASK);
				candleBidAsk.openBid = (float) object.getDouble(OPEN_BID);
				candleBidAsk.complete = object.getBoolean(COMPLETE);
				candleBidAsk.setTime(object.getString(TIME));
				candleBidAsk.volume = object.getInt(VOLUME);

				candles.add(candleBidAsk);
			}
		} else if (candleFormat == OandaTypes.CandleFormat.midpoint) {
			List<CandleMid> candles = candle.candles;
			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				CandleMid candleMid = new CandleMid(candle.granularity);

				candleMid.closeMid = (float) object.getDouble(CLOSE_MID);
				candleMid.highMid = (float) object.getDouble(HIGH_MID);
				candleMid.lowMid = (float) object.getDouble(LOW_MID);
				candleMid.openMid = (float) object.getDouble(OPEN_MID);
				candleMid.volume = object.getInt(VOLUME);
				candleMid.setTime(object.getString(TIME));
				candleMid.complete = object.getBoolean(COMPLETE);

				candles.add(candleMid);
			}
		}

	}

}
