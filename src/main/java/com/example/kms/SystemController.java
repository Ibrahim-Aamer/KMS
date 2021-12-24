package com.example.kms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SystemController implements Initializable {

        @FXML
        private ScrollPane MenuScroll;

        @FXML
        private GridPane OrderGrid;
        @FXML
        private Button DisplayOrders;


        ArrayList<OrderController> order;
        int rows=0;
        int cols=0;
        @FXML
        void DisplayOrders(ActionEvent event) throws IOException {
          if(event.getSource()==DisplayOrders)
          {
              SystemDisplay object=SystemDisplay.getInstance();
              object.getOrdersFromServer();
              this.ShowOrders();
          }

        }

    @FXML
    void CheckOrderCompletion(MouseEvent event) {

            for(int i=0 ; i<order.size() ;i++)
            {
                if(order.get(i).isCompleted() || order.get(i).isCancelled())
                {
                    order.remove(i);
                    OrderGrid.getChildren().remove(i);
                }
            }
    }
    public void ShowOrders() throws IOException {

        SystemDisplay object=SystemDisplay.getInstance();

        for(int i=0 ; i<object.getOrders().size() ;i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("Order.fxml"));
            AnchorPane anchorPane = null;
            anchorPane = (AnchorPane) fxmlLoader.load();
            order.add((OrderController) fxmlLoader.getController());
            order.get(i).setTotal(object.getOrders().get(i).getTotal());
            order.get(i).setTableNo(object.getOrders().get(i).getTbaleNo());
            order.get(i).setOrderProducts(object.getOrders().get(i).getOrder_products());
            this.OrderGrid.add(anchorPane, cols, rows);

            this.OrderGrid.add(anchorPane, cols, rows);
            if (cols == 2) {
                rows++;
            }
            cols++;
        }

    }

    @FXML
    void DisplayOrders(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    rows=1;
    cols=0;
    order=new ArrayList<OrderController>();


    }


}
