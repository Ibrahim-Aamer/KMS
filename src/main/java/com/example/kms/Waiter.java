package com.example.kms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Waiter extends EmployeeKMS {
    static Waiter  waiter;
    Menu menu;

    public static Waiter getInstance()
    {
        if(waiter==null)
            waiter=new Waiter();
        return waiter;
    }
    public void SendOrder(Order obj)
    {
        try (Socket socket = new Socket("localhost", 4470)) {

            System.out.println("welcome client");
            //Socket socket = new Socket("localhost", 4444);
            System.out.println("Client connected");
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Ok");
            Message message = new Message("Waiter-Add Order");

            //Writing new order into message
            message.setNewOrder(obj);



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
    public void setMenuProducts() throws IOException, ClassNotFoundException {
        Data data = Data.getDataInstance();
        this.RefreshLists(data.getMessage().getEmployeeObject());

        menu=Menu.GetInstance();
        //sets arraylist of products
        this.menu.setProducts(data.getMessage().getProductsList());
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


}
