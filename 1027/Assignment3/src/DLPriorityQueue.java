/**
 * DLPriorityQueue creates a priority queue
 * @author Sangjae Lee
 * @version 1.0
 * Due Date : March 21  2023
 * Last Edited : March 16 2023
 * Professor : Professor Sarlo
 */

public class DLPriorityQueue<T> implements PriorityQueueADT<T>
{
	/**
	 *	Instance doubly linked node for front of the list
	 */
	private DLinkedNode<T> front;
	/**
	 *	Instance doubly linked node for rear of the list
	 */
	private DLinkedNode<T> rear;
	/**
	 *	Instance int variable to count the length of the queue
	 */
	private int count;
	
	/**
	 *	Constructor for a priority queue
	 */
	public DLPriorityQueue() 
	{
		front = null; 
		rear = null;
		count = 0;
	}
	
	/**
	 * 	method to add a node into the priority queue
	 * 
	 *  @param data the data to be stored in the doubly linked list
	 *  @param priority the double value for the priority of the node in the 
	 */
	public void add(T data, double priority) 
	{
		/**
		 *	 node that will be included into the queue
		 */
		DLinkedNode<T> node = new DLinkedNode<T>(data, priority);
		/**
		 *	current to indicate the current node being checked	 
		 */
		DLinkedNode<T> current = front;
		/**
		 *	previous to indicate the previous node of the node being checked	 
		 */
		DLinkedNode<T> previous = null;
		
		/**
		 *	check if the queue is empty
		 *	else check every node to locate the location and add in the node
		 */
		if (current == null)
		{
			front = node;
			rear = node;
		}
		else 
		{
			while (current != null && current.getPriority() < node.getPriority())
			{
				previous = current;
				current = current.getNext();
			}
			
			if (current == null)
			{
				previous.setNext(node);
				node.setPrev(previous);
				rear = node;
			}
			else if (previous == null)
			{
				node.setNext(current);
				current.setPrev(node);
				front = node;
			}
			else
			{
				previous.setNext(node);
				node.setPrev(previous);
				node.setNext(current);
				current.setPrev(node);
			}
		}
		count++;
	}
	
	/**
	 * method to update the priority and reallocate the node in a appropriate place
	 * first remove the item from the list and add it back with the new priority
	 * 
	 * @param data the data of the node to be updated
	 * @param newPriority the new priority to be updated
	 * @throws InvalidElementException when the item is not in the queue
	 */
	public void updatePriority(T data, double newPriority) throws InvalidElementException 
	{
		/**
		 *	current to indicate the current node being checked	 
		 */
		DLinkedNode<T> current = front;
		/**
		 *	previous to indicate the previous node of the node being checked	 
		 */
		DLinkedNode<T> previous = null;
		
		/**
		 * while loop to find the appropriate node and update priority
		 */
		while(current != null)
		{
			if (current.getDataItem().equals(data))
			{
				current.setPriority(newPriority);
				break;
			}
			previous = current;
			current = current.getNext();
		}
		
		/**
		 * throws exception if the item is not found
		 */
		if (current == null)
		{
			throw new InvalidElementException("dataItem is not in the priority queue");
		}
		/**
		 * checks the location and removes the node from the queue
		 */
		if (previous == null)
		{
			front = current.getNext();
			
			if (front != null)
			{
				current.getNext().setPrev(null);
			}
			current.setNext(null);
		}
		if(current.getNext() == null)
		{
			rear = current.getPrev();
			
			if (current.getPrev() != null)
			{
				current.getPrev().setNext(null);
			}
			current.setPrev(null);
		}
		if (previous != null && current.getNext() != null)
		{
			previous.setNext(current.getNext());
			current.getNext().setPrev(current.getPrev());
		}
		count--;
		/**
		 * add a new node with the same data but with updated priority
		 */
		add(current.getDataItem(), current.getPriority());
	}
	
	/**
	 * method to remove the node with smallest priority and return the data of the node
	 * 
	 * @throws EmptyPriorityQueueException when the queue is empty
	 * @return the data stored in the node with the smallest priority
	 */
	public T removeMin() throws EmptyPriorityQueueException 
	{
		/**
		 * if the queue is empty, throw an exception
		 */
		if (front == null) 
		{
			throw new EmptyPriorityQueueException ("empty queue");
		}
		
		/**
		 * store the data from the front
		 */
		T result = front.getDataItem();
		/**
		 * remove the front node
		 */
		front = front.getNext();
		
		/**
		 * check if the queue is empty after the remove
		 * if not organize the queue and return the value
		 */
		if (front == null) 
		{
			rear = null;
		}
		else
		{
			if (front.getPrev() != null)
			{
				front.getPrev().setNext(null);
				front.setPrev(null);	
			}
			else
			{
				front = null;
				rear = null;
			}
		}
		count--;
		return result;	
	}
	
	/**
	 * method to check if the queue is empty
	 */
	public boolean isEmpty() 
	{
		if (front == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * method to check the size of the queue
	 * 
	 * @return the count value of the queue
	 */
	public int size() 
	{
		return count;
	}
	
	/**
	 * method to return the string value of the queue
	 * 
	 * @return the string value of the queue
	 */
	public String toString() 
	{
		DLinkedNode<T> current = front;
		String returnVal = "";
		while(current != null)
		{
			returnVal += current.getDataItem().toString();
			current = current.getNext();
		}
		
		return returnVal;
	}
	
	/**
	 * method to return the rear of the queue
	 * 
	 * @return the rear of the queue
	 */
	public DLinkedNode<T> getRear()
	{
		return rear;
	}
}
