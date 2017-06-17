package au.com.corexin.oanda.v2.bo;

import au.com.corexin.oanda.v2.util.Utils;
import org.joda.time.DateTime;

public class Price extends AbstractOandaBo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String instrument;
	public String time;
	public DateTime dateTime;
	public float bid;
	public float ask;
	public String status;

	public static Price newPrice(float p) {
		Price price = new Price();
		price.setBid(p);
		return price;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
		this.dateTime = Utils.fromUTCStringToLocalTime(time);
	}

	public float getBid() {
		return bid;
	}

	public void setBid(float bid) {
		this.bid = bid;
	}

	public float getAsk() {
		return ask;
	}

	public void setAsk(float ask) {
		this.ask = ask;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Price [instrument=" + instrument + ", time=" + dateTime + ", bid=" + bid + ", ask=" + ask + ", status=" + status + "]";
	}

}
