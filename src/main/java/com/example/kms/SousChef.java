package com.example.kms;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class SousChef extends EmployeeKMS {

    static SousChef chef = null;
    ArrayList<Ingredients> ingredients;

    public void setIngredients(ArrayList<Ingredients> ing) { chef.ingredients = ing;
    }

    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }

    SousChef() {
        chef = null;


    }

    public int deductIngredientQuantity(int q,int Index)
    {
        int quan=ingredients.get(Index).getQuantity();
        int remians=quan-q;
        return remians;
    }
    public static SousChef getInstance() {
        if (chef == null) {
            chef = new SousChef();
        }
        return chef;

    }

    SousChef(String fname, String lname, String username, String password, String empType) {
        //idCounter++;//incrementing ID counter

        this.firstName = fname;
        this.lastName = lname;
        this.username = username;
        this.password = password;
        this.employeeType = empType;
    }

    public void InitializeStaticInstance(String fname, String lname, String username, String password, String empType, ArrayList<Ingredients> ingList) {
        //idCounter++;//incrementing ID counter

        chef.firstName = fname;
        chef.lastName = lname;
        chef.username = username;
        chef.password = password;
        chef.employeeType = empType;
        chef.ingredients = ingList;
    }

    //implements the request to the server where , the Ingredients Array is returned Back
    public void SendMealPrep(Task obj)
    {
        try (Socket socket = new Socket("localhost", 4470)) {

            System.out.println("welcome client");
            //Socket socket = new Socket("localhost", 4444);
            System.out.println("Client connected");
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Ok");
            Message message = new Message("Sous Chef-AddMealPrep");

            //Writing new Task into message
            message.setNewTask(obj);

            message.setIngredientsList(this.ingredients);//writing updated ingredients list into msg


            //Writing username and password into message
            //message.setUsername(usernameField.getText());
            //message.setPassword(passwordField.getText());

            os.writeObject(message);
            System.out.println("Waiting for server ...");

            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            Message returnMessage = (Message) is.readObject();
            System.out.println("Return Message is : " + returnMessage.getQuery());

            //Storing return Message into data
            //Data data = Data.getDataInstance();
            //data.setMessage(returnMessage);
            //System.out.println(data.getMessage().getEmployeeObject().getEmployeeType());


            socket.close();

            // closing the scanner object
            //sc.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    public void RequestForLeave(String startDate, String endDate)
    {
        try (Socket socket = new Socket("localhost", 4470)) {

            System.out.println("welcome client");
            //Socket socket = new Socket("localhost", 4444);
            System.out.println("Client connected");
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Ok");
            Message message = new Message("Leave-Request");

            LeaveRequest newLeaveRequest = new LeaveRequest(startDate,endDate,this.id,this.firstName+" "+this.lastName,this.employeeType);
            //message
            message.setNewLeaveRequest(newLeaveRequest);//writing new leave request in message


            os.writeObject(message);
            System.out.println("Waiting for server ...");

            //System.out.println("EMP : " + assignedTo.getFirstName());
            //System.out.println("Task date: "+task.getTaskDate());

            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            Message returnMessage = (Message) is.readObject();
            System.out.println("Return Message is : " + returnMessage.getQuery());

            //Storing return Message into data
            //Data data = Data.getDataInstance();
            //data.setMessage(returnMessage);
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