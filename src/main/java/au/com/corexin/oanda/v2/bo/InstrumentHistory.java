package au.com.corexin.oanda.v2.bo;

import au.com.corexin.oanda.v2.bo.type.GranularityType;

import java.util.Arrays;

public class InstrumentHistory extends AbstractOandaBo {
	public String instrument;
	public GranularityType granularity;
	public CandleMid[] candles;
	@Override
	public String toString() {
		return "InstrumentHistory [instrument=" + instrument + ", granularity=" + granularity + ", candles=" + Arrays.toString(candles) + "]";
	}
}
