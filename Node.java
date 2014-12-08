/**
 * 
 */

/**
 * This class creates a node that is used in the LinkedList class.
 * Each node contains patient information such as first and last name, 
 * insurance, arrival time, priority, and position.
 * @author Terena Chao
 *
 */
public class Node {
	
	/**
	 * Default Constructor sets next to null.
	 */
	Node(){
			next = null;
	}
	
	Node next;
	String firstName;
	String lastName;
	String insurance;
	int time;
	int priority;
	int position; 

}
