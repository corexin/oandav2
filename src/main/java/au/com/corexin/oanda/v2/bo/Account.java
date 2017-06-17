package au.com.corexin.oanda.v2.bo;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Account extends AbstractOandaBo {

	public String accountId;
	public String accountName;
	public double balance;
	public double unrealizedPl;
	public double nav;
	public double marginUsed;
	public double marginAvailable;
	public int openTradeCount;
	public int pendingOrderCount;
	public double marginRate;
	public String accountCurrency;

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("accountId", accountId)
				.append("accountName", accountName)
				.append("balance", balance)
				.append("unrealizedPl", unrealizedPl)
				.append("nav", nav)
				.append("marginUsed", marginUsed)
				.append("marginAvailable", marginAvailable)
				.append("openTradeCount", openTradeCount)
				.append("pendingOrderCount", pendingOrderCount)
				.append("marginRate", marginRate)
				.append("accountCurrency", accountCurrency)
				.toString();
	}
}
