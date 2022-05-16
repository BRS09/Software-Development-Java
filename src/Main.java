import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Predetermined variables to use in each class
        String firstName = "Brayden";
        String lastName = "Scott";
        String address = "999 W 9 St";
        String phoneNumber = "777-777-7777";
        String plumbingProblem = "Busted water line";
        ArrayList<String> partsRequired = new ArrayList<>();
        int hoursOnJob = 2;
        double hourlyRate = 90.00;
        double taxes = 10.00;
        double otherExpenses = 5.00;

        //Add the parts
        partsRequired.add("Crimp rings");
        partsRequired.add("Pex pipe");

        //Instantiate the classes
        Client client = new Client(firstName, lastName, address, phoneNumber, plumbingProblem);
        Job job = new Job(hoursOnJob, partsRequired);
        Invoice invoice = new Invoice(hourlyRate, taxes, otherExpenses);

        //Output the classes as strings with toString methods
        System.out.println(client);
        System.out.println(job);
        System.out.println(invoice);

        //Change the variables of the Client object
        client.setFirstName("John");
        client.setLastName("Doe");
        client.setAddress("999 John Doe St");
        client.setPhoneNumber("777-888-9999");
        client.setPlumbingProblem("Clogged toilet");

        //Change the variables of the Job object
        partsRequired.clear();
        partsRequired.add("Plunger");
        job.setHoursOnJob(6);

        //Change the variables of the Invoice object
        invoice.setHourlyRate(120.00);
        invoice.setTaxes(50.00);
        invoice.setOtherExpenses(45.00);

        //Output the objects as strings again
        System.out.println(client);
        System.out.println(job);
        System.out.println(invoice);
    }
}
