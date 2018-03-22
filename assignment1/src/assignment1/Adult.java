package assignment1;

//author Chern Jian Lee

public class Adult extends Person{
    private boolean hasDependent = false;

    private Adult partner;
    private Dependent dependent;



    public Adult(String name, int age, String gender, String status, boolean image) {
        super(name, age, gender, status, image);
    }

    public boolean getHasDependent() {
        return hasDependent;
    }

    public Adult getPartner() {
        return partner;
    }

    public Dependent getDependent() {
        return dependent;
    }

    public void setHasDependent(boolean hasDependent) {
        this.hasDependent = hasDependent;
    }

    public void setPartner(Adult partner) {
        this.partner = partner;
    }

    public void setDependent(Dependent dependent) {
        this.dependent = dependent;
    }


    public void addDependent(Adult adult, Dependent dependent){
        if(withRelationship(adult)){
            int findIndex = relatedIndex(adult);
            getRelationship().get(findIndex).setType("partner");
        }else{
            getRelationship().add(new Relationship("partner", adult));
        }
        getRelationship().add(new Relationship("parent", dependent));
        dependent.getRelationship().add(new Relationship("dependent", this));
        this.hasDependent = true;
        this.partner = adult;
        this.dependent = dependent;
    }


    public void addRelationship(String type, Adult adult){
        if(withRelationship(adult)){
            System.out.println(getName() + " is already in relationship with " + adult.getName());
        }else{
            getRelationship().add(new Relationship(type, adult));
            adult.getRelationship().add(new Relationship(type, this));
            System.out.println(this.getName() + " is now " + type + " of " + adult.getName());
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
        }else{
            System.out.println(getName() + " does not have any relationship");
        }
        if(hasDependent){
            printFamily();
        }else{
            System.out.println(getName() +  " does not have any dependent");
        }
        System.out.println("===================================");
    }


}