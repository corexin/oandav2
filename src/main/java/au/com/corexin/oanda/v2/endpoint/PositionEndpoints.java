package au.com.corexin.oanda.v2.endpoint;

import au.com.corexin.oanda.v2.bo.OpenPosition;
import au.com.corexin.oanda.v2.bo.Position;
import au.com.corexin.oanda.v2.bo.PositionClosed;
import au.com.corexin.oanda.v2.converter.PositionConverter;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionEndpoints extends Endpoint {

	private final String openPositionsRoute = "/v3/accounts/{account_id}/openPositions";
	private final String positionRoute = "/v3/accounts/{account_id}/positions/{instrument}";
	private final String closePositionRoute = "/v3/accounts/{account_id}/positions/{instrument}/close";

	public PositionEndpoints(AccountType accountType) {
		super(accountType);
	}

	/**
	 * Get a list of all open positions
	 *
	 * @param accountId account id
	 * @return list of positions
	 * @throws UnirestException
	 */
	public List<OpenPosition> getOpenPositions(String accountId) throws UnirestException {

		String endpoint = makeEndpoint(accountType, openPositionsRoute);

		Map<String, String> routeParams = new HashMap<String, String>();
		routeParams.put(ACCOUNT_ID, String.valueOf(accountId));

		HttpResponse<JsonNode> jsonResponse = this.Get(routeParams, null,
				endpoint);

		throwErrorsIfAny(jsonResponse);
		return PositionConverter.populateOpenPositions(jsonResponse);
	}

	/**
	 * Get the position for an instrument
	 *
	 * @param accountId
	 *            account id
	 * @param instrument
	 *            instrument
	 * @return position
	 * @throws UnirestException
	 */
	public Position getPosition(String accountId, String instrument)
			throws UnirestException {
		String endpoint = makeEndpoint(accountType, positionRoute);

		Map<String, String> routeParams = new HashMap<String, String>();
		routeParams.put(ACCOUNT_ID, String.valueOf(accountId));
		routeParams.put(INSTRUMENT, instrument);

		HttpResponse<JsonNode> jsonResponse = this.Get(routeParams, null,
				endpoint);

		throwErrorsIfAny(jsonResponse);
		return PositionConverter.populatePosition(jsonResponse);
	}

	/**
	 * Close an existing position
	 *
	 * @param accountId
	 *            account id
	 * @param instrument
	 *            instrument
	 * @return positionClosed model
	 * @throws UnirestException
	 */
	public PositionClosed closePosition(String accountId, String instrument)
			throws UnirestException {
		String endpoint = makeEndpoint(accountType, positionRoute);

		Map<String, String> routeParams = new HashMap<String, String>();
		routeParams.put(ACCOUNT_ID, String.valueOf(accountId));
		routeParams.put(INSTRUMENT, instrument);

		HttpResponse<JsonNode> jsonResponse = this.Put(routeParams, endpoint);

		throwErrorsIfAny(jsonResponse);

		return PositionConverter.populatePositionClosed(jsonResponse);
	}

}