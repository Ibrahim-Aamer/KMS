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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HeadChefUpdateMenuController implements Initializable {
    @FXML
    private Button AddMembers;

    @FXML
    private Button CreateMenu;

    @FXML
    private Button DeleteButton;

    @FXML
    private TextArea Description;

    @FXML
    private Label EditProduct;

    @FXML
    private Label EditProduct1;

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
    private ListView<String> ProductsList;

    @FXML
    private Label PromptMessage;

    @FXML
    private Button SubmitButton;

    @FXML
    private Button UpdateMenu;

    @FXML
    private Button homePage;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button signoutButton;
    int index=0;
    ObservableList<String> items = FXCollections.observableArrayList ();
    private Stage primaryStage;

    @FXML
    void UpdateMenu(ActionEvent event) throws IOException, ClassNotFoundException {
        if(event.getSource()==SubmitButton)
        {
            String PN=ProductName.getText();
            String ImagePath=Imagepath.getText();
            Double price=Double.parseDouble(Price.getText());
            String des=Description.getText();
            HeadChef  object =HeadChef.getInstance();
            object.setProduct(index,PN,ImagePath,price,des);

            Data data = Data.getDataInstance();
            EmployeeKMS emp = data.getMessage().getEmployeeObject();
            this.RefreshLists(emp);

            HeadChef hc = HeadChef.getInstance();
            hc.setProducts(data.getMessage().getProductsList());

            ProductsList.getItems().clear();
            ProductsList.getItems().addAll(this.getNewProductListStr());


            PromptMessage.setText("Product has been edited");

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
    void DeleteMenuProduct(ActionEvent event) throws IOException, ClassNotFoundException {

        if(event.getSource()==DeleteButton)
        {
            HeadChef  object =HeadChef.getInstance();
            object.deleteProduct(index);

            //ResetList(index);
            Data data = Data.getDataInstance();
            EmployeeKMS emp = data.getMessage().getEmployeeObject();
            this.RefreshLists(emp);

            HeadChef hc = HeadChef.getInstance();
            hc.setProducts(data.getMessage().getProductsList());

            ProductsList.getItems().clear();
            ProductsList.getItems().addAll(this.getNewProductListStr());

            Imagepath.setText("");
            Description.setText("");
            Price.setText("");
            ProductName.setText("");
            PromptMessage.setText("Product has been deleted from the Menu");

        }
    }

    public ArrayList<String> getNewProductListStr()
    {
        ArrayList<String> strList = new ArrayList<String>();

        HeadChef hc = HeadChef.getInstance();

        for(int c=0 ; c<hc.getProducts().size();c++)
        {
            strList.add(hc.getProducts().get(c).getName());
        }

        return strList;
    }

    public void ResetList(int index)
    {
        ProductsList.getItems().remove(index);
    }


    @FXML
    void ListViewDisplayHandled(MouseEvent event) {
       index= ProductsList.getSelectionModel().getSelectedIndex();
       HeadChef object= HeadChef.getInstance();
       ProductName.setText(object.getProducts().get(index).getName());
       Imagepath.setText(object.getProducts().get(index).getImgPath());
       Price.setText(Double.toString(object.getProducts().get(index).getPrice()));
       Description.setText(object.getProducts().get(index).getDescription());


    }

    @FXML
    void HoverHandled(MouseEvent event) {

    }

    @FXML
    void UpdateMenuButtonPressed(ActionEvent event) {


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

    public ArrayList<Product> MakeProducts()
    {
        ArrayList<Product> products=new ArrayList<Product>();
        Product obj=new Product();
        obj.setName("karahi");
        obj.setImgPath("karahi");
        obj.setPrice(3434);
        obj.setDescription("delicious");
        products.add(obj);
        obj=new Product();
        obj.setName("lolnokhana");
        obj.setImgPath("gaib");
        obj.setPrice(3434);
        obj.setDescription("lol");
        products.add(obj);
        obj=new Product();
        obj.setName("Vegan anda");
        obj.setImgPath("anda");
        obj.setPrice(345);
        obj.setDescription("lol are u for real");
        products.add(obj);

     return products;
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

        object.setProducts(data.getMessage().getProductsList());

        for(int i=0 ; i<object.getProducts().size() ;i++)
        {
            items.add(object.getProducts().get(i).getName());
        }
        ProductsList.setItems(items);


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

            SousChef sousChef = SousChef.getInstance();
            EmployeeKMS emp = data.getMessage().getEmployeeObject();

            //Sous chef initialized
            sousChef.InitializeStaticInstance(emp.getFirstName(),emp.getLastName(), emp.getUsername(),
                    emp.getEmployeeType(),emp.getPassword(),data.getMessage().getIngredientsList());

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
