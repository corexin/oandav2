package au.com.corexin.oanda.v2.bo;

/**
 * Created by valera on 27.11.2014.
 */
public class TransactionTransferFounds extends Transaction {
    public float amount;
    public float accountBalance;
    public OandaTypes.Reason reason;
}
