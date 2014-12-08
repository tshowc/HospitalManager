/**
 * 
 */
import java.util.Scanner;

/**
 * This class contains and displays the Hospital menu for the program. 
 * Depending on the character entered, the program will perform a specific action. 
 * @author Terena Chao
 *
 */
public class HospitalMain {

	/**
	 * This main method contains the menu includes the options to enter a patient,
	 *  find and delete next patient, search for patient and his or her position, 
	 *  and print out the list of patients. 
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList HospitalList = new LinkedList();
		Scanner in = new Scanner(System.in);
		char choice;
		//Setting up the menu to show up after every action has been completed.
		do{
			System.out.println();
			System.out.println("Please select your option from the following menu:");
			System.out.println("E: Enter a new patient");
			System.out.println("N: Find next patient & remove him/her from the list");
			System.out.println("P: Determine the position of a specific patient");
			System.out.println("D: Print the list of patients");
			System.out.println("Q: Quit");
			choice = in.next().charAt(0);
			choice = Character.toUpperCase(choice); 
			
			//Depending on char input a specific action will occur.
			switch(choice){
				case 'E': HospitalList.newNode();
						 	System.out.println("Press enter to return to main menu.");
						 	//consumes possible extra \n char
						 	String consume = in.nextLine();
						 	//Allows user to press enter to bring up the menu again
						 	in.nextLine();
				break;
				case 'N': HospitalList.remove();
				 			System.out.println("Press enter to return to main menu.");
				 			consume = in.nextLine();
				 			in.nextLine();

				break;
				case 'P': HospitalList.searchPosition();
	 						System.out.println("Press enter to return to main menu.");
	 						consume = in.nextLine();
	 						in.nextLine();
				break;
				case 'D': HospitalList.print();
				 			System.out.println("Press enter to return to main menu.");
				 			consume = in.nextLine();
				 			in.nextLine();

				break;
				case 'Q':
				break;
				default: System.out.println("Invalid Character. Try Again.");
			
			}
			
		}while(choice != 'Q'); //Program Quits

	}

}
