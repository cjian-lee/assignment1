package assignment1;
import java.util.ArrayList;

//author Chern Jian Lee

public abstract class Person {

    private String name;
    private int age;
    private String gender;
    private String status;
    private boolean image;
    private ArrayList<Relationship> relationship = new ArrayList<>();


    //constructors to choose whether to reset status
    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    public Person(String name, int age, String gender, String status, boolean image) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.status = status;
        this.image = image;
    }



    //getters
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



    //setters
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


    //to remove "this person" from his related person's relationship list
    public void removeRelated(){
        for(int i = 0; i < getRelationship().size(); i++){
            int removedIndex = getRelationship().get(i).getPerson().relatedIndex(this);
            if(relationship.get(i).getType().equals("partner")){
                ((Adult) relationship.get(i).getPerson()).setPartner(null);
            }
            getRelationship().get(i).getPerson().getRelationship().remove(removedIndex);
        }
    }

    public boolean withRelationship(Person person){
        for(int i = 0; i < relationship.size(); i++){
            if(relationship.get(i).getPerson().equals(person)) {
                return true;
            }
        }return false;
    }

    public int relatedIndex(Person person){
        for (int i = 0; i < relationship.size(); i++){
            if(relationship.get(i).getPerson().equals(person)){
                return i;
            }
        }return -1;
    }


    public boolean hasRelationship(){
        if(relationship.size() > 0){
            return true;
        }return false;
    }

    public void printFamily() {
        for (int i = 0; i < relationship.size(); i++) {
            if(relationship.get(i).getType().equals("parent") || relationship.get(i).getType().equals("dependent")){
                System.out.println(getName() + " is " + relationship.get(i).getType() + " of " + relationship.get(i).getPerson().getName());
            }

        }
    }

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
