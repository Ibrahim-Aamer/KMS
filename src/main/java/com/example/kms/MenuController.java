package com.example.kms;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MenuController implements Initializable  {
	 @FXML
	    private GridPane CartGrid;

	    @FXML
	    private ComboBox<String> CashPayment;

	    @FXML
	    private GridPane CheckoutGrid;

	    @FXML
	    private Label Discount;

	    @FXML
	    private Label GST;

	    @FXML
	    private ScrollPane MenuScroll;

	    @FXML
	    private Label Subtotal;

	    @FXML
	    private Label Total;

	    @FXML
	    private GridPane grid;

	    @FXML
	    private ListView<String> listview;
	    

	    @FXML
	    private TextField Tableno;


    int CartRow=1;

    private ArrayList<CartController> cartproducts ;
    private Image image;

    private List<ItemController> itemController;




    public MenuController() {
    }
    public void setDiscount(Double value)
    {
    	Discount.setText(Double.toString(value));
    }
    public Double getDiscount()
    {
    	Double value=Double.parseDouble(Discount.getText());
    return value;
    }
    
    public void setTotal(Double value)
    {
    	Total.setText(Double.toString(value));
    }
    public Double getTotal()
    {
    	Double value=0.0;
    	if(Total.getText()!="0")
    	value=Double.parseDouble(Total.getText());
    
    return value;
    }

    public void ClearCart()
    {
    	int size=cartproducts.size();
    	cartproducts.clear();

    	Total.setText("0");
    	Subtotal.setText("0");

       // for(int i=0; i<CartGrid.getChildren().size() ; i++)        	
    	CartGrid.getChildren().clear();
    	CartRow=1;
    	
    }
    
    public  ArrayList<CartProduct> getCart()
    {
    	ArrayList<CartProduct> products=new ArrayList<CartProduct>();
    	for(int i=0 ; i<cartproducts.size(); i++)
    	{
    	CartProduct e=new CartProduct();
    	e.setName(cartproducts.get(i).getCartFood());
    	e.setPrice(cartproducts.get(i).getCartPrice());
    	e.setQuantity(cartproducts.get(i).getQuantity());
    	products.add(e);
    	}
    return products;
    }

    public void setTableno(int value)
    {
       Tableno.setText(Integer.toString(value));
    }
    public int getTableno()
    {
    	int val=0;
      //String value=Tableno.getText();
      if(Tableno!=null)
    	  val=Integer.parseInt(	Tableno.getText());
    return val;
    }
    
    public void setGST(Double value)
    {
    	GST.setText(Double.toString(value));
    }
    public Double getGST()
    {
    	Double value=Double.parseDouble(GST.getText());
    return value;
    }

    public void setSubtotal(Double value)
    {
    	Subtotal.setText(Double.toString(value));
    }
    public Double getSubtotal()
    {
    	Double value=Double.parseDouble(Subtotal.getText());
    return value;
    }

    private ArrayList<Product> getData() {
        ArrayList<Product> p = new ArrayList<Product>();
        Product obj = new Product();
        obj.setName("Kheer 250");
        obj.setPrice(232.99D);
        obj.setImgPath("pictures/cherry.png");
        p.add(obj);
        
        
        obj = new Product();
        obj.setName("Paratha");
        obj.setPrice(232.99D);
        obj.setImgPath("pictures/banana.png");
        p.add(obj);
        

        obj = new Product();
        obj.setName("Biryani");
        obj.setPrice(243.99D);
        obj.setImgPath("pictures/peach.png");
        p.add(obj);
        
        
        obj = new Product();
        obj.setName("Plain Karahi");
        obj.setPrice(232.99D);
        obj.setImgPath("pictures/orange.png");
        p.add(obj);
        
        
        obj = new Product();
        obj.setName("Kebab");
        obj.setPrice(232.99D);
        obj.setImgPath("pictures/mango.png");
        p.add(obj);
        
        
        obj = new Product();
        obj.setName("Mutton Karahi");
        obj.setPrice(232.99D);
        obj.setImgPath("pictures/kiwi.png");
        p.add(obj);

                return p;
    }


 
    public void initialize(URL location, ResourceBundle resources) {

        Menu object= Menu.GetInstance();
        cartproducts=new ArrayList<CartController>();
        itemController=new ArrayList<ItemController>();
        Tableno.setText("0");
        Discount.setText(Double.toString(object.getDiscount()));
        Total.setText("0");
        this.setGST(object.getGst());
        CashPayment.setValue(object.getPaymentMethod().get(0));
        CashPayment.setItems(object.getPaymentMethod());
        
        
        //setting out the checkout Button
		FXMLLoader fxmlLoader = new FXMLLoader();
	    


        //When the waiter login then products will be refreshed in the Menu

//        object.getProducts().addAll(this.getData());
        // GridPane

  
        int column = 0;
        int row = 1;
     
       try { 
       for(int i = 0; i < object.getProducts().size(); ++i)
		{
			  FXMLLoader fxmlLoader2 = new FXMLLoader();	
		      fxmlLoader2.setLocation(this.getClass().getResource("Product.fxml"));
		       AnchorPane anchorPane = null;
		  
			anchorPane = (AnchorPane)fxmlLoader2.load();
			itemController.add((ItemController)fxmlLoader2.getController()) ;
            itemController.get(i).setData((Product)object.getProducts().get(i));
		    if (column == 2) {
		        column = 0;
		        ++row;
		    }

            this.grid.add(anchorPane, column++, row);
            GridPane.setMargin(anchorPane, new Insets(10.0D));

		}
       } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
     
    }
 

    private void setTotal()
    {
    	Double total=0.0;
    	for(int i=0 ; i<cartproducts.size() ; i++)
    	{
    		total+=cartproducts.get(i).getCartPrice();
    	}
    	Subtotal.setText(Double.toString(total));
    	Double discount=this.getDiscount();
    	Double gst=(Double)(this.getGST()/100.0);
    	Double t= gst*(this.getSubtotal()-discount)+(this.getSubtotal()-discount);
        Total.setText(Double.toString(t));
    }
    @FXML
    void ActionHandled(ActionEvent event) throws IOException {


    }
   
    
    @FXML
    void TotalCalculated(MouseEvent event) {
    	//System.out.println("move hovereddddddddddddddddddddddddd");
       this.setTotal();
       this.RemoveCartProduct();   	
    }
    
    private void RemoveCartProduct()
    {
    	     for(int i=0 ; i<cartproducts.size() ; i++)
    	   	{
	    	   	if(cartproducts.get(i).getQuantity()==0)
	    	   	{
	    	   		cartproducts.remove(i);
	    	   		CartGrid.getChildren().remove(i);
	    	   	}
    	   	}	
    }
    
    private boolean ProductIsPresent(String name)
    {
    	for(int i=0 ; i<cartproducts.size() ; i++)
    	{
    		if(cartproducts.get(i).getCartFood()==name)
    			return true;
    	}
    return false;
    }
    @FXML
    void HoverHandled(MouseEvent event) throws IOException {
        Menu object=Menu.GetInstance();
       for(int i=0 ; i< itemController.size() ; i++)
       {
    	   if(itemController.get(i).isSelected() && !(ProductIsPresent(object.getProducts().get(i).getName())))
    	   {
    		   FXMLLoader fxmlLoader = new FXMLLoader();
   		       fxmlLoader.setLocation(this.getClass().getResource("CartProduct.fxml"));
   		       AnchorPane anchorPane = null;
   		       System.out.println("we been selected");
   			   anchorPane = (AnchorPane)fxmlLoader.load();
   			 
   		    cartproducts.add((CartController)fxmlLoader.getController()) ;
   		    cartproducts.get(cartproducts.size()-1).setData(object.getProducts().get(i).getName(),object.getProducts().get(i).getPrice(), 1);
   		
               this.CartGrid.add(anchorPane, 0,CartRow);
               CartGrid.setMargin(anchorPane, new Insets(0.0D));
               CartRow++;
               this.setTotal(); //updating the total price
    		   itemController.get(i).setSelected(false);
    	   }
       }
    }

}
