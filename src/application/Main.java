package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.transform.Rotate;
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		
			Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
	        
	        Scene scene = new Scene(root);
	        
	        primaryStage.setScene(scene);
	        primaryStage.centerOnScreen();
	        primaryStage.setTitle("i192029");
	        primaryStage.show();
		} catch(Exception e) {
		System.out.println("error detected");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
