package au.com.corexin.oanda.v2.bo;

public class OrderMarket extends Order {
	public int units;
	public float takeProfit;
	public float stopLoss;
	public float trailingStop;

	public float upperBound;
	public float lowerBound;

	@Override
	public String toString() {
		return "OrderMarket [units=" + units + ", takeProfit=" + takeProfit + ", stopLoss=" + stopLoss + ", trailingStop=" + trailingStop + ", upperBound=" + upperBound + ", lowerBound=" + lowerBound + ", toString()="
				+ super.toString() + "]";
	}

}