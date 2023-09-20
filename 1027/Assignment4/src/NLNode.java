/**
 * NLNode creates a node for the tree
 * @author Sangjae Lee
 * @version 2.0
 * Due Date : April 6  2023
 * Last Edited : March 16 2023
 * Professor : Professor Sarlo
 */

import java.util.Comparator;
import java.util.Iterator;
public class NLNode<T> 
{
	/**
	 *	Instance variable to indicate the parent of the node
	 */
	private NLNode<T> parent;
	/**
	 *	Instance List to store the children of the node
	 */
	private ListNodes<NLNode<T>> children;
	/**
	 *	Instance variable for the data to be stored in the node
	 */
	private T data;
	
	/**
	 * constructor for an empty node
	 */
	public NLNode()
	{
		parent = null;
		children = new ListNodes<NLNode<T>>();
	}
	
	/**
	 * constructor for a node with data and parent
	 * 
	 * @param d the data to be stored in the node
	 * @param p the parent of the node
	 */
	public NLNode (T d, NLNode<T> p)
	{
		children = new ListNodes<NLNode<T>>();
		data = d;
		parent = p;
	}
	
	/**
	 * method to set the Parent of a node
	 * 
	 * @param p the node that is going to be the parent
	 */
	public void setParent(NLNode<T> p)
	{
		this.parent = p;
	}
	
	/**
	 * method to get the Parent of a node
	 * 
	 * @retun the parent of the node
	 */
	public NLNode<T> getParent()
	{
		return this.parent;
	}
	
	/**
	 * method to add a children to the node
	 * 
	 * @param newChild the node that is going to be added into the children list
	 */
	public void addChild(NLNode<T> newChild)
	{
		newChild.setParent(this);
		this.children.add(newChild);
	}

	/**
	 * method to get the children of a node
	 * 
	 * @retun the iterator of the children of the node
	 */
	public Iterator<NLNode<T>> getChildren()
	{
		return this.children.getList();
	}
	
	/**
	 * method to get the children of a node but now sorted based on the sorter provided
	 * 
	 * @retun the sorted iterator of the children of the node 
	 */
	public Iterator<NLNode<T>> getChildren(Comparator<NLNode<T>> sorter)
	{
		return this.children.sortedList(sorter);
	}
	
	/**
	 * method to get the data of a node
	 * 
	 * @retun the data of the node
	 */
	public T getData()
	{
		return this.data;
	}
	
	/**
	 * method to set the data of a node
	 * 
	 * @param d the data to be set on the node
	 */
	public void setData(T d)
	{
		this.data = d;
	}
}
