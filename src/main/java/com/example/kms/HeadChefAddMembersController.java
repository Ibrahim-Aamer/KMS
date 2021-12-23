package com.example.kms;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class HeadChefAddMembersController implements Initializable {

    @FXML
    private Button AddMembers;

    @FXML
    private Button AuthenticateButton;

    @FXML
    private Button CreateMenu;

    @FXML
    private Label EditProduct;

    @FXML
    private Label EditProduct1;

    @FXML
    private Label EmployeeName;

    @FXML
    private ListView<String> EmployeeTypesList;

    @FXML
    private ScrollPane MenuScroll;

    @FXML
    private TextField Password;
    @FXML
    private TextField FirstName;
    @FXML
    private TextField LastName;

    @FXML
    private Label PromptMessage;

    @FXML
    private TextField Username;

    @FXML
    private Button homePage;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button signoutButton;

    int index=0;
    ObservableList<String> items = FXCollections.observableArrayList ("Waiter","Sous Chef","Kitchen Manager","Junior Chef");
    private Stage primaryStage;

    public void Clear()
    {
        Username.setText("");
        Password.setText("");
        FirstName.setText("");
        LastName.setText("");
    }

    @FXML
    void AuthenticateEmployee(ActionEvent event) {
        if(event.getSource()==AuthenticateButton)
        {
            String username=Username.getText();
            String password=Password.getText();
            String f1=FirstName.getText();
            String f2=LastName.getText();
            String employeeType=EmployeeTypesList.getSelectionModel().getSelectedItem();

            //flag to check unique username
            boolean flag = true;

            Data data = Data.getDataInstance();

            //Validating username
            for(int c=0 ; c<data.getMessage().getEmployeeList().size() ; c++ )
            {
                //If username already existing flag = false
                if(Username.getText().equals(data.getMessage().getEmployeeList().get(c).getUsername()))
                {
                    flag = false;
                }
            }

            if(flag == true)
            {
                HeadChef object = HeadChef.getInstance();
                object.CreateEmployee(username, password, f1, f2, employeeType);
                PromptMessage.setText("Employee has been authenticated");
                Clear();
            }
            else
            {
                PromptMessage.setText("Username Already Taken");
            }
        }


    }


    @FXML
    void HoverHandled(MouseEvent event) {

    }


    @FXML
    void homePageButtonPressed(ActionEvent event) {


        try {
            changeScene("HeadChefHomePage.fxml", event);
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
    void AddMembersButtonPressed(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Data data = Data.getDataInstance();

        try {
            this.RefreshLists(data.getMessage().getEmployeeObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        index=0;
        HeadChef object= HeadChef.getInstance();

        EmployeeTypesList.setItems(items);
    }

    public void ListViewDisplayHandled(MouseEvent mouseEvent) {
    }

    public void CreateMenuButtonPressed(ActionEvent event) {
        try {
            changeScene("HeadChefCreateMenu.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
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

    public void UpdateMenuButtonPressed(ActionEvent event) {
        try {
            changeScene("HeadChefUpdateMenu.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
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

            HeadChef headChef = HeadChef.getInstance();
            EmployeeKMS emp = data.getMessage().getEmployeeObject();

            //Head chef initialized
            headChef.InitializeStaticInstance(emp.getFirstName(),emp.getLastName(), emp.getUsername(),
                    emp.getEmployeeType(),emp.getPassword(),data.getMessage().getProductsList());

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


