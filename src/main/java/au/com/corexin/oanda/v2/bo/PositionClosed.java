package au.com.corexin.oanda.v2.bo;

import java.util.Arrays;

public class PositionClosed {
	public long[] ids;
	public String instrument;
	public int totalUnits;
	public float price;

	@Override
	public String toString() {
		return "PositionClosed [ids=" + Arrays.toString(ids) + ", instrument=" + instrument + ", totalUnits=" + totalUnits + ", price=" + price + "]";
	}

}
