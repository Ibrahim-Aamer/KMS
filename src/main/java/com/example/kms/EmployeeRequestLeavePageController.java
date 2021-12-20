package com.example.kms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeRequestLeavePageController {

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
    void ActionHandled(ActionEvent event) {

    }



    @FXML
    void homePageButtonPressed(ActionEvent event) {

        System.out.println("Hello Home Page");

        //Changing scene
        try {
            changeScene("EmployeeHomePage.fxml", event);
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
        try {
            changeScene("KitchenManagerMakeSchedulePage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void GoToScheduleButtonPressed(ActionEvent event)
    {
        //Changing scene
        try {
            changeScene("EmployeeSchedulePage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void RequestLeaveButtonPressed(ActionEvent event) {


    }



    @FXML
    void initialize() {

        this.primaryStage = primaryStage;

        Data data = Data.getDataInstance();
        data.setWord("I am data");

        /*DatePickerSkin datePickerSkin = new DatePickerSkin(dateSelector);
        Node popupContent = datePickerSkin.getPopupContent();

        //-----Code Below disables the date cells of date picker which are older than today--
        dateSelector.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0 );
                if(date.compareTo(today) < 0) {
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });*/
        //-----------------------------------------------------------------------------------
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

}
