package au.com.corexin.oanda.v2.endpoint;

import au.com.corexin.oanda.v2.bo.OandaJsonKey;
import au.com.corexin.oanda.v2.util.OandaConstants;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by steven on 2017/6/17.
 */
public abstract class Endpoint implements OandaJsonKey, OandaConstants {
    public static enum AccountType {
        Practice, Real
    }
    protected   String OAUTH_KEY ;

    protected static final String realEndpoint = "https://api-fxtrade.oanda.com";
    protected static final String practiceEndpoint = "https://api-fxpractice.oanda.com";

    protected AccountType accountType;

    public Endpoint(AccountType accountType) {
        this.accountType = accountType;
        if (AccountType.Practice.equals(accountType)) {
            OAUTH_KEY = OAUTH_KEY_PRACTICE;
        } else {
            OAUTH_KEY = OAUTH_KEY_PRACTICE;
        }
    }

    public HttpResponse<JsonNode> Get(Map<String, String> contextPathParams, Map<String, Object> fields, String endpoint) throws UnirestException {

        GetRequest request = Unirest.get(endpoint);
        if (contextPathParams != null && contextPathParams.size() > 0)
            this.setContextPathParams(endpoint, request, contextPathParams);

        return request.header("Authorization", String.format("Bearer %s", OAUTH_KEY)).queryString(fields).asJson();
    }

    public HttpResponse<JsonNode> Post(Map<String, String> routeParams, Map<String, Object> fields, String endpoint) throws UnirestException {

        HttpRequestWithBody request = Unirest.post(endpoint);
        if (routeParams != null && routeParams.size() > 0)
            this.setContextPathParams(endpoint, request, routeParams);

        return request.header("Authorization", String.format("Bearer %s", OAUTH_KEY)).fields(fields).asJson();
    }

    public HttpResponse<JsonNode> Patch(Map<String, String> routeParams, Map<String, Object> fields, String endpoint) throws UnirestException {

        HttpRequestWithBody request = Unirest.patch(endpoint);
        if (routeParams != null && routeParams.size() > 0)
            this.setContextPathParams(endpoint, request, routeParams);

        return request.header("Authorization", String.format("Bearer %s", OAUTH_KEY)).fields(fields).asJson();
    }

    public HttpResponse<JsonNode> Delete(Map<String, String> routeParams, String endpoint) throws UnirestException {

        HttpRequestWithBody request = Unirest.delete(endpoint);
        if (routeParams != null && routeParams.size() > 0)
            this.setContextPathParams(endpoint, request, routeParams);

        return request.header("Authorization", String.format("Bearer %s", OAUTH_KEY)).asJson();
    }

    protected String makeEndpoint(AccountType accountType, String contextPath) {

        if (accountType == AccountType.Practice)
            return String.format("%s%s", practiceEndpoint, contextPath);

        if (accountType == AccountType.Real)
            return String.format("%s%s", realEndpoint, contextPath);

        else
            return null;
    }

    private HttpRequest setContextPathParams(String endpoint, HttpRequest request, Map<String, String> routeParams) {
        Pattern p = Pattern.compile("\\{\\w+}");
        Matcher matcher = p.matcher(endpoint);

        while (matcher.find()) {
            String tmp = matcher.group();
            String spl = tmp.substring(1, tmp.length() - 1);

            request.routeParam(spl, routeParams.get(spl));
        }

        return request;
    }
}
