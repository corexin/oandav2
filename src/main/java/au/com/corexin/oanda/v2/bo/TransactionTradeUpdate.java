package au.com.corexin.oanda.v2.bo;

public class TransactionTradeUpdate extends Transaction {
    public int units;
    public OandaTypes.Side side;
    public float stopLossPrice;
    public int tradeId;

}
