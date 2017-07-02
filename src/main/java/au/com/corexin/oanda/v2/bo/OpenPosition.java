package au.com.corexin.oanda.v2.bo;

import java.util.ArrayList;
import java.util.List;

public class OpenPosition extends Position {
	public String instrument;
	public double pl;
	public double unrealisedPL;
	public double resettablePL;
	public double financing;
	public double commission;
	public PositionDetails longPositionDetails;
	public PositionDetails shortPositionDetails;


	public static class PositionDetails {
		public List<String> tradeIds = new ArrayList<String>();
		public double unrealisedPL;
		public double resettablePL;
		public double financing;
		public int units;
		public double averagePrice;
		public double pl;
	}
}