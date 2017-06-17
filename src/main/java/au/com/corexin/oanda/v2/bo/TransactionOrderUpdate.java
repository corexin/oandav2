package au.com.corexin.oanda.v2.bo;

public class TransactionOrderUpdate extends Transaction {
    public int units;
    public float price;
    public OandaTypes.Reason reason;
    public Integer expiry;
}

