/**
 * DLinkedNode creates a node for the doubly linked list
 * @author Sangjae Lee
 * @version 1.0
 * Due Date : March 21  2023
 * Last Edited : March 16 2023
 * Professor : Professor Sarlo
 */

public class DLinkedNode <T>
{
	private T dataItem;
	private double priority;
	private DLinkedNode<T> next;
	private DLinkedNode<T> prev;
	
	/**
	 * constructor for the node of the doubly linked list
	 * 
	 * @param data the data to be stored in the node
	 * @param prio the priority of the node
	 */
	public DLinkedNode (T data, double prio)
	{
		dataItem = data;
		priority = prio;
	}
	
	/**
	 * constructor for the node of the doubly linked list
	 */
	public DLinkedNode() 
	{
		dataItem = null;
		priority = 0;
	}
	
	/**
	 * getter for the priority of the node
	 * 
	 * @return the priority of the node
	 */
	public double getPriority()
	{
		return priority;
	}
	
	/**
	 * getter for the data of the node
	 * 
	 * @return the data of the node
	 */
	public T getDataItem()
	{
		return dataItem;
	}
	
	/**
	 * getter for next node
	 * 
	 * @return the next node of the node
	 */
	public DLinkedNode <T> getNext()
	{
		return next;
	}
	
	/**
	 * getter for previous node
	 * 
	 * @return the previous node of the node
	 */
	public DLinkedNode <T> getPrev()
	{
		return prev;
	}
	
	/**
	 * setter for the data of the node
	 */
	public void setDataItem(T Data)
	{
		dataItem = Data;
	}
	
	/**
	 * setter for priority of the node
	 */
	public void setPriority(double prior)
	{
		priority = prior;
	}

	/**
	 * setter for next node
	 */
	public void setNext(DLinkedNode <T> node)
	{
		next = node;
	}
	
	/**
	 * setter for previous node
	 */
	public void setPrev(DLinkedNode <T> node)
	{
		prev = node;
	}
}
