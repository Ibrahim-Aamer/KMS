package com.example.kms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
	@FXML
	private Rectangle BackGround;

	@FXML
	private Button Cancel;

	@FXML
	private Button Complete;

	@FXML
	private Label TableNumber;

	@FXML
	private ProgressBar TimeOut;

	@FXML
	private GridPane order;
	boolean cancelled=false;
	boolean completed=false;

	@FXML
	void OrderCancelled(ActionEvent event) {
		if(event.getSource()==Cancel)
		{
			cancelled=true;
		}

	}

	@FXML
	void OrderCompleted(ActionEvent event) {
		if(event.getSource()==Complete)
		{
			completed=true;
		}
	}

	int rows = 1;
	ArrayList<OrderProductController> ProductController=new ArrayList<OrderProductController>();
	ArrayList<OrderProduct> OrderProducts=new ArrayList<OrderProduct>();
	Double Total;


	public ArrayList<OrderProduct> getOrderProducts() {
		return OrderProducts;
	}

	public void setOrderProducts(ArrayList<OrderProduct> list) throws IOException {

		OrderProducts=list;

		this.setOrderProducts();

	}
	public void setOrderProducts(List<CartProduct> list) throws IOException {
		for (int i = 0; i < list.size(); i++) {
			OrderProduct object = new OrderProduct();
			object.setProductName(list.get(i).getName());
			object.setQuantity(list.get(i).getQuantity());
			// list.get(i).getPrice();
			OrderProducts.add(object);


		}

	}

	public void setOrderProducts() {

		for (int i = 0; i < getOrderProducts().size(); i++) {

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(this.getClass().getResource("OrderProduct.fxml"));
			AnchorPane anchorPane = null;

			try {
				anchorPane = (AnchorPane) fxmlLoader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (anchorPane == null) {

			}

			ProductController.add((OrderProductController) fxmlLoader.getController());


			ProductController.get(i).setProductName(OrderProducts.get(i).getProductName());
			ProductController.get(i).setQuantity(OrderProducts.get(i).getQuantity());
			if (this.order == null) {

			}

			this.order.add(anchorPane, 0, rows);
			this.order.setMargin(anchorPane, new Insets(0.0D));
			rows++;
		}
	}

	public int getTableNo() {
		return Integer.parseInt(TableNumber.getText());
	}

	public void setTableNo(int tableNo) {
		TableNumber.setText(Integer.toString(tableNo));
	}

	public Double getTotal() {
		return Total;
	}

	public void setTotal(Double total) {
		Total = total;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public boolean isCompleted() {
		return completed;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		cancelled=false;
		completed=false;
		rows = 1;
		ProductController = new ArrayList<OrderProductController>();
		OrderProducts = new ArrayList<OrderProduct>();
		this.setOrderProducts();

	}
}