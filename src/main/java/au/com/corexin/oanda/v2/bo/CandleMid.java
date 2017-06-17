package au.com.corexin.oanda.v2.bo;

import au.com.corexin.oanda.v2.bo.type.GranularityType;
import au.com.corexin.oanda.v2.bo.type.Side;
import au.com.corexin.oanda.v2.util.Utils;
import org.joda.time.DateTime;

public class CandleMid extends AbstractOandaBo {

	public enum Direction {
		UP, DOWN, STILL
	};

	public GranularityType granular;

	public String time;
	public DateTime dateTime;
	public float openMid;
	public float closeMid;
	public float highMid;
	public float lowMid;
	public int volume;
	public boolean complete;

	public CandleMid(GranularityType granular) {
		this.granular = granular;
	}

	public boolean higherThan(CandleMid another) {
		return getHigh() > another.getHigh();
	}

	public boolean lowerThan(CandleMid another) {
		return getLow() < another.getLow();
	}

	public Direction getDirection() {
		if (goingUp()) {
			return Direction.UP;
		} else if (goingDown()) {
			return Direction.DOWN;
		} else {
			return Direction.STILL;
		}
	}

	public boolean goingUp() {
		return openMid < closeMid;
	}

	public boolean goingDown() {
		return openMid > closeMid;
	}

	public float getHigh() {
		if (goingUp()) {
			return closeMid;
		} else {
			return openMid;
		}
	}

	public float getLow() {
		if (goingUp()) {
			return openMid;
		} else {
			return closeMid;
		}
	}

	public float getOpenCloseSpeed() {
		return (openMid - closeMid) / granular.seconds;
	}

	public float getHighLowSpeed() {
		return (highMid - lowMid) / granular.seconds;
	}

	public float getAbsSpeed() {
		return Math.abs(getOpenCloseSpeed());
	}

	public float getIncreaseValue() {
		return highMid - openMid;
	}

	public float getDecreaseValue() {
		return openMid - lowMid;
	}

	public float getOpenCloseValueChange() {
		return closeMid - openMid;
	}

	public float getHighLowAverage() {
		return (highMid + lowMid) / 2;
	}

	public float getOpenCloseAverage() {
		return (openMid + closeMid) / 2;
	}

	public boolean touchThePrice(float price, Side side) {
		return price < highMid && price > lowMid;
	}

	public int getPipsChange() {
		return (int) Math.abs(openMid * 100 - closeMid * 100);
	}

	public String getTime() {
		return time;
	}

	public GranularityType getGranular() {
		return granular;
	}

	public void setGranular(GranularityType granular) {
		this.granular = granular;
	}

	public void setTime(String time) {
		this.time = time;
		this.dateTime = Utils.fromUTCStringToLocalTime(time);
	}

	@Override
	public String toString() {
		return "CandleMid [time=" + dateTime + ", openMid=" + openMid + ", closeMid=" + closeMid + ", highMid=" + highMid + ", lowMid=" + lowMid + ", volume=" + volume + ", complete=" + complete + "]";
	}
}