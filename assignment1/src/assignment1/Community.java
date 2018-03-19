/**
 * 
 */
package assignment1;

/**
 * @author Xiaotian Lu
 *
 */
import java.util.ArrayList;
import java.util.Scanner;


public class Community {
	
    private ArrayList<Person> personList = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    
    public ArrayList<Person> getPersonList() {
        return personList;
    }
    
    public void printMenu(){
        boolean flag = false;
        while(!flag){
            System.out.println("====================================");
            System.out.println("           MiniNet Menu");
            System.out.println("------------------------------------");
            System.out.println("1  ->  List Everyone");
            System.out.println("2  ->  Select a person");
            System.out.println("3  ->  Add new person");
            System.out.println("4  ->  Remove Existing Person");
            System.out.println("5  ->  Are these two direct friends?");
            System.out.println("0  ->  Exist MiniNet");
            System.out.println("====================================");
            System.out.println("Input number of function: ");
            String choice = sc.next();
            switch(choice){
                case "1":
                    listEveryone();
                    break;
                case "2":
                    updateSelected(selectPerson());
                    break;
                case "3":
                    addToCommunity();
                    break;
                case "4":
                    removePerson();
                    break;
                case "0":
                    flag = true;
                    break;
                default:
                    System.out.println("Invalid Input, please try again: ");
            }
        }
    }

    private void listEveryone(){
        System.out.println("===========================");
        System.out.println("Listing People in Community");
        System.out.println("---------------------------");
        if(personList.size() == 0) {
            System.out.println("There's no one in community");
            System.out.println("===========================");
            return;
        }
        for (int i = 0; i < personList.size(); i++) {
            System.out.println((i + 1) + ": " +personList.get(i).getName());
        }
        System.out.println("===========================");
    }

}
