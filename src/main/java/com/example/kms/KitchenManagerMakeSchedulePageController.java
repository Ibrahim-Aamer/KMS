package com.example.kms;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.synedra.validatorfx.Check;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.ResourceBundle;

public class KitchenManagerMakeSchedulePageController implements Initializable {

    @FXML
    private ListView<String> EmployeesListView;

    @FXML
    private ListView<String> TasksListView;

    @FXML
    private Button assignTaskButton;

    @FXML
    private DatePicker taskDatePicker;

    @FXML
    private Label taskDetails;

    @FXML
    private Label taskName;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane MenuScroll;

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

    private Stage primaryStage;

    @FXML
    void AssignTaskButtonPressed(ActionEvent event)
    {
        System.out.println(taskDatePicker.getValue());

        Data data = Data.getDataInstance();
        Message msg = data.getMessage();

        KitchenManager kitchenManager = new KitchenManager(msg.getEmployeeObject().getFirstName(),msg.getEmployeeObject().getLastName(),
                msg.getEmployeeObject().getUsername(),msg.getEmployeeObject().getPassword(),
                msg.getEmployeeObject().getEmployeeType());

        List<EmployeeKMS> empskms = data.getMessage().getEmployeeList();
        List<Task> tasks = data.getMessage().getTasksList();

        //Adding selected data into task
        tasks.get(selectedTask).setTaskDate(taskDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));


        //Calling this function which assigns
        kitchenManager.AssignTaskToEmployee(empskms.get(selectedEmployee),tasks.get(selectedTask));


    }

    @FXML
    void taskDatePickerPressed(ActionEvent event) {

    }

    @FXML
    void ActionHandled(ActionEvent event) {


    }



    @FXML
    void homePageButtonPressed(ActionEvent event) {

        System.out.println("Hello Home Page");
        //Changing scene
        try {
            changeScene("KitchenManagerHomePage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    void signOutButtonPressed(ActionEvent event){

        System.out.println("Sign Out Button Pressed Home Page");

        /*System.out.println("Client Started");

        try {

            Socket soc= new Socket ("localhost", 9806);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a String!");
            String str= userInput.readLine();
            PrintWriter out = new PrintWriter (soc.getOutputStream(),true);
            out.println(str);
            BufferedReader in = new BufferedReader(new InputStreamReader (soc.getInputStream()));
            System.out.println(in.readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }*/

        //Changing scene
        try {
            changeScene("LoginPage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void GoToMakeScheduleButtonPressed(ActionEvent event) {

        //Changing scene

    }

    @FXML
    void GoToScheduleButtonPressed(ActionEvent event)
    {
        //Changing scene
        try {
            changeScene("KitchenManagerSchedulePage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void RequestLeaveButtonPressed(ActionEvent event) {

        //Changing scene
        try {
            changeScene("KitchenManagerRequestLeavePage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }



    @FXML
    void initialize() {

        this.primaryStage = primaryStage;

        Data data = Data.getDataInstance();
        data.setWord("I am data");

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

    private int selectedEmployee = -1;//holds index of selected employee
    private int selectedTask = -1;//holds index of selected task

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Getting data static instance
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


        InitializeTaskDatePicker();//function to initialize red dates of datepicker

        //Initializing array lists to be diaplayed on page
        ArrayList<String> strEmployeesList = this.getEmployeesList();
        ArrayList<String> strTaskList = this.getTasksList();




        //--------CODE TO INITIALIZE LIST VIEW OF EMPLOYEES-------------------------------
        EmployeesListView.getItems().addAll(strEmployeesList);
        EmployeesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                selectedEmployee=EmployeesListView.getSelectionModel().getSelectedIndex();//getting current selection

                System.out.println(selectedEmployee);
            }
        });
        //-------------------------------------------------------------------------------

        //--------CODE TO INITIALIZE LIST VIEW OF Tasks----------------------------------
        TasksListView.getItems().addAll(strTaskList);
        TasksListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                selectedTask=TasksListView.getSelectionModel().getSelectedIndex();//getting current selection

                //Setting values of taskName and askDetails
                taskName.setText(data.getMessage().getTasksList().get(selectedTask).getTaskName());
                taskDetails.setText(data.getMessage().getTasksList().get(selectedTask).getTaskDetails());

                System.out.println(selectedTask);
            }
        });
        //-------------------------------------------------------------------------------


    }


    @FXML
    void HoverHandled(MouseEvent event) {

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

    public ArrayList<String> getEmployeesList()
    {
        Data data = Data.getDataInstance();
        Message msg = data.getMessage();

        ArrayList<String> employeesList = new ArrayList<String>();

        List<EmployeeKMS> empskms = data.getMessage().getEmployeeList();

        for(int i =0 ; i<empskms.size(); i++) {

            System.out.println("Name : "+empskms.get(i).getFirstName()+" "+empskms.get(i).getLastName());

            employeesList.add(empskms.get(i).getID()+" "+empskms.get(i).getFirstName()+" "+empskms.get(i).getLastName());
        }

        return employeesList;

    }

    public ArrayList<String> getTasksList()
    {
        Data data = Data.getDataInstance();
        Message msg = data.getMessage();

        ArrayList<String> taskList = new ArrayList<String>();

        List<Task> tasks = data.getMessage().getTasksList();

        for(int i =0 ; i<tasks.size(); i++) {

            taskList.add(tasks.get(i).getTaskName());
        }

        return taskList;

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
