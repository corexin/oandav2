package au.com.corexin.oanda.v2.converter;

import au.com.corexin.oanda.v2.bo.OandaJsonKey;
import au.com.corexin.oanda.v2.bo.OpenPosition;
import au.com.corexin.oanda.v2.bo.Position;
import au.com.corexin.oanda.v2.bo.PositionClosed;
import au.com.corexin.oanda.v2.bo.type.Side;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PositionConverter extends AbstractOandaJsonConverter {

	public static List<OpenPosition> populateOpenPositions(HttpResponse<JsonNode> response) {
		JSONObject object = response.getBody().getObject();
		JSONArray array = object.getJSONArray(POSITIONS);
		List<OpenPosition> positions = new ArrayList();

		for (int i = 0; i < array.length(); i++) {

			JSONObject node = (JSONObject) array.get(0);
			OpenPosition position = new OpenPosition();
			position.instrument = node.getString(INSTRUMENT);
			position.pl = node.getDouble(PL);
			position.unrealisedPL = node.getDouble(OandaJsonKey.unrealizedPL);
			position.resettablePL = node.getDouble(OandaJsonKey.resettablePL);
			position.financing = node.getDouble(OandaJsonKey.financing);
			if(node.get("short")!=null)
			{
				populatePositionDetails((JSONObject)node.get("short"));
			}
			if(node.get("long")!=null)
			{
				populatePositionDetails((JSONObject)node.get("long"));
			}

			positions.add(position);
		}

		return positions;

	}


	public static Position populatePosition(HttpResponse<JsonNode> response) {
		JSONObject object = response.getBody().getObject();
		Position position = new Position();

		if (response.getStatus() > 299 || response.getStatus() < 200)
		{
			position.code = ""+response.getStatus();
			position.message = response.getStatusText();
			return position;
		}
		position.avgPrice = (float) object.getDouble(AVG_PRICE);
		position.instrument = object.getString(INSTRUMENT);
		position.side = Side.valueOf(object.getString(SIDE));
		position.units = object.getInt(UNITS);

		return position;
	}

	private static OpenPosition.PositionDetails populatePositionDetails(JSONObject jsonObj) {
		OpenPosition.PositionDetails details = new OpenPosition.PositionDetails();

		if(jsonObj.has(tradeIDs)) {
			JSONArray objects = jsonObj.getJSONArray(tradeIDs);
			if (objects != null)
			objects.forEach(name -> details.tradeIds.add(name.toString()));
		}
		details.unrealisedPL = jsonObj.getDouble(unrealizedPL);
		details.financing  = jsonObj.getDouble(financing);
		details.units  = jsonObj.getInt(units);
		if(jsonObj.has(averagePrice))
			details.averagePrice  = jsonObj.getDouble(averagePrice);
		details.pl = jsonObj.getDouble(pl);
		return details;
	}


	public static PositionClosed populatePositionClosed(HttpResponse<JsonNode> response) {
		JSONObject object = response.getBody().getObject();
		PositionClosed positionClosed = new PositionClosed();

		positionClosed.instrument = object.getString(INSTRUMENT);
		positionClosed.price = (float) object.getDouble(PRICE);
		positionClosed.totalUnits = object.getInt(TOTAL_UNITS);

		JSONArray array = object.getJSONArray(IDS);
		positionClosed.ids = new long[array.length()];

		for (int i = 0; i < array.length(); i++) {
			positionClosed.ids[i] = array.getLong(i);
		}

		return positionClosed;
	}

}
