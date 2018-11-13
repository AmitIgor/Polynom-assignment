package myMath;

import java.util.ArrayList;
//import java.lang.Iterable;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Predicate;

import myMath.Monom;

/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Igor & Amit
 *
 */
public class Polynom implements Polynom_able{

	private ArrayList<Monom> p = new ArrayList <Monom>();

	/**
	 * Initiate empty polynom
	 */
	public Polynom () {
		this.add(new Monom(0,0));
	}
	/////////////////////////////////////////////////////////////
	/**
	 * Initializer with string
	 * example for input : "-3x^3 - 3x^4 + 5x^2 - 1x^1"
	 * split the string with 'space' and store it on 'splited_p'
	 * checking each string: if operator => merge the operator with next string
	 * split strings with "x^", add with the new information (from last split) new monom to p with parse(Double/Int).
	 * @param _p Gets a string from the user, which contains a data of a new polynom.
	 */
	public Polynom (String _p) {
		/////////////////
		_p = HandlePolynomSpaces(_p);
		////////////////

		String reg = " ";
		String[] splited_p = _p.split(reg);

		for (int i = 0; i < splited_p.length; i++) {
			String mTemp = "";
			if (splited_p[i].length() == 1) {
				if (splited_p[i].charAt(0)=='+' || splited_p[i].charAt(0) == '-') {
					mTemp = splited_p[i]+splited_p[i+1];
					i++;
				}
			}
			else 
				mTemp = splited_p[i];
			if (mTemp.charAt(mTemp.length()-1) == 'x')
				mTemp+="^1";
			if (mTemp.charAt(0) == 'x')
				mTemp = "1"+mTemp;
			if (mTemp.charAt(0) == '+'  && mTemp.charAt(1) == 'x')
				mTemp = "1" + mTemp.substring(1);
			if (mTemp.charAt(0) == '-'  && mTemp.charAt(1) == 'x')
				mTemp = "-1" + mTemp.substring(1);
			/////////////
			if (!(mTemp.contains("x"))) {
				double a = Double.parseDouble(mTemp);
				int b = 0;
				this.add(new Monom(a,b));
			}
			else {
			/////////////
				String[] monom_p = mTemp.split("x\\^");
				double a = Double.parseDouble(monom_p[0].charAt(0) == '+'? monom_p[0].substring(1) : monom_p[0]);
				int b = Integer.parseInt(monom_p[1]);
				this.add(new Monom(a,b));
			}
		}
	}
	/////////////////////////////////////////////////////
	/** 
	 * this method checks if a certain polynom has spaces, if it does -> delete them, and prepares the string for split
	 * by monoms (according to '-' / '+' signs).
	 * @param str the string in which we need to check for spaces into.
	 * @return str returns the string with spaces if needed.
	 * */
	public String HandlePolynomSpaces(String str) {

		str = str.replaceAll(" ", "");						// init the string to avoid string with " " and "" combined.

		if(!(str.contains(" "))) {
			if(str.contains("+")) {
				str = str.replaceAll("\\+" , " +");
			}
			if(str.contains("-")) {
				str = str.replaceAll("\\-" , " -");
			}
		}
		
		if(str.charAt(0) == ' ')							// incase we split a Polynom who starts with '-', example: " -x +3x^2".
			str = str.substring(1);
		return str;
	}
	///////////////////////////////////////////////////





	/////////////////////////////////////////////////////////////
	/**
	 * Adding all monoms in pa to this polynom, one by one.
	 * @param pa The polynom which we are adding to our function's operand polynom.
	 */
	public void add (Polynom_able pa){

		Iterator<Monom> t = pa.iterator();
		while (t.hasNext())
		{
			this.add(new Monom(t.next()));
		}
	}
	///////////////////////////////////////////////////////////////
	/**
	 * Adding monom to a polynom according to its _power index.
	 * @param m1 The new monom added to the polynom.
	 */
	public void add(Monom m1) {
		if (p.size() - 1 < m1.get_power()) {
			while (p.size() < m1.get_power())
			{
				Monom mTemp = new Monom (0,p.size());
				p.add(mTemp);
			}
			p.add(new Monom(m1));
		}
		else
			p.get(m1.get_power()).add(m1);
	}
	/////////////////////////////////////////////////////////////

