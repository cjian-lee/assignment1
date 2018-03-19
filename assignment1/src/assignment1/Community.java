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
        System.out.println("=============================");
        System.out.println("|Listing People in Community|");
        System.out.println("-----------------------------");
        if(personList.size() == 0) {
            System.out.println("|                           |");
            System.out.println("|There's no one in community|");
            System.out.println("|                           |");
            System.out.println("=============================");
            return;
        }
        for (int i = 0; i < personList.size(); i++) {
            System.out.println((i + 1) + ": " +personList.get(i).getName());
        }
        System.out.println("=============================");
    }
    
    private void updateSelected(Person person){
        if(person == null){
            System.out.println("|Try to add a person first  |");
            System.out.println("=============================");
        }else {
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
                } else if (choice.equals("0")) {
                    flag = true;
                }
            } while (!flag);
        }
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
   //infinite loop fixed
    private Person selectPerson(){
        listEveryone();
        if(personList.size() != 0){
            System.out.println("Input person's name for further manipulation:");
            boolean flag = false;
            while(!flag) {
                String personName = sc.next();
                for (int i = 0; i < personList.size(); i++) {
                    if (personList.get(i).getName().equals(personName)) {
                        System.out.println(personList.get(i).getName() + " has been selected.");
                        return personList.get(i);
                    }
                }
            System.out.println("Cannot find " + personName + ". Please try again: ");
            }
        }return null;
    }
    
    private void addRelationship(Person person){
        System.out.println("People in community to have relationship with: ");
        if(personList.size() == 1){
            System.out.println("No other person to have relationship with, add another person first.");
            return;
        }
        if(person instanceof Adult){
            addAdultRelation((Adult) person);
        }else if(person instanceof Dependent){
            addChildrenRelation((Dependent) person);
        }
    }

    private void addChildrenRelation(Dependent dependent){
        if(dependent.getAge() <= 2){
            System.out.println("Children who is 2 or under 2 years old cannot have any friend");
        }else{
            System.out.println("People in community to have relationship with: ");
            int counter = 1;
            for(int i = 0; i < personList.size(); i++){
                if(!personList.get(i).getName().equals(dependent.getName())){
                    System.out.println(counter + ": " + personList.get(i).getName() + "  |  Age: " +personList.get(i).getAge());
                }
            }
            System.out.println("Input person's name to establish a friendship with:");
            String personName = sc.next();
            if (findPerson(personName) != null){
                dependent.addRelationship("friends", findPerson(personName));
            }else{
                System.out.println("Invalid Input");
            }
        }
    }

    private void addAdultRelation(Adult adult){
        System.out.println("People in community to have relationship with: ");
        int counter = 1;
        for(int i = 0; i < personList.size(); i++){
            if(!personList.get(i).getName().equals(adult.getName())){
                System.out.println(counter + ": " + personList.get(i).getName() + "  |  Age: " +personList.get(i).getAge());
                counter++;
            }
        }
        System.out.println("Input person's name to establish a relationship:");
        String personName = sc.next();
        if(findPerson(personName) != null && findPerson(personName) instanceof Adult){
            System.out.println("Specify relationship type: ");
            System.out.println("NOTE: person who is partner of another person cannot be partner with!");
            String type = sc.next();
            if(adult.getPartner() != null){
                if(type.equals("partner")){
                    System.out.println(adult.getName() + " is already partner of " + adult.getPartner().getName());
                    System.out.println("Cannot add partner relationship with other person");
                }else{
                    adult.addRelationship(type, (Adult)findPerson(personName));
                }
            }else{
                if(type.equals("partner") && ((Adult) findPerson(personName)).getPartner() != null){
                    System.out.println(personName + " is already partner of " + ((Adult) findPerson(personName)).getPartner().getName());
                    System.out.println("Cannot add partner relationship with " + personName);
                }else{
                    adult.addRelationship(type, (Adult)findPerson(personName));
                    if(type.equals("partner")){
                        adult.setPartner((Adult) findPerson(personName));
                        ((Adult) findPerson(personName)).setPartner(adult);
                    }
                }
            }
        }else{
            System.out.println("Invalid Input or out of age range");
        }
    }

}
