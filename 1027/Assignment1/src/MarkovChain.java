/**
 * This Class represents a Markov Chain.
 * @author Robin (Sangjae) Lee
 * @version 2.0
 * Due Date : February 8 2023
 * Last Edited : February 7 2023
 * Professor : Professor Sarlo
 */

public class MarkovChain 
{
	//Instance variable that represents the state vector of the Markov chain
	private Vector stateVector;
	// Instance variable that represents the transition matrix of the Markov chain
	private Matrix transitionMatrix;
	
	/**
	 * Constructor creates a Markov chain using the state vector and transition matrix
	 * @param sVector The state vector of the Markov chain
	 * @param tMatrix Transition matrix of the Markov chain
	 */
	public MarkovChain (Vector sVector, Matrix tMatrix)
	{
		stateVector = sVector;
		transitionMatrix = tMatrix;
	}
	
	/**
	 * method to check if the transition matrix and the state vector is valid for a Markov chain
	 * @return true if it is valid, false if it is not valid
	 */
	public boolean isValid ()
	{
		// local double variable  output
		double temp = 0;
		
		// check square and match the number of columns in the state vector.
		if (transitionMatrix.getNumCols() == transitionMatrix.getNumRows() && transitionMatrix.getNumCols() == stateVector.getNumCols())
		{
			for (int i = 0; i < stateVector.getNumCols(); i++)
			{
				temp = temp + stateVector.getData()[0][i];
			}
			
			// check if the sum of values in the state vector must equal 1.0
			if (temp < 1.1 && temp > 0.9) 
			{
				for (int i = 0; i < transitionMatrix.getNumRows(); i++)
				{
					temp = 0;
					
					for (int j = 0; j < transitionMatrix.getNumCols(); j++)
					{
						temp += transitionMatrix.getElement(i, j);
					}
					
					// check the sum of each row of the transition matrix equal 1.0
					if (temp >= 1.1 || temp <= 0.9)
					{
						return false;
					}
				}
				
				return true;
			}
			else
			{
				return false;
			}
		}
		else 
		{
			return false;
		}
	}
	
	/**
	 * method to compute a probability matrix using the Markov chain provided
	 * @param numSteps number of steps the Markov chain is taking
	 * @return returns the probability matrix of the Markov chain, null if the Markov chain is invalid
	 */
	public Matrix computeProbabilityMatrix (int numSteps)
	{
		// local matrix object for matrix output	 
		Matrix resultMatrix = transitionMatrix;
		
		// check if the matrixes are valid
		if (isValid() == true) 
		{
			// create the resulting vector 
			for (int i = 0; i < numSteps - 1; i++) 
			{
				resultMatrix = resultMatrix.multiply (this.transitionMatrix);
			}
			
			resultMatrix = this.stateVector.multiply(resultMatrix);	
			return resultMatrix;
		}
		else 
		{
			return null;
		}
	}
}
