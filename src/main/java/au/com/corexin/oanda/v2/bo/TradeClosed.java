package au.com.corexin.oanda.v2.bo;

import au.com.corexin.oanda.v2.bo.type.Side;

public class TradeClosed extends AbstractOandaBo {
    public long id;
    public float price;
    public String instrument;
    public float profit;
    public Side side;
    public String time;
	@Override
	public String toString() {
		return "TradeClosed [id=" + id + ", price=" + price + ", instrument=" + instrument + ", profit=" + profit
				+ ", side=" + side + ", time=" + time + "]";
	}
    
}
