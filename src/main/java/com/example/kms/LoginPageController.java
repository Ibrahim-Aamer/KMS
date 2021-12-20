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
    void initialize() {
        //loadingImage.setVisible(false);
    }



    @FXML
    void loginButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException, UnknownHostException, InterruptedException {
        System.out.println("login button pressed");
        //loadingImage.setVisible(true);

        try {
            authenticateLoginFromServer(usernameField.getText(),passwordField.getText());
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

        //Changing scene
        try {
            //changeScene("EmployeeHomePage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    void authenticateLoginFromServer(String username, String password) throws IOException, ClassNotFoundException {

        System.out.println(username+" "+password);

        try (Socket socket = new Socket("localhost", 4444)) {

            System.out.println("welcome client");
            //Socket socket = new Socket("localhost", 4444);
            System.out.println("Client connected");
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Ok");
            Message message = new Message(15, "ibrahim aamer");
            Employee emp = new Employee("Hadia","Aamer",username,password,"3");
            message.setEmployee(emp);
            os.writeObject(message);
            System.out.println("Waiting for server ...");

            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            Message returnMessage = (Message) is.readObject();
            System.out.println("return Message is : " + returnMessage.getText());

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

