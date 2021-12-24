package com.example.kms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SystemDisplay extends EmployeeKMS{
    static SystemDisplay system;

    ArrayList<Order> orders;

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public static SystemDisplay getInstance()
    {
        if(system==null)
        {
            system=new SystemDisplay();
        }
        return system;
    }

    public void getOrdersFromServer()
    {
        try (Socket socket = new Socket("localhost", 4470)) {

            System.out.println("welcome client");
            //Socket socket = new Socket("localhost", 4444);
            System.out.println("Client connected");
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Ok");
            Message message = new Message("System-Request Orders");

            os.writeObject(message);
            System.out.println("Waiting for server ...");

            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            Message returnMessage = (Message) is.readObject();
            System.out.println("return Message is : " + returnMessage.getQuery());

            //Storing return Message into data
            Data data = Data.getDataInstance();
            data.setMessage(returnMessage);

            this.orders = data.getMessage().getOrdersList();

            for(int c=0 ; c<orders.size(); c++)
            {
                System.out.println("Order : "+c);
                for(int i=0; i<orders.get(c).getOrder_products().size();i++)
                {
                    System.out.println(orders.get(c).getOrder_products().get(i).getProductName());
                }
            }
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
