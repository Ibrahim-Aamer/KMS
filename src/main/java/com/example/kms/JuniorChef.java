package com.example.kms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class JuniorChef extends EmployeeKMS{
    static JuniorChef chef;
    ArrayList<Ingredients> ingredients;

    public static JuniorChef getInstance()
    {
        if(chef==null)
        {
            chef=new JuniorChef();
        }
        return chef;
    }

    public void AddIngredient(String name, int quantity)
    {

        Ingredients ing=new Ingredients(quantity,name);
        //ingredients.add(ing);
        SendIngredients(ing);
    }

    public void AddSupplierOrder(String name, String number , String des)
    {
        Task object=new Task();
        String Result=TurnToString(name,number,des);
        object.setTaskName("Place Order To Supplier");
        object.setTaskDetails(Result);
        object.setTaskDate(null);
        SendSupplierOrder(object);
    }
    public String TurnToString(String name, String number , String des)
    {
        String result = "";
        result+="Supplier: ";
        result+=name;
        result+="\n";
        result+="Number: ";
        result+=number;
        result+="\n";
        result+="Descrption: ";
        result+=des;
        result+="\n";
        return result;

    }

    public void InitializeStaticInstance(String fname, String lname, String username, String password, String empType) {
        //idCounter++;//incrementing ID counter

        chef.firstName = fname;
        chef.lastName = lname;
        chef.username = username;
        chef.password = password;
        chef.employeeType = empType;
        //chef.ingredients = ingList;
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


    public void SendSupplierOrder(Task obj)
    {
        try (Socket socket = new Socket("localhost", 4470)) {

            System.out.println("welcome client");
            //Socket socket = new Socket("localhost", 4444);
            System.out.println("Client connected");
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Ok");
            Message message = new Message("Junior Chef-AddSupplyOrder");

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
    public void SendIngredients(Ingredients ing)
    {
        try (Socket socket = new Socket("localhost", 4470)) {

            System.out.println("welcome client");
            //Socket socket = new Socket("localhost", 4444);
            System.out.println("Client connected");
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Ok");
            Message message = new Message("Junior Chef-Add New Ingredient");

            //Writing new Ingredient into message
            message.setNewIngredient(ing);

            message.setIngredientsList(this.ingredients);//writing updated ingredients list into msg


            //Writing username and password into message
            //message.setUsername(usernameField.getText());
            //message.setPassword(passwordField.getText());

            os.writeObject(message);
            System.out.println("Waiting for server ...");

            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            Message returnMessage = (Message) is.readObject();
            System.out.println("Return Message is : " + returnMessage.getQuery());


            socket.close();

        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
