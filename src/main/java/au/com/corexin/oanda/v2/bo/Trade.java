package au.com.corexin.oanda.v2.bo;

public class Trade extends AbstractOandaBo {
	public long id;
	public int units;
	public OandaTypes.Side side;
	public String instrument;
	public String time;
	public float price;
	public float takeProfit;
	public float stopLoss;
	public float trailingStop;
	public float trailingAmount;
	@Override
	public String toString() {
		return "Trade [id=" + id + ", units=" + units + ", side=" + side + ", instrument=" + instrument + ", time=" + time + ", price=" + price
				+ ", takeProfit=" + takeProfit + ", stopLoss=" + stopLoss + ", trailingStop=" + trailingStop + ", trailingAmount=" + trailingAmount
				+ "]";
	}
}
