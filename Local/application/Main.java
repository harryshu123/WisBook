/*
 Team : A40
 Project a2(Initial GUI page) Social Network
 Team Members : Chokkarapu Sai Teja , Lintong Han, Zhihao Shu , Lakshay
 Date : 12/2/19
*/



package application;


import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javafx.scene.shape.Line;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;

public class Main extends Application {
	class vector{
	int x,y;
		
		vector(int a, int b){
			x=a;
			y=b;
		}
	};

int fl=5;
	// store any command-line arguments that were entered.
	// NOTE: this.getParameters().getRaw() will get these also
	private List<String> args;

	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 1000;
	private static final String APP_TITLE = "Hello to WisBook!";
	
    
int c =15;
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
       
		// save args example
		args = this.getParameters().getRaw();

		//set top label
		Label title = new Label("WisBook");	
           
        	GridPane pane = new GridPane();
   

		// Creates a canvas that can draw shapes and text
				Canvas canvas = new Canvas(WINDOW_WIDTH*(0.7), WINDOW_HEIGHT*(0.7));
				GraphicsContext gc = canvas.getGraphicsContext2D();
				// Write some text
				// Text is filled with the fill color
				gc.setFill(Color.BLUE);
			HashMap<Integer,vector> map = new HashMap<Integer,vector>();
//			HashMap<Integer,vector> map1 = new HashMap<Integer,vector>();
//			
		map.put(0,new vector(150,150));
		map.put(1,new vector(550,550));
		map.put(2,new vector(550,150));
		map.put(3,new vector(150,550));
		map.put(4,new vector(350,150));
		map.put(5,new vector(350,550));
		map.put(6,new vector(150,350));
		map.put(7,new vector(550,350));
 int j =1;
 int temp =fl;
	for(Map.Entry<Integer,vector> i : map.entrySet()) {
		if(temp==0)break;
		String k="Friend "+j++;
		gc.fillText(k,	i.getValue().x,i.getValue().y-10);
		gc.fillOval(i.getValue().x,i.getValue().y, 30, 30);temp--;
	
		
		gc.setStroke(Color.BLUE);
				
				gc.setLineWidth(2);
				gc.strokeLine(350+c, 350+c, i.getValue().x+c, i.getValue().y+c);
	}
				
				
				// Draw a few circles
				gc.setFill(Color.BLACK);
				
				// center
				gc.fillText("Center Person",350,350-10);
				gc.setFill(Color.RED);
				gc.fillOval(350,350, 30, 30);
				
				
				pane.getChildren().add(canvas);

		VBox info = new VBox();
		info.getChildren().add(new Label("Friends List"));
		
		//bottom button
		System.out.println(c);
	ListView<Button> list = new ListView<Button>();
		for(int i =1;i<=fl;i++) { 
				
			String k="Friend"+i;
			Button b;
		b = new Button (k);
		list.getItems().add(b);
//		ObservableList<Button> items = FXCollections.observableArrayList (b);
//		
//		list.setItems(items);
		}
		info.getChildren().add(list);
	
		
		
		BorderPane root = new BorderPane();
		
		
		
		TextField text1 = new TextField("Enter and click Submit");
		
		ListView<String> allpeople = new ListView<String>();
		ObservableList<String> people = FXCollections.observableArrayList (
			    "p1", "p2", "p3", "p4","p5","p6");
		allpeople.setItems(people);
		
     
		//log page
		BorderPane log = new BorderPane();

		Scene scene = new Scene(log,WINDOW_WIDTH, WINDOW_HEIGHT*(0.7));
		
		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		Button submit = new Button("submit");
		
      
        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                primaryStage.setScene(mainScene);           }
        };
        root.setBottom(submit);
        BorderPane.setAlignment(submit, Pos.CENTER);
		 submit.setOnAction(event1);
		//button to the next page
		Circle c = new Circle();
		//set left 
		log.setLeft(text1);
		BorderPane.setAlignment(text1, Pos.CENTER);
		//set right
		log.setCenter(allpeople);
		BorderPane.setAlignment(allpeople, Pos.CENTER);
		//set bottom
		log.setBottom(submit);
		BorderPane.setAlignment(submit, Pos.CENTER);

		root.setCenter(info);
		root.setTop(title);
		root.setLeft(pane);

		BorderPane.setAlignment(info, Pos.CENTER);
		
		BorderPane.setAlignment(title, Pos.CENTER);
		BorderPane.setAlignment(pane, Pos.BOTTOM_CENTER);

		
		   Button b1 = new Button ("Back");
	        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent e)
	            {
	                primaryStage.setScene(scene);           }
	        };
	        root.setBottom(b1);
	        BorderPane.setAlignment(b1, Pos.CENTER);
			 b1.setOnAction(event);
			
		BorderPane.setAlignment(submit, Pos.CENTER);
		// Add the stuff and set the primary stage
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(mainScene);
		//primaryStage.setScene(scene);
		primaryStage.show();
	}

	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
