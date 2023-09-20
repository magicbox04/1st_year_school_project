
public class test 
{
	
	public static void main(String[] args) 
	{

		

		try {
			Vector sv = new Vector(3, new double[] {0.3, 0.5, 0.2});
			Matrix tm = new Matrix(3, 3, new double[] {0.5, 0.0, 0.5, 0.2, 0.7, 0.1, 0.1, 0.4, 0.5});
			MarkovChain markov = new MarkovChain(sv, tm);
			
			Matrix m1 = markov.computeProbabilityMatrix(1);
			Matrix m2 = markov.computeProbabilityMatrix(2);
			Matrix m3 = markov.computeProbabilityMatrix(3);
			
			double[][] r1 = {{0.270, 0.430, 0.300}};
			double[][] r2 = {{0.251, 0.421, 0.328}};
			double[][] r3 = {{0.243, 0.426, 0.332}};

			if (equalArrays(m1.getData(), r1) && equalArrays(m2.getData(), r2) && equalArrays(m3.getData(), r3)) 
			{
				System.out.println("Test 3 Passed");
			} else {
				System.out.println("Test 3 Failed");
			}
		} catch (Exception e) {
			System.out.println("Test 3 Failed");
		}
	}

	
	
	private static boolean equals (double a, double b) {
		// Compares two doubles to see if they equal, taking rounding errors into consideration.
		return Math.abs(a-b) < 0.001;
	}
	
	private static boolean equalArrays (double[][] A, double[][] B) {
		// Compares two arrays of doubles to see if they are identical.
		if (A.length != B.length || A[0].length != B[0].length) {
			// If the dimensions are different, immediately return false.
			return false;
		}
		// The dimensions are the same, so now loop through all the elements and check if any is different between the two arrays.
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				if (!equals(A[i][j],B[i][j])) {
					System.out.println("not equal  " + A[i][j] + " != " + B[i][j]);
					return false;
				}
			}
		}
		return true;
	}
}
