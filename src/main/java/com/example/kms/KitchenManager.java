package com.example.kms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class KitchenManager extends EmployeeKMS {



    //Default constructor
    //KitchenManager() {}


    //Parameterized constructor
    KitchenManager(String fname, String lname, String username, String password, String empType)
    {
        //idCounter++;//incrementing ID counter

        this.firstName = fname;
        this.lastName = lname;
        this.username = username;
        this.password = password;
        this.employeeType = empType;
    }

    public void AssignSchedule(EmployeeKMS assignedTo, Task task)
    {
        try (Socket socket = new Socket("localhost", 4470)) {

            System.out.println("welcome client");
            //Socket socket = new Socket("localhost", 4444);
            System.out.println("Client connected");
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Ok");
            Message message = new Message("KitchenManger-AssignTask");

            message.setEmpAssignedto(assignedTo);
            message.setAssignedTask(task);

            //Writing username and password into message
            //message.setUsername(usernameField.getText());
            //message.setPassword(passwordField.getText());

            os.writeObject(message);
            System.out.println("Waiting for server ...");

            System.out.println("EMP : " + assignedTo.getFirstName());
            System.out.println("Task date: "+task.getTaskDate());

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

    //Function to add Machine related task into KMS
    public void MachineryMaintenance(Task newTask)
    {
        try (Socket socket = new Socket("localhost", 4470)) {

            System.out.println("welcome client");
            //Socket socket = new Socket("localhost", 4444);
            System.out.println("Client connected");
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Ok");
            Message message = new Message("KitchenManger-AddTask");

            //Writing new Task into message
            message.setNewTask(newTask);

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



}
