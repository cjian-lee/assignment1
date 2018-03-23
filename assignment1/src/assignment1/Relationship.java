package assignment1;


//author Chern Jian Lee

public class Relationship {

    private String type;
    private Person person;

    //constructor to initialize a 1 to 1 relationship
    public Relationship(String type, Person person) {
        this.type = type;
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


}
