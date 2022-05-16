import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class SemesterProject extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //Variables used in program
        double priceOfParts = 0.0;
        Person client = new Client();
        Job job = new Job();
        Invoice invoice = new Invoice();
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(15, 10, 15, 10));
        vBox.setAlignment(Pos.CENTER);
        HBox hBox = getHBox();
        vBox.getChildren().add(hBox);
        Button clientButton = setButton();
        Button jobButton = setButton();
        Button invoiceButton = setButton();
        Button dashboardButton = setButton();
        Button submitButton = new Button("Submit");
        GridPane clientGridPane = setGridPane();
        GridPane jobGridPane = setGridPane();
        GridPane invoiceGridPane = setGridPane();
        setClientGridPane(clientGridPane);
        setJobGridPane(jobGridPane);
        setInvoiceGridPane(invoiceGridPane);
        Accordion accordion = new Accordion();
        accordion.setPadding(new Insets(10, 10, 10, 10));
        submitButton.setMinHeight(20);
        submitButton.setMinWidth(100);
        setHBox(hBox, dashboardButton, clientButton, jobButton, invoiceButton);
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();

        try {
            //Dashboard button clicked
            hBox.getChildren().get(0).setOnMouseClicked(e -> {
                //If any of the other grid panes are showing, remove them and add the accordion
                if(vBox.getChildren().contains(clientGridPane) || vBox.getChildren().contains(jobGridPane) || vBox.getChildren().contains(invoiceGridPane)){
                    vBox.getChildren().removeAll(clientGridPane, jobGridPane, invoiceGridPane, submitButton);
                    vBox.getChildren().add(accordion);
                }
                //If the accordion is showing, remove it
                else if(vBox.getChildren().contains(accordion)){
                    vBox.getChildren().remove(accordion);
                }
                //Add the accordion to the VBox view
                else{
                    vBox.getChildren().add(accordion);
                }
                //Size the stage to the scene
                primaryStage.sizeToScene();
            });

            //Client button is clicked
            hBox.getChildren().get(1).setOnMouseClicked(e -> {
                //If any of the other views are showing, remove them and set the client grid pane to the VBox view
                if(vBox.getChildren().contains(jobGridPane) || vBox.getChildren().contains(invoiceGridPane) || vBox.getChildren().contains(accordion)){
                    vBox.getChildren().removeAll(jobGridPane, invoiceGridPane, accordion, submitButton);
                }
                setVBoxGrid(clientGridPane, vBox, primaryStage);
            });

            //Job button is clicked
            hBox.getChildren().get(2).setOnMouseClicked(e -> {
                //If any of the other views are showing, remove them and set the job grid pane to the VBox view
                if(vBox.getChildren().contains(clientGridPane) || vBox.getChildren().contains(invoiceGridPane) || vBox.getChildren().contains(accordion)){
                    vBox.getChildren().removeAll(clientGridPane, invoiceGridPane, accordion, submitButton);
                }
                setVBoxGrid(jobGridPane, vBox, primaryStage);
            });

            //Invoice button is clicked
            hBox.getChildren().get(3).setOnMouseClicked(e -> {
                //If any of the other grid panes are showing, remove them and set the invoice grid pane on the VBox view
                if(vBox.getChildren().contains(clientGridPane) || vBox.getChildren().contains(jobGridPane) || vBox.getChildren().contains(accordion)){
                    vBox.getChildren().removeAll(clientGridPane, jobGridPane, accordion, submitButton);
                }
                setVBoxGrid(invoiceGridPane, submitButton, vBox, primaryStage);
            });

            //Add button is clicked
            jobGridPane.getChildren().get(2).setOnMouseClicked(e -> {
                //If the TextField is not empty store the entered value into the partsRequired value in the Job class.
                if(!((TextField) jobGridPane.getChildren().get(1)).getText().isEmpty()){
                    job.setPartsRequired(((TextField)jobGridPane.getChildren().get(1)).getText());
                    //Reset the TextField text
                    ((TextField) jobGridPane.getChildren().get(1)).setText("");
                    //Request focus back to the TextField
                    jobGridPane.getChildren().get(1).requestFocus();
                }
            });

            //Submit button is clicked
            submitButton.setOnMouseClicked(e -> {

                //Set the variables in the Client class from each TextField
                client.setFirstName(((TextField) clientGridPane.getChildren().get(1)).getText());
                client.setLastName(((TextField) clientGridPane.getChildren().get(3)).getText());
                client.setAddress(((TextField) clientGridPane.getChildren().get(5)).getText());
                client.setPhoneNumber(((TextField) clientGridPane.getChildren().get(7)).getText());
                client.setPlumbingProblem(((TextField) clientGridPane.getChildren().get(9)).getText());

                //If the hours on the job TextField is not empty, store it in the hoursOnJob variable in the Job class
                if(!((TextField) jobGridPane.getChildren().get(4)).getText().isEmpty()){
                    job.setHoursOnJob(Integer.parseInt(((TextField) jobGridPane.getChildren().get(4)).getText()));
                }

                //Set the variables in the Invoice class from the TextFields
                invoice.setHourlyRate(Double.parseDouble(((TextField) invoiceGridPane.getChildren().get(1)).getText()));
                invoice.setTaxes(Double.parseDouble(((TextField) invoiceGridPane.getChildren().get(5)).getText()));
                invoice.setOtherExpenses(Double.parseDouble(((TextField) invoiceGridPane.getChildren().get(7)).getText()));
                invoice.setTotal(Double.parseDouble(((TextField) invoiceGridPane.getChildren().get(3)).getText()), job);

                //Set up the dashboard with the information acquired
                setDashboard(client, job, invoice, accordion);
            });

            //When the accordion is clicked, size the stage to the scene
            accordion.heightProperty().addListener((obs, oldHeight, newHeight) -> primaryStage.sizeToScene());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //Method used to create the HBox used in the program
    public HBox getHBox(){
        HBox hBox = new HBox(1);
        hBox.setPadding(new Insets(10, 15, 10, 15));
        hBox.setStyle("#808080");
        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }

    //Method used to set the information and widgets needed in the HBox
    private void setHBox(HBox hBox, Button dashboardButton, Button clientButton, Button jobButton, Button invoiceButton){
        clientButton.setText("Client");
        jobButton.setText("Job");
        invoiceButton.setText("Invoice");
        dashboardButton.setText("Dashboard");
        hBox.getChildren().add(dashboardButton);
        hBox.getChildren().add(clientButton);
        hBox.getChildren().add(jobButton);
        hBox.getChildren().add(invoiceButton);
    }

    //Method used to create a button
    private Button setButton(){
        Button button = new Button();
        button.setMinHeight(30);
        button.setMinWidth(100);

        return button;
    }

    //Method used to create a grid pane
    private GridPane setGridPane(){
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(15, 15, 25, 15));
        gridPane.setVgap(10);
        gridPane.setHgap(5);
        return gridPane;
    }

    //Method used to set the widgets needed in the client grid pane
    private void setClientGridPane(GridPane clientGridPane){
        clientGridPane.add(new Label("First Name:"), 0, 0);
        clientGridPane.add(new TextField(), 1, 0);
        clientGridPane.add(new Label("Last Name:"), 0, 1);
        clientGridPane.add(new TextField(), 1, 1);
        clientGridPane.add(new Label("Address:"), 0, 2);
        clientGridPane.add(new TextField(), 1, 2);
        clientGridPane.add(new Label("Phone Number:"), 0, 3);
        clientGridPane.add(new TextField(), 1, 3);
        clientGridPane.add(new Label("Plumbing Problem:"), 0, 4);
        clientGridPane.add(new TextField(), 1, 4);
    }

    //Method used to set the widgets needed in the job grid pane
    private void setJobGridPane(GridPane jobGridPane){
        jobGridPane.add(new Label("Parts Required:"), 0, 0);
        jobGridPane.add(new TextField(), 1, 0);
        jobGridPane.getChildren().get(1).setId("partsRequired");
        jobGridPane.add(new Button("Add"), 2, 0);
        jobGridPane.add(new Label("Hours on Job:"), 0, 1);
        jobGridPane.add(new TextField(), 1, 1);
    }

    //Method used to set the widgets needed in the invoice grid pane
    private void setInvoiceGridPane(GridPane invoiceGridPane){
        invoiceGridPane.add(new Label("Hourly Rate:"), 0, 0);
        invoiceGridPane.add(new TextField(), 1, 0);
        invoiceGridPane.add(new Label("Price of Parts:"), 0, 1);
        invoiceGridPane.add(new TextField(), 1, 1);
        invoiceGridPane.add(new Label("Taxes:"), 0, 2);
        invoiceGridPane.add(new TextField(), 1, 2);
        invoiceGridPane.add(new Label("Other Expenses:"), 0, 3);
        invoiceGridPane.add(new TextField(), 1, 3);
    }

    //Method used to set the widgets and information needed in the dashboard accordion
    private void setDashboard(Person client, Job job, Invoice invoice, Accordion accordion){
        GridPane gridPane = setGridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        TitledPane paneOne = new TitledPane(client.getFullName(), gridPane);
        if(client.getFirstName() != null){
            accordion.getPanes().add(paneOne);
            getAccordionPane(client, job, invoice, gridPane);
        }
        else{
            paneOne.setText("Add a client");
            accordion.getPanes().add(paneOne);
        }
    }

    //Method used to get and set the information in the grid pane within the accordion
    private void getAccordionPane(Person client, Job job, Invoice invoice, GridPane gridPane){
        Font font = Font.font("Verdana", FontWeight.EXTRA_BOLD,18);
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));

        gridPane.add(new Label("Client:"), 0, 0);
        ((Label) gridPane.getChildren().get(0)).setFont(font);
        gridPane.add(new Text(client.toString()), 0, 1);
        gridPane.add(new Label("Job:"), 0, 2);
        ((Label) gridPane.getChildren().get(2)).setFont(font);
        gridPane.add(new Text(job.toString()), 0, 3);
        gridPane.add(new Label("Invoice:"), 0, 4);
        ((Label) gridPane.getChildren().get(4)).setFont(font);
        gridPane.add(new Text(invoice.toString(numberFormat)), 0, 5);
    }

    //Method used to check if the TextFields all have information, if not disable the button, if so enable it
    private void validTable(GridPane gridPane, Button button){
        for (Node node : gridPane.getChildren()) {
            if(node instanceof TextField){
                ((TextField) node).textProperty().addListener((observableValue, oldVal, newVal) -> {
                    if(!newVal.trim().isEmpty() && isFull(gridPane)){
                        button.setDisable(false);
                    }
                    else{
                        button.setDisable(true);
                    }
                });
            }
        }
    }

    //Method used to check if all the TextFields contain information except the partsRequired node
    private boolean isFull(GridPane gridPane){
        for(Node node : gridPane.getChildren()){
            if(node instanceof TextField){
                if(((TextField) node).getText().trim().isEmpty() && !Objects.equals(node.idProperty().getValue(), "partsRequired")){
                    return false;
                }
            }
        }

        return true;
    }

    //Method used to set the grid contained within the VBox
    private void setVBoxGrid(GridPane gridPane, Button button, VBox vBox, Stage primaryStage){
        button.setDisable(!isFull(gridPane));

        //If the VBox contains a gridPane, remove it and size the stage to the scene
        if(vBox.getChildren().contains(gridPane)){
            vBox.getChildren().removeAll(gridPane, button);
            primaryStage.sizeToScene();
        }
        //If it does not contain a grid pane, add the grid pane to the view and size the stage to the scene
        else{
            vBox.getChildren().addAll(gridPane, button);
            primaryStage.sizeToScene();
        }

        //Check if the user has entered all the necessary information to submit the grid
        validTable(gridPane, button);
    }

    //Method overload used for GridPanes that do not contain a submit button
    private void setVBoxGrid(GridPane gridPane, VBox vBox, Stage primaryStage){
        if(vBox.getChildren().contains(gridPane)){
            vBox.getChildren().removeAll(gridPane);
            primaryStage.sizeToScene();
        }
        else{
            vBox.getChildren().addAll(gridPane);
            primaryStage.sizeToScene();
        }
    }
}
