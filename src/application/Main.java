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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import graph.Graph;
import graph.Person;
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
Graph g = new Graph();
int fl=5;
	// store any command-line arguments that were entered.
	// NOTE: this.getParameters().getRaw() will get these also
	private List<Person> args;

	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 1000;
	private static final String APP_TITLE = "Hello to WisBook!";
	
    
int c =15;

//first page friends list
private void setlist(BorderPane log,Graph g,Stage primaryStage,Scene scene) {
	
    Set<Person>s=g.getAllVertices();
    Set<Person>user= new HashSet<Person>();
    
    
    ListView<Button> list1 = new ListView<Button>();
	for(Person i : s ) { 
		Button b3;
		
		
	b3 = new Button (i.getName());
	
	log.setCenter(list1);
	list1.getItems().add(b3);
System.out.print(i);
	}

}


//2nd Page friends list
private ListView<Button> setlist2(BorderPane info,Graph g,Person text1,Stage primaryStage,Scene scene) {
	
	String center=text1.getName();	
	ListView<Button> list = new ListView<Button>();
	// f is the friends of the center person 
	List<Person> f=g.getAdjacentVerticesOf(text1);
	
		for(Person i : f) { 
		Button b;
		b = new Button (i.getName());
		list.getItems().add(b);
		 EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
	         public void handle(ActionEvent e)
	         { 	BorderPane root1= new BorderPane();
	         Person per = null;
	         	for(Person i : g.getAllVertices()) {if(i.getName().equals(b.getText()))per=i;}
	         	Scene secondS = new Scene(root1,WINDOW_WIDTH, WINDOW_HEIGHT);
	         	secondS=secondScene(per,primaryStage,scene);
	         	
	             primaryStage.setScene(secondS);
	             }
	         
	         
	     };
	     b.setOnAction(event1);
		}
		info.setRight(list);
		return list;
		
}

private GridPane ani_graph(Canvas canvas,GraphicsContext gc,Person text1) {gc.setFill(Color.BLUE);
HashMap<Integer,vector> map = new HashMap<Integer,vector>();
//HashMap<Integer,vector> map1 = new HashMap<Integer,vector>();
GridPane pane= new GridPane();
map.put(0,new vector(150,150));
map.put(1,new vector(550,550));
map.put(2,new vector(550,150));
map.put(3,new vector(150,550));
map.put(4,new vector(350,150));
map.put(5,new vector(350,550));
map.put(6,new vector(150,350));
map.put(7,new vector(550,350));
int j =1;
int temp =g.order()-1;
List<Person>friends= g.getAdjacentVerticesOf(text1);
System.out.println(friends.size()+"   ");
System.out.println(" this is text 1"+text1);
int f1 =0;
for(Map.Entry<Integer,vector> i : map.entrySet()) {

if(f1<friends.size()) {
	System.out.println("hello macha "+friends.get(f1));
String k=friends.get(f1++).getName();
gc.fillText(k,	i.getValue().x,i.getValue().y-10);}
else break;
gc.fillOval(i.getValue().x,i.getValue().y, 30, 30);


gc.setStroke(Color.BLUE);
	
	gc.setLineWidth(2);
	gc.strokeLine(350+c, 350+c, i.getValue().x+c, i.getValue().y+c);
}
	
	
	// Draw a few circles
	gc.setFill(Color.BLACK);
	
	// center
	gc.fillText(text1.getName(),350,350-10);
	gc.setFill(Color.RED);
	gc.fillOval(350,350, 30, 30);
  return pane;
//	pane.getChildren().add(canvas);


	}
Scene secondScene(Person text1,Stage primaryStage,Scene scene) {
	
	GridPane pane = new GridPane();
	Canvas canvas = new Canvas(WINDOW_WIDTH*(0.7), WINDOW_HEIGHT*(0.7));
	GraphicsContext gc = canvas.getGraphicsContext2D();
	BorderPane root= new BorderPane();
	 ListView<Button> list= new ListView<Button>();
	Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
	pane =ani_graph(canvas,gc,text1);
	pane.getChildren().add(canvas);
	Button b1 = new Button ("Back");
	   b1.setOnAction(e->primaryStage.setScene(scene));
	   root.setBottom(b1);
       BorderPane.setAlignment(b1, Pos.CENTER);
	list=setlist2(root,g,text1,primaryStage,scene);
	
	VBox info = new VBox();
	Text head= new Text("Friends List");
	info.getChildren().add(head);
	info.setAlignment(Pos.TOP_RIGHT);
	root.setCenter(info);
	root.setLeft(pane);
	BorderPane.setAlignment(info, Pos.CENTER);
	//BorderPane.setAlignment(pane, Pos.BOTTOM_CENTER);

	return mainScene;
	
}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
       TextField text1 = new TextField("Enter Center Person");
		TextField text2 = new TextField("Enter Friend's name");
		// save args example
//		args = this.getParameters().getRaw();

		//set top label
		Label title = new Label("WisBook");	
           
        	GridPane pane = new GridPane();
   

		// Creates a canvas that can draw shapes and text
				Canvas canvas = new Canvas(WINDOW_WIDTH*(0.7),  WINDOW_HEIGHT*(0.7));
				GraphicsContext gc = canvas.getGraphicsContext2D();
				// Write some text
				// Text is filled with the fill color
				
				
		//bottom button
		System.out.println(c);

		BorderPane root = new BorderPane();

		//log page
		BorderPane log = new BorderPane();

		Scene scene = new Scene(log,WINDOW_WIDTH, WINDOW_HEIGHT*(0.7));
		
		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		// popup window 
		
		
