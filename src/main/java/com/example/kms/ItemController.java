package com.example.kms;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
public class ItemController implements Initializable {
    @FXML
    private Button Description;

    @FXML
    private Label ProductName;

    @FXML
    private Label ProductPrice;

    @FXML
    private Button button;

    @FXML
    private ImageView image;
 
    
    private boolean Selected;
    private Product product;



    @FXML
    void SelectionHandled(ActionEvent event)
    {
    	if(event.getSource()==button)
    	{	
    		System.out.println("button has been handled");
    	     Selected=true;
    	
    	}
     else
         Selected=false;
    				

    }
	@FXML
	void DescriptionHandled(ActionEvent event) {

		if(event.getSource()== Description)
		{


		}
	}

    public Button getDescription() {
		return Description;
	}

	public void setDescription(Button description) {
		Description = description;
	}

	public Label getProductName() {
		return ProductName;
	}

	public void setProductName(Label productName) {
		ProductName = productName;
	}

	public Label getProductPrice() {
		return ProductPrice;
	}

	public void setProductPrice(Label productPrice) {
		ProductPrice = productPrice;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	public ImageView getImage() {
		return image;
	}

	public void setImage(ImageView image) {
		this.image = image;
	}

	public boolean isSelected() {
		return Selected;
	}

	public void setSelected(boolean selected) {
		Selected = selected;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	

	     public ItemController() {
	    }

	

	    public  void setData(Product p) {
	        this.product = p;
	        this.ProductName.setText(p.getName());
	        this.ProductPrice.setText("Rs." + p.getPrice());


	        Image image = null;
			try {
				System.out.println(p.getImgPath());

				image = new Image(this.getClass().getResourceAsStream("pictures/"+p.getImgPath()));
			}
			catch(Exception e)
			{
				System.err.println(e.getMessage());
			}
			
			this.image.setImage(image);
	    }
	    
	    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
      Selected=false;		
	}

}