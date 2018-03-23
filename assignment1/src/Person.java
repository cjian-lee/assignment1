
import java.util.ArrayList;


/**
 * 
 * served as an abstract class to be extended by Dependent and Adult class
 * store personal profiles
 *
 *
 * @author Chern Jian Lee
 * @studentNo. s3373345
 * @version 1.8
 * @since 1.0
 */


public abstract class Person {

	/**
	 * instance variables for building up profile
	 */
    private String name;
    private int age;
    private String gender;
    private String status;
    private boolean image;
    private ArrayList<Relationship> relationship = new ArrayList<>(); //to hold "this" person's relationships with other people

    /**
     * constructor for initialize an Adult or Dependent object
     * @param name person's name
     * @param age  person's age
     * @param gender  person's gender(any type of gender)
     * @param status    can be optional
     * @param image		can be switched on/off
     */
    public Person(String name, int age, String gender, String status, boolean image) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.status = status;
        this.image = image;
    }



    /**
     * getters for instance variables
     * @return
     */
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<Relationship> getRelationship() {
        return relationship;
    }

    public boolean getImage(){
        return image;
    }



    /**
     * setters to change value of instance variables
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void setImage(boolean image){
        this.image = image;
    }


    /**
     * to remove "this" person and type of relationship from other people's relationship 
     * lists 
     */
    public void removeRelated(){
        for(int i = 0; i < getRelationship().size(); i++){
            int removedIndex = getRelationship().get(i).getPerson().relatedIndex(this);
            if(relationship.get(i).getType().equals("partner")){
                ((Adult) relationship.get(i).getPerson()).setPartner(null);
            }
            getRelationship().get(i).getPerson().getRelationship().remove(removedIndex);
        }
    }

    /**
     * check if a person has a relationship with "this" person
     * @param person
     * @return true if so
     * 		   false if not
     */
    public boolean withRelationship(Person person){
        for(int i = 0; i < relationship.size(); i++){
            if(relationship.get(i).getPerson().equals(person)) {
                return true;
            }
        }return false;
    }

    /**
     * to get the index of another person within relationship list 
     * @param person
     * @return
     */
    public int relatedIndex(Person person){
        for (int i = 0; i < relationship.size(); i++){
            if(relationship.get(i).getPerson().equals(person)){
                return i;
            }
        }return -1;
    }

/**
 * to check if "this" person has any relationship
 * @return
 */
    public boolean hasRelationship(){
        if(relationship.size() > 0){
            return true;
        }return false;
    }

    /**
     * to print out parents and dependents
     */
    public void printFamily() {
        for (int i = 0; i < relationship.size(); i++) {
            if(relationship.get(i).getType().equals("parent") || relationship.get(i).getType().equals("dependent")){
                System.out.println(getName() + " is " + relationship.get(i).getType() + " of " + relationship.get(i).getPerson().getName());
            }

        }
    }

    /**
     * print out other relationship
     */
    public void printRelation(){
        for(int i = 0; i < relationship.size(); i++){
            if((!relationship.get(i).getType().equals("parent")) && (!relationship.get(i).getType().equals("dependent"))){
                System.out.println(getName() + " and " + relationship.get(i).getPerson().getName() +
                        " are " + relationship.get(i).getType());
            }
        }
    }



    //abstract class to be overridden in subclasses
    public abstract void printProfile();




}

