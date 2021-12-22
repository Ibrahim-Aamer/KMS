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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    { //Getting data static instance
        Data data = Data.getDataInstance();


        //-----First Getting fresh message from server--------
        try {
            this.RefreshLists(data.getCurrentEmployeeKms());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //-----------------------------------------------------

        Message msg = data.getMessage();


        //Initializing date task picker
        this.InitializeTaskDatePicker();


        //--------CODE TO INITIALIZE LIST VIEW OF EMPLOYEES-------------------------------

        ArrayList<String > tasksStr = new ArrayList<String>();

        tasksList.getItems().addAll(tasksStr);
        tasksList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                selectedTask=tasksList.getSelectionModel().getSelectedIndex();//getting current selection

                if(selectedTask < staticTaskList.size()) {
                    taskDateLabel.setText(staticTaskList.get(selectedTask).getTaskDate());
                    taskDetailsLabel.setText(staticTaskList.get(selectedTask).getTaskDetails());
                    taskNameLabel.setText(staticTaskList.get(selectedTask).getTaskName());
                }
                System.out.println(staticTaskList.size());
                System.out.println(selectedTask);
            }
        });
        //-------------------------------------------------------------------------------



    }


    @FXML
    void taskDatePickerPressed(ActionEvent event) {


        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date =Date.from(taskDatePicker.getValue().atStartOfDay(defaultZoneId).toInstant());
        String dateStr = simpleDateFormat.format(date);//formatting input date
        System.out.println("Date str : "+dateStr);

        ArrayList<String > tasksStr = this.getTasksListStr(dateStr);

        for(int c=0 ; c<tasksStr.size();c++)
        {
            System.out.println("tttt : " + tasksStr.get(c));
        }

        tasksList.getItems().clear();
        tasksList.getItems().addAll(tasksStr);



    }

    static List<Task> staticTaskList = new ArrayList<Task>();//static list of tasks for description

    //function to return task array list string
    public ArrayList<String> getTasksListStr(String strDate)
    {
        Data data = Data.getDataInstance();
        EmployeeKMS currEmp = data.getMessage().getEmployeeObject();//getting current employee instance


        ArrayList<String> strTaskList = new ArrayList<String>();//new array list

        staticTaskList = new ArrayList<Task>();

        List<Task> tasksList = currEmp.getTasks();//getting tasks list

        for(int c=0 ; c<tasksList.size() ; c++)
        {
            System.out.println("Dates : " +strDate+" "+tasksList.get(c).getTaskDate());
            if(strDate.equals(tasksList.get(c).getTaskDate()))
            {
                staticTaskList.add(tasksList.get(c));
                strTaskList.add(tasksList.get(c).getTaskName());//adding same date task to list
            }
        }

        return strTaskList;
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

            //-----------------USELESS------------------------
            List<EmployeeKMS> empskms = data.getMessage().getEmployeeList();

            for(int i =0 ; i<empskms.size(); i++) {

                System.out.println("Name : "+empskms.get(i).getFirstName()+" "+empskms.get(i).getLastName());

                List<Task> tasks = empskms.get(i).getTasks();
                for (int c = 0; c < tasks.size(); c++) {
                    System.out.println(tasks.get(c).getTaskDetails());
                }
            }
            //-----------------------------------------------------

            socket.close();

            // closing the scanner object
            //sc.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}



