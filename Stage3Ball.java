package stage3;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.sql.rowset.Joinable;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.MainGUI;
import main.FinishGUI;
import stage1.Stage1MainFrame;
import stage2.Stage2Constant;
import stage3.Stage3MainFrame;



public class Stage3Ball extends JPanel implements Stage2Constant, Runnable {
	Stage3MainFrame rootComponent;


	int x = CANVAS_WIDTH / 2 - BALL_WIDTH / 2 + 20; 
	int y = CANVAS_HEIGHT - BALL_WIDTH / 2 - 110;
	int width = BALL_WIDTH;
	int height = BALL_HEIGHT; 
	int ballSpeed; 
	int x_direction, y_direction; 

	Stage3Bar bar;
	
	Point getCenter() {
		return new Point(x + (BALL_WIDTH / 2), y + (BALL_HEIGHT / 2)); 
	}

	Point getBottomCenter() { 
		return new Point(x + (BALL_WIDTH / 2), y + (BALL_HEIGHT)); 

	}

	Point getTopCenter() { 
		return new Point(x + (BALL_WIDTH / 2), y);
	}

	Point getLeftCenter() { 
		return new Point(x, y + (BALL_HEIGHT / 2));
	}

	Point getRightCenter() {
		return new Point(x + (BALL_WIDTH), y + (BALL_HEIGHT / 2));
	}

	Stage3Ball(Stage3MainFrame rootComponent) {
		this.rootComponent = rootComponent;
		x_direction = Math.random() > 0.5 ? 1 : -1; 
		y_direction = -1; // 
		ballSpeed = (int) (Math.random() * 3) + 5; 
	}

	private void checkCollision() { 

		if (x <= 0 || x + BALL_WIDTH >= CANVAS_WIDTH) { 
			x_direction *= -1;
		}

		if (y <= 0) { // 
			y_direction *= -1;
		}

		if (getBottomCenter().y >= Stage3Bar.y) { 
			if (isCollideWithBar(new Rectangle(x, y, width, height),
					new Rectangle(bar.x, bar.y, bar.width, bar.height))) {
				y_direction *= -1;
			}
		}

	}

	private void movement() { 

		x += (ballSpeed * x_direction); // 
		y += (ballSpeed * y_direction); // 

		checkCollision(); 

	}

	private boolean isCollideWithBar(Rectangle rect1, Rectangle rect2) {

		return rect1.intersects(rect2);

	}

	boolean isCollideWithBlock(Stage3Block block) {
		Rectangle rect1 = new Rectangle(x, y, width, height);
		Rectangle rect2 = new Rectangle(block.x, block.y, block.width, block.height);
		return rect1.intersects(rect2); 
	}


	

	
	@Override
	public void run() {
		
		try {
			while (true) {
				movement();
				invalidate();
				repaint();
		
				if (rootComponent.isGameOver()||rootComponent.gameClear()) {
					break;
				}
				Thread.sleep(20);
				
				System.out.println("ddd");
		}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		}
		
	}

	
	

	

	
	

}