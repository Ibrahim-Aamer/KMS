package com.example.kms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HeadChefHomePageController implements Initializable {

    public Label firstNameLabel;
    public Label secondNameLabel;
    public Label userNameLabel;
    public Label empTypeLabel;
    @FXML
    private Button AddMembers;

    @FXML
    private Button CreateMenu;

    @FXML
    private Label EditProduct;

    @FXML
    private Label EditProduct1;

    @FXML
    private Label EmployeeName;

    @FXML
    private ScrollPane MenuScroll;

    @FXML
    private Button UpdateMenu;

    @FXML
    private Button homePage;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button signoutButton;
    private Stage primaryStage;

    @FXML
    void AddMembersButtonPressed(ActionEvent event) {
        try {
            changeScene("HeadChefAddMembers.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void CreateMenuButtonPressed(ActionEvent event) {
        try {
            changeScene("HeadChefCreateMenu.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    void HoverHandled(MouseEvent event) {

    }

    @FXML
    void UpdateMenuButtonPressed(ActionEvent event) {
        try {
            changeScene("HeadChefUpdateMenu.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
    public void changeScene(String fxml, ActionEvent event) throws IOException {

        //------Following code changes scene to Schedule-------
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        //-----------------------------------------------------

    }
    @FXML
    void homePageButtonPressed(ActionEvent event) {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Data data = Data.getDataInstance();

        EmployeeKMS emp = data.getMessage().getEmployeeObject();

        firstNameLabel.setText(emp.getFirstName());
        secondNameLabel.setText(emp.getLastName());
        userNameLabel.setText(emp.getUsername());
        empTypeLabel.setText(emp.getEmployeeType());

    }


}
