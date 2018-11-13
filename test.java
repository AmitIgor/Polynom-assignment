package myMath;

public class test {

	public static void main(String[] args) {
		
		// סטרינג בלי רווחים
		Polynom pol = new Polynom ("2x^2 +3x-4");
		System.out.println(pol);
		
		
		
		//יצירת סטרינג
		System.out.println("Check #1.a: Creating a polynom from a string: \n");
		String strCheck = "2.0x^0 - 1.0x^1 + 5.0x^2 + 3.0x^4";
		Polynom_able poly1 = new Polynom("2x^0 + 3x^4 + 5x^2 - x");
		System.out.println("Poly1 should be: 2.0x^0 - 1.0x^1 + 5.0x^2 + 3.0x^4");
		System.out.println("Poly1 is: " + poly1 );
		System.out.println("Works? " + poly1.equals(new Polynom(strCheck)) + "\n");

		// סטרינג של פולינום עם מינוס בהתחלה
		System.out.println("Check #1.b: Creating a polynom from a string with a minus at the start: \n");
		Polynom poly5 = new Polynom("-2x^0 - 1x^1 + 2x^2");
		System.out.println("Poly5 = " + poly5 + "\n");


		//		הצהרה ריקה
		System.out.println("Check #2: Creating an empty polynom: \n");
		Polynom_able poly2 = new Polynom();
		strCheck = poly2.toString();
		System.out.println("Poly2 should be: 0x^0");
		System.out.println("Poly2 is: " + poly2);
		System.out.println("Works? " + poly2.equals(new Polynom(strCheck)) + "\n");


		//		העתקה
		System.out.println("Check #3: Copying a polynom: \n");
		Polynom_able poly3 = new Polynom();
		System.out.println("Poly3 is : " + poly3);
		poly3 = poly1.copy();
		System.out.println("Poly3 adress = Poly1 adress? " + (poly1==poly3) + "\n");
		System.out.println("Poly1 = " + poly1 + "\nPoly3 = " + poly3);
		System.out.println("Works? " + poly3.equals(poly1) + "\n");


		//		חיבור של פולינום עם מונום
		System.out.println("Check #4.a: Adding monom to existing polynom: \n");
		strCheck = "2.0x^0 - 1.0x^1 + 5.0x^2 + 3.0x^4 + 4.5x^5";
		System.out.println("Our string for Check is: " + strCheck);
		System.out.println("Poly1 = " + poly1 + "\n");
		poly1.add(new Monom(4.5,5));
		System.out.println("Poly1 + the new monom 4.5x^5 = " + poly1);
		System.out.println("Are poly1 and and " + strCheck + " equal? " + strCheck.equals(poly1) + "\n");


		// בדיקה נוספת עם תוספת שנייה של מונום שכבר קיים בפולינום, אופציונאלי.
		System.out.println("Check #4.b: Adding monom to existing polynom: \n");
		poly1.add(new Monom(4.5,5));
		System.out.println("Poly1 + the new monom 4.5x^5 = " + poly1);
		System.out.println("Are poly1 and and " + strCheck + " equal? " + strCheck.equals(poly1) + "\n");


		//		חיבור של שני פולינומים
		System.out.println("Check #5: Adding one polynom to another: \n");
		System.out.println("Poly1 = " + poly1 + "\nPoly3 = " + poly3);
		strCheck = "4.0x^0 - 2.0x^1 + 10.0x^2 + 6.0x^4 + 9.0x^5";
		System.out.println("Our string for Check = " + strCheck);
		poly1.add(poly3);
		System.out.println("Poly1 + Poly3 = " + poly1);
		System.out.println("Works? " + poly1.equals(strCheck) + "\n");


		//		חיסור פולינומים
		System.out.println("Check #6: Substructing one polynom from another: \n");
		System.out.println("Poly1 = " + poly1 + "\npoly2 = " + poly2);
		Polynom oldPoly2 = new Polynom (poly2.toString());
		;		poly2.substract(poly1);
		System.out.println("Poly2 - Poly1 = " + poly2);
		poly2.add(poly1);
		System.out.println("Works? " + poly2.equals(oldPoly2) + "\n");

		// 		כפל פולינומים
		System.out.println("Check #7.a: Multiplying one polynom with another: \n");
		System.out.println("Poly1 = " + poly1 + "\npoly3 = " + poly3);
		poly1.multiply(poly3);
		System.out.println("Poly1 * Poly2 = " + poly1 + "\n");		


		// כפל פולינום בפולינום שמכיל מונום אחד
		System.out.println("Check #7.b: Multiplying one polynom with another (polynom who contains only 1 monom): \n");
		Polynom poly4 = new Polynom ("4x^2");
		System.out.println("Poly3 = " + poly3 + "\nPoly4 = " + poly4);		
		poly3.multiply(poly4);
		System.out.println("Poly3 * Poly4 = " + poly3 + "\n");
		System.out.println("Poly2 = " + poly2 + "\nPoly3 = " + poly3);
		poly2.multiply(poly3);
		System.out.println("Poly2 * Poly3 = " + poly2 + "\n");


		// כפל של מונום במונום
		System.out.println("Check #7.c: Multiplying one monom with another: \n");
		Monom mon1 = new Monom(2,1);
		System.out.println("Mon1 = " + mon1);					
		System.out.println("Mon1 * Mon1 = " + mon1.multiply(mon1) + "\n");
		Monom mon2 = new Monom(5,3);
		System.out.println("Mon2 = " + mon2 + "Mon1 = " + mon1);
		System.out.println("Mon2 * Mon1 = " + mon2.multiply(mon1) + "\n");



		//   בדיקת קיימות אחד משורשי הפולינום בטווח מסויים
		Polynom_able p1 = new Polynom ("1x^2 + 7x^1 + 6x^0");
		System.out.println("* New polynom p1 is 1x^2 + 7x^1 + 6x^0, his roots are X1,X2 = -1, -6 ");
		System.out.println("Check #8.a: Root of the polynom p1 in range [-3,3] ");
		System.out.println("Y = 0 when x = " + p1.root(-3, 3, Double.MIN_VALUE) + "\n");
		System.out.println("Check #8.b: Root of the polynom p1 in range [-4,-1] ");
		System.out.println("Y = 0 when x = " + p1.root(-4, -1, Double.MIN_VALUE) + "\n");
		System.out.println("Check #8.c: Root of a polynom p1 in range [-6,-1] ");
		System.out.println("Y = 0 when x = " + p1.root(-6, -1, Double.MIN_VALUE) + "\n");
		System.out.println("Check #8.d: Root of a polynom p1 in range [-2,-0.5] ");
		System.out.println("Y = 0 when x = " + p1.root(-2, -0.5, Double.MIN_VALUE) + "\n");
		System.out.println("Check #8.e: Root of a polynom p1 in range [-7,-2]");
		System.out.println("Y = 0 when x = " + p1.root(-7, -2, Double.MIN_VALUE) + "\n");



		// 		בדיקות קיימות אחד משורשי פולינום בטווח מסויים, על פולינום גבוה ממעלה 2.

		Polynom p2 = new Polynom ("1x^5 - 5x^4 + 2x^3 + 14x^2 - 3x^1 - 9x^0");			
		System.out.println("* New polynom p2 is 1x^2 + 7x^1 + 6x^0, his roots are X1,X2,X3 = -1, 1, 3 ");
		System.out.println("Check #9.a: Root of the polynom p2 in range [-3,3] ");
		System.out.println("Y = 0 when x = " + p2.root(0, 3, Double.MIN_VALUE) + "\n");
		System.out.println("Check #9.b: Root of a polynom p2 in range [-2,0] ");
		System.out.println("Y = 0 when x = " + p2.root(-2, 0, Double.MIN_VALUE) + "\n");
		System.out.println("Check #9.c: Root of a polynom p2 in range [2,5]");
		System.out.println("Y = 0 when x = " + p2.root(0, 5, Double.MIN_VALUE) + "\n");
		System.out.println("Check #9.d: Root of a polynom p2 in range [1.1,3.2]");
		System.out.println("Y = 0 when x = " + p2.root(1.1, 3, Double.MIN_VALUE) + "\n");



	}

}
