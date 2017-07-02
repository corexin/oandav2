package au.com.corexin.oanda.v2.bo;

public class Instrument extends AbstractOandaBo {
	public String name;
	public String displayName;
	public int dipslayPrecision;
	public int maxOrderUnits;
	public int minTradeSize;
	public String type;

	@Override
	public String toString() {
		return "Instrument{" +
				"name='" + name + '\'' +
				", displayName='" + displayName + '\'' +
				", dipslayPrecision=" + dipslayPrecision +
				", maxOrderUnits=" + maxOrderUnits +
				", minTradeSize=" + minTradeSize +
				", type='" + type + '\'' +
				'}';
	}
}
