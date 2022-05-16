import java.text.NumberFormat;

public class Invoice extends Job {
    //Variables used in the Invoice class
    private double hourlyRate;
    private double taxes;
    private double otherExpenses;
    private double total;
    private double priceOfParts;

    //Default constructor
    public Invoice(){

    }

    //Constructor
    public Invoice(double hourlyRate, double taxes, double otherExpenses){
        this.hourlyRate = hourlyRate;
        this.taxes = taxes;
        this.otherExpenses = otherExpenses;
    }

    //Constructor without optional otherExpenses
    public Invoice(double hourlyRate, double taxes){
        this.hourlyRate = hourlyRate;
        this.taxes = taxes;
    }

    //Sets the hourly rate of contractor
    public void setHourlyRate(double hourlyRate){
        this.hourlyRate = hourlyRate;
    }

    //Gets the hourly rate of the contractor
    public double getHourlyRate(){
        return this.hourlyRate;
    }

    //Sets the taxes from the job
    public void setTaxes(double taxes){
        this.taxes = taxes;
    }

    //Gets the taxes from the job
    public double getTaxes(){
        return this.taxes;
    }

    //Sets the other expenses of the job
    public void setOtherExpenses(double otherExpenses){
        this.otherExpenses = otherExpenses;
    }

    //Gets the other expenses of the job
    public double getOtherExpenses(){
        return this.otherExpenses;
    }

    public void setTotal(double priceOfParts, Job job){
        this.priceOfParts = priceOfParts;
        this.total = total + (job.getHoursOnJob() * this.hourlyRate) + this.taxes + this.otherExpenses + priceOfParts;
    }

    public double getTotal(){
        return this.total;
    }

    //Outputs the Invoice class as a string
    public String toString(NumberFormat numberFormat){
        return "Hourly rate: " + numberFormat.format(this.hourlyRate) + "\n" + "Taxes: " + numberFormat.format(this.taxes) + "\n" + "Other expenses: " + numberFormat.format(this.otherExpenses) + "\n" + "Price of Parts: " + numberFormat.format(this.priceOfParts) + "\n" + "Total: " + numberFormat.format(this.total);
    }

}
