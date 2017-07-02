package au.com.corexin.oanda.v2.converter;

import au.com.corexin.oanda.v2.bo.Account;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.json.JSONObject;

public class AccountConverter extends AbstractOandaJsonConverter {

	public static Account populateAccount(HttpResponse<JsonNode> jsonResponse) {

		JSONObject jsonResult = (JSONObject) jsonResponse.getBody().getObject().get(ACCOUNT);

		Account account = new Account();

		account.accountId = jsonResult.getString(ID);
		account.accountName = jsonResult.getString(ALIAS);
		account.balance = jsonResult.getDouble(BALANCE);

		account.unrealizedPl = jsonResult.getDouble(unrealizedPL);
		account.nav = jsonResult.getDouble(NAV);
		account.marginUsed = jsonResult.getDouble(marginUsed);
		account.marginAvailable = jsonResult.getDouble(marginAvailable);
		account.openTradeCount = jsonResult.getInt(openTradeCount);
		account.pendingOrderCount = jsonResult.getInt(pendingOrderCount);
		account.marginRate = jsonResult.getDouble(marginRate);
		account.accountCurrency = jsonResult.getString(currency);

		return account;
	}

}
