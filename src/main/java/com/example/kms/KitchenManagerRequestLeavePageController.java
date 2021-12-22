package com.example.kms;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class KitchenManagerRequestLeavePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker LeaveEndDatePicker;

    @FXML
    private Button goToMachineMaintenance;

    @FXML
    private Button goToMakeScheduleBtn;

    @FXML
    private Button goToScheduleBtn;

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
    void OngoToScheduleBtn(ActionEvent event) {

        try {
            changeScene("KitchenManagerSchedulePage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }


    }

    @FXML
    void goToMachineMaintenanceButtonPressed(ActionEvent event) {

        try {
            changeScene("KitchenManagerMachineMaintenance.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    void goToMakeScheduleBtn(ActionEvent event)
    {
        try {
            changeScene("KitchenManagerMakeSchedulePage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void homePageButtonPressed(ActionEvent event)
    {
        try {
            changeScene("KitchenManagerHomePage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void onRequestLeaveButton(ActionEvent event)
    {
        try {
            changeScene("KitchenManagerRequestLeavePage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void signOutButtonPressed(ActionEvent event)
    {
        try {
            changeScene("LoginPage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void initialize()
    {
        this.InitializeTaskDatePicker(this.leaveStartDatePicker);
        this.InitializeTaskDatePicker(this.LeaveEndDatePicker);
    }




    void InitializeTaskDatePicker(DatePicker datePicker)
    {
        DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
        Node popupContent = datePickerSkin.getPopupContent();

        //-----Code Below disables the date cells of date picker which are older than today--
        datePicker.setDayCellFactory(picker -> new DateCell() {
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
