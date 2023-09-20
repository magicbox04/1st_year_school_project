/**
 * Powerset represents a Power Set from a given set.
 * @author Sangjae Lee
 * @version 2.0
 * Due Date : February 27 2023
 * Last Edited : February 27 2023
 * Professor : Professor Sarlo
 */

// import math for the power method
import java.lang.Math;

public class PowerSet<T>
{
	/**
	 * instance set array for the power set
	 */
	Set<T>[] set;
	
	@SuppressWarnings("unchecked")
	
	/**
	 * Constructor creates a power set
	 * @param element T array for the list of elements to create a power set with
	 */
	public PowerSet(T[] elements)
	{
		// possible combinations
		int powLength = (int) Math.pow(2, elements.length);
		// creat a set with length of all the possible combinations
		set = new Set[powLength];
		// binary string stores
		String binary = "";
		
		for (int i = 0; i < powLength; i++)
		{
			for (int j = 0; j < elements.length; j++)
			{
				if (j == elements.length - Integer.toBinaryString(i).length())
				{
					binary += Integer.toBinaryString(i);
					break;
				}
				else
				{
					binary += 0;
				}
			}
			
			// create a set to add the elements from the binary created
			set[i] = new Set<T>();
			// when there is 1 in the binary add the element at the position
			for (int k = 0; k < binary.length(); k++)
			{
				if (binary.charAt(k) == '1')
				{
					set[i].add(elements[k]);
				}
			}
			
			// reset the binary
			binary = "";
		}
	}
	
	/**
	 * method to get the length of the power set
	 * @return the length of the set
	 */
	public int getLength()
	{
		return set.length;
	}
	
	/**
	 * method to return the set stored at index i of the array
	 * @param i the index i of the set that will be returned
	 * @return the set stored at index i of the array
	 */
	public Set<T> getSet(int i)
	{
		return set[i];
	}	
}



