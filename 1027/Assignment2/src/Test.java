
public class Test {
	private int Runs()
	{
		int maxStreak = 0;
		int currMax = 0;
		int returnVal = 0;
		
		for (int i = 0; i < cardps.getLength(); i++)
		{
			if (isRun(cardps.getSet(i)) == true) 
			{
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
}
