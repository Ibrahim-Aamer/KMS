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
import javafx.scene.control.TextArea;
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

public class HeadChefCreateMenuController implements Initializable {
    @FXML
    private TextArea Description;

    @FXML
    private Label EmployeeName;

    @FXML
    private TextField Imagepath;

    @FXML
    private ScrollPane MenuScroll;

    @FXML
    private TextField Price;

    @FXML
    private TextField ProductName;

    @FXML
    private Label PromptMessage;

    @FXML
    private Button SubmitProduct;
    @FXML
    private Button AddMembers;

    @FXML
    private Button UpdateMenu;

    @FXML
    private Button goToRequestLeave;

    @FXML
    private Button goToRequestLeave1;

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
    void UpdateMenuButtonPressed(ActionEvent event) {
        try {
            changeScene("HeadChefUpdateMenu.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }


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
    void CreateMenu(ActionEvent event) {
      if(event.getSource()== SubmitProduct)
      {
          String name=ProductName.getText();
          String Image=Imagepath.getText();
          Double P=Double.parseDouble(Price.getText());
          String descrption=Description.getText();
          if(name!=""&& Image!="" && P!=0 && descrption!="" )
          {
              HeadChef Obj=HeadChef.getInstance();
              Obj.AddNewProduct(name,Image,P,descrption);
              PromptMessage.setText("Product has been added to the Menu");
              //Obj.SendProducts();
              Clear();
          }
          else
          {
              PromptMessage.setText("You have left some of the fields Empty");
          }
      }
    }


    public void Clear()
    {
        ProductName.setText("");
        Price.setText("0");
        Imagepath.setText("");
        Description.setText("");
    }
    public boolean IsDouble(String d)
    {
        boolean res=true;
        for(int i=0 ; i<d.length() ; i++)
        {
            if(d.charAt(i)<48 || d.charAt(i)>57 || d.charAt(i)!=46)
            {
                res=false;
            }
        }
        return res;
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
    public void changeScene(String fxml, ActionEvent event) throws IOException {

        //------Following code changes scene to Schedule-------
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        //-----------------------------------------------------

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Data data = Data.getDataInstance();

        try {
            RefreshLists(data.getMessage().getEmployeeObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //Page Refreshed

        HeadChef object=HeadChef.getInstance();

        ProductName.setText("");
        Price.setText("0");
        Imagepath.setText("");
        Description.setText("");
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
