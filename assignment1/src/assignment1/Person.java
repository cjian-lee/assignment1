package assignment1;
import java.util.ArrayList;

//authour Chern Jian Lee


public abstract class Person {

    private String name;
    private int age;
    private String gender;
    private String status = "Not Available";
    private ArrayList<Relationship> relationship = new ArrayList<>();


    //constructors to choose whether to reset status
    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    public Person(String name, int age, String gender, String status) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.status = status;
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

    public void display_Details() {
        System.out.println(this.name);
        System.out.println(this.age);
        System.out.println(this.gender);
        System.out.println(this.status);

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


    //to remove "this person" from his related person's relationship list
    public void removeRelated(){
        for(int i = 0; i < getRelationship().size(); i++){
            int removedIndex = getRelationship().get(i).getPerson().relatedIndex(this);
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

//    public void printRelationship(){
//        if(hasRelationship()) {
//            for (int i = 0; i < relationship.size(); i++) {
//                Person relatedPerson = relationship.get(i).getPerson();
//                System.out.println(getName() + " and " + relatedPerson.getName() + " are " + relationship.get(i).getType());
//            }
//        }else{
//            System.out.println(getName() + " currently has no relationship");
//        }
//    }

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


