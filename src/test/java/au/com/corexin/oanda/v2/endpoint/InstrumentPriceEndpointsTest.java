package au.com.corexin.oanda.v2.endpoint;

import au.com.corexin.oanda.v2.bo.type.GranularityType;
import au.com.corexin.oanda.v2.util.AllInstrument;
import au.com.corexin.oanda.v2.util.OandaConstants;
import au.com.corexin.oanda.v2.util.Utils;
import org.joda.time.DateTime;

/**
 * Created by steven on 2017/7/2.
 */
public class InstrumentPriceEndpointsTest {
    InstrumentPriceEndpoints subject = new InstrumentPriceEndpoints(Endpoint.AccountType.Practice);

    @org.junit.Test
    public void getCandles() throws Exception {
        DateTime start = DateTime.now().minusDays(2);
        System.out.println(Utils.generateToString(
                subject.getCandles(AllInstrument.WEST_TEXAS_OIL.code, GranularityType.H1,null,start, null)));
    }

    @org.junit.Test
    public void getAllInstruments() throws Exception {
        System.out.println(Utils.generateToString(
                subject.getInstruments(OandaConstants.PRACTICE_V2_PrimaryAccount)));
    }

    @org.junit.Test
    public void getPrices() throws Exception {
        System.out.println(Utils.generateToString(
                subject.getPrices(OandaConstants.PRACTICE_V2_PrimaryAccount, AllInstrument.AU200_AUD.code)));
    }

}