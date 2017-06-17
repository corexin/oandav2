package au.com.corexin.oanda.v2.bo;

public class Instrument extends AbstractOandaBo {
	public String instrument;
	public String displayName;
	public float pip;
	public int maxTradeUnits;

	@Override
	public String toString() {
		return "Instrument [instrument=" + instrument + ", displayName=" + displayName + ", pip=" + pip
				+ ", maxTradeUnits=" + maxTradeUnits + "]";
	}

}
