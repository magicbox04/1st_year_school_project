/**
 * FileStructure represents the linked structure that will store the information of the 
 * file objects in your file system 
 * @author Sangjae Lee
 * @version 3.0
 * Due Date : April 6  2023
 * Last Edited : April 5 2023
 * Professor : Professor Sarlo
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileStructure 
{
	/**
	 *	Instance node to indicate the root of the tree
	 */
	private NLNode<FileObject> root;
	
	/**
	 * constructor for the node of the doubly linked list
	 * 
	 * @param fileObjectName the name of the file/folder
	 */
	public FileStructure (String fileObjectName) throws FileObjectException
	{
		FileObject file = new FileObject(fileObjectName);
		
		if (file.isDirectory() == true || file.isFile() == true)
		{
			root = new NLNode<FileObject>(file, null);
			FileStructureRecusive(root);
		}
	}
	
	/**
	 * Method to get the root of the tree
	 * 
	 * @return the root of the tree
	 */
	public NLNode<FileObject> getRoot()
	{
		return root;
	}
	
	/**
	 * method returns a String iterator with the names of all the files of 
	 * the specified type represented by nodes of this FileStructure
	 * each name includes the absolute path to the file
	 * 
	 * @param type the String value of the type to be found
	 * @return String iterator with the names of all the files of the specified type
	 */
	public Iterator<String> filesOfType (String type) throws FileObjectException
	{
		List<String> files = new ArrayList<String>();
		// recursive call
		filesOfTypeRecusive(root, type, files);
		return files.iterator();
	}
	
	/**
	 * method returns a String iterator with the names of all the files of 
	 * the specified type represented by nodes of this FileStructure
	 * each name includes the absolute path to the file
	 * 
	 * @param type the String value of the type to be found
	 * @return String iterator with the names of all the files of the specified type
	 */
	public String findFile(String name) throws FileObjectException
	{
		List<String> fileRoute = new ArrayList<String>();
		String output;
		// recursive call
		findFileRecursive(root, name, fileRoute);
		
		output = fileRoute.toString();
		// cut out the square bracket
		output = output.substring(1, output.length()-1);
		return output;
	}
	
	/**
	 * Recursive method used in FileStructure constructor
	 * 
	 * @param paraNode a node that has the file information
	 * @throws FileObjectException when there is problem while constructing the FileStructure
	 */
	private void FileStructureRecusive(NLNode<FileObject> parameterNode) throws FileObjectException
	{
		//This happens when r represents a file
		if (parameterNode.getData().isFile() == true)
		{
			return;
		}
		else
		{
			FileObject file = parameterNode.getData();
			Iterator<FileObject> fileIterator = file.directoryFiles();
			
			//check every fileIterator
			while(fileIterator.hasNext() == true)
			{
				NLNode<FileObject> node =  new NLNode<FileObject>(fileIterator.next(), parameterNode);
				
				parameterNode.addChild(node);
				node.setParent(parameterNode);
				// recursive call
				FileStructureRecusive (node);
			}
		}
	}
	
	/**
	 * Recursive method used in filesOfType method
	 * 
	 * @param paraNode a node that has the file information
	 * @throws FileObjectException when there is problem while constructing the FileStructure
	 */
	private void filesOfTypeRecusive(NLNode<FileObject> parameterNode, String fileObjectName, List<String> tempList) throws FileObjectException
	{
		if(parameterNode.getData().isFile() == true)
		{
			if (parameterNode.getData().getName().endsWith(fileObjectName) == true)
			{
				tempList.add(parameterNode.getData().getLongName());
			}
		}
		else
		{
			Iterator<NLNode<FileObject>> fileIterator = parameterNode.getChildren();
			
			//check every fileIterator
			while (fileIterator.hasNext() == true)
			{
				filesOfTypeRecusive(fileIterator.next(), fileObjectName, tempList);
			}
		}
	}
	
	/**
	 * Recursive method used in findFile method
	 * 
	 * @param paraNode a node that has the file information
	 * @param fileName a string that stores the fileName we are looking for
	 * @param tempList the string list to store the file location string
	 * @throws FileObjectException when there is problem while constructing the FileStructure
	 */
	private void findFileRecursive(NLNode<FileObject> parameterNode, String fileName, List<String> tempList) throws FileObjectException
	{
		if(parameterNode.getData().isFile() == true)
		{
			// tempList.size() == 0 because we only need one value
			if (parameterNode.getData().getName().endsWith(fileName) == true && tempList.size() == 0)
			{
				tempList.add(parameterNode.getData().getLongName());
			}
		}
		else
		{
			Iterator<NLNode<FileObject>> fileIterator = parameterNode.getChildren();
			
			//check every fileIterator
			while (fileIterator.hasNext() == true)
			{
				findFileRecursive(fileIterator.next(), fileName, tempList);
			}
		}
	}
}
