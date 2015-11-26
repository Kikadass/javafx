package javafx;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.shape.Circle;

public class ACircle extends Circle{
	public float x;
	public float y;
	public float dx;
	public float dy;
	
	public ACircle(){
		this.x = 100;
		this.y = 100;
		this.dx = -1.5f;
		this.dy = -1.5f;
		this.setRadius(20);
		this.setCenterX(x);
		this.setCenterY(y);
	}
	
	public ACircle(int x, int y, int radius){
		this.x = x;
		this.y = y;
		this.dx = -1.5f;
		this.dy = -1.5f;
		this.setRadius(radius);
		this.setCenterX(x);
		this.setCenterY(y);
	}
	
	public ACircle(int x, int y, int radius, float dx, float dy){
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.setRadius(radius);
		this.setCenterX(x);
		this.setCenterY(y);
	}
	
	public int sign(double k){
		if (k >= 0) return 1;
		else return -1;
	}
	
	public void collide(Scene scene, ArrayList<ACircle> circles){
		// check collision against walls
		if (this.getTranslateX()+ this.getCenterX() < this.getRadius() && dx < 0)	dx = -dx;
		else if (scene.getWidth() - this.getTranslateX()- this.getCenterX()  < this.getRadius() && dx > 0) dx = -dx;
		if (this.getTranslateY()+ this.getCenterY()  < this.getRadius() && dy < 0)	dy = -dy;
		else if (scene.getHeight() - 50 - this.getCenterY() - this.getTranslateY()  < this.getRadius() && dy > 0) dy = -dy;
		
		// check collision against other bugs	
		for (int i = 0; i < circles.size(); i++){
			if (this.getTranslateX() != circles.get(i).getTranslateX() && this.getTranslateX() != circles.get(i).getTranslateX()){
				// (x2-x1)^2 + (y1-y2)^2 <= (r1+r2)^2
				int a = (int) Math.pow(((this.getCenterX() + this.getTranslateX()) - (circles.get(i).getCenterX() + circles.get(i).getTranslateX())), 2);
				int b = (int) Math.pow(((this.getCenterY() + this.getTranslateY()) - (circles.get(i).getCenterY() + circles.get(i).getTranslateY())), 2);
				int c = (int) Math.pow(this.getRadius() + circles.get(i).getRadius(), 2);
				
				int collisionPointX = (int) (((this.getTranslateX() * circles.get(i).getRadius()) + (circles.get(i).getTranslateX() * this.getRadius())) 
						/ (this.getRadius() + circles.get(i).getRadius()));
						 
				int collisionPointY = 
						(int) (((this.getTranslateY() * circles.get(i).getRadius()) + (circles.get(i).getTranslateY() * this.getRadius())) 
						/ (this.getRadius() + circles.get(i).getRadius()));
				
				if (a + b <= c){
					float newdx = 
							  (float) ((this.dx * (this.getRadius() - circles.get(i).getRadius()) + (2 * circles.get(i).getRadius() * circles.get(i).dx)) 
							 / (this.getRadius() + circles.get(i).getRadius()));
					float newdy = 
							  (float) ((this.dy * (this.getRadius() - circles.get(i).getRadius()) + (2 * circles.get(i).getRadius() * circles.get(i).dy)) 
							 / (this.getRadius() + circles.get(i).getRadius()));
					circles.get(i).dx = 
							  (float) ((circles.get(i).dx * (circles.get(i).getRadius() - this.getRadius()) + (2 * this.getRadius() * this.dx)) 
									 / (circles.get(i).getRadius() + this.getRadius()));
					circles.get(i).dy = 
							 (float) ((circles.get(i).dy * (circles.get(i).getRadius() - this.getRadius()) + (2 * this.getRadius() * this.dy)) 
									 / (circles.get(i).getRadius() + this.getRadius()));
					this.setTranslateX(this.getTranslateX()- dx);
					this.setTranslateY(this.getTranslateY()- dy);  
					this.dx = newdx;
					this.dy = newdy;
				}
			}
		}
	}
	
	public void update(Scene scene){
		this.setTranslateX(this.getTranslateX()+ dx);
		this.setTranslateY(this.getTranslateY() + dy);
	}
}
