package javafx;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class hello extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception{
		primaryStage.setTitle("Hello World!");
		final Text text = new Text();
		text.setText("Noone has said hello!");
		Button btn = new Button();
		
		btn.setText("HELLO");
		btn.setOnAction(new EventHandler<ActionEvent>(){
			int n = 0;
			
			@Override
			public void handle(ActionEvent arg0) {
				n++;
				text.setText("Hello " + n);
				
			}
			
		});
		
		VBox pane = new VBox();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(25, 25, 25, 25));
		pane.getChildren().add(text);
		pane.getChildren().add(btn);
		
				
		primaryStage.setScene(new Scene(pane, 300, 300));
		/*
		//primaryStage.sizeToScene();
		primaryStage.setWidth(400);
		primaryStage.setHeight(400);
		*/
		primaryStage.show();
	}
	
	public static void main(String[] args){
		Application.launch(args);
	}
}
