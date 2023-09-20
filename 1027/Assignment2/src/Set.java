/**
 * Set simple collection implemented with a singly-linked list.
 * @author Sangjae Lee
 * @version 2.0
 * Due Date : February 27 2023
 * Last Edited : February 27 2023
 * Professor : Professor Sarlo
 */

public class Set <T>
{
	/**
	 * The head of a linked list
	 */
	private LinearNode<T> setStart;
	
	/**
	 * Constructor creates a linked list
	 */
	public Set() 
	{
		setStart = null;
	}
	
	/**
	 * method that adds a node to the linked list
	 * @param element represents the data to be inputed in the next node
	 */
	public void add(T element) 
	{
		//a new node to add to the list
		LinearNode<T> node = new LinearNode<T>(element);
		
		// if the list is empty set start to the new node
		if(setStart == null) 
		{
            setStart = node;
        } 
		else 
		{
			LinearNode<T> tempNode = setStart;
            
            while(tempNode.getNext() != null) 
            {
                tempNode = tempNode.getNext();    
            }
            
            tempNode.setNext(node);
        }
	}
	
	/**
	 * method that returns the number of items in the linked list
	 * @return the length of the linked list
	 */
	public int getLength() 
	{
		// variable to store the length
		int count = 0;
		LinearNode<T> tempNode = setStart;
		
		// use while loop to count the nodes in the linked list
		while(tempNode != null) 
        {
            tempNode = tempNode.getNext();
            count++;
        }
		
		return count;
	}
	
	/**
	 * method that gets the data stored in ith location
	 * @param i represents the location that the user wants to search
	 * @return return the element if the location exists. return null if DNE
	 */
	public T getElement(int i)
	{
		// variable to check what index the while loop is
		int count = 0; 
		LinearNode<T> tempNode = setStart;
		
		// check the count and parameter. output the tempNode element if index found
        while (tempNode != null)
        {
            if (count == i)
            {
                return tempNode.getElement();
            }
            count++;
            tempNode = tempNode.getNext();
        }
        
		return null;
	}
	
	/**
	 * method that checks if the linked list contains the element in the parameter
	 * @param element represents the data to be searched in the linked list
	 * @return true if the element exists, false if not
	 */
	public boolean contains(T element)
	{
		LinearNode<T> tempNode = setStart;
		
		while(tempNode != null) 
        {
            tempNode = tempNode.getNext();
            // if statement to check if the current node contains the element
            if (tempNode.getElement() == element)
            {
            	return true;
            }
            else
            {	
            	return false;
            }
        }
		
		return false;
	}
	
	/**
	 * method that returns a string containing each of the elements in the Set separated by a space
	 * @return returnStr the string value of the elements
	 */
	public String toString()
	{
		// a string to store the string value of the output
		String returnStr = "";
		LinearNode<T> tempNode = setStart;
		
		// adds the element in the tempNode to the string
		while(tempNode != null) 
        {
			returnStr += (" " + tempNode.getElement());
			tempNode = tempNode.getNext();
        }
		
		return returnStr;
	}
}
