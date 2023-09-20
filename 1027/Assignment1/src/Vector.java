/**
 * This Class represents a Vector.
 * @author Robin (Sangjae) Lee
 * @version 1.0
 * Due Date : February 8 2023
 * Last Edited : January 31 2023
 * Professor : Professor Sarlo
 */

public class Vector extends Matrix
{
	/**
	 * A constructor that makes a vector
	 * @param c an int value that represents the number of columns in the vector 
	 */
	public Vector (int c)
	{
		super(1,c);
	}
	
	/**
	 * A constructor that makes a vector
	 * @param c An int value that represents the number of columns in the vector 
	 * @param linArr A double array that represents the data inside the vector
	 */
	public Vector (int c, double[] linArr)
	{
		super(1, c, linArr);
	}
	
	/**
	 * A method that gets the element in a certain position of the vector
	 * @param c An int value that represents the columns position of the vector 
	 * @return A double value that is located on the column position provided
	 */
	public double getElement (int c) 
	{
		return super.getData()[0][c];
	}
}