package au.com.corexin.oanda.v2.endpoint;

import au.com.corexin.oanda.v2.bo.Account;
import au.com.corexin.oanda.v2.converter.AccountConverter;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.HashMap;
import java.util.Map;

public class AccountEndPoints extends Endpoint {

    private final String getSingleAccountContextPath = "/v3/accounts/{account_id}";
    private final String getSingleAccountSummaryContextPath = "/v3/accounts/{account_id}/summary";

    public AccountEndPoints(AccountType accountType) {
        super(accountType);
    }

    public Account getAccountSummary(String accountId) throws UnirestException {
        String endpoint = makeEndpoint(accountType, getSingleAccountContextPath);

        Map<String, String> routeParams = new HashMap<String, String>();
        routeParams.put(ACCOUNT_ID, String.valueOf(accountId));
        HttpResponse<JsonNode> jsonResponse = this.Get(routeParams, null, endpoint);

        if (jsonResponse.getStatus() > 299 || jsonResponse.getStatus() < 200)
            throw new UnirestException(jsonResponse.getBody().toString());

        return AccountConverter.populateAccount(jsonResponse);
    }

}
