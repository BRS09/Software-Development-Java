public class Client extends Person {
    //Variables used for the client
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String plumbingProblem;

    //Default constructor
    public Client(){

    }

    //Constructor
    public Client(String firstName, String lastName, String address, String phoneNumber, String plumbingProblem){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.plumbingProblem = plumbingProblem;
    }

    //Sets the first name of the client
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    //Gets the first name of the client
    public String getFirstName(){
        return this.firstName;
    }

    //Sets the last name of the client
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    //Gets the last name of the client
    public String getLastName(){
        return this.lastName;
    }

    //Gets the full name of the client
    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

    //Sets the address of the client
    public void setAddress(String address){
        this.address = address;
    }

    //Gets the address of the client
    public String getAddress(){
        return this.address;
    }

    //Sets the phone number of the client
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    //Gets the phone number of the client
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    //Sets the clients plumbing problem
    public void setPlumbingProblem(String plumbingProblem){
        this.plumbingProblem = plumbingProblem;
    }

    //Gets the clients plumbing problem
    public String getPlumbingProblem(){
        return this.plumbingProblem;
    }

    //Outputs the Client class as a string
    @Override
    public String toString(){
        return "First name: " + this.firstName + "\n" + "Last name: " + this.lastName + "\n" + "Full name: " + getFullName() + "\n" + "Address: " + this.address + "\n" + "Phone number: " + this.phoneNumber + "\n" + "Plumbing problem: " + this.plumbingProblem;
    }
}
