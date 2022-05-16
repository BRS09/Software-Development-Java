public abstract class Person {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String plumbingProblem;

    public abstract void setFirstName(String firstName);
    public abstract void setLastName(String lastName);
    public abstract void setAddress(String address);
    public abstract void setPhoneNumber(String phoneNumber);
    public abstract void setPlumbingProblem(String plumbingProblem);
    public abstract String getFirstName();
    public abstract String getLastName();
    public abstract String getAddress();
    public abstract String getPhoneNumber();
    public abstract String getPlumbingProblem();
    public abstract String getFullName();
}
