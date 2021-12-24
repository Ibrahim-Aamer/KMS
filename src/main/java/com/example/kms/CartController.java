package com.example.kms;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CartController {

    @FXML
    private Label CartFood;

    @FXML
    private Label CartPrice;

    @FXML
    private Button add;

    public String getCartFood() {
		return CartFood.getText();
	}
	public void setCartFood(String cartFood) {
		CartFood.setText(cartFood);
	}
	public Double getCartPrice() {
		return Double.parseDouble(CartPrice.getText());
	}
	public void setCartPrice(Double cartPrice) {
		CartPrice.setText(Double.toString(cartPrice));
	}
	public Button getAdd() {
		return add;
	}
	public void setAdd(Button add) {
		this.add = add;
	}
	public int getQuantity() {
		return Integer.parseInt(quantity.getText()) ;
	}
	public void setQuantity(int q) {
		this.quantity.setText(Integer.toString(q));
	}
	public Button getSubtract() {
		return subtract;
	}
	public void setSubtract(Button subtract) {
		this.subtract = subtract;
	}
	@FXML
    private Label quantity;

    @FXML
    private Button subtract;

    @FXML
    void ActionHandled(ActionEvent event) {

    	int q=Integer.parseInt(quantity.getText());
    	if(event.getSource()==add)
    	{
    	 q++;
    	 int quan=Integer.parseInt(quantity.getText());
    	 Double total=Double.parseDouble(CartPrice.getText());
    	 Double individualPrice= total/quan;
    	 quan++;
    	 Double PriceNow=individualPrice*quan;
    	 CartPrice.setText(Double.toString(PriceNow));
    	}
    	else if(event.getSource()==subtract)
    	{
    		if(q!=0)
    		{
    		q--;
    		int quan=Integer.parseInt(quantity.getText());
    		Double total=Double.parseDouble(CartPrice.getText());
    		Double individualPrice= total/quan;
    		quan--;
    		Double PriceNow=individualPrice*quan;
    		CartPrice.setText(Double.toString(PriceNow));
    		}
    	
    	}
    	quantity.setText(Integer.toString(q));
    		
    }
   public void setData(String name, Double price,int n)
   {
	   this.CartFood.setText(name);
	   this.CartPrice.setText(Double.toString(price));
	   this.quantity.setText(Integer.toString(n));
   }
   
}
