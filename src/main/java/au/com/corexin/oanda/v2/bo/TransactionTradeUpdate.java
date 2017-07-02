package au.com.corexin.oanda.v2.bo;

import au.com.corexin.oanda.v2.bo.type.Side;

public class TransactionTradeUpdate extends Transaction {
    public int units;
    public Side side;
    public float stopLossPrice;
    public int tradeId;

}
