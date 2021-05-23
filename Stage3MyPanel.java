package stage3;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JPanel;

import stage2.Stage2Constant;

public class Stage3MyPanel extends JPanel implements Stage2Constant {

	Stage3MainFrame rootComponent;
//	Ball ball;
	
	public Stage3MyPanel() {
		this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		this.setBackground(Color.LIGHT_GRAY);
		
		
		
	}
	

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		drawUI(g2d);
	}

	public void drawUI(Graphics2D g2d) {


		for (int i = 0; i < BLOCK_ROWS; i++) {
			for (int j = 0; j < BLOCK_COLUMNS; j++) {

				if (rootComponent.blocks[i][j].isHidden) {
					continue;
				} else if (rootComponent.blocks[i][j].color == 0) {
					g2d.setColor(Color.WHITE);
				} else if (rootComponent.blocks[i][j].color == 1) {
					g2d.setColor(Color.YELLOW);
				} else if (rootComponent.blocks[i][j].color == 2) {
					g2d.setColor(Color.BLUE);
				} else if (rootComponent.blocks[i][j].color == 3) {
					g2d.setColor(Color.MAGENTA);
				} else if (rootComponent.blocks[i][j].color == 4) {
					g2d.setColor(Color.RED);
				}
				g2d.fillRect(rootComponent.blocks[i][j].x, rootComponent.blocks[i][j].y,
						rootComponent.blocks[i][j].width, rootComponent.blocks[i][j].height);


			}


			g2d.setColor(Color.BLUE);
			g2d.setFont(new Font("���ü", Font.BOLD, 20));
			g2d.drawString("score : " + rootComponent.score, CANVAS_WIDTH / 2 - 50, 20);

			
			g2d.setColor(Color.BLACK); 
			g2d.fillOval(rootComponent.balls[0].x, rootComponent.balls[0].y, BALL_WIDTH, BALL_HEIGHT); // Ÿ�� �׸���

			
			g2d.setColor(Color.WHITE); 
			g2d.fillOval(rootComponent.balls[1].x, rootComponent.balls[1].y, BALL_WIDTH, BALL_HEIGHT); // Ÿ�� �׸���

			
			g2d.setColor(Color.RED); 
			g2d.fillOval(rootComponent.balls[2].x, rootComponent.balls[2].y, BALL_WIDTH, BALL_HEIGHT); // Ÿ�� �׸���
			
			
			
			g2d.setColor(Color.WHITE);
			g2d.fillRect(rootComponent.bar.x, rootComponent.bar.y, BAR_WIDTH, BAR_HEIGHT);



		}
	}
}
