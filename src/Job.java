import java.util.ArrayList;

public class Job extends Client{
    //Variables used for Job class
    private ArrayList<String> partsRequired = new ArrayList<>();
    private int hoursOnJob;

    //Default constructor
    public Job(){

    }

    //Constructor
    public Job(int hoursOnJob, ArrayList<String> partsRequired){
        this.hoursOnJob = hoursOnJob;
        this.partsRequired = partsRequired;
    }

    //Sets the parts required for the job
    public void setPartsRequired(String partsRequired){
        this.partsRequired.add(partsRequired);
    }

    //Gets the parts required for the job
    public ArrayList<String> getPartsRequired(){
        return this.partsRequired;
    }

    //Sets the hours on the job
    public void setHoursOnJob(int hoursOnJob){
        this.hoursOnJob = hoursOnJob;
    }

    //Gets the hours on the job
    public int getHoursOnJob(){
        return this.hoursOnJob;
    }

    //Outputs the Job class as a string
    @Override
    public String toString(){
        return "Parts required: " + this.partsRequired + "\n" + "Hours on the job: " + this.hoursOnJob;
    }
}
