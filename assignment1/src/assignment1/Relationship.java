package assignment1;

import java.util.ArrayList;

public class Relationship {

    private String type;
    private Person person;
    private ArrayList<String> list = new ArrayList<String>();

    //constructor to initialize a 1 to 1 relationship
    public Relationship(String type, Person person) {
        this.type = type;
        this.person = person;
    }

    public Relationship(Person person) {
        this.person = person;
    }

    public String getType() {
        return type;
    }

    public Person getPerson() {
        return person;
    }

    public void setType(String type) {
        this.type = type;
    }

    //    public void addFriend() {
//    	list.add(person);
//    }
//
    public void removeFriend() {
        list.remove(person);
    }


}