package scraping;

public class Polynomial {

	// This class represents polynomials of the form
	// c_n*x^n + c_(n-1)*x^(n-1)+ ... + c_2*x^2 + c_1*x + c_0
	//
	// where c_0, c_1, c_2, ... c_(n-1), c_n are the coefficients
	// of the polynomial kept in an array (i.e. coefficients)
	//
	// and the degree is the highest power of x 
	// where c_i is not zero kept in an int (i.e. degree)
	//
	// Note: the array of coefficients may be larger than the degree
	//		 
	
	private int[] coefficients;
	private int degree = 0;
	
	// Create a polynomial based on the array of coefficients.
	//
	Polynomial (int[] c) {
		
		if (c.length == 0) // no factors provided
			throw new ArrayIndexOutOfBoundsException();
		
		// Create the array of the coefficients
		coefficients = new int[c.length];
		for (int i = 0; i < c.length; i++)
			coefficients[i] = c[i];
		
		// Determine the degree of the polynomial
		for (degree = coefficients.length -1; degree > 0; degree--) { 
			if (coefficients[degree] != 0)
				break;

		}
	}
	
	// Return the degree of the polynomial.
	//
	public int getDegree() {
		
		return degree;
		
	}
	
	// Return the array of the coefficients
	//
	public int[] getCoefficients() {
		
		return coefficients;
	}
	
	// Add p to the current polynomial 
	// return the degree of the resulting polynomial
	//
	public int add(Polynomial p) {
		
		int[] old_c = null;
		assert (old_c = coefficients.clone()).length > degree;
		
		if (coefficients.length > p.degree) {
			//the result fits in the current array of coefficients
			
			for (int i = 0; i <= p.degree; i++)
				coefficients[i] += p.coefficients[i];			
		} else {
			// the result doen't fit in the current array of coefficients
			
			int[] newCoefficients = new int[p.degree+1];
			
			// First put the coefficients that need to be pairwise added
			for (int i = 0; i < coefficients.length; i++)
				newCoefficients[i] = coefficients[i] + p.coefficients[i];
						
			// Then put the extra coefficients
			for (int i = coefficients.length; i <= p.degree; i++)
				newCoefficients[i] = p.coefficients[i];
			
			coefficients = newCoefficients;
		}
		
		// Determine the degree of the result
		if (degree == p.degree) { 
			// degree depends of the result of the pairwise addition
			
			// Find the new degree
			for (; degree > 0; degree--)
				if (coefficients[degree]!=0)
					break;
			
		} else // the higher degree is the new degree 
			
			if (p.degree > degree)			
				degree = p.degree;
			
		assert checkPost(degree, old_c, coefficients, p): this.toString() + " " + p.toString();
		return degree;
	}
	
	private boolean checkPost (int d, int[] old_c, int[] c, Polynomial p) {
		
		if (old_c.length <= p.coefficients.length) {
			for (int i = 0; i < old_c.length; i++)
				if (c[i] != old_c[i]+p.coefficients[i])
					return false;
			for (int i = old_c.length; i < p.coefficients.length; i++)
				if (c[i] != p.coefficients[i])
					return false;
		} else {
			for (int i = 0; i < p.coefficients.length; i++)
				if (c[i] != old_c[i]+p.coefficients[i])
					return false;
			for (int i = p.coefficients.length; i < old_c.length; i++)
				if (c[i] != old_c[i])
					return false;
		}
		
		for (int i = c.length -1; i > d; i--)
			if (c[i] != 0)
				return false;
		
		if (c[d] == 0)
			return false;
		
		return true;
	}
	
	// Subtract p from the current polynomial
	// return the degree of the resulting polynomial
	//
	public int subtract(Polynomial p) {
		// Subtraction is the addition of the negative		
		
		int[] negCoefficients = new int[p.degree+1];
		for (int i = 0; i <= p.degree; i++)
			negCoefficients[i] = 0 - p.coefficients[i];
		Polynomial negP = new Polynomial(negCoefficients);
		
		return add(negP);
	}
	
	// Two polynomials are equal
	// if they have the have the same degree 
	// and all corresponding coefficients are equal 
	//
	@Override
	public boolean equals(Object o) {
		
		if (!(o instanceof Polynomial))
			return false;
		
		// o should be a polynomial
		Polynomial p = (Polynomial) o;
		
		// degrees must match for equality
		if (degree != p.degree)
			return false;
		
		// corresponding coefficients must match for equality
		for (int i = 0; i < degree; i++)
			if (coefficients[i] != p.coefficients[i])
				return false;
		
		return true;
	}
	
	// The hash code is the sum of the products of
	// the coefficients and their corresponding power of x
	//
	@Override
	public int hashCode() {
		int sum = 0;
		for (int i = 0; i <= degree; i++)
			sum += i * coefficients[i];
		
		return sum;
	}
	
	// Get the polynomial as a string of the form
	// c_nx^n+c_(n-1)x^(n-1)+ ... + c_1x^1+c_0
	// but only show no zero elements
	//
	@Override
	public String toString() {
		
		String s = "";
		
		// First show the constant, if there is any
		if (coefficients[0] != 0)
		  s = coefficients[0]+s;
		
		// Then add the each power of x with the corresponding factor 
		for (int i = 1; i <= degree; i++)
			if (coefficients[i]!=0)
				if (!s.equals(""))
					s = coefficients[i] + "x^" + i + "+" + s;
				else
					s = coefficients[i] + "x^" + i;
		
		
		if (s.equals("")) // Only the constant 0
			s = "0";
		
		return s;
	}
}
