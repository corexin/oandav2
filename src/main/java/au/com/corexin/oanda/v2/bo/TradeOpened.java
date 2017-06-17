package au.com.corexin.oanda.v2.bo;

public class TradeOpened extends AbstractOandaBo {
	public long id;
    public int units;
    @Override
    public String toString() {
    	return "TradeOpened [id=" + id + ", units=" + units + "]";
    }
}
