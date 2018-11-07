
package myMath;

/**
 * This class represents a simple "Monom" of shape a*x^b, where 'a' is a real number and 'b' is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */

public class Monom implements function{

	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);					// צריך לזכור להוסיף מקרי קצה (לדוגמא שליליים)
	}
	
	/**
	 * function 
	 * @return f(x)=a*x^b
	 */
	
	public double f(double x) {								// object.f(x);
		return _coefficient*Math.pow(x, _power);
	}
	
	public Monom(Monom ot) {								
		this(ot.get_coefficient(), ot.get_power());
	}
	
	
	/**
	 * Getters
	 * @return _power
	 * @return _coefficient
	 */
	
	public int get_power() {
		return _power;
	}
	
	public double get_coefficient() {
		return _coefficient;
	}
	
	// ***************** add your code below **********************


	//****************** Private Methods and Data *****************
	
	/**
	 * Sets coefficient
	 * @param a The Coefficient to be set.
	 */
	
	public void set_coefficient(double a){
		this._coefficient = a;
	}
	
	/**
	 * Sets power
	 * @param p The power to be set.
	 */
	public void set_power(int p) {
		this._power = p;
	}

	public String toString() {
		return _coefficient + "x^" + _power;
	}
	
	/**
	 * Adding monom m1 to the operand monom.
	 * @param m1 The monom which needs to be built.
	 */
	public void add(Monom m1) {								// assuming that both monoms has the same power
		this.set_coefficient(this._coefficient + m1._coefficient);
	}
	
	/**
	 * Multiplying the operand monom with m1, saving the result in m2 and returns the result.
	 * @param m1 The monom in which we multiply by.
	 * @return m2 Contains the multiplication of m1 and function's operand monom.
	 */
	public Monom multiply(Monom m1) {
		Monom m2 = new Monom(this._coefficient * m1._coefficient, this._power + m1._power);			// (ax^b) * (cx^d) = (a*c)x^(b+d)
		return m2;
	}
	
	/**
	 * derivating a monom, multiplying the monom's power with his coefficient, and then lowering the power by 1 (as long as it is higher than 0)
	 * @return m1 The new monom with the derivative of the function's operand.
	 */
	public Monom derivative() {
		
		Monom m1 = new Monom(0,0);
		if(this._power == 0) 									
			return m1;
			
		else {
			 m1.set_coefficient(this._coefficient * this._power);					// if the power is positive and higher than 0, coefficiient is being multiplied by power, 
			 m1.set_power(this._power - 1);										// power is lowered by 1.	
		}
		return m1;
	}
	
	private double _coefficient; // 
	private int _power; 
	
}
