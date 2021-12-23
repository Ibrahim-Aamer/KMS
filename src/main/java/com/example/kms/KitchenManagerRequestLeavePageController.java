package com.example.kms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class KitchenManagerRequestLeavePageController implements Initializable {

    @FXML
    private ListView<String> AllLeavesListView;

    @FXML
    private Button LeaveAcknowledgeButton;

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


    public void LeaveAcknowledgeButton(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        Data data = Data.getDataInstance();
        Message msg = data.getMessage();

        ArrayList<LeaveRequest> leaveRequests = msg.getAllLeaveRequests();

        KitchenManager kitchenManager = new KitchenManager(msg.getEmployeeObject().getFirstName(),msg.getEmployeeObject().getLastName(),
                msg.getEmployeeObject().getUsername(),msg.getEmployeeObject().getPassword(),
                msg.getEmployeeObject().getEmployeeType());

        //Sending Acknowledge message to server
        kitchenManager.AcknowledgeLeaveRequest(leaveRequests.get(selectedLeave));

        //Refreshing list view
        RefreshLists(msg.getEmployeeObject());
        AllLeavesListView.getItems().clear();
        AllLeavesListView.getItems().addAll(this.getLeaveRequestsStrList());
    }

    int selectedLeave = -1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //--------CODE TO INITIALIZE LIST VIEW OF EMPLOYEES-------------------------------

        ArrayList<String > listStr = this.getLeaveRequestsStrList();

        AllLeavesListView.getItems().addAll(listStr);
        AllLeavesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                selectedLeave=AllLeavesListView.getSelectionModel().getSelectedIndex();//getting current selection

                System.out.println(selectedLeave);
            }
        });
        //-------------------------------------------------------------------------------
    }

    public ArrayList<String> getLeaveRequestsStrList()
    {
        ArrayList<String> strList = new ArrayList<String>();

        Data data = Data.getDataInstance();
        Message msg = data.getMessage();

        ArrayList<LeaveRequest> leaveRequests = msg.getAllLeaveRequests();

        for(int c=0; c<leaveRequests.size() ; c++)
        {
            strList.add(leaveRequests.get(c).getEmployeeName() + "-" +leaveRequests.get(c).getStartDate() + "-to-" + leaveRequests.get(c).getEndDate());
        }

        return strList;
    }


    void RefreshLists(EmployeeKMS currEmp) throws IOException, ClassNotFoundException {


        try (Socket socket = new Socket("localhost", 4470)) {

            System.out.println("welcome client");
            //Socket socket = new Socket("localhost", 4444);
            System.out.println("Client connected");
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Ok");
            Message message = new Message("LoginQuery");

            //Writing username and password into message
            message.setUsername(currEmp.username);
            message.setPassword(currEmp.password);

            os.writeObject(message);
            System.out.println("Waiting for server ...");

            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            Message returnMessage = (Message) is.readObject();
            System.out.println("return Message is : " + returnMessage.getQuery());

            //Storing return Message into data
            Data data = Data.getDataInstance();
            data.setMessage(returnMessage);
            //System.out.println(data.getMessage().getEmployeeObject().getEmployeeType());


            socket.close();

            // closing the scanner object
            //sc.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
