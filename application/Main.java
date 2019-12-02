package application;


import java.io.FileInputStream;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	// store any command-line arguments that were entered.
	// NOTE: this.getParameters().getRaw() will get these also
	private List<String> args;

	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 600;
	private static final String APP_TITLE = "Hello to WisBook!";

	@Override
	public void start(Stage primaryStage) throws Exception {
		// save args example
		args = this.getParameters().getRaw();

		//set top label
		Label title = new Label("WisBook");
		
		//Left Diagram 
		GridPane pane = new GridPane();
	
		pane.add(new Button("name1"),0,0);
		pane.add(new Button("Center People"),1,1);
		pane.add(new Button("name3"),0,2);
		pane.add(new Button("name4"),2,0);
		pane.add(new Button("name5"),2,2);
		
		//right box
		VBox info = new VBox();
		info.getChildren().add(new Label("Center People Info"));
		
		//bottom button
	
		
		
		ListView<String> list = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList (
			    "friend1", "friend2", "friend3", "friend4");
		list.setItems(items);
		info.getChildren().add(list);
		
//		// Creates a canvas that can draw shapes and text
//		Canvas canvas = new Canvas(WINDOW_WIDTH/2, WINDOW_HEIGHT/2);
//		GraphicsContext gc = canvas.getGraphicsContext2D();
//		// Write some text
//		// Text is filled with the fill color
//		gc.setFill(Color.GREEN);
//		gc.setFont(new Font(30));
//		gc.fillText("Hello World!", 70, 170);
//		// Draw a line
//		// Lines use the stroke color
//		gc.setStroke(Color.BLUE);
//		gc.setLineWidth(2);
//		gc.strokeLine(40, 100, 250, 50);
//		// Draw a few circles
//		gc.setFill(Color.BLACK);
//		// The circles draw from the top left, so to center them, subtract the radius from each coordinate
//		gc.fillOval(40-15, 100-15, 30, 30);
//		gc.setFill(Color.RED);
//		gc.fillOval(250-15, 50-15, 30, 30);

		//vbox.getChildren().add(canvas);
		

		// Main layout is Border Pane example (top,left,center,right,bottom)
		BorderPane root = new BorderPane();
		
		
		
		TextField text = new TextField("Please enter the person: ");
		
		ListView<String> allpeople = new ListView<String>();
		ObservableList<String> people = FXCollections.observableArrayList (
			    "p1", "p2", "p3", "p4","p5","p6");
		allpeople.setItems(people);
		
        Button b = new Button ("Back");
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                primaryStage.close();
            }
        };
		 b.setOnAction(event);
		//log page
		BorderPane log = new BorderPane();
		//button to the next page
		Button submit = new Button("submit");
		//set left 
		log.setLeft(text);
		BorderPane.setAlignment(text, Pos.CENTER);
		//set right
		log.setCenter(allpeople);
		BorderPane.setAlignment(allpeople, Pos.CENTER);
		//set bottom
		log.setBottom(submit);
		BorderPane.setAlignment(submit, Pos.CENTER);

		// Add the vertical box to the center of the root pane
		root.setCenter(info);
		root.setTop(title);
		root.setLeft(pane);
		root.setBottom(b);
		
		BorderPane.setAlignment(info, Pos.CENTER);
		BorderPane.setAlignment(b, Pos.CENTER);
		BorderPane.setAlignment(title, Pos.CENTER);
		BorderPane.setAlignment(pane, Pos.BOTTOM_CENTER);
		
		
		
		
		
		
		
		Scene scene = new Scene(log,WINDOW_WIDTH, WINDOW_HEIGHT);
		
		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

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
