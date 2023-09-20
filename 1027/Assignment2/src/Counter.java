/**
 * Counter calculate the number of points from a Cribbage hand.
 * @author Sangjae Lee
 * @version 2.0
 * Due Date : February 27 2023
 * Last Edited : February 27 2023
 * Professor : Professor Sarlo
 */

public class Counter 
{
	/**
	 * the powerset of cards in hand
	 * */
	PowerSet<Card> cardps;
	/** 
	 * the starter of the Cribbage hand.
	 */
	Card starter;
	
	/**
	 * Constructor creates a counter object with a list of hands and a starter
	 * @param hand The hand player has
	 * @param starter The starter the player has
	 */
	public Counter(Card[] hand, Card starter)
	{
		cardps = new PowerSet<Card>(hand);
		this.starter = starter;
	}
	
	/**
	 * method to check if the set of card has a run larger or equal to 3
	 * provided in the assignment
	 * @param set of cards that is in hand
	 * @return boolean value for whether the hand has a run or not
	 */
	private boolean isRun (Set<Card> set) 
	{
		int n = set.getLength();
		
		if (n <= 2) 
		{
			return false; // Run must be at least 3 in length.
		}
		
		int[] rankArr = new int[13];
		
		for (int i = 0; i < 13; i++) 
		{
			rankArr[i] = 0; // Ensure the default values are all 0.
		}
		
		for (int i = 0; i < n; i++) 
		{
			rankArr[set.getElement(i).getRunRank()-1] += 1;
		}

		// Now search in the array for a sequence of n consecutive 1's.
		int streak = 0;
		int maxStreak = 0;
		
		for (int i = 0; i < 13; i++) 
		{
			if (rankArr[i] == 1) 
			{
				streak++;
				if (streak > maxStreak) 
				{
					maxStreak = streak;
				}
		} 
			else 
			{
				streak = 0;
			}
		}
		
		if (maxStreak == n) 
		{ 
			return true;
		} 
		else 
		{	
			return false;
		}
	}
	
	/**
	 * method to check the number points earned from pairs
	 * @return number of points earned with pairs
	 */
	private int Pair() // does not work
	{
		// the return value;
		int pairScore = 0;
		// comparison card value to check for pairs
		int comparison;
		
		for (int i = 1; i < cardps.getLength(); i++)
		{
			// cardRunRank to compare the other cards
			comparison = cardps.getSet(i).getElement(0).getRunRank();
			for (int j = 1; j < cardps.getSet(i).getLength(); j++)
			{
				// get run rank of the current card
				int current = cardps.getSet(i).getElement(j).getRunRank();
				
				// check current and the comparison
				if (comparison == current && cardps.getSet(i).getLength() == 2)
				{
					pairScore += 2;
				}
			}
		}
		
		return pairScore;
	}
	
	/**
	 * method to check the number of points earned with runs
	 * @return number of points earned with runs
	 */
	private int Runs()
	{
		// stores maximum number of run
		int maxStreak = 0;
		// stores current maximum number of run
		int currMax = 0;
		// the final score to be returned
		int returnVal = 0;
		
		for (int i = 0; i < cardps.getLength(); i++)
		{
			if (isRun(cardps.getSet(i)) == true) 
			{
				// reused the isRun code. 
				int n = cardps.getSet(i).getLength();
				int[] rankArr = new int[13];
				
				for (int j = 0; j < 13; j++) 
				{
					rankArr[j] = 0; 
				}
				
				for (int j = 0; j < n; j++) 
				{
					rankArr[cardps.getSet(i).getElement(j).getRunRank()-1] += 1;
				}

				int streak = 0;
				currMax = 3;
				
				for (int j = 0; j < 13; j++)
				{
					if (rankArr[j] == 1) 
					{
						streak++;
						if (streak > currMax) 
						{
							currMax = streak;
						}
					} 
					else 
					{
						streak = 0;
					}
				}
				
				// compare current max and previous max to find the score
				if (currMax > maxStreak)
				{
					maxStreak = currMax;
					returnVal = 0;
				}
				if (currMax == maxStreak)
				{
					returnVal += maxStreak;
				}
			}
		}
		
		return returnVal;
	}
	
	/**
	 * method to check the number of points earned with fifteens
	 * @return number of points earned with fifteens
	 */
	private int Fifteen() // does not work
	{
		// the sum of the fifteen rank of the power set
		int sumScore = 0;
		// the score to be returned
		int returnScore = 0;
		
		for (int i = 0; i < cardps.getLength(); i++)
		{
			for (int j = 0; j < cardps.getSet(i).getLength(); j++)
			{
				sumScore += cardps.getSet(i).getElement(j).getFifteenRank();
			}
			
			if (sumScore == 15)
			{
				returnScore += 2;
			}
			sumScore = 0;
		}
		
		return returnScore;
	}
	
	/**
	 * method to check the number of points earned with flush
	 * @return number of points earned with flush
	 */
	private int Flush()
	{
		// array to store the number of each suits in the hand
		int counter[] = new int [4];
		// stores the max number of suit in the array
		int max = 0;
		
		for (int i = 1; i < cardps.getLength(); i++)
		{
			if (cardps.getSet(i).getLength() > 3) 
			{
				// increase the number in counter array depending on the suit
				for (int j = 0; j < cardps.getSet(i).getLength(); j++)
				{
					if (cardps.getSet(i).getElement(j).getSuit() == "S")
					{
						counter[0]++;
					}
					else if (cardps.getSet(i).getElement(j).getSuit() == "H")
					{
						counter[1]++;
					}
					else if (cardps.getSet(i).getElement(j).getSuit() == "C")
					{
						counter[2]++;
					}
					else if (cardps.getSet(i).getElement(j).getSuit() == "D")
					{
						counter[3]++;
					}
				}
				
				// set max to the number of spade suits in the hand
				max = counter[0];
				// find the max number of continuous suits
				for (int j = 0; j < counter.length; j++)
				{
					if (max < counter[j])
					{
						max = counter[j];
					}
				}
				
				// reset the array 
				for (int j = 0; j < counter.length; j++)
				{
					counter[j]= 0;
				} 
			}
		}
		
		// if max is 4 or 5 return max
		if (max == 4 || max == 5)
		{
			return max;
		}
		else 
		{
			return 0;
		}		
	}
	
	/**
	 * method to check the number of points earned with his knobs
	 * @return number of points earned with his knobs (1 or 0)
	 */
	private int hisKnobs()
	{
		// find the suit of the starter
		String comparison = starter.getSuit();
		
		// find if the the hand has a jack and it matches the suit of the starter
		for (int i = 1; i < cardps.getLength(); i++)
		{
			for (int j = 0; j < cardps.getSet(i).getLength(); j++)
			{
				if (comparison == cardps.getSet(i).getElement(j).getSuit() && cardps.getSet(i).getElement(j).getRunRank() == 11)
				{
					return 1;
				}
			}
		}
		
		return 0;
	}
	
	/**
	 * method to returns the total points earned
	 * @return the total points earned
	 */
	public int countPoints()
	{
		return (Pair() + Runs() + Fifteen() + Flush() + hisKnobs());
	}
}
