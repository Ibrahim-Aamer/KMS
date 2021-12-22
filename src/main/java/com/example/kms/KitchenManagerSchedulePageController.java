package com.example.kms;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class KitchenManagerSchedulePageController implements Initializable {

    @FXML
    private Button goToMachineMaintenance;

    @FXML
    private Button goToMakeSchedule;

    @FXML
    private Button goToRequestLeave;

    @FXML
    private Button goToSchedule;

    @FXML
    private Button homePage;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button signoutButton;

    @FXML
    private Label taskDateLabel;

    @FXML
    private DatePicker taskDatePicker;

    @FXML
    private Label taskDetailsLabel;

    @FXML
    private Label taskNameLabel;

    @FXML
    private ListView<String> tasksList;
    private Stage primaryStage;

    int selectedTask = -1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Initializing date task picker
        this.InitializeTaskDatePicker();

        //str

        //--------CODE TO INITIALIZE LIST VIEW OF EMPLOYEES-------------------------------
        //tasksList.getItems().addAll(strEmployeesList);
        tasksList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                selectedTask=tasksList.getSelectionModel().getSelectedIndex();//getting current selection

                System.out.println(selectedTask);
            }
        });
        //-------------------------------------------------------------------------------

    }

    @FXML
    void GoToMakeScheduleButtonPressed(ActionEvent event) {
        try {
            System.out.println("machine page");
            changeScene("KitchenManagerMakeSchedulePage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    void GoToScheduleButtonPressed(ActionEvent event) {

    }

    @FXML
    void RequestLeaveButtonPressed(ActionEvent event) {
        try {

            changeScene("KitchenManagerRequestLeavePage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    void goToMachineMaintenanceButtonPressed(ActionEvent event) {

        try {

            changeScene("KitchenManagerMachineMaintenancePage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    void homePageButtonPressed(ActionEvent event) {

        try {

            changeScene("KitchenManagerHomePage.fxml", event);
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


    //Generic function to change scene
    public void changeScene(String fxml, ActionEvent event) throws IOException, IOException {

        //------Following code changes scene to Schedule-------
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        this.primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        //-----------------------------------------------------

    }

    void InitializeTaskDatePicker()
    {
        DatePickerSkin datePickerSkin = new DatePickerSkin(taskDatePicker);
        Node popupContent = datePickerSkin.getPopupContent();

        //-----Code Below disables the date cells of date picker which are older than today--
        taskDatePicker.setDayCellFactory(picker -> new DateCell() {
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



