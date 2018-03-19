package assignment1;

import java.util.ArrayList;

public class Dependent extends Person{
    private boolean hasParent = true;

    public Dependent(String name, int age, String gender) {
        super(name, age, gender);
    }

    public Dependent(String name, int age, String gender, String status) {
        super(name, age, gender, status);
    }
//
//    public void addParents(Adult adult1, Adult adult2){
//        adult1.addDependent(adult2, this);
//        adult2.addDependent(adult1, this);
//        this.hasParent = true;
//    }
//need to be fixed
    public void addRelationship(String type, Dependent dependent){
        if (withinRange(dependent)){
            if(withRelationship(dependent)){
                System.out.println(getName() + " and " + dependent.getName() + " are already in relationship");
            }else{
                getRelationship().add(new Relationship(type, dependent));
                dependent.getRelationship().add(new Relationship(type, this));
            }
        }else{
            System.out.println("Out of age range.");
        }
    }

    private boolean withinRange(Dependent dependent){
        if(dependent.getAge() > 2 && dependent.getAge() < 16){
            if(Math.abs(dependent.getAge()- getAge()) >= 3){
                return true;
            }else{
                return false;
            }
        }return false;
    }

    public void addRelationship(String type, Person person){
        if (withinRange(person)){
            if(withRelationship(person)){
                System.out.println(getName() + " and " + person.getName() + " are already in relationship");
            }else{
                getRelationship().add(new Relationship(type, person));
                person.getRelationship().add(new Relationship(type, this));
            }
        }else{
            System.out.println("Out of age range.");
        }
    }

    private boolean withinRange(Person person){
        if(person.getAge() > 2 && person.getAge() < 16){
            if(Math.abs(person.getAge()- getAge()) <= 3){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    @Override
    public void printProfile() {
        System.out.println("===================================");
        System.out.println("Print Profile of " + getName());
        System.out.println("-----------------------------------");
        System.out.println("Name   -> " + getName());
        System.out.println("Age    -> " + getAge());
        System.out.println("Gender -> " + getGender());
        System.out.println("Status -> " + getStatus());
        if(hasRelationship()){
            printRelation();
            printFamily();
        }else{
            System.out.println(getName() + " does not have any relationship");
        }
        System.out.println("===================================");
    }
}

