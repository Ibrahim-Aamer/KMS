package com.example.kms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


public class LoginPageController {



    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Button loginButton;

    @FXML
    private TextField password;

    @FXML
    private Label prompt;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button signoutButton;


    private Stage primaryStage;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        //loadingImage.setVisible(false);

    }



    @FXML
    void loginButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException, UnknownHostException, InterruptedException {
        //System.out.println("login button pressed");
        //loadingImage.setVisible(true);

        authenticateLoginFromServer(usernameField.getText(),passwordField.getText());

        //Changing scene
        try
        {
            Data data = Data.getDataInstance();
            Message msg = data.getMessage();
            EmployeeKMS currEmp = data.getMessage().getEmployeeObject();

            if(currEmp.getEmployeeType().equals("Kitchen Manager"))
            {
                data.setCurrentEmployeeKms(currEmp);

                changeScene("KitchenManagerHomePage.fxml", event);
            }
            else if(currEmp.getEmployeeType().equals("Sous Chef"))
            {
                data.setCurrentEmployeeKms(currEmp);

                changeScene("SousChefHomePage.fxml", event);
            }
            else if(currEmp.getEmployeeType().equals("Head Chef"))
            {
                data.setCurrentEmployeeKms(currEmp);

                changeScene("HeadChefHomePage.fxml", event);
            }
            else if(currEmp.getEmployeeType().equals("Junior Chef"))
            {
                data.setCurrentEmployeeKms(currEmp);

                changeScene("JuniorChefHomePage.fxml", event);
            }
            else if(currEmp.getEmployeeType().equals("System"))
            {
                data.setCurrentEmployeeKms(currEmp);

                changeScene("System.fxml", event);
            }
            else if(currEmp.getEmployeeType().equals("Waiter"))
            {
                data.setCurrentEmployeeKms(currEmp);

                changeScene("Checkout.fxml", event);
            }
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    void authenticateLoginFromServer(String username, String password) throws IOException, ClassNotFoundException {

        System.out.println(username+" "+password);

        try (Socket socket = new Socket("localhost", 4470)) {

            System.out.println("welcome client");
            //Socket socket = new Socket("localhost", 4444);
            System.out.println("Client connected");
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Ok");
            Message message = new Message("LoginQuery");

            //Writing username and password into message
            message.setUsername(usernameField.getText());
            message.setPassword(passwordField.getText());

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

