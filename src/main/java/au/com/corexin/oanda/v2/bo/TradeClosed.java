package au.com.corexin.oanda.v2.bo;

public class TradeClosed extends AbstractOandaBo {
    public long id;
    public float price;
    public String instrument;
    public float profit;
    public OandaTypes.Side side;
    public String time;
	@Override
	public String toString() {
		return "TradeClosed [id=" + id + ", price=" + price + ", instrument=" + instrument + ", profit=" + profit
				+ ", side=" + side + ", time=" + time + "]";
	}
    
}
