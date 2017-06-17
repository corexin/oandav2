package au.com.corexin.oanda.v2.bo;

public class Range extends AbstractOandaBo implements Comparable<Range>{
	public static float onePip = 0.01f; // default 10 pips
	public static float fivePips = onePip*5;
	public static float tenPips = onePip*10;
	
	public float low;
	public float high;
	
	public Range higherRange;
	public Range lowerRange;
	
	public Range(float l, float h)
	{
		this.low =l;
		this.high = h;
	}
	
 
	public boolean higherThan(Range another)
	{
		if(low>another.low)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public boolean lowerThan(Range another)
	{
		if(low<another.low)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	@Override
	public int compareTo(Range another) {
		if (this.higherThan(another)) {
			return 1;
		} else if (this.lowerThan(another)) {
			return -1;
		} else {
			return 0;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(high);
		result = prime * result + Float.floatToIntBits(low);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Range other = (Range) obj;
		if (Float.floatToIntBits(high) != Float.floatToIntBits(other.high))
			return false;
		if (Float.floatToIntBits(low) != Float.floatToIntBits(other.low))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Range [low=" + low + ", high=" + high + "]";
	}
}
