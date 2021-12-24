package com.example.kms;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class WaiterController implements Initializable{
	MenuController MenuCard;

    @FXML
    private GridPane MenuGrid;
    @FXML
    private Button CheckOut;



    @FXML
    void TakeOrder(ActionEvent event) throws IOException {
        if(event.getSource()==CheckOut) {

			Waiter object=Waiter.getInstance();
			OrderController obj = new OrderController();
			//setting the table number where the order has to be delivered

			Double total = MenuCard.getTotal();
			int tableNumber = MenuCard.getTableno();
			ArrayList<CartProduct> orders=MenuCard.getCart();
			ArrayList<OrderProduct> order_products=new ArrayList<OrderProduct>();


			for(int i=0 ; i<orders.size() ; i++)
			{
				order_products.add(new OrderProduct());
				order_products.get(i).setQuantity(orders.get(i).getQuantity());
				order_products.get(i).setProductName(orders.get(i).getName());
			}
		    Order sendOrder= new Order(total,tableNumber,order_products);
            object.SendOrder(sendOrder);

		}
		MenuCard.ClearCart();
    }
	//turn the cart products into string separated by '*' to send to the server side

	public MenuController getMenuCard() {
		return MenuCard;
	}

	public void setMenuCard(MenuController menuCard) {
		MenuCard=menuCard;
	}


	public OrderController EditOrder()
	{
		return null;
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {



		System.out.println("Checkout--");


		Waiter object=Waiter.getInstance();
		try {
			object.setMenuProducts();//has to initialize the menu products
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		//after this you can initialize the waiter object
		MenuCard=null;
		   FXMLLoader fxmlLoader = new FXMLLoader();
		       fxmlLoader.setLocation(this.getClass().getResource("Menu.fxml"));
		       AnchorPane anchorPane = null;
		       System.out.println("we been selected");
			   try {
				anchorPane = (AnchorPane)fxmlLoader.load();
				MenuCard=(MenuController)fxmlLoader.getController() ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 

           this.MenuGrid.add(anchorPane, 0,0);
           MenuGrid.setMargin(anchorPane, new Insets(0.0D));
		// TODO Auto-generated method stub

	}

}