	/**
	 * Substract p1 from this polynom : p = p - p1
	 * @param p1 The polynom which we substract from our function's operand p.
	 */
	public void substract(Polynom_able p1) {
		Iterator<Monom> t = p1.iterator();
		while (t.hasNext())
		{
			Monom mTemp = new Monom(t.next());
			mTemp.set_coefficient(mTemp.get_coefficient()*(-1));
			this.add(mTemp);
		}
	}
	/////////////////////////////////////////////////////////////
	/**
	 * Multiply p*p1 using 'deep add' method.
	 * @param p1 The polynom which we multiply by.
	 * @multResult = P(x)i*P(y)j	i,j are polynoms index
	 */
	public void multiply(Polynom_able p1) {
		Iterator<Monom> p1Iter = p1.iterator();
		Polynom pNew = new Polynom();
		while (p1Iter.hasNext())
		{
			Monom p1Temp = p1Iter.next();
			Iterator<Monom> pIter = p.iterator();
			while (pIter.hasNext())
			{
				Monom pTemp = pIter.next();
				pNew.add(pTemp.multiply(p1Temp));
			}
		}
		this.p = pNew.p;
	}
	/////////////////////////////////////////////////////////////
	/**
	 * Compare p with p1 using 'deep monom compare' method.
	 * @param p1 the polynom which we need to compare with.
	 * @return false = not equals, true = equals.
	 */
	public boolean equals (Polynom_able p1) {
		Iterator<Monom> p1Iter = p1.iterator();
		Iterator<Monom> pIter = p.iterator();
		Monom p1Temp;
		Monom pTemp;
		Monom_Comperator isEqual = new Monom_Comperator();
		while (p1Iter.hasNext() && pIter.hasNext())
		{
			p1Temp = p1Iter.next();
			pTemp = pIter.next();
			if (isEqual.compare(p1Temp, pTemp) != 0)
				return false;
		}
		return true;
	}
	/////////////////////////////////////////////////////////////
	/**
	 * Checks if the given polynom is the zero polynom.
	 * @return true if polynom is Zero, false isn't.
	 */
	public boolean isZero() {
		for (Monom monom : p) {
			if (monom.get_coefficient() != 0)
				return false;
		}
		return true;
	}
	/////////////////////////////////////////////////////////////
	/**
	 * Calculating root between x0 and x1 with eps is the correction limit.		 ASSUMING: f(x0)*f(x1)<=0
	 * @param x0 left bound of [x0, x1]
	 * @param x1 right bound of [x0, x1]
	 * @param eps a small number aspiring to 0
	 * @return x root of a polynom which has been found
	 */
	public double root(double x0, double x1, double eps) {		

		if(f(x0) == 0)
			return x0;
		if(f(x1) == 0)
			return x1;
		double min = f(x0)<0 ? x0 :x1;
		double max = f(x0)>=0 ? x0 :x1;
		double x = min;
		double move = (max - min)/2;
		while (Math.abs(f(x)) >= eps)
		{
			if (f(x) < 0)
				x += move;
			if (f(x) > 0)
				x -= move;
			if (move == 0) 
				break;
			move /= 2;
		}
		return x;

	}
	/////////////////////////////////////////////////////////////
	/**
	 * Creation of new polynom_able pCopy using 'deep copy' method.
	 * @return pCopy A new polynom who contains the operand-polynom's data.
	 */
	public 	Polynom_able copy() {
		Polynom_able pCopy = new Polynom();
		for (Monom monom : p) {
			pCopy.add(monom);
		}

		return pCopy;
	}
	/////////////////////////////////////////////////////////////
	/**
	 * Creation of new polynom which is derivatived p. 
	 * @return dp A new polynom who contains the derivative of our function's operand polynom.
	 */
	public Polynom_able derivative() {

		Polynom_able dp = new Polynom();
		Iterator<Monom> it = p.iterator();
		while (it.hasNext())
		{
			Monom m1 = new Monom(it.next());
			if (m1.get_coefficient() == 0) continue;
			dp.add(m1.derivative());
		}

		return dp;

	}
	/////////////////////////////////////////////////////////////
	/**
	 * Computes area above y=0
	 * @param x0,x1 = bounds for area calculation
	 * @param eps = deltaX = length for each rectangle using 'Riemann's Sum' method.	
	 * @return area = Sum: f(Xi)*deltaX
	 */
	public double area(double x0,double x1, double eps) {
		//		double deltaX = (x1-x0)/eps;
		double deltaX = eps;
		double area = 0;
		for (double i = x0; i < x1; i+=deltaX) {
			if (f(i) <= 0) continue; 
			area += Math.abs((f(i)*deltaX));
		}
		return area;
	}
	/////////////////////////////////////////////////////////////
	/**
	 * @return iterator over p
	 */
	public Iterator<Monom> iterator(){
		return p.iterator();
	}
	/////////////////////////////////////////////////////////////
	@Override
	/**
	 * @param x : value of x to compute
	 * @return f(x) : value of y for this x.
	 */
	public double f(double x) {
		double sum = 0;
		for (Monom monom : p) {
			sum+= monom.f(x);
		}
		return sum;
	}
	/////////////////////////////////////////////////////////////
	/**
	 * "Transforms" the data of a polynom object into a new string object.
	 * @return forPrint The output of a polynom as a new string.
	 */
	public String toString() {
		//return for zero polynom
		if (isZero() == true)
			return "0x^0";

		String forPrint = "";
		Iterator<Monom> it = p.iterator();
		boolean flagNotFirst = false;
		//first monom in polynom
		if (it.hasNext()) {		
			Monom mPrintFirst = new Monom(it.next());
			if (mPrintFirst.get_coefficient() == 0)
				forPrint+="";
			else {
				flagNotFirst = true;
				forPrint+= mPrintFirst;
			}
		}

		while (it.hasNext()) {

			Monom mPrint = new Monom (it.next());
			if (mPrint.get_coefficient() == 0)
				continue;

			if (mPrint.get_coefficient() > 0)
			{
				if (flagNotFirst)	
					forPrint+=" + ";
				else
					flagNotFirst = true;	//not first anymore
			}

			else
			{
				mPrint.set_coefficient(-mPrint.get_coefficient());
				if (flagNotFirst)
					forPrint+=" - ";
				else
				{
					forPrint+="-";
					flagNotFirst = true;	//not first anymore
				}	
			}

			forPrint+=mPrint.toString();

		}
		return forPrint;
	}

}
