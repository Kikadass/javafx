package javafx;

import javafx.application.Application;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.animation.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
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
		
		//Menu
		MenuBar menuBar = new MenuBar();
		menuBar.setMinWidth(width);
		root.getChildren().add(menuBar);

		
		final Menu menu1 = new Menu("File");
		final Menu menu2 = new Menu("View");
		final Menu menu3 = new Menu("Edit");
		final Menu menu4 = new Menu("Simulation");
		final Menu menu5 = new Menu("Help");
		menuBar.getMenus().addAll(menu1, menu2, menu3, menu4, menu5);
		
		//inner menus
		MenuItem menu11 = new MenuItem("New Configuration");
		MenuItem menu12 = new MenuItem("Open Configuration File");
		MenuItem menu13 = new MenuItem("Save");
		MenuItem menu14 = new MenuItem("Save As");

		menu1.getItems().addAll(menu11, menu12, menu13, menu14);
		
		MenuItem menu21 = new MenuItem("Display Configuration");
		MenuItem menu22 = new MenuItem("Edit Configuration");
		MenuItem menu23 = new MenuItem("Info About Life Forms");
		MenuItem menu24 = new MenuItem("Info About Map");
		
		menu2.getItems().addAll(menu21, menu22, menu23, menu24);

		MenuItem menu31 = new MenuItem("Modify Life Form parameters");
		MenuItem menu32 = new MenuItem("Remove Life Form");
		MenuItem menu33 = new MenuItem("Add Life Form");
		
		menu3.getItems().addAll(menu31, menu32, menu33);
		
		MenuItem menu41 = new MenuItem("Run");
		MenuItem menu42 = new MenuItem("Pause/Restart");
		MenuItem menu43 = new MenuItem("Reset");
		MenuItem menu44 = new MenuItem("Display Map At Each Iteration");
		
		menu4.getItems().addAll(menu41, menu42, menu43, menu44);
		
		MenuItem menu51 = new MenuItem("Display info about application");
		MenuItem menu52 = new MenuItem("Display info about author");
		
		menu5.getItems().addAll(menu51, menu52);
		
		
		ArrayList<ACircle> circles = new ArrayList<ACircle>(); 
		for (int i = 0; i < nCircles; i++){
			Random rnd = new Random();
			ACircle circle = new ACircle();
			do{
				int radius = rnd.nextInt(40)+10;
				int x = rnd.nextInt(width - radius*2) + radius;
				int y = rnd.nextInt(height - 50 - radius*2) + radius;
				float dx = rnd.nextInt(10);
				float dy = rnd.nextInt(10);

				circle = new ACircle(x, y, radius, dx, dy);
			
			}while(circle.colliding(circles));
			circles.add(circle);
			
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
				
				menuBar.setMinWidth(scene.getWidth());

				
				if (!pause){
					for (int i = 0; i < nCircles; i++) {
						circles.get(i).update(scene);
						circles.get(i).collideWalls(scene);
						circles.get(i).collide(circles);
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
