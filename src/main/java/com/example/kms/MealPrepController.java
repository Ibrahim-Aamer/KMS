package com.example.kms;

import com.example.kms.Ingredients;
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

public class MealPrepController implements Initializable {


    @FXML
    private TextArea Description;

    @FXML
    private TextField IngredientName;

    @FXML
    private TextField IngredientQuantity;

    @FXML
    private ListView<String> ListIngredients;

    @FXML
    private TextField MealPrepName;

    @FXML
    private ScrollPane MenuScroll;

    @FXML
    private Label PromptMessage;

    @FXML
    private Button SubmitIngredient;

    @FXML
    private Button SubmitMeal;

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
    ObservableList<String> items = FXCollections.observableArrayList ();
    ArrayList<Ingredients> ingredients;
    boolean IngredientsAdded=false;
    int Index = 0;

    private Stage primaryStage;


    @FXML
    void GoToScheduleButtonPressed(ActionEvent event) throws IOException {
        if(event.getSource()==goToSchedule)
        {
            Parent root = FXMLLoader.load(getClass().getResource("SousChefSchedule.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void HoverHandled(MouseEvent event) {

    }
    @FXML
    void SelectIngredient(MouseEvent event) {
        Index=ListIngredients.getSelectionModel().getSelectedIndex();
        SousChef obj=SousChef.getInstance();
        Ingredients ing=obj.getIngredients().get(Index);
        IngredientName.setText(ing.getName());
        IngredientQuantity.setText(Integer.toString(ing.getQuantity()));
    }
    @FXML
    void RequestLeaveButtonPressed(ActionEvent event) throws IOException {
        if(event.getSource()==goToRequestLeave)
        {
            Parent root = FXMLLoader.load(getClass().getResource("SousChefRequestLeave.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    void homePageButtonPressed(ActionEvent event) throws IOException {
        if(event.getSource()==homePage)
        {
            Parent root = FXMLLoader.load(getClass().getResource("SousChefHomePage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
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
    void GoToMealPrepPressed(ActionEvent event) throws IOException {



    }

    @FXML
    void MealPrepHandled(ActionEvent event) throws QuantityIsZero, IngredientNotAdded {
        if(event.getSource()==SubmitIngredient)
        {
            int quantity=Integer.parseInt(IngredientQuantity.getText());
            String name=IngredientName.getText();
            if(quantity==0) {
                PromptMessage.setText("The Quantity Of Ingredient Entered is zero. Cannot Register");
                throw new QuantityIsZero("The Quantity Of Ingredient Entered is zero. Cannot Register");
            }
            if(name=="")
            {
                PromptMessage.setText("You haven't added any Ingredient" );
                throw new IngredientNotAdded("You haven't added any Ingredient");
            }
            if(quantity!=0 && name!="")
            {
                SousChef obj=SousChef.getInstance();

                int quan=Integer.parseInt(IngredientQuantity.getText());
                int remains=obj.deductIngredientQuantity(quan,Index);
                if(remains<0)
                {
                    PromptMessage.setText("The Ingredient in the Inventory has become zero");
                    obj.getIngredients().get(Index).setQuantity(0);
                }
                else
                {
                    obj.getIngredients().get(Index).setQuantity(remains);
                }
                ingredients.add(obj.getIngredients().get(Index));
                ClearIngredient();
                IngredientsAdded=true;
            }
        }
        if(event.getSource()==SubmitMeal)
        {
            if(IngredientsAdded==true)
            {
                if(MealPrepName.getText()!="" && Description.getText()!="" )
                {

                    Task obj=CreateTask();
                    System.out.println("Test :"+MealPrepName.getText()+"   "+ Description.getText());

                    SousChef.getInstance().SendMealPrep(obj);
                    System.out.println("Test :"+MealPrepName.getText()+"   "+ Description.getText());
                    PromptMessage.setText("Meal Prep Submitted" );
                    IngredientsAdded=false;
                    Clear();
                }
                else
                {
                    PromptMessage.setText("You have left some of the fields empyty" );
                    throw new IngredientNotAdded("You have left some of the fields empyty");
                }
            }
            else
            {
                PromptMessage.setText("You haven't added any Ingredient" );
                throw new IngredientNotAdded("You haven't added any Ingredient");
            }
        }
    }
    public void Clear()
    {
        IngredientQuantity.setText("0");
        IngredientName.setText("");
        MealPrepName.setText("");
        Description.setText("");
        IngredientsAdded=false;
        ingredients.clear();
    }


    public Task CreateTask()
    {
        Task object=new Task();
        // object.setEmployee(null);
        object.setTaskName(MealPrepName.getText());
        object.setTaskDate(null);
        object.setTaskDetails(TurnToString());
        return object;
    }
    public void ClearIngredient()
    {
        IngredientQuantity.setText("0");
        IngredientName.setText("");


    }
    public String TurnToString()
    {
        String result="";
        result+="Meal Prep Name: ";
        result+=MealPrepName.getText();
        result+="\n";
        result+="Ingredients: \n";

        for(int i=0 ; i<ingredients.size() ;i++)
        {
            result+=ingredients.get(i).getName();
            result+=" x ";
            result+=Integer.toString(ingredients.get(i).getQuantity());
            result+="\n";
        }
        result+="Description: \n";
        result+=Description.getText();
        result+="\n";

        return result;
    }
    public ArrayList<Ingredients> makeIngredients()
    {


        ArrayList<Ingredients> array=new ArrayList<Ingredients>();
        Ingredients obj=new Ingredients(2,"coconut");
        array.add(obj);
        obj=new Ingredients(2,"milk");
        array.add(obj);
        obj=new Ingredients(2,"sugar");
        array.add(obj);
        return array;

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Data data = Data.getDataInstance();
        try {
            RefreshLists(data.getMessage().getEmployeeObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //Objects refreshed...


        ingredients= new ArrayList<Ingredients>();
        SousChef obj=SousChef.getInstance();

       // obj.setIngredients(obj.getIngredients());//set the ingredients that will come from the server
        for(int i=0 ; i<obj.getIngredients().size() ;i++)
        {
            items.add(obj.getIngredients().get(i).getName());
        }
        ListIngredients.setItems(items);
        IngredientQuantity.setText("0");
        IngredientName.setText("");
        MealPrepName.setText("");
        Description.setText("");
        IngredientsAdded=false;
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

