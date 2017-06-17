package au.com.corexin.oanda.v2.endpoint;

import au.com.corexin.oanda.v2.util.OandaConstants;

/**
 * Created by steven on 2017/6/17.
 */
public class AccountEndPointsTest {

    AccountEndPoints subject = new AccountEndPoints(Endpoint.AccountType.Practice);

    @org.junit.Test
    public void getAccountSummary() throws Exception {
        subject.getAccountSummary(OandaConstants.PRACTICE_V2_PrimaryAccount);
    }

}