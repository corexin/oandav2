package au.com.corexin.oanda.v2.bo;

import au.com.corexin.oanda.v2.bo.type.Side;

public class TransactionSimple extends Transaction {
    public String instrument;
    public int units;
    public Side side;
    public float price;
}
