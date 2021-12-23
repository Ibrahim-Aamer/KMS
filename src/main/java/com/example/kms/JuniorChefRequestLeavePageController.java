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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class JuniorChefRequestLeavePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label EmployeeName;

    @FXML
    private DatePicker LeaveEndDatePicker;

    @FXML
    private Button ManageInventory;

    @FXML
    private ScrollPane MenuScroll;

    @FXML
    private Button PlaceOrder;

    @FXML
    private Label PromptMessage;

    @FXML
    private Button RequestLeave;

    @FXML
    private Button Schedule;

    @FXML
    private Button homePage;

    @FXML
    private DatePicker leaveStartDatePicker;

    @FXML
    private Button requestLeaveButton;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button signoutButton;
    private Stage primaryStage;

    @FXML
    void ManageInventoryButtonPressed(ActionEvent event) {
        try {
            changeScene("JuniorChefManageInventory.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void PlaceOrderButtonPressed(ActionEvent event) {
        try {
            changeScene("JuniorChefPlaceOrder.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void RequestLeaveButtonPressed(ActionEvent event) {
        try {
            changeScene("JuniorChefRequestLeave.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void goToSchedule(ActionEvent event) {

        try {
            changeScene("JuniorChefSchedule.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void homePageButtonPressed(ActionEvent event) {
        try {
            changeScene("JuniorChefHomePage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

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

        JuniorChef juniorChef = JuniorChef.getInstance();


        //Setting attributes of sous chef object
        juniorChef.InitializeStaticInstance(currEmp.getFirstName(), currEmp.getLastName(), currEmp.getUsername(),
                currEmp.getPassword(), currEmp.getEmployeeType());


        //Sending server request
        juniorChef.RequestForLeave(strStartDate,strEndDate);

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

}
