package com.example.kms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class HeadChef extends EmployeeKMS{

    static HeadChef chef=null;
    ArrayList<Product> products;

    public static void setChef(HeadChef chef) {
        HeadChef.chef = chef;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void CreateEmployee(String username , String pass , String f1, String f2, String emptype)
    {
        EmployeeKMS object=new EmployeeKMS();
        object.setFirstName(f1);
        object.setLastName(f2);
        object.setUsername(username);
        object.setPassword(pass);
        object.setEmployeeType(emptype);
        SendEmployee(object);

    }
    //write server implementation
    public void SendEmployee(EmployeeKMS obj)
    {
        try (Socket socket = new Socket("localhost", 4470)) {

            System.out.println("welcome client");
            //Socket socket = new Socket("localhost", 4444);
            System.out.println("Client connected");
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Ok");
            Message message = new Message("Head Chef-Add New Member");

            //Writing new Employee to be added to message
            message.setNewMember(obj);


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

    public void InitializeStaticInstance(String fname, String lname, String username, String password, String empType, ArrayList<Product> ingList) {
        //idCounter++;//incrementing ID counter

        chef.firstName = fname;
        chef.lastName = lname;
        chef.username = username;
        chef.password = password;
        chef.employeeType = empType;
        chef.products = ingList;
    }

    //write the client side of the code and initialize the products array
    public void FillProducts()
    {

    }
    public static HeadChef getInstance()
    {
        if(chef==null)
        {
            chef=new HeadChef();
        }
        return chef;
    }
    HeadChef()
    {
        chef=null;
    }

    public void AddNewProduct(String name, String Image,Double price, String description)
    {
        Product object=new Product();

        object.setName(name);
        object.setPrice(price);
        object.setImgPath(Image);
        object.setDescription(description);
        products.add(object);

        this.SendProduct(object);
    }
    public void setProduct(int Index,String name, String Image,Double price, String description)
    {
        products.get(Index).setName(name);
        products.get(Index).setPrice(price);
        products.get(Index).setDescription(description);
        products.get(Index).setImgPath(Image);

        //Sending edited product to server
        this.SendProduct(products.get(Index));
    }
    public void deleteProduct(int Index)
    {
        //Getting product to be deleted
        Product prodDel = this.getProducts().get(Index);

        try (Socket socket = new Socket("localhost", 4470)) {

            System.out.println("welcome client");
            //Socket socket = new Socket("localhost", 4444);
            System.out.println("Client connected");
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Ok");
            Message message = new Message("Head Chef-Delete Menu Item");

            //Writing new new Product into message
            message.setNewProduct(prodDel);


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

    //server implemetation where the head chef will send back an arraylist of products to the server
    public void SendProduct(Product newProduct)
    {
        try (Socket socket = new Socket("localhost", 4470)) {

            System.out.println("welcome client");
            //Socket socket = new Socket("localhost", 4444);
            System.out.println("Client connected");
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Ok");
            Message message = new Message("Head Chef-Create Menu");

            //Writing new new Product into message
            message.setNewProduct(newProduct);


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
