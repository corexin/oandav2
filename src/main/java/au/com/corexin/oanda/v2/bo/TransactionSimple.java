package au.com.corexin.oanda.v2.bo;

public class TransactionSimple extends Transaction {
    public String instrument;
    public int units;
    public OandaTypes.Side side;
    public float price;
}
