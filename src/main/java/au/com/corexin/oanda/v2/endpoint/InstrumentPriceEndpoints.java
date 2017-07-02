package au.com.corexin.oanda.v2.endpoint;

import au.com.corexin.oanda.v2.bo.Candle;
import au.com.corexin.oanda.v2.bo.CandleMid;
import au.com.corexin.oanda.v2.bo.Instrument;
import au.com.corexin.oanda.v2.bo.Price;
import au.com.corexin.oanda.v2.bo.type.GranularityType;
import au.com.corexin.oanda.v2.converter.InstrumentPriceConverter;
import au.com.corexin.oanda.v2.util.AllInstrument;
import au.com.corexin.oanda.v2.util.OandaConstants;
import au.com.corexin.oanda.v2.util.Utils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by steven on 2017/7/2.
 */
public class InstrumentPriceEndpoints  extends Endpoint {
    public static final String INSTRUMENT_ROUTE = "/v3/accounts/{accountID}/instruments";
    public static final String PRICE_ROUTE = "/v3/accounts/{accountID}/pricing";
    public static final String CANDLE_ROUTE = "/v3/instruments/{instrument}/candles";

    public InstrumentPriceEndpoints(AccountType accountType) {
        super(accountType);
    }


    public List<Instrument> getInstruments(String accountId) throws Exception {
        String endpoint = makeEndpoint(accountType, INSTRUMENT_ROUTE);

        Map<String, String> routeParams = new HashMap<String, String>();
        routeParams.put(ACCOUNTID, String.valueOf(accountId));

        HttpResponse<JsonNode> response = this.Get(routeParams, null, endpoint);

        if (response.getStatus() != 200) {
            throw new UnirestException(response.getBody().toString());
        }

        return InstrumentPriceConverter.populateInstruments(response);
    }


    public Candle<CandleMid> getCandles(String instrument, GranularityType granularityType, Integer count , DateTime startDate, DateTime end) throws Exception {
        String endpoint = makeEndpoint(accountType, CANDLE_ROUTE);
        Map<String, String> routeParams = new HashMap<String, String>();
        routeParams.put(INSTRUMENT, String.valueOf(instrument));

        Map<String, Object> candleQueryParams = InstrumentPriceConverter.createCandleQueryParams(granularityType, count, startDate, end);
        HttpResponse<JsonNode> response = this.Get(routeParams, candleQueryParams, endpoint);

        if (response.getStatus() != 200) {
            throw new UnirestException(response.getBody().toString());
        }

        return InstrumentPriceConverter.populateCandle(response);
    }



    public List<Price> getPrices(String account, String instrument) throws Exception {
        String endpoint = makeEndpoint(accountType, PRICE_ROUTE);
        Map<String, String> routeParams = new HashMap<String, String>();
        routeParams.put(ACCOUNTID, String.valueOf(account));

        Map<String, Object> candleQueryParams = new HashMap<>();
        candleQueryParams.put(INSTRUMENTS, instrument);
        HttpResponse<JsonNode> response = this.Get(routeParams, candleQueryParams, endpoint);

        if (response.getStatus() != 200) {
            throw new UnirestException(response.getBody().toString());
        }

        return InstrumentPriceConverter.populatePrice(response);

    }


}