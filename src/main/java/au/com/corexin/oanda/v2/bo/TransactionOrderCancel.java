package au.com.corexin.oanda.v2.bo;


public class TransactionOrderCancel extends Transaction {
    public int orderId;
    public OandaTypes.Reason reason;
}
