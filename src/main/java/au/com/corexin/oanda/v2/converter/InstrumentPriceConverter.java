package au.com.corexin.oanda.v2.converter;

import au.com.corexin.oanda.v2.bo.*;
import au.com.corexin.oanda.v2.bo.type.GranularityType;
import au.com.corexin.oanda.v2.util.Utils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstrumentPriceConverter extends AbstractOandaJsonConverter {

	public static Map<String, Object> createCandleQueryParams(
			GranularityType granularity, Integer count,
			DateTime start, DateTime end) throws Exception {

		Map<String, Object> fields = new HashMap<String, Object>();

		if (granularity == null)
			granularity = GranularityType.S5;
		fields.put(GRANULARITY, granularity.toString());

		if (start != null) {
			fields.put(FROM, Utils.timeAsUTC(start).toString());
		
			if (end != null) {
				fields.put(TO, end.toString());
			}
			else {
				DateTime endDate = new DateTime(DateTimeZone.UTC).minusMinutes(1);
				fields.put(TO, endDate.toString());
			}

		}else{
			if (count == null)
				count = 500;
			fields.put(COUNT, count);			
		}

		if (start != null)
			fields.put(INCLUDE_FIRST, true);

		fields.put("dailyAlignment", 23);

		fields.put("weeklyAlignment", OandaTypes.WeeklyAlignment.Friday.toString());

		return fields;
	}

	public static Candle populateCandle(HttpResponse<JsonNode> jsonResponse) {
		Candle result = new Candle();
		JSONObject obj = jsonResponse.getBody().getObject();

		result.instrument = obj.getString(INSTRUMENT);
		result.granularity = GranularityType.valueOf(obj.getString(GRANULARITY));

		JSONArray array = obj.getJSONArray(CANDLES);

		List candles = result.candles;

		for (int i = 0; i < array.length(); i++) {
			JSONObject object = array.getJSONObject(i);
			CandleMid candleMid = new CandleMid(result.granularity);
			candleMid.volume = object.getInt(VOLUME);
			JSONObject jsonCandle = object.getJSONObject(MID);
			candleMid.openMid = (float) jsonCandle.getDouble(OPEN);
			candleMid.closeMid = (float) jsonCandle.getDouble(CLOSE);
			candleMid.highMid = (float) jsonCandle.getDouble(HIGH);
			candleMid.lowMid = (float) jsonCandle.getDouble(LOW);
			candleMid.setTime(object.getString(TIME));
			candleMid.complete = object.getBoolean(COMPLETE);
			candles.add(candleMid);
		}
		return result;
	}

	public static List<Price> populatePrice(HttpResponse<JsonNode> jsonResponse ) {
		JSONObject obj = jsonResponse.getBody().getObject();
		JSONArray array = obj.getJSONArray(PRICES);
		List<Price> prices = new ArrayList<Price>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject object = array.getJSONObject(i);
			Price price = new Price();

			price.ask = (float) object.getDouble(CLOSE_ASK);
			price.bid = (float) object.getDouble(CLOSE_BID);
			price.instrument = object.getString(INSTRUMENT);
			price.status = object.optString(STATUS);
			price.setTime(object.optString(TIME));

			prices.add(price);
		}
		return prices;
	}

	public static List<Instrument> populateInstruments(HttpResponse<JsonNode> jsonResponse) {
		JSONObject obj = jsonResponse.getBody().getObject();
		JSONArray array = obj.getJSONArray(INSTRUMENTS);
		List<Instrument> prices = new ArrayList<Instrument>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject object = array.getJSONObject(i);
			Instrument instrument = new Instrument();

			instrument.name = object.getString(NAME);
			instrument.displayName= object.getString(DISPLAY_NAME);
			instrument.dipslayPrecision = object.getInt(displayPrecision);
			instrument.maxOrderUnits = object.getInt(maximumOrderUnits);
			instrument.minTradeSize = object.getInt(minimumTradeSize);
			instrument.type = object.optString(TYPE);
			prices.add(instrument);
		}
		return prices;
	}
}
