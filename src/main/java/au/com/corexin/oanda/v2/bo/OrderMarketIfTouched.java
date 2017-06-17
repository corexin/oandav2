package au.com.corexin.oanda.v2.bo;

public class OrderMarketIfTouched extends OrderMarket {

	public String expiry;

	@Override
	public String toString() {
		return "OrderMarketIfTouched [expiry=" + expiry + ", toString()=" + super.toString() + "]";
	}

}
