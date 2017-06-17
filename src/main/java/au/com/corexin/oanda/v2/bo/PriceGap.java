package au.com.corexin.oanda.v2.bo;

public class PriceGap extends AbstractOandaBo implements Comparable<PriceGap> {

	private static final long serialVersionUID = 1L;
	private double high;
	private double low;
	private double stepScale;

	public PriceGap(double h, double l, double stepScale) {
		this.high = (h);
		this.low = (l);
		this.stepScale = (stepScale);
	}

	public Integer getGapInSteps() {
		return (int)(high/stepScale-low/stepScale);
	}

	@Override
	public int compareTo(PriceGap o) {
		return getGapInSteps() - o.getGapInSteps();
	}

	@Override
	public String toString() {
		return "PriceGap [high=" + high + ", low=" + low + ", stepScale=" + stepScale + ", getGapInSteps()=" + getGapInSteps() + "]";
	}

}