// for the entrance top of the main scene
        Text t = new Text("People in Social Network");
     log.setTop(t);
     log.setAlignment(t, Pos.CENTER);
//         root.setBottom(submit);
//        BorderPane.setAlignment(submit, Pos.CENTER);
	
		
		//set left 
		VBox v = new VBox();
		v.getChildren().addAll(text1,text2);
		
		log.setLeft(v);
		v.setAlignment(Pos.CENTER);
		// ADD and re3move for main page
		Button ADD = new Button ("ADD");
		Button Rem= new Button ("Remove");
		//Hbox for add and remove
		HBox hb = new HBox();
		hb.getChildren().addAll(ADD,Rem);
		
		/// all the enter person unable to use Person inside the scope of the EventHandler so used a list.
		List<String>all= new ArrayList<String>();
		v.getChildren().add(hb);
		
		
		
		
		
		// setting the action for the ADD button
			ADD.setOnAction(e->{
				String cp= text1.getText();
				String friend= text2.getText();
			Person p1= new Person(cp);
			Person p2=new Person (friend);
				// 0 index is the center person .
				all.add(0,cp);
				// "check" is to check if the Centerperson friend is added to the graph or not 
				boolean check=false;
				Person pcp=null;
				for(Person i :g.getAllVertices()) {if(i.getName().equals(cp))pcp=i;}
				List<Person> ff=g.getAdjacentVerticesOf(pcp);
				for(Person i : ff) {
					if(i.getName().equals(friend)) {check=true;
						Alert alert = new Alert(AlertType.ERROR,"\""+cp+"\""+" is already friend to "+"\""+friend+"\"");
						alert.showAndWait().filter(r->r==ButtonType.OK);
					}
				}	if(!check) {g.addEdge(p1, p2);g.addEdge(p2, p1);}
				System.out.println("size "+g.size());
				setlist(log,g,primaryStage,scene);
			//setlist2(root,g,text1.getText());
			//ani_graph(pane,canvas,gc,text1.getText());
			System.out.println(all);
			});
			setlist(log,g,primaryStage,scene);
			
			
			
			
			// setting the action for remove 
			Rem.setOnAction(e->{
				String cp= text1.getText();
				String friend= text2.getText();
		
				// 0 index is the center person .
				all.add(0,cp);
				// "check" is to check if the Centerperson friend is added to the graph or not 
				boolean check=false;
				Person Center = null,Friend=null;
				for(Person i :g.getAllVertices()) {if(i.getName().equals(cp))Center=i;}
				
				List<Person> ff=g.getAdjacentVerticesOf(Center);
				
				// checking if the center person is the friend the current one or not
				
				
				for(Person i : ff) {
					if(i.getName().equals(friend)) {Friend=i;check=true;}}
				
				if(!check) {
					Alert alert = new Alert(AlertType.ERROR,"\""+cp+"\""+" is NOT friend to "+"\""+friend+"\"");
						alert.showAndWait().filter(r->r==ButtonType.OK);}
					
					else g.removeEdge(Center,Friend);
				
				
				for(Person i : ff)
				System.out.println("size "+g.size());
				//setlist(log,g,primaryStage,scene);
			//setlist2(root,g,text1.getText());
			//ani_graph(pane,canvas,gc,text1.getText());
			System.out.println(all);
			});
			setlist(log,g,primaryStage,scene);
			Button submit = new Button("submit");
		
    //  submit.setOnAction(e->primaryStage.setScene(mainScene));
        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            { 	BorderPane root1= new BorderPane();
            	Person p1=null;
            	//
            	for(Person i : g.getAllVertices()) {if(i.getName().equals(text1.getText()))p1=i;}
            	Scene secondS = new Scene(root1,WINDOW_WIDTH, WINDOW_HEIGHT);
            	secondS=secondScene(p1,primaryStage,scene);
            	
                primaryStage.setScene(secondS);
                }
            
            
        };
       	 submit.setOnAction(event1);
		if(all.size()>0)
			text1.setText(all.get(0));
		
			
		BorderPane.setAlignment(v, Pos.CENTER);
		log.setBottom(submit);
		BorderPane.setAlignment(submit, Pos.CENTER);
		VBox info = new VBox();
		Text head= new Text("Friends List");
		info.getChildren().add(head);
		info.setAlignment(Pos.TOP_RIGHT);
		root.setCenter(info);
		root.setTop(title);
		root.setLeft(pane);

		BorderPane.setAlignment(info, Pos.CENTER);
		
		BorderPane.setAlignment(title, Pos.CENTER);
		BorderPane.setAlignment(pane, Pos.BOTTOM_CENTER);

		
		   Button b1 = new Button ("Back");
		   b1.setOnAction(e->primaryStage.setScene(scene));
//	        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
//	            public void handle(ActionEvent e)
//	            {
//	            	
//	            	
//	            	primaryStage.show();
//	                primaryStage.setScene(page2); 
//	               
//	            }
//	        };
	        root.setBottom(b1);
	        BorderPane.setAlignment(b1, Pos.CENTER);
//			 b1.setOnAction(event);
	        
			
		BorderPane.setAlignment(submit, Pos.CENTER);
		// Add the stuff and set the primary stage
		primaryStage.setTitle(APP_TITLE);
//		primaryStage.setScene(mainScene);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
