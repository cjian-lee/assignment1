

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * works as a social network where user interactions are realized
 *
 *
 * This class is the driver class where data from other classes is used to realize functions
 * Two types of people will be stored in community(personList): Adults and Children
 * Relationship between Adults can be either "friend" or "partner"
 * Relationship between Children can only be "friend". Besides, age gap constraints apply.
 * No single parent or no child without any two parents is allowed
 *
 *
 * @author Xiaotian Lu
 * @studentNo. s3664804
 * @version 1.8
 * @since 1.0
 */

public class Community {

    /**
     * Person type ArrayList to store "qualified" people in community
     */
    private ArrayList<Person> personList = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);


    
    public void establishCommunity() {
    	initialPeople();
    	printMenu();
    	
    }
    
    /**
     * initial data of different types of people for user's ease of use
     * two children "Lily" and "Luke" were set as dependent of Jake & Lucy and Mike & Jay respectively
     */
    private void initialPeople(){
        Person jake = new Adult("Jake", 19, "female","studying" ,false);
        Person lucy = new Adult("Lucy", 20, "unknown", "working", false);
        Person mike = new Adult("Mike", 19, "unknown", "unemployed", false);
        Person jay = new Adult("Jay", 22, "male","working", true);
        Person gloria = new Adult("Gloria", 19, "female","unhappy", true);
        Person mitch = new Adult("Mitch", 19, "female", "happy", false);
        Person cam = new Adult("Cam", 19, "female", "boring", true);

        Person lily = new Dependent("Lily", 13, "female", "annoying", false);
        Person luke = new Dependent("Luke", 9, "male", "driving", true);

        personList.add(jake);
        personList.add(lucy);
        personList.add(mike);
        personList.add(jay);
        personList.add(gloria);
        personList.add(mitch);
        personList.add(cam);
        personList.add(lily);
        personList.add(luke);

        ((Adult) jake).addDependent((Adult) lucy, (Dependent) lily);
        ((Adult) lucy).addDependent((Adult) jake, (Dependent) lily);//Lily is successfully added as dependent of Jake & Lucy
                                                                    //Lucy & Jake automatically become partner and both parents of Lily
        ((Adult) mike).addDependent((Adult) jay, (Dependent) luke);
        ((Adult) jay).addDependent((Adult) mike, (Dependent) luke);//Luke is successfully added as dependent of Mike & Jay
                                                                   //Mike & Jay automatically become partner and both parents of Luke
    }

    /**
     * to print menu, ask user to input and direct to corresponding method based on user input.
     */
    private void printMenu() {
        boolean flag = false;
        while (!flag) {
            System.out.println("========================================\n"+
                               "|              MiniNet Menu            |\n" +
                               "----------------------------------------\n" +
                               "| 1  ->  List Everyone                 |\n" +
                               "| 2  ->  Select a person               |\n" +
                               "| 3  ->  Add new person                |\n" +
                               "| 4  ->  Remove Existing Person        |\n" +
                               "| 5  ->  Are these two direct friends? |\n" +
                               "| 0  ->  Exist MiniNet                 |\n" +
                               "========================================\n" +
                               "Input number of function: ");
            String choice = sc.nextLine();
            switch (choice) {
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
                    break;
                case "0":
                    flag = true;
                    break;
                default:
                    System.out.println("Invalid Input, please try again: ");
            }
        }
    }

    /**
     * to list everyone's name within community
     */
    private void listEveryone() {
        System.out.println("===============================================");
        System.out.println("|         Listing People in Community         |");
        System.out.println("-----------------------------------------------");
        if (personList.size() == 0) {
            System.out.println("|                                             |");
            System.out.println("|         There's no one in community         |");
            System.out.println("|                                             |");
            System.out.println("===============================================");
            return;
        }
        String personType;
        for (int i = 0; i < personList.size(); i++) {
            if(personList.get(i) instanceof Adult){
                personType = "Adult";
            }else{
                personType = "Dependent";
            }

            System.out.print((i + 1) + ":Name -> ");
            System.out.printf("%-15s|", personList.get(i).getName());
            System.out.println("    Type -> " + personType);
        }
        System.out.println("===============================================");
    }

    /**
     * present a submenu when a person was selected
     * @param person to pass in a person to manipulate this person
     */
    private void updateSelected(Person person) {
        if (person == null) {
            System.out.println("|          Try to add a person first          |");
            System.out.println("===============================================");
        } else {
            boolean flag = false;
            do {
                System.out.println("________________________________________________________________\n" +
                                   "Input following number for further manipulations on " + person.getName() + "\n" +
                                   "1  ->  Print Profile\n" +
                                   "2  ->  Add Relationship\n" +
                                   "3  ->  Update Name\n" +
                                   "4  ->  Update Age\n" +
                                   "5  ->  Update Gender\n" +
                                   "6  ->  Update Status\n" +
                                   "7  ->  Modify Existing Relationship\n" +
                                   "8  ->  Switch ON/OFF Image\n" +
                                   "0  ->  Return to Main Menu\n" +
                                   "_________________________________________________________________\n" +
                                   "Please input your choice:");
                String choice = sc.nextLine();
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
                } else if (choice.equals("8")) {
                    switchOnOffImage(person);
                } else if (choice.equals("0")) {
                    System.out.println("RETURNING BACK TO MAIN MENU...");
                    flag = true;
                } else{
                    System.out.println("! Invalid Input. Please try again !");
                }
            } while (!flag);
        }
    }

    /**
     *
     * @param person pass in person and change his name
     */
    private void updateName(Person person) {
        System.out.println("Input new name:");
        String newName = sc.nextLine();
        person.setName(newName);
        System.out.println("Name update successful!");
    }

    /**
     * user is not allowed to change a child to adult and vise versa
     * @param person pass in person and change his age
     */
    private void updateAge(Person person) {
        int newAge = ageValid();
        if (person.getAge() < 16 && newAge >= 16) {
            System.out.println("! Cannot Change Child To Adult !");
            System.out.println("! Age Update Not Successful !");
        } else if (person.getAge() >= 16 && newAge < 16) {
            System.out.println("! Cannot Change Adult To Child !");
            System.out.println("! Age Update Not Successful !");
        } else {
            person.setAge(newAge);
            System.out.println("Age Update Successful");
        }
    }

    /**
     * @param person pass in person and change his gender
     */
    private void updateGender(Person person) {
        System.out.println("Input New Gender:");
        person.setGender(sc.nextLine());
        System.out.println("Gender Update Successful");
    }

    /**
     * @param person pass in person and change his status
     */
    private void updateStatus(Person person) {
        System.out.println("Input New Status:");
        person.setStatus(sc.nextLine());
        System.out.println("Status Update Successful");
    }

    /**
     * @param person pass in person and switch on/off his image display
     */
    private void switchOnOffImage(Person person){
        System.out.println("Image is currently " + (person.getImage()?"switched on":"switched off"));
        System.out.println("Do you want to " + (person.getImage()?"switch it off":"switch it on") + " ?(y/n)");
        String s = yesNoValid();
        if(s.equals("y")){
            if (person.getImage()){
                person.setImage(false);
            }else{
                person.setImage(true);
            }
            System.out.println("Image has been " + (person.getImage()?"switched on":"switched off"));
        }else{
            System.out.println("Update Not Successful. User Choose Not to Update");
        }

    }

    /**
     * @return person based on user input
     */
    private Person selectPerson() {
        listEveryone();
        if (personList.size() != 0) {
            System.out.println("Input Person's Name For Further Manipulation:");
            boolean flag = false;
            while (!flag) {
                String personName = sc.nextLine();
                for (int i = 0; i < personList.size(); i++) {
                    if (personList.get(i).getName().equals(personName)) {
                        System.out.println(personList.get(i).getName() + " Has Been Selected");
                        return personList.get(i);
                    }
                }
                System.out.println("! Cannot Find " + personName + ". Please Try Again !");
                listEveryone();
            }
        }
        return null;
    }

    /**
     * to add relationship to an adult or child
     * @param person pass in super class reference and judge if the reference is point to an adult
     *               or child using instanceof and do corresponding casting
     */
    private void addRelationship(Person person) {
        if (personList.size() == 1) {
            System.out.println("No other person to have relationship with, add another person first.");
            return;
        }
        if (person instanceof Adult) {
            addAdultRelation((Adult) person);
        } else if (person instanceof Dependent) {
            addChildrenRelation((Dependent) person);
        }
    }

    /**
     * Children who are 2 or under 2 years old cannot have any friends.
     * Children who are 2 or under 2 years old have dependent/parent relationship established once they
     *    were added to community.
     * Children who are above 2 years old can have "friend" relationship under certain constraints.
     * @param dependent
     */
    private void addChildrenRelation(Dependent dependent) {
        if (dependent.getAge() <= 2) {
            System.out.println("Children who is 2 or under 2 years old cannot have any friend");
        } else {
            System.out.println("People in community to have relationship with: ");
            int counter = 1;
            for (int i = 0; i < personList.size(); i++) {    // to print out people in community except himself
                if (!personList.get(i).getName().equals(dependent.getName())) {
                    System.out.print(counter + ": ");
                    System.out.printf("%-15s|", personList.get(i).getName());
                    System.out.println("  Age: " + personList.get(i).getAge());
                    counter++;

                }
            }
            System.out.println("Input person's name to establish a friendship with:");
            String personName = sc.nextLine();
            if (findPerson(personName) != null && !findPerson(personName).equals(dependent)) { // cannot have self-associated relationship
                dependent.addRelationship("friend", findPerson(personName));
            } else {
                System.out.println("! Cannot Find " + personName + " or User Trying To Add Self-Relationship !");
            }
        }
    }

    /**
     * To add new relationship to an adult.
     * adult who is already someone's partner cannot add or be added new "partner" relationship     *
     * @param adult
     */
    private void addAdultRelation(Adult adult) {
        String personName;
            System.out.println("People in community to have relationship with: ");
            int counter = 1;
            for (int i = 0; i < personList.size(); i++) {
                if (!personList.get(i).getName().equals(adult.getName())) {
                    System.out.print(counter + ": ");
                    System.out.printf("%-15s|", personList.get(i).getName());
                    System.out.println("  Age: " + personList.get(i).getAge());
                    counter++;
                }
            }
            System.out.println("Input person's name to establish a relationship:");
            personName = sc.nextLine();
        if (findPerson(personName) != null && !findPerson(personName).equals(adult)) {
            if(findPerson(personName) instanceof Adult) {
                System.out.println("Specify relationship type(partner/friend): ");
                System.out.println("NOTE: person who is partner of another person cannot be partner with!");
                String type = partnerOrFriend();
                if (adult.getPartner() != null) {
                    if (type.equals("partner")) {
                        System.out.println(adult.getName() + " is already partner of " + adult.getPartner().getName());
                        System.out.println("Cannot add partner relationship with other person");
                    } else {
                        adult.addRelationship(type, (Adult) findPerson(personName));
                    }
                } else {
                    if (type.equals("partner") && ((Adult) findPerson(personName)).getPartner() != null) {
                        System.out.println(personName + " is already partner of " + ((Adult) findPerson(personName)).getPartner().getName());
                        System.out.println("Cannot add partner relationship with " + personName);
                    } else {
                        adult.addRelationship(type, (Adult) findPerson(personName));
                        if (type.equals("partner")) {
                            adult.setPartner((Adult) findPerson(personName));
                            ((Adult) findPerson(personName)).setPartner(adult);
                        }
                    }
                }
            }else{
                System.out.println("! Cannot Establish Relationship With Other Dependent !" );
            }
        } else {
            System.out.println("! Cannot Find " + personName + " or User Trying To Add Self-Relationship !");
        }
    }


    /**
     * checking if 2 people have direction relationship
     * not allowed to check if there's nobody or only one in community
     */
    private void directFriend() {
        if (personList.size() == 0) {
            System.out.println("=============================");
            System.out.println("|There's no one in community|");
            System.out.println("|Try to add two people first|");
            System.out.println("=============================");
        } else if (personList.size() == 1) {
            System.out.println("=============================");
            System.out.println("|Only one person in community|");
            System.out.println("|Try to add another first    |");
            System.out.println("==============================");
        } else {
            listEveryone();
            String firstName, secondName;
            do {
                System.out.println("Input first person's name (input '/' to return):");
                firstName = sc.nextLine();
                if (firstName.equals("/")){
                    System.out.println("Returning to Main Menu....");
                    return;
                }
                if (findPerson(firstName) == null) {

                    System.out.println("Cannot find first person, please try again.");
                }
                }while(findPerson(firstName) == null);
             do{
                System.out.println("Input second person's name (input '/' to return):");
                secondName = sc.nextLine();
                 if (secondName.equals("/")){
                     System.out.println("Returning to Main Menu....");
                     return;
                 }
                if (findPerson(secondName) == null) {
                    System.out.println("Cannot find second person, please try again.");
                }
            } while (findPerson(secondName) == null);
            Person firstPerson = findPerson(firstName);
            Person secondPerson = findPerson(secondName);
            if (firstPerson.withRelationship(secondPerson)) {
                System.out.println(firstName + " is " + firstPerson.getRelationship().get(firstPerson.relatedIndex(secondPerson)).getType()
                        + " of " + secondName);
            } else {
                System.out.println(firstName + " is not in relationship with " + secondName);
            }
        }
    }


    /**
     * to remove existing person from community
     * not allowed to remove if there's no one in community
     */
    private void removePerson() {
        String deleteName;
        Person deletePerson;
        if (personList.size() == 0) {
            System.out.println("=============================");
            System.out.println("|There's no one in community|");
            System.out.println("|Try to add one person first|");
            System.out.println("=============================");
        } else {
            do {
                listEveryone();
                System.out.println("Input name you want to delete (input '/' to return):");
                deleteName = sc.nextLine();
                deletePerson = findPerson(deleteName);
                if(deleteName.equals("/")){
                    System.out.println("Returning to Main Menu....");
                    return;
                }
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

    /**
     * to delete an adult from community
     * delete adult who has a dependent will lead to deletion of both if user confirm it
     *      and his partner will be available for a new partner relationship
     * @param deletePerson
     */
    private void deleteAdult(Person deletePerson) {
        if (!((Adult) deletePerson).getHasDependent()) {
            deletePerson.removeRelated();
            personList.remove(deletePerson);
            System.out.println(deletePerson.getName() + " was deleted.");
        } else {
            String dependentName = ((Adult) deletePerson).getDependent().getName();
            Dependent dependentPerson = ((Adult) deletePerson).getDependent();

            System.out.println(deletePerson.getName() + " is parent of " + dependentName);
            System.out.println("Remove " + deletePerson.getName() + " will remove " + dependentName);
            System.out.println("Do you wish to continue? (y/n)");
            String choice = yesNoValid();
            if (choice.equals("y")) {
                ((Adult) deletePerson).getPartner().setDependent(null);
                ((Adult) deletePerson).getPartner().setHasDependent(false);
                ((Adult) deletePerson).getPartner().setPartner(null);
                deletePerson.removeRelated();
                dependentPerson.removeRelated();
                personList.remove(dependentPerson);
                personList.remove(deletePerson);
                System.out.println(deletePerson.getName() + " was deleted.");
            } else if (choice.equals("n")) {
                System.out.println("delete not successful");
            }
        }
    }

    /**
     * to delete a dependent from community
     * delete a dependent will not delete his parents. after deletion, his parent will still be in "partner"
     *      relationship, but they will become available parents for a new dependent.
     * @param deletePerson
     */
    private void deleteDependent(Person deletePerson) {
        for (int i = 0; i < deletePerson.getRelationship().size(); i++) {
            if (deletePerson.getRelationship().get(i).getType().equals("dependent")) {
                ((Adult) deletePerson.getRelationship().get(i).getPerson()).setHasDependent(false);
            }
        }
        deletePerson.removeRelated();
        personList.remove(deletePerson);
        System.out.println(deletePerson.getName() + " was deleted.");
    }

    /**
     * to add a person into community and create his own profile
     * status is optional: either input by user or "Not Available" by default
     * image is optional: either to switch on or off
     * adding adult will not affect other people in community
     * adding dependent will force user to assign two parents for the new dependent
     * adding dependent will not be successful if there's not enough (at least 2) parents currently in community
     */
    private void addToCommunity() {
        System.out.println("Input Name:");
        String name = sc.nextLine();
        if(findPerson(name) != null){                   // checking name to see if input name has already been in community
            System.out.println("! " + name + " is Already In Community !"); // if not, proceed to next step
            System.out.println("! Add To Community Not Successful !");      // if so, return
            return;
        }
        int age = ageValid();        //to valid input age
        System.out.println("Input gender:");
        String gender = sc.nextLine();
        System.out.println("Do you wish to set up status now? (y/n)");
        String choice = yesNoValid();   // to force user to input either "y" for yes or "n" for no
        String status = "Not available";
        if (choice.equals("y")) {
            System.out.println("Input status:");
            status = sc.nextLine();
        } else if (choice.equals("n")) {
            status = "Not available";
        }
        System.out.println("Do you wish to display profile image now? (y/n)");
        boolean image = displayImage();    // false: switch off| true: switch on
        if (age >= 16) {                    // adult can be added to community directly
            personList.add(addToAdult(name, age, gender, status, image));
            System.out.println(name + " added to the community.");
        } else {                            // adding dependents will force user to assign 2 parents first
            if (getAvailParent() < 2) {     // failed to add a dependent if there's not enough parents
                System.out.println("Cannot add " + name + ". Not enough adults available.");
            } else {
                addTwoParents(name, age, gender, status, image);
            }
        }
    }

    // to return boolean value according to user input
    private boolean displayImage(){
        String s = yesNoValid();
        if(s.equals("y")){
            return true;
        }else{
            return false;
        }
    }

    /**
     * to add two qualified parents to a newly added dependent
     * 2 parents can be in "partner" relationship but each of them must have no dependent
     * if either parents has "partner" relationship with someone else but has no dependent,
     *      remove "partner" relationship with existing partner and establish a new "partner"
     *      relationship with new partner according to user input
     * @param name
     * @param age
     * @param gender
     * @param status
     * @param image
     */
    private void addTwoParents(String name, int age, String gender, String status, boolean image){
        System.out.println("Choose 2 of the following people as parents: ");
        listAvailParent();
        Adult adult1, adult2;
        do {
            System.out.println("Input one parent's name: ");
            String parent1 = sc.nextLine();
            System.out.println("input the other parent's name: ");
            String parent2 = sc.nextLine();
            adult1 = ((Adult) findPerson(parent1));
            adult2 = ((Adult) findPerson(parent2));
            if(adult1 == null || adult2 == null){
                System.out.println("! Invalid Input. Please Input Names Of Following Available Adults !");
                listAvailParent();
            }
        }while(adult1 == null || adult2 == null);  //user input has to be valid
        if(adult1.getHasDependent() || adult2.getHasDependent()){    //cannot choose people who already have dependent
            if(adult1.getHasDependent()) {
                System.out.println("! " + adult1.getName() + " Has Already Had a Dependent !\n" +
                        "! Failed to Add !");
            }
            if(adult2.getHasDependent()){
                System.out.println("! " + adult2.getName() + " Has Already Had a Dependent !\n" +
                        "! Failed to Add !");
            }
        }else {
            if(adult1.getPartner() != null || adult2.getPartner() != null){ // if person has already been in a partner
                if(adult1.getPartner() != null){                            // relationship, remove existing one and
                    if(breakUp(adult1).equals("n")){                  // add new one
                        return;
                    }
                }
                if(adult2.getPartner() != null){
                    if(breakUp(adult2).equals("n")){
                        return;
                    }

                }
            }
            Person newDependent = new Dependent(name, age, gender, status, image);
            adult1.addDependent(adult2, ((Dependent) newDependent));
            adult2.addDependent(adult1, ((Dependent) newDependent));
            personList.add(newDependent);
            System.out.println(name + " added. " + adult1.getName() + " and " + adult2.getName() + " are now parents of " + name + ".");
        }
    }


    /**
     * to remove existing "partner" relationship and add a new "partner" relationship with someone else
     * @param adult
     * @return
     */
    private String breakUp(Adult adult){
        System.out.println(adult.getName() + " is currently partner of " + adult.getPartner().getName() + "\n" +
                           "Do you wish to change partner? (y/n)");
        String choice = yesNoValid();
        if(choice.equals("n")){
            System.out.println("! Fail to Add !\n" +
                               "! User Choose Not to Change Current Partner !");
        }else{
            adult.removeRelatedPartner();
        }
        return choice;
    }



    /**
     * to modify a person's relationship with another person: change from "partner" to "friend" or "friend" to partner
     * @param person
     */

    private void modifyRelationship(Person person) {
        if(person.getRelationship().size() == 0){ // cannot modify relationship if there's no existing relationship
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

    /**
     * a single adult(has no partner) can have "friend" relationship with anyone
     * a single adult cannot have "partner" relationship with someone who has a partner
     * @param adult
     */

    private void modifySingleRelation(Adult adult){
        System.out.println("!NOTE: Cannot Add Partner Relationship With Other Person Who Has a Partner!");
        System.out.println("Input another person's name to modify existing relationship:");
        printModifiedRelation(adult);
        String anotherName = sc.nextLine();
        Person anotherPerson = findPerson(anotherName);
        if (anotherPerson != null && adult.withRelationship(anotherPerson)){
            if (anotherPerson instanceof Adult) {
                String existingRelation = adult.getRelationship().get(adult.relatedIndex(anotherPerson)).getType();
                System.out.println(adult.getName() + " is currently " + existingRelation + " of " + anotherName);
                System.out.println("Specify new relationship: ");
                String newRelation = partnerOrFriend();
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
                    System.out.println("Relationship modified successfully");
                    System.out.println(adult.getName() + " is now " + newRelation + " of " + anotherName);
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

    /**
     * a has-partner adult can have "friend" relationship and cannot have any "partner" relationship with anyone else
     * a pair of couple can be modified to "friend" only when their dependent was deleted.
     * @param adult
     */
    private void modifyHasPartnerRelation(Adult adult) {
        System.out.println("!NOTE: " + adult.getName() + " is partner of " + adult.getPartner().getName() + "!");
        System.out.println("!Cannot add partner relationship with other person!");
        System.out.println("Input another person's name to modify existing relationship:");
        printModifiedRelation(adult);
        String anotherName = sc.nextLine();
        Person anotherPerson = findPerson(anotherName);
        if (anotherPerson != null && adult.withRelationship(anotherPerson)) {
            if (anotherPerson instanceof Adult) {
                String existingRelation = adult.getRelationship().get(adult.relatedIndex(anotherPerson)).getType();
                System.out.println(adult.getName() + " is currently " + existingRelation + " of " + anotherName);
                System.out.println("Specify new relationship: ");
                String newRelation = partnerOrFriend();
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

    //validation when user inputting relationship type(friend/partner)
    private String partnerOrFriend(){
        String s;
        do{
            s = sc.nextLine();
            if(!s.equals("partner") && !s.equals("friend")){
                System.out.println("! Wrong Relationship,  An Adult Can Only Be Partner or Friend With Another Adult !");
                System.out.println("! Input 'partner' Or 'friend' !");
            }
        }while(!s.equals("partner") && !s.equals("friend"));
        return s;
    }

    // to print out currently available parents who have no dependent in community
    private void listAvailParent(){
        int counter = 0;
        for(int i = 0; i < personList.size(); i++){
            if(personList.get(i) instanceof Adult && !((Adult)personList.get(i)).getHasDependent()){
                counter++;
                System.out.println(counter + ":   " + personList.get(i).getName());
            }
        }
    }

    // to return number of available parents who have no dependent in community
    private int getAvailParent(){
        int counter = 0;
        for(int i = 0; i < personList.size(); i++){
            if(personList.get(i) instanceof Adult && !((Adult)personList.get(i)).getHasDependent()){
                counter++;
            }
        }
        return counter;
    }

    // add an Adult object by calling class Adult constructor
    private Person addToAdult(String name, int age, String gender, String status, boolean image){
        return new Adult(name, age, gender, status, image);
    }

    // to valid user inputting age
    private int ageValid(){
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
            sc.nextLine();
        }while(age < 0);
        return age;
    }


    private Person findPerson(String name) {
        for (int i = 0; i < personList.size(); i++) {
            if (personList.get(i).getName().equals(name)) {
                return personList.get(i);
            }
        }
        return null;
    }

    // to valid user inputting "y" or "n"
    private String yesNoValid(){
        String s;
        do{
            s = sc.nextLine();
            if(!s.equals("y") && !s.equals("n")){
                System.out.println("Invalid input. Input 'y' for yes or 'n' for no");
            }
        }while(!s.equals("y") && !s.equals("n"));
        return s;
    }

    // to print out a people that has relationship with a person to be modified
    private void printModifiedRelation(Adult adult){
        System.out.println("----------------------------------------");
        for(int i = 0; i < adult.getRelationship().size(); i++){
            if((!adult.getRelationship().get(i).getType().equals("dependent"))){
                System.out.println("Name:  " + adult.getRelationship().get(i).getPerson().getName() +
                                   "      |      Type: " + adult.getRelationship().get(i).getType());
            }
        }
        System.out.println("----------------------------------------");
    }
}