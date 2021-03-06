package au.com.corexin.oanda.v2.bo;

import au.com.corexin.oanda.v2.bo.type.Side;

public class Order extends AbstractOandaBo {
	public long id;
	public String instrument;
	public String time;
	public float price;
	public OandaTypes.OrderType type;
	public Side side;

	@Override
	public String toString() {
		return "Order [id=" + id + ", instrument=" + instrument + ", time=" + time + ", price=" + price + ", type=" + type + ", side=" + side + "]";
	}

}
