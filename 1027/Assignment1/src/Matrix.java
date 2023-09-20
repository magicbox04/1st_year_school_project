/**
 * This Class represents a Matrix.
 * @author Robin (Sangjae) Lee
 * @version 2.0
 * Due Date : February 8 2023
 * Last Edited : February 7 2023
 * Professor : Professor Sarlo
 */

public class Matrix 
{	
	// Instance int variable that represents the number of rows in a matrix
	private int numRows;
	// Instance int variable that represents the number of columns in a matrix
	private int numCols;
	// Instance 2d double array variable that represents the data inside the matrix
	private double[][] data;
	
	/**
	 * Constructor creates an empty Matrix 
	 * @param r The number of rows in the matrix
	 * @param c The number of columns in the matrix
	 */
	public Matrix (int r, int c) 
	{
		numRows = r;
		numCols = c;
		data = new double [numRows][numCols]; 
	}
	
	/**
	 * Constructor creates a Matrix with information
	 * @param r The number of rows in the matrix
	 * @param c The number of columns in the matrix
	 * @param linArr The 1d double array with the data of the matrix
	 */
	public Matrix (int r, int c, double[] linArr)
	{
		numRows = r;
		numCols = c;
		data = new double [numRows][numCols]; 
		int i = 0;
		
        for (int j = 0; j < numRows; j++) 
        {
        	for (int k = 0; k < numCols; k++)
        	{
        		data[j][k] = linArr[i++];
        	}
        }
            
	}
	
	/**
	 * Getter for the rows of a matrix 
	 * @return numRows number of rows of the matrix in int
	 */
	public int getNumRows()
	{
		return numRows;
	}
	
	/**
	 * Getter for the columns of a matrix 
	 * @return numCols number of columns of the matrix in int
	 */
	public int getNumCols()
	{
		return numCols;
	}
	
	/**
	 * Getter for the data of a matrix 
	 * @return data number of data of the matrix in 2d double array
	 */
	public double[][] getData()
	{
		return data;
	}
	
	/**
	 * Getter for the element in a given position of a matrix 
	 * @param r The row position of the matrix
	 * @param c The column position of the matrix
	 * @return data[r][c] a double value at a specific position in the matrix
	 */
	public double getElement(int r, int c)
	{
		return data[r][c];
	}
	
	/**
	 * Setter that assigns a value on a position
	 * @param r The row position of the Matrix
	 * @param c The column position of the Matrix
	 * @param value The double value that is going to be assigned to a position
	 */
	public void setElement(int r, int c, double value)
	{
		data[r][c] = value;
	}
	
	/**
	 * Method that transposes a matrix
	 */
	public void transpose() 
	{
		// local int variable to swap numCols and numRows	 
		int temp;
		// local double array to store transposed data
		double[][] transData = new double [numCols][numRows];
        
		// create transpose matrix
        for(int i = 0; i < this.numRows; i++) 
        {
            for (int j = 0; j < this.numCols; j++) 
            {
            	transData[j][i] = this.data[i][j];
            }
        }
        
        this.data = transData;
        temp = this.numCols;
        this.numCols = this.numRows;
        this.numRows = temp;  		
    }
	
	/**
	 * Method that multiplies a matrix with a scaler value
	 * @param scalar a double value that the matrix gets multiplied by
	 * @return multiData a matrix that has been multiplied
	 */
	public Matrix multiply (double scalar)
	{
		
		// local Matrix object for matrix output
		Matrix multiData = new Matrix(this.numRows,this.numCols);

		// multiply vector to the matrix
		for(int i = 0; i < this.numCols; i++) 
        {
            for (int j = 0; j < this.numRows; j++) 
            {
            	multiData.data[j][i] = this.data[j][i] * scalar;
            }
        }
		
		return multiData;
	}
	
	/**
	 * Method that multiplies a matrix with another matrix
	 * @param other Another matrix that is getting multiplied to the first matrix
	 * @return multiMatrix the result matrix of the multiplication 
	 */
	public Matrix multiply (Matrix other)
	{
		// check if the collumn number does not equal to the row number of the other
		if (this.numCols != other.numRows) 
		{
			return null;
		}
		else 
		{
			// local Matrix object for matrix output
			Matrix multiMatrix = new Matrix(this.numRows,other.numCols);
			
			// multiply matrix with the other matrix
			for (int i = 0; i < this.numRows; i++) 
			{
	            for (int j = 0; j < other.numCols; j++) 
	            {
	                for (int k = 0; k < other.numRows; k++) 
	                {
	                    multiMatrix.data[i][j] += this.data[i][k] * other.data[k][j];
	                }
	            }
	        }
			
		return multiMatrix;
		}
	}
	
	/**
	 * Method that prints the matrix as a string
	 * @return returnVal A string representation of the matrix
	 */
	public String toString()
	{
		
		// local string variable for string output
		String returnVal = "";
		
		// print empty matrix if the matrix is empty
		if (this.data.length == 0)
		{
			return "Empty matrix";
		}
		else
		{
			// print the matrix in string
			for(int i = 0; i < this.numRows; i++) 
	        {
	            for (int j = 0; j < this.numCols; j++) 
	            {
	            		returnVal = String.format(returnVal + "%8.3f", this.data[i][j]);
	            }
	            
	            returnVal = String.format(returnVal + "\n");
	        }
			
			return returnVal;	
		}
	}
}