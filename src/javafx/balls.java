package javafx;

import javafx.application.Application;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.animation.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import java.util.*;

public class balls extends Application {

	int width = 1000;
	int height = 700;
	int nCircles = 10;
	boolean pause = true;
	boolean stop = true;
	
	
	@Override
	public void start(Stage stage1) throws Exception {
		Group root = new Group();
		Rectangle rectangle = new Rectangle(0, height-50, width, 50);
		root.getChildren().add(rectangle);
		
		
		ArrayList<ACircle> circles = new ArrayList<ACircle>(); 
		for (int i = 0; i < nCircles; i++){
			Random rnd = new Random();
			int radius = rnd.nextInt(40)+10;
			int x = rnd.nextInt(width - radius*2) + radius;
			int y = rnd.nextInt(height - 50 - radius*2) + + radius;
			float dx = rnd.nextInt(10);
			float dy = rnd.nextInt(10);
					//(float) Math.sqrt(4.5-dx*dx);
			circles.add(new ACircle(x, y, radius, dx, dy));
		}
		
		for (int i = 0; i < nCircles; i++){
			root.getChildren().add(circles.get(i));
		}
		
		final Scene scene = new Scene (root, width, height);
		
		Button pauseBtn = new Button();
		
		pauseBtn.setText("Pause");
		pauseBtn.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0) {
				if (pause){
					pauseBtn.setText("Pause");
					pause = false;
				}
				else {
					pauseBtn.setText("Replay");
					pause = true;
				}
				
			}
			
		});		

		
		Button stopBtn = new Button();
		
		pauseBtn.setVisible(false);
		stopBtn.setText("START");
		stopBtn.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0) {
				if (stop){
					pauseBtn.setVisible(true);
					stopBtn.setText("STOP");
					stop = false;
					pause = false;
				}
				else {
					for (int i = 0; i < nCircles; i++){
						circles.get(i).setTranslateX(0);
						circles.get(i).setTranslateY(0);
					}
					pauseBtn.setVisible(false);
					stopBtn.setText("START");
					stop = true;
					pause = true;
				}
				
			}
			
		});		
		
		root.getChildren().add(stopBtn);	
		root.getChildren().add(pauseBtn);
		
		
		KeyFrame frame = new KeyFrame(Duration.millis(16), new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent t) {
				pauseBtn.setLayoutX(scene.getWidth()/2 - 50);
				pauseBtn.setLayoutY(scene.getHeight()- 40);
				
				stopBtn.setLayoutX(scene.getWidth()/2 + 10);
				stopBtn.setLayoutY(scene.getHeight()- 40);
				
				rectangle.setLayoutY(scene.getHeight() - height);
				rectangle.setWidth(scene.getWidth());
				
				if (!pause){
					for (int i = 0; i < nCircles; i++) {
						circles.get(i).update(scene);
						circles.get(i).collide(scene, circles);
					}
					
				}
				
			}
			
		});
		
		TimelineBuilder.create().cycleCount(javafx.animation.Animation.INDEFINITE).keyFrames(frame).build().play();	
			
		
		stage1.setTitle("Deez nuts!");
		stage1.setScene(scene);
		//System.out.println(scene.getWidth() + " " + scene.getHeight());
		//stage1.setWidth(scene.getWidth());
		//stage1.setHeight(scene.getHeight()+ 100);
		//System.out.println(scene.getWidth() + " " + scene.getHeight());
		
		stage1.show();
		//scene.setHeight(300);
		//System.out.println(scene.getWidth() + " " + scene.getHeight());

	}
	
	public static void main(String[] args) {
		launch(args);

	}

}
