package com.example.kms;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SousChefRequestLeaveController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane MenuScroll;

    @FXML
    private Label PromptMessage;

    @FXML
    private Button goToMealPrep;

    @FXML
    private Button goToRequestLeave;

    @FXML
    private Button goToSchedule;

    @FXML
    private Button homePage;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private DatePicker LeaveEndDatePicker;

    @FXML
    private DatePicker leaveStartDatePicker;

    @FXML
    private Button requestLeaveButton;

    @FXML
    private Button signoutButton;
    private Stage primaryStage;

    //THIS BUTTON SENDS A REQUEST TO SERVER TO SAVE
    @FXML
    void onRequestLeaveButton(ActionEvent event)
    {
        Data data = Data.getDataInstance();
        EmployeeKMS currEmp = data.getMessage().getEmployeeObject();//getting current employee instance


        //----CONVERTING TASK PICKER DATES TO STRING-------
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date1 =Date.from(leaveStartDatePicker.getValue().atStartOfDay(defaultZoneId).toInstant());
        Date date2 =Date.from(LeaveEndDatePicker.getValue().atStartOfDay(defaultZoneId).toInstant());

        String strStartDate = simpleDateFormat.format(date1);//formatting input date
        String strEndDate = simpleDateFormat.format(date2);//formatting input date
        System.out.println("Date str : "+strStartDate+" "+ strEndDate);
        //-------------------------------------------------

        SousChef sousChef = SousChef.getInstance();

        ArrayList<Ingredients> ingList = new ArrayList<Ingredients>();

        //Setting attributes of sous chef object
        sousChef.InitializeStaticInstance(currEmp.getFirstName(), currEmp.getLastName(), currEmp.getUsername(),
                currEmp.getPassword(), currEmp.getEmployeeType(), ingList);


        //Sending server request
        sousChef.RequestForLeave(strStartDate,strEndDate);

    }

    @FXML
    void GoToMealPrepPressed(ActionEvent event) {

        try {
            changeScene("SousChefMealPrep.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    void GoToScheduleButtonPressed(ActionEvent event) {

        try {
            changeScene("SousChefSchedule.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void HoverHandled(MouseEvent event) {

    }

    @FXML
    void RequestLeaveButtonPressed(ActionEvent event) {

        try {
            changeScene("SousChefRequestLeave.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    void homePageButtonPressed(ActionEvent event) {

        try {
            changeScene("SousChefHomePage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    void signOutButtonPressed(ActionEvent event) {

        try {
            changeScene("LoginPage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    void initialize() {
        InitializeLeaveDatePicker();//Initializing date pickers

    }

    //Generic function to change scene
    public void changeScene(String fxml, ActionEvent event) throws IOException {

        //------Following code changes scene to Schedule-------
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        //-----------------------------------------------------

    }

    void InitializeLeaveDatePicker()
    {
        DatePickerSkin datePickerSkin = new DatePickerSkin(leaveStartDatePicker);
        Node popupContent = datePickerSkin.getPopupContent();

        //-----Code Below disables the date cells of date picker which are older than today--
        leaveStartDatePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0 );
                if(date.compareTo(today) < 0) {
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });
        //-----------------------------------------------------------------------------------
        datePickerSkin = new DatePickerSkin(LeaveEndDatePicker);
        popupContent = datePickerSkin.getPopupContent();

        //-----Code Below disables the date cells of date picker which are older than today--
        LeaveEndDatePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0 );
                if(date.compareTo(today) < 0) {
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });
        //-----------------------------------------------------------------------------------

    }
}
