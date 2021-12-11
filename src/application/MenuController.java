package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MenuController implements Initializable  {
	 @FXML
	    private Label CartFood;

	    @FXML
	    private Pane CartPane1;

	    @FXML
	    private Label CartPrice;

	    @FXML
	    private ScrollBar CartScroll;

	    @FXML
	    private ComboBox<String> CashPayment;

	    @FXML
	    private Button Checkout;

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
	    private Button add;

	    @FXML
	    private GridPane grid;

	    @FXML
	    private ListView<String> listview;

	    @FXML
	    private Label quantity;

	    @FXML
	    private Button subtract;

    private List<Product> products ;
    private Image image;
    private ProductListener productListener;
   
    ObservableList<String> items =FXCollections.observableArrayList (
    		  " ",  " ", "Appetizers", "Main Course", "Soups", "Beverages" , "Midnight Deals", "Tuesday Specials" , "Platters");
    
 
    public MenuController() {
    }

    private ArrayList<Product> getData() {
        ArrayList<Product> p = new ArrayList<Product>();
        Product obj = new Product();
        obj.setName("Kheer 250");
        obj.setPrice(232.99D);
        obj.setImgPath("/pictures/cherry.png");
        p.add(obj);
        
        
        obj = new Product();
        obj.setName("Paratha");
        obj.setPrice(232.99D);
        obj.setImgPath("/pictures/banana.png");
        p.add(obj);
        
        
        obj = new Product();
        obj.setName("Biryani");
        obj.setPrice(243.99D);
        obj.setImgPath("/pictures/peach.png");
        p.add(obj);
        
        
        obj = new Product();
        obj.setName("Plain Karahi");
        obj.setPrice(232.99D);
        obj.setImgPath("/pictures/orange.png");
        p.add(obj);
        
        
        obj = new Product();
        obj.setName("Kebab");
        obj.setPrice(232.99D);
        obj.setImgPath("/pictures/mango.png");
        p.add(obj);
        
        
        obj = new Product();
        obj.setName("Mutton Karahi");
        obj.setPrice(232.99D);
        obj.setImgPath("/pictures/kiwi.png");
        p.add(obj);
        
                return p;
    }

    private void setChosenProduct(Product p) {
        this.CartFood.setText(p.getName());
        this.CartPrice.setText("RS." + p.getPrice());
        //this.image = new Image(this.getClass().getResourceAsStream(p.getImgPath()));
        //this.fruitImg.setImage(this.image);
       // this.chosenFruitCard.setStyle("-fx-background-color: #" + fruit.getColor() + ";\n    -fx-background-radius: 30;");
    }

    public void initialize(URL location, ResourceBundle resources) {
        listview.setItems(items);
        products=new ArrayList<Product>();
      //  grid=new GridPane();
        this.products.addAll(this.getData());
        if (this.products.size() > 0) {
        	  System.out.println(this.products.get(0).getName());
        	this.setChosenProduct((Product)this.products.get(2));
            this.productListener = new ProductListener() {
                public void onClickListener(Product f) {
                    MenuController.this.setChosenProduct(f);
                }
            };
          
        }

        int column = 0;
        int row = 1;
     
       try { 
       for(int i = 0; i < this.products.size(); ++i) 
		{
			  System.out.println(this.products.get(i).getName());
			  System.out.println(this.products.get(i).getImgPath());
			  //System.out.println(this.products.get(0).getName());
			FXMLLoader fxmlLoader = new FXMLLoader();
		    fxmlLoader.setLocation(this.getClass().getResource("Product.fxml"));
		    AnchorPane anchorPane = null;
		  
			anchorPane = (AnchorPane)fxmlLoader.load();
				    ItemController itemController = (ItemController)fxmlLoader.getController();
		    itemController.setData((Product)this.products.get(i), this.productListener);
		    if (column == 2) {
		        column = 0;
		        ++row;
		    }

            this.grid.add(anchorPane, column++, row);
            this.grid.setMinWidth(-1.0D);
            this.grid.setPrefWidth(-1.0D);
            this.grid.setMaxWidth(-1.0D / 0.0);
            this.grid.setMinHeight(-1.0D);
            this.grid.setPrefHeight(-1.0D);
            this.grid.setMaxHeight(-1.0D / 0.0);
            GridPane.setMargin(anchorPane, new Insets(10.0D));

		}
       } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }
    @FXML
    void ActionHandled(ActionEvent event) {
    	//System.out.printf("max %f /n",MenuScroll.getValue()); 
    }

}
