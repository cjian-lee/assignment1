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
    
    private void updateSelected(Person person){

        boolean flag = false;
        do {
            System.out.println("________________________________________________________________");
            System.out.println("Input following number for further manipulations on " + person.getName());
            System.out.println("1  ->  Print Profile");
            System.out.println("2  ->  Add Relationship");
            System.out.println("3  ->  Update Name");
            System.out.println("4  ->  Update Age");
            System.out.println("5  ->  Update Gender");
            System.out.println("6  ->  Update Status");
            System.out.println("0  ->  Return to Main Menu");
            System.out.println("_________________________________________________________________");
            System.out.println("Please input your choice:");
            String choice = sc.next();
            if (choice.equals("1")) {
                person.printProfile();
            } else if (choice.equals("2")) {
                addRelationship(person);
            } else if (choice.equals("3")) {
                updateName(person);
            } else if (choice.equals("4")) {
                updateAge(person);
            } else if (choice.equals("5")) {
                updateGender(person);
            } else if (choice.equals("6")) {
                updateStatus(person);
            } else if (choice.equals("0")){
                flag = true;
            }
        }while(!flag);
    }
    
    private void updateName(Person person){
        System.out.println("Input new name:");
        person.setName(sc.next());
        System.out.println("Name update successful!");
    }

    private void updateAge(Person person){
        System.out.println("Input new age:");
        int newAge = sc.nextInt();
        sc.nextLine();
        if(person.getAge() < 16 && newAge >= 16){
            System.out.println("Cannot change child to adult");
            System.out.println("Age update not successful.");
        }else if(person.getAge() >= 16 && newAge < 16){
            System.out.println("Cannot change adult to child");
            System.out.println("Age update not successful.");
        }else{
            person.setAge(newAge);
            System.out.println("Age update successfully!");
        }
    }

    private void updateGender(Person person){
        System.out.println("Input new gender:");
        person.setGender(sc.next());
        System.out.println("Gender update successful!");
    }

    private void updateStatus(Person person){
        System.out.println("Input new status:");
        person.setStatus(sc.next());
        System.out.println("Status update successful!");
    }

}
