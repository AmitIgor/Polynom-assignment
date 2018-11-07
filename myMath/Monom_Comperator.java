package myMath;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	@Override
	/**
	 * compare with Integer/Double compare function
	 * @param o1, o2, The monoms which we compare.
	 * @return -1 if o1<o2	;	0 if o1=o2	;	1 if o1>o2
	 */
	public int compare(Monom o1, Monom o2) {
		int e = Integer.compare(o1.get_power(), o2.get_power());
		if (e == 0)
			e = (Double.compare(o1.get_coefficient(), o2.get_coefficient()));
		return e;
	}

	// ******** add your code below *********

}
