package au.com.corexin.oanda.v2.bo;

import au.com.corexin.oanda.v2.bo.type.Side;

public class Position extends AbstractOandaBo {
	public String instrument;
	public int units=0;
	public Side side;
	public float avgPrice;

	@Override
	public String toString() {
		return "Position [instrument=" + instrument + ", units=" + units + ", side=" + side + ", avgPrice=" + avgPrice + "]";
	}

}