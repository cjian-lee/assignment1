package assignment1;


/**
 * 
 * an inherited class from Person Class 
 * allow user to add friend and partner relationship between adults
 * print out his profiles
 *
 * @author Chern Jian Lee
 * @studentNo. s3373345
 * @version 1.8
 * @since 1.0
 */


public class Adult extends Person{
    private boolean hasDependent = false;

    /**
     * to store an adult's partner and his dependent
     */
    private Adult partner;
    private Dependent dependent;


    public Adult(String name, int age, String gender, String status, boolean image) {
        super(name, age, gender, status, image);
    }

    /**
     * getters to return instance variables
     * @return
     */
    public boolean getHasDependent() {
        return hasDependent;
    }

    public Adult getPartner() {
        return partner;
    }

    public Dependent getDependent() {
        return dependent;
    }

    /**
     * setters to change the value of instance variables
     * @param hasDependent
     */
    public void setHasDependent(boolean hasDependent) {
        this.hasDependent = hasDependent;
    }

    public void setPartner(Adult partner) {
        this.partner = partner;
    }

    public void setDependent(Dependent dependent) {
        this.dependent = dependent;
    }


    /**
     * to add a dependent and a partner at the same time
     * @param adult
     * @param dependent
     */
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

    /**
     * to add a friend with another adult
     * @param type
     * @param adult
     */
    public void addRelationship(String type, Adult adult){
        if(withRelationship(adult)){
            System.out.println(getName() + " is already in relationship with " + adult.getName());
        }else{
            getRelationship().add(new Relationship(type, adult));
            adult.getRelationship().add(new Relationship(type, this));
            System.out.println(this.getName() + " is now " + type + " of " + adult.getName());
        }
    }
    
    /**
     * to remove a "partner" relationship with another person 
     */
    public void removeRelatedPartner(){
        for(int i = 0; i < getRelationship().size(); i++){
            int removedIndex = getRelationship().get(i).getPerson().relatedIndex(this);
            if(getRelationship().get(i).getType().equals("partner")){
                ((Adult) getRelationship().get(i).getPerson()).setPartner(null);
                this.setPartner(null);
                getRelationship().get(i).getPerson().getRelationship().remove(removedIndex);
                getRelationship().remove(i);
            }
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