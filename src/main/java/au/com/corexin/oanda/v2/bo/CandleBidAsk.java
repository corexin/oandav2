package au.com.corexin.oanda.v2.bo;

import au.com.corexin.oanda.v2.bo.type.GranularityType;

public class CandleBidAsk extends CandleMid{
	
    public float openBid;
    public float openAsk;
    public float highBid;
    public float highAsk;
    public float lowBid;
    public float lowAsk;
    public float closeBid;
    public float closeAsk;
    
	public CandleBidAsk(GranularityType granular)
	{
		super(granular);
	}

	@Override
	public String toString() {
		return "\nCandleBidAsk [time=" + dateTime + ", openBid=" + openBid
				+ ", openAsk=" + openAsk + ", highBid=" + highBid
				+ ", highAsk=" + highAsk + ", lowBid=" + lowBid + ", lowAsk="
				+ lowAsk + ", closeBid=" + closeBid + ", closeAsk=" + closeAsk
				+ ", volume=" + volume + ", complete=" + complete + "]";
	}
}
