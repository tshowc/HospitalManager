/**
 * 
 */

import java.util.Scanner;


/**
 * 
 * This class creates a linked list filled with nodes that contains patient information.
 * @author Terena Chao
 *
 */
public class LinkedList {
	/**
	 * Default Constructor sets first to null and size of list and position to 0. 
	 */
	LinkedList(){
		first = new Node();
		first = null;
		size = 0;
		pos = 0;
	}
	
	/**
	 * This method checks in the list is empty or not.
	 * @return trueorfalse True if the list is empty
	 */
	boolean isEmpty(){
		if (first == null){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * This method checks whether two node's priority are equal or not.
	 * @param node1 Node to be compared
	 * @param node2 Node to be compared
	 * @return trueorfalse True if they are equal
	 */
	boolean hasEqualPrior(Node node1, Node node2){
		if (node1.priority == node2.priority){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * This method checks to make sure time entered is in military format.
	 * @return t int value for arrival time
	 */
	int configTime(){
		Scanner in = new Scanner(System.in);
		String checkTime;
		int t;
		int check = 1; //Check for do while loop
		
		do{
		System.out.print("Please Enter Time in Military Format (Ex. 100, 2400): ");
		checkTime = in.next();
		
		//Turn string checkTime into int
		t = Integer.parseInt(checkTime);
		
		if ((checkTime.length() != 4) && (checkTime.length() != 3)){
			System.out.println("Invalid time. Please try again.");
			check = 0;
		}
		else if ((t > 2400) || (t <= 0)){
			System.out.println("Invalid time. Please try again.");
			check = 0;
		}
		else{
			check = 1; //Time is properly formatted.
		}
		}while(check != 1);
		
		return t;
		
	}
	
	/**
	 * This method checks to sure priority is within bounds from 1 to 100.
	 * @return prior int value containing priority
	 */
	int configPriority(){
		Scanner in = new Scanner(System.in);
		int check = 1; //Check for do while loop
		int prior;
		
		do{
		System.out.print("Please Enter Priority (1 to 100): ");
		prior = in.nextInt();
		
		if ((prior > 100) || (prior < 0)){
			System.out.println("Priority should be between 0 and 100. Please re-enter value.");
			check = 0;
		}
		else{
			check = 1;
		}
		}while(check != 1); 
		
		return prior;
	}
	/**
	 * This method creates a new Node to enter into the linked list. 
	 * The method also allows the user to enter patient information 
	 * such as first and last name, insurance, time, and priority.
	 * Afterwards it is inserted into the linked list.
	 */
	void newNode(){
		Node Patient = new Node(); // New Default Node Created
		
		//priority is declared beforehand to prevent complications in the go block
		int prior; 
		
		Scanner in = new Scanner(System.in);
		
		//User is prompted for information about patient
		System.out.print("Please Enter Last Name: ");
		String ln = in.next();
		ln = ln.toUpperCase();
		System.out.print("Please Enter First Name: ");
		String fn = in.next();
		fn = fn.toUpperCase();
		System.out.print("Please Enter Insurance: ");
		in.nextLine();
		String insure = in.nextLine();
		insure = insure.toUpperCase();
	
		//Assigning information to Node class objects.
		Patient.firstName = fn;
		Patient.lastName = ln;
		Patient.insurance = insure;
		Patient.time = configTime();
		Patient.priority = configPriority();
	
		
		//the new Node is then inserted into the list
		insert(Patient);
	}
	/**
	 * This method inserts the node created in newNode() into the linked list. 
	 * It will also insert in descending order based on priority.
	 * If priority is equal then it will enter the nodes based on arrival time.
	 * @param newNode the Node with patient information created in newNode();
	 */
	void insert(Node newNode){
		Node temp = newNode; //pointer to new Node
		Node current = first; //pointer to current, in this case current is pointing to first
		if (isEmpty()){
			//If list is empty just set first to the new Node (temp).
			first = temp; 
			current = first;
		}

		else if (hasEqualPrior(temp, first)){
			//If first's priority is equal to temp's priority
			//sort by time instead. 
			if (temp.time < first.time){
				current = temp;
				temp = first;
				first = current;
				first.next = temp;
			}
			else if (temp.time > first.time){
				current = temp;
				temp = first.next;
				first.next = current;
				current.next = temp; 
			}
		}
		//If temp priority is greater that first priority, insert in front
		else if (temp.priority > first.priority){
			current = temp;
			temp = first;
			first = current;
			first.next = temp;}
		
		//If temp priority is less than first priority, insert behind
		else if (temp.priority < first.priority){
			Node lesser; //lesser will make sure the list does not lose nodes
			//Traverse the list to find if nodes have same priority
			while ((current.next != null) 
					&& (temp.priority < current.next.priority) 
					&& (!hasEqualPrior(temp,current.next))){
				current = current.next;
			}
			//If there are no alike priority values, insert behind
			if ((temp.priority < current.priority)){
				lesser = current.next;
				current.next = temp;
				current.next.next = lesser; 
			}
			//If alike priority values are found, compare times.
			else if (hasEqualPrior(temp,current.next)){
				if (temp.time < current.next.time){
					lesser = current.next;
					current.next = temp;
					current.next.next = lesser;
				}
				//If time is greater than other node's time
				else if (temp.time > current.next.time){
					//Taking in account of more than one alike priority values
					//Traverse the list to find possible same values
					while ((current.next.next != null) && (hasEqualPrior(temp, current.next))
						&& (temp.time > current.next.time)){
						current = current.next;
					}
					//If more alike priority values exist and temp's time value is less than another 
					//alike value's time, insert between. 
					if ((hasEqualPrior(temp, current.next)) && (temp.time < current.next.time)){
						lesser = current.next;
						current.next = temp;
						current.next.next = lesser;
					}
					//If temp's time is greater than all of the alike priority value, insert behind.
					else{
						lesser = current.next.next;
						current.next.next = temp;
						current.next.next.next = lesser;
					}
					
				}
			}
		}
		else{
			System.out.println("Nothing Inserted"); //In case nothing was inserted.
		};
		size++; //Counts how many elements are in the list.
	}
	
	/**
	 * This method prompts the user to enter a name to find in the list.
	 * If the name is in the list, than the method will display the position 
	 * of the person and how many people are in front of him/her.
	 */
	void searchPosition(){
		if (isEmpty()){
			System.out.println("List is empty.");
		}
		else{
			//Instantiate Scanner to read input
			//Enter the name you want to search for
			Scanner in = new Scanner(System.in);
			System.out.print("Enter Last Name: ");
			String searchLN = in.nextLine();
			searchLN = searchLN.toUpperCase();
			System.out.print("Enter First Name: ");
			String searchFN = in.nextLine();
			searchFN = searchFN.toUpperCase();
			
			Node current; //pointer to help traverse the list
			current = first;
			pos = 1;
			//By combining strings. we can compare the two to make sure the first and last
			//names match up.
			String enteredName = searchFN + searchLN;
			//Traverse the list to find the name
			while ((current != null) && (!(enteredName.equals(current.firstName + current.lastName)))){
				current = current.next;
				pos++;
			}
			//If name is not in the list, program will print out message.
			if (current == null){
				String noPatient = searchLN + ", " + searchFN + " could not be found.";
			
				System.out.println(noPatient);
			}
			else{
				//Calculate how many people are in front of searched patient
				int people = pos - 1;
				//In the case of there only being one person in the list,
				//no such thing as -1 persons so it becomes 0.
				if (people == -1){
					people = 0;
				}
				String yesPatient = searchLN + ", " + searchFN + " is in position " + pos 
								+ " and currently has " + people + " person(s) in front of him/her.";
				System.out.println(yesPatient);
			
			}
		}
	}
	
	/**
	 * This method prints out the linked list node's data in a table-like format.
	 */
	void print(){
		System.out.format("%-20s%-20s%-20s%-20s%-20s%s", "Last Name", "First Name", "Insurance", "Priority", "Arrival","\n");
		if (isEmpty()){
			System.out.println("List is Empty.");
		}
		else{
			//setting up list traversal
			Node current = first;
			String timeString;
			String print; 
			while(current != null){
				//turn time into a string
				timeString = " " + current.time;
				//If statements to add ":" to the time stamp
				if (timeString.length() == 5){
					timeString = timeString.substring(0,3) + ":" + timeString.substring(3, timeString.length());
				}
				else if (timeString.length() == 4){
					timeString = timeString.substring(0,2) + ":" + timeString.substring(2, timeString.length());
				}
				//Print out Patient Details
				System.out.format("%-20s%-20s%-20s%-20s%-20s%s", current.lastName,
								current.firstName, current.insurance, current.priority,
								timeString,"\n" );
				current = current.next;
			}
		}
		
	}
	
	/**
	 * This method checks for the next person in the list and removes him or her. 
	 * Before removing the person, the method asks for confirmation whether or not 
	 * the user really wants to remove the patient. 
	 */
	void remove(){
		//Instantiate Scanner to read input
		Scanner in = new Scanner(System.in);
		if (isEmpty()){
			System.out.println("List is empty. No one to remove.");
		}
		else{
			//First is always the next patient in the list to receive medical attention
			//and is the one that needs to be deleted.
			String nextPatient = first.lastName + ", " + first.firstName
								+ "  is the next patient in the list.";
			System.out.println(nextPatient);
			//Confirmation whether to delete patient or not
			System.out.println("Do you want to remove this person from the list (Y/N)?");
			char choice = in.next().charAt(0);
			choice = Character.toUpperCase(choice);
			
			if (choice == 'Y'){
				//Important to create strings objects of node information
				//because node is going to be deleted.
				String fn = first.firstName;
				String ln = first.lastName;
				first = first.next; //Patient is "deleted"
				String Patient = ln + ", " + fn + " was removed.";
				System.out.println(Patient);
			}	
			else if (choice == 'N'){
				//do nothing
			}	
			else{
				System.out.println("Invalid Character. Could not remove patient.");
			}
		}
	}


	Node first;//First node in the linked list. 
	int size; //Size of linked list
	int pos;//Position of patient

}
