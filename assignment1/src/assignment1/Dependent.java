package assignment1;


//author Chern Jian Lee


public class Dependent extends Person{
    public Dependent(String name, int age, String gender, String status, boolean image) {
        super(name, age, gender, status, image);
    }

    public void addRelationship(String type, Person person){
        if (withinRange(person)){
            if(withRelationship(person)){
                System.out.println(getName() + " and " + person.getName() + " are already in relationship");
            }else{
                getRelationship().add(new Relationship(type, person));
                person.getRelationship().add(new Relationship(type, this));
                System.out.println(this.getName() + " is now friend of " + person.getName());
            }
        }else{
            System.out.println("! Cannot Add Friend With " + person.getName() + " !\n" +
                               "! Children Can Only Have Friends With At Most 3 Year Age Gap !.");
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
        System.out.println("===================================\n" +
                           "Print Profile of " + getName() + "\n" +
                           "-----------------------------------\n" +
                           "Name   -> " + getName() + "\n" +
                           "Age    -> " + getAge() + "\n" +
                           "Gender -> " + getGender() + "\n" +
                           "Status -> " + getStatus() + "\n" +
                           "Image  -> " + (getImage()?"Switched On":"Switched Off") + "\n" +
                           "-----------------------------------" );
        if(hasRelationship()){
            printRelation();
            printFamily();
        }else{
            System.out.println(getName() + " does not have any relationship");
        }
        System.out.println("===================================");
    }
}


