package application;

import java.net.URL;
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
	    private Label ProductName;

	    @FXML
	    private Label ProductPrice;

	    @FXML
	    private Button button;

	    @FXML
	    private ImageView image;

	    @FXML
	    void ButtonHandled(ActionEvent event) {

	    }
	    
	    private Product product;
	    private ProductListener productListener;
	
	    public ItemController() {
	    }

	    @FXML
	    private void click(MouseEvent mouseEvent) {
	        this.productListener.onClickListener(this.product);
	    }

	    public  void setData(Product p, ProductListener L) {
	        this.product = p;
	        this.productListener = L;
	        this.ProductName.setText(p.getName());
	        this.ProductPrice.setText("Rs." + p.getPrice());
	        Image image = new Image(this.getClass().getResourceAsStream(p.getImgPath()));
	        this.image.setImage(image);
	    }
	    
	    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}