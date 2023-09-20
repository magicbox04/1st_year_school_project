/**
 * FindShortestPath is an algorithm to find the shortest path
 * @author Sangjae Lee
 * @version 2.0
 * Due Date : March 21  2023
 * Last Edited : March 20 2023
 * Professor : Professor Sarlo
 */
import java.io.FileNotFoundException;
import java.io.IOException;

public class FindShortestPath 
{
	/**
	 * main method to run the algorithm
	 * 
	 * @param args text supplied on the command line
	 * @throws InvalidDungeonCharacterException
	 * @throws FileNotFoundException 
	 * @throws IOException
	 * @throws Exception 
	 */
	public static <T> void main (String[] args) throws InvalidDungeonCharacterException, FileNotFoundException, IOException
	{
		try
		{
			/**
			 * check exception for no input file
			 */
			if (args.length < 1) 
			{
				throw new Exception("No input file specified");
			}
			/**
			 * set the appropriate variable
			 */
			String dungeonFileName = args[0];
			Dungeon dungeon =  new Dungeon(dungeonFileName);
			DLPriorityQueue<Hexagon> queue = new DLPriorityQueue<Hexagon>();
			
			/**
			 * add a first hexagon of the dungeon to the queue
			 */
			queue.add(dungeon.getStart(), 0);
			dungeon.getStart().markEnqueued();
			Hexagon exit = null;
		
			/**
			 * while loop to check the hexagon until it finds an exit or until there is no possible route 
			 */
			while (queue.isEmpty() == false && exit == null)
			{
				Hexagon deque = queue.removeMin();
				deque.markDequeued();
				boolean isDragon = false;
				int D = 0;
				
				if (deque.isExit() == true)
				{
					exit = deque;
					break;
				}
				/**
				 * check for dragons
				 */
				for (int i = 0; i < 6; i++)
				{
					if (deque.getNeighbour(i)!= null)
					{
						if (deque.getNeighbour(i).isDragon() == true)
						{
							isDragon = true;
						}
					}
				}
				
				if (deque.isDragon() == false && isDragon == false)
				{
					/**
					 * check the neighbors for an optimum pass 
					 */
					for (int i = 0; i < 6; i++)
					{
						if (deque.getNeighbour(i) != null)
						{
							if (deque.getNeighbour(i).isWall() == false && deque.getNeighbour(i).isMarkedDequeued() == false)
							{
								Hexagon neighbour = deque.getNeighbour(i);
								D = 1 + deque.getDistanceToStart();
								
								if (neighbour.getDistanceToStart() > D)
								{
									neighbour.setDistanceToStart(D);
									neighbour.setPredecessor(deque);
									
									if (neighbour.isMarkedEnqueued() == true )
									{
										queue.updatePriority(neighbour, neighbour.getDistanceToStart() + neighbour.getDistanceToExit(dungeon));
									}
									else
									{
										queue.add(neighbour, neighbour.getDistanceToStart() + neighbour.getDistanceToExit(dungeon));
										neighbour.markEnqueued();	
									}
								}
							}
						}
					}
				}
			}
			/**
			 * Print the length of the path
			 */
			if (exit == null)
			{
				System.out.println("No path found");
			}
			else
			{
				System.out.println("Path of length " + (exit.getDistanceToStart() + 1) + " found");
			}
		}
		/**
		 * catch the exceptions found
		 */
		catch (InvalidElementException e)
		{
			System.out.println(e.getMessage());
		}
		catch (EmptyPriorityQueueException e)
		{
			System.out.println(e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		} catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
}

