package assignment1;
import java.util.ArrayList;

public class Adult extends Person{
    private boolean hasDependent = false;

    private Adult partner;
    private Dependent dependent;



    public Adult(String name, int age, String gender) {
        super(name, age, gender);
    }

    public Adult(String name, int age, String gender, String status) {
        super(name, age, gender, status);
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

	@Override
	public void printProfile() {
		// TODO Auto-generated method stub
		
	}
  
}


    