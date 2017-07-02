package au.com.corexin.oanda.v2.endpoint;

import au.com.corexin.oanda.v2.bo.OpenPosition;
import au.com.corexin.oanda.v2.bo.Position;
import au.com.corexin.oanda.v2.util.OandaConstants;
import au.com.corexin.oanda.v2.util.Utils;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

import java.util.List;

/**
 * Created by steven on 2017/6/22.
 */
public class PositionEndpointsTest {

    PositionEndpoints endpoints = new PositionEndpoints(Endpoint.AccountType.Practice);

    @Test
    public void testGetOpenPosition() throws UnirestException {
        List<OpenPosition> openPositions = (List<OpenPosition>) endpoints.getOpenPositions(OandaConstants.PRACTICE_V2_PrimaryAccount);
        System.out.println(Utils.generateToString(openPositions));
    }
}