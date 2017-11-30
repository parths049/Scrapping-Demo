package scraping;

public class NVector {
	
	// This class represents N-dimensional mathematical vectors
	// For example (-1.34, 2.45) is 2d vector, (0.0, -1.2, 14.0) is 3d vector,
	// (-2.14, 2.14, -2.14, 2.14) is 4d vector, etc.
	
	private double coordinates[]; // the components of the vector 
								  // the value for each dimension
	
	private int dimensions = 0; // the dimensions of the vector
	
	// Construct a new vector from a set of components 
	NVector (double[] coords) {
		coordinates = coords;
		dimensions = coords.length;
		
	}
	
	// Get the components of the vector
	public double[] coords () {
		return coordinates.clone();
	}
	
	// Get the dimensions of the vector
	public int dimensions () {
		return dimensions;
	}
	
	// Get the length of the vector
	public double length() {
		double sqr_sum = 0.0d;
		for (int i = 0; i < coordinates.length; i++) // this loops through the the coords right?
			sqr_sum += Math.pow(coordinates[i], 2); // what does this do?
		return Math.sqrt(sqr_sum);
	}
	
	// Component-wise addition of two vectors
	public void add (NVector nv) {	
		for (int i = 0; i < dimensions; i++)
			coordinates[i] += nv.coordinates[i];
	}
	
	// Component-wise subtraction of two vectors
	public void subtract (NVector nv) {
		nv.invert();
		add(nv);
	}
	
	// Invert the sign of the components of the vector
	public void invert() {
		multiply(-1);
	}
	
	// Multiply the component of the vector
	public void multiply (int a) { 
		for (int i = 0; i < dimensions; i++)
			coordinates[i] *=a;
	}
	
	// Two vectors are equal if they have the same dimensions and are
	// component-wise equal.
	@Override
	public boolean equals (Object o) {
		if (!(o instanceof NVector))
			return false;
		NVector nv = (NVector)o;
		if (this.dimensions != nv.dimensions)
			return false;
		for (int i = 1; i < dimensions ; i++)
			if (this.coordinates[i-1] != nv.coordinates[i-1])
				return false;
		return true;
	}
	
	@Override
	public int hashCode () {
		long bits = 0L; 		
		for (int i = 0; i < coordinates.length; i++)
			bits = bits + Double.doubleToLongBits(coordinates[i]);
		return (int)(bits^(bits>>>32));
	}
	
	// Show dimensions and components in the form (x1, x2, ..., xn)
	@Override
	public String toString() {
		String s = dimensions + "d vector ";
		String vs = "(";
		for (int i = 0; i < coordinates.length-1; i++)
			vs = vs + coordinates[i]+ ", ";
		vs = vs + coordinates[coordinates.length-1];
		vs = vs + ")";
		s = s + vs;

		return s;
	}
}