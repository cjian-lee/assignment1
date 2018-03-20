/**
 * 
 */
package assignment1;

/**
 * @author Xiaotian Lu
 *
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
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
                case "5":
                    directFriend();
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
                System.out.println("7  ->  Modify Existing Relationship");
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
                } else if (choice.equals("7")) {
                	modifyRelationship(person);
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
    
    
    private void directFriend(){
        if(personList.size() == 0){
            System.out.println("=============================");
            System.out.println("|There's no one in community|");
            System.out.println("|Try to add two people first|");
            System.out.println("=============================");
        }else if(personList.size() == 1){
            System.out.println("=============================");
            System.out.println("|Only one person in community|");
            System.out.println("|Try to add another first    |");
            System.out.println("=============================");
        }else{
            listEveryone();
            String firstName, secondName;
            do {
                System.out.println("Input first person's name:");
                firstName = sc.next();
                System.out.println("Input second person's name:");
                secondName = sc.next();
                if(findPerson(firstName) == null){
                    System.out.println("Cannot find first person, please try again.");
                }
                if(findPerson(secondName) == null){
                    System.out.println("Cannot find second person, please try again.");
                }
            }while(findPerson(firstName) == null || findPerson(secondName) == null);
            Person firstPerson = findPerson(firstName);
            Person secondPerson = findPerson(secondName);
            if(firstPerson.withRelationship(secondPerson)){
                System.out.println(firstName + " is " + firstPerson.getRelationship().get(firstPerson.relatedIndex(secondPerson)).getType()
                                    + " of " + secondName);
            }else{
                System.out.println(firstName + " is not in relationship with " + secondName);
            }
        }
    }
    
    //delete person form personList
    private void removePerson(){
        String deleteName;
        Person deletePerson;
        if(personList.size() == 0){
            System.out.println("=============================");
            System.out.println("|There's no one in community|");
            System.out.println("|Try to add one person first|");
            System.out.println("=============================");
        }else {
            do {
                listEveryone();
                System.out.println("Input name you want to delete:");
                deleteName = sc.next();
                deletePerson = findPerson(deleteName);
                if (deletePerson == null) {
                    System.out.println("Cannot find " + deleteName + ", please try again:");
                }
            } while (deletePerson == null);
            if (deletePerson instanceof Adult) {
                deleteAdult(deletePerson);
            } else if (deletePerson instanceof Dependent) {
                deleteDependent(deletePerson);
            }
        }
    }

    private void deleteAdult(Person deletePerson){
        if(!((Adult) deletePerson).getHasDependent()){
            deletePerson.removeRelated();
            personList.remove(deletePerson);
            System.out.println(deletePerson.getName() + " was deleted.");
        }else{
            String dependentName = ((Adult) deletePerson).getDependent().getName();
            Dependent dependentPerson = ((Adult) deletePerson).getDependent();

            System.out.println(deletePerson.getName() + " is parent of " + dependentName);
            System.out.println("Remove " + deletePerson.getName() + " will remove " + dependentName);
            System.out.println("Do you wish do continue? (y/n)");
            String choice;
            do {
                choice = sc.next();
                if(!choice.equals("y") && !choice.equals("n")){
                    System.out.println("Wrong Input! Please input 'y' for yes or 'n' for no.");
                    System.out.println("Do you wish do continue? (y/n)");
                }
            }while(!choice.equals("y") && !choice.equals("n"));
            if(choice.equals("y")){
                ((Adult) deletePerson).getPartner().setDependent(null);
                ((Adult) deletePerson).getPartner().setHasDependent(false);
                ((Adult) deletePerson).getPartner().setPartner(null);
                deletePerson.removeRelated();
                dependentPerson.removeRelated();
                personList.remove(dependentPerson);
                personList.remove(deletePerson);
                System.out.println(deletePerson.getName() + " was deleted.");
            }else if(choice.equals("n")){
                System.out.println("delete not successful");
            }
        }
    }

    private void deleteDependent(Person deletePerson){
        for(int i = 0; i < deletePerson.getRelationship().size(); i++){
            if(deletePerson.getRelationship().get(i).getType().equals("dependent")){
                ((Adult)deletePerson.getRelationship().get(i).getPerson()).setHasDependent(false);
            }
        }
        deletePerson.removeRelated();
        personList.remove(deletePerson);
        System.out.println(deletePerson.getName() + " was deleted.");
    }
    
    private Person findPerson(String name){
        for(int i = 0; i < personList.size(); i++){
            if(personList.get(i).getName().equals(name)){
                return personList.get(i);
            }
        }return null;
    }
    
    
// age int validation realized. yes or no validation needed!!!!
    private void addToCommunity() {
        System.out.println("Input Name:");
        String name = sc.next();
        int age = -1;
        do{
            System.out.println("Input age:");
            try {
                age = sc.nextInt();
            }catch(InputMismatchException e){
                sc.nextLine();
            }
            if(age < 0){
                System.out.println("Invalid Input. Age can only be positive number.");
            }
        }while(age < 0);
        System.out.println("Input gender:");
        String gender = sc.next();
        System.out.println("Do you wish to set up status now? (y/n)");
        String choice = sc.next();
        String status = "Not available";
        if (choice.equals("y")) {
            System.out.println("Input status:");
            status = sc.next();
        } else if (choice.equals("n")) {
            status = "Not available";
        }
        if (age >= 16) {
            personList.add(addToAdult(name, age, gender, status));
            System.out.println(name + " added to the community.");
        } else {
            if (getAvailParent() < 2) {
                System.out.println("Cannot add " + name + ". Not enough adults available.");
            } else {
                listAvailParent();
                System.out.println("Input one parent's name: ");
                String parent1 = sc.next();
                System.out.println("input the other parent's name: ");
                String parent2 = sc.next();
                Adult adult1 = ((Adult) findPerson(parent1));
                Adult adult2 = ((Adult) findPerson(parent2));
                Person newDependent = new Dependent(name, age, gender, status);
                adult1.addDependent(adult2, ((Dependent) newDependent));
                adult2.addDependent(adult1, ((Dependent) newDependent));
                personList.add(newDependent);
                System.out.println(name + " added. " + parent1 + " and " + parent2 + " are now parents of " + name + ".");
            }
        }
    }
    
    //more tests needed
    private void modifyRelationship(Person person) {
        if(person.getRelationship().size() == 0){
            System.out.println("No other related people to modify, add a relationship first!");
        }else {
            if (person instanceof Adult) {
                Adult adult = (Adult) person;
                if (adult.getPartner() != null) {
                    modifyHasPartnerRelation(adult);
                } else {
                    modifySingleRelation(adult);
                }
            } else {
                System.out.println("Fail to modify!");
                System.out.println("Reason: Dependent can only be friend with another dependent.");
            }
        }
    }

    private void modifySingleRelation(Adult adult){
        System.out.println("!NOTE: Cannot add partner relationship with other person who has a partner!");
        System.out.println("Input another person's name to modify existing relationship:");
        String anotherName = sc.next();
        Person anotherPerson = findPerson(anotherName);
        if (anotherPerson != null && adult.withRelationship(anotherPerson)){
            if (anotherPerson instanceof Adult) {
                String existingRelation = adult.getRelationship().get(adult.relatedIndex(anotherPerson)).getType();
                System.out.println(adult.getName() + " is currently " + existingRelation + " of " + anotherName);
                System.out.println("Specify new relationship: ");
                String newRelation = sc.next();
                if(newRelation.equals("partner") && ((Adult) anotherPerson).getPartner() != null){
                    System.out.println("Fail to modify relationship.");
                    System.out.println("Reason: " + anotherPerson.getName() + " is already partner of " +
                                        ((Adult) anotherPerson).getPartner().getName());
                }else if(newRelation.equals("partner") && ((Adult) anotherPerson).getPartner() == null){
                    adult.getRelationship().get(adult.relatedIndex(anotherPerson)).setType("partner");
                    adult.setPartner((Adult)anotherPerson);
                    anotherPerson.getRelationship().get(anotherPerson.relatedIndex(adult)).setType("partner");
                    ((Adult) anotherPerson).setPartner(adult);
                    System.out.println("Relationship modified successfully");
                    System.out.println(adult.getName() + " is now " + newRelation + " of " + anotherName);
                }else{
                    adult.getRelationship().get(adult.relatedIndex(anotherPerson)).setType(newRelation);
                    anotherPerson.getRelationship().get(anotherPerson.relatedIndex(adult)).setType(newRelation);
                }
            }else{
                System.out.println("Fail to modify relationship.");
                System.out.println("Reason: " + anotherName + " is a child/dependent");
            }
        }else{
            System.out.println("Cannot find " + anotherName + " or " + anotherName + " not in " + adult.getName() +
                    "'s relationship list, please try again.");
        }
    }

    private void modifyHasPartnerRelation(Adult adult) {
        System.out.println("!NOTE: " + adult.getName() + " is partner of " + adult.getPartner().getName() + "!");
        System.out.println("!Cannot add partner relationship with other person!");
        System.out.println("Input another person's name to modify existing relationship:");
        String anotherName = sc.next();
        Person anotherPerson = findPerson(anotherName);
        if (anotherPerson != null && adult.withRelationship(anotherPerson)) {
            if (anotherPerson instanceof Adult) {
                String existingRelation = adult.getRelationship().get(adult.relatedIndex(anotherPerson)).getType();
                System.out.println(adult.getName() + " is currently " + existingRelation + " of " + anotherName);
                System.out.println("Specify new relationship: ");
                String newRelation = sc.next();
                if (newRelation.equals("partner")) {
                    System.out.println("Fail to modify relationship.");
                    System.out.println("Reason: " + adult.getName() + " is already partner of " + adult.getPartner().getName());
                } else if(adult.getPartner().equals(anotherPerson)){
                    if(adult.getHasDependent()) {
                        System.out.println("Fail to modify relationship.");
                        System.out.println("Reason: modify partner relationship with " + anotherName + " will affect " +
                                adult.getDependent().getName());
                        System.out.println("Try to delete " + adult.getName() + "'s dependent first!");
                    }else{
                        adult.getRelationship().get(adult.relatedIndex(anotherPerson)).setType(newRelation);
                        adult.setPartner(null);
                        anotherPerson.getRelationship().get(anotherPerson.relatedIndex(adult)).setType(newRelation);
                        ((Adult) anotherPerson).setPartner(null);
                        System.out.println("Relationship modified successfully");
                        System.out.println(adult.getName() + " is now " + newRelation + " of " + anotherName);
                    }
                } else{
                    if (newRelation.equals(existingRelation)) {
                        System.out.println("Fail to modify relationship.");
                        System.out.println("Reason: " + adult.getName() + " is already " + existingRelation + " of " + anotherName);
                    } else {
                        adult.getRelationship().get(adult.relatedIndex(anotherPerson)).setType(newRelation);
                        anotherPerson.getRelationship().get(anotherPerson.relatedIndex(adult)).setType(newRelation);
                        System.out.println("Relationship modified successfully");
                        System.out.println(adult.getName() + " is now " + newRelation + " of " + anotherName);
                    }
                }
            } else {
                System.out.println("Fail to modify relationship.");
                System.out.println("Reason: " + anotherName + " is a child/dependent");
            }
        } else {
            System.out.println("Cannot find " + anotherName + " or " + anotherName + " not in " + adult.getName() +
                                "'s relationship list, please try again.");
        }
    }
    
}
