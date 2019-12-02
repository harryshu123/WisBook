package application;


import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
	


	// store any command-line arguments that were entered.
	// NOTE: this.getParameters().getRaw() will get these also
	private List<String> args;

	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 600;
	private static final String APP_TITLE = "Hello to WisBook!";
	Random random = new Random();
    int numberOfRows = 25;
    int numberOfColumns = 25;
    public void addCirclesToGridPane(GridPane gridPane, List<Circle> circles)
    {
        
            gridPane.add(circles.get(0), 0, 0);
        
    }
    private static final int R = 10;
    private static final Color lineColor = Color.FIREBRICK.deriveColor(0, 1, 1, .6);
    private Circle createCircle() {
        final Circle circle = new Circle(R);

        circle.setStroke(Color.WHITE);
        circle.setStrokeWidth(10);
        circle.setStrokeType(StrokeType.INSIDE);
        circle.setFill(Color.WHITE);
        circle.relocate(0, 0);

        return circle;
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		
//hello
       
		// save args example
		args = this.getParameters().getRaw();

		//set top label
		Label title = new Label("WisBook");
	
		
      
		
		
		
		
		/////
//		"0"(0,2)
//				1(1,0).(drawlineto(0,2))		
           
        	GridPane pane = new GridPane();
        	Circle circle = createCircle();
    		Text text = new Text("42");
    		text.setBoundsType(TextBoundsType.VISUAL); 
    	
//    		pane.getChildren().addAll(circle, text);
         
		
		//pane.add(name1,0,0);
	
		
		pane.add(new Button("name1"),0, 0);
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
		BorderPane root = new BorderPane();
		
		
		
		TextField text1 = new TextField("Please enter the person: ");
		
		ListView<String> allpeople = new ListView<String>();
		ObservableList<String> people = FXCollections.observableArrayList (
			    "p1", "p2", "p3", "p4","p5","p6");
		allpeople.setItems(people);
		
     
		//log page
		BorderPane log = new BorderPane();

		Scene scene = new Scene(log,WINDOW_WIDTH, WINDOW_HEIGHT);
		
		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		Button submit = new Button("submit");
		
		Line line = new Line();
        line.setStartX(1);
        line.setStartY(1);
        line.setEndX(10.0);
        line.setEndY(10.0);
        pane.getChildren().add(line);
        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                primaryStage.setScene(mainScene);           }
        };
        root.setBottom(submit);
        BorderPane.setAlignment(submit, Pos.CENTER);
		 submit.setOnAction(event1);
		//button to the next page
		
		//set left 
		log.setLeft(text1);
		BorderPane.setAlignment(text1, Pos.CENTER);
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
		
		
		BorderPane.setAlignment(info, Pos.CENTER);
		
		BorderPane.setAlignment(title, Pos.CENTER);
		BorderPane.setAlignment(pane, Pos.BOTTOM_CENTER);

		
		   Button b = new Button ("Back");
	        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent e)
	            {
	                primaryStage.setScene(scene);           }
	        };
	        root.setBottom(b);
	        BorderPane.setAlignment(b, Pos.CENTER);
			 b.setOnAction(event);
			
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
