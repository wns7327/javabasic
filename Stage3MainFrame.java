package stage3;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import main.FinishGUI;
import stage2.Stage2Constant;

public class Stage3MainFrame extends JFrame implements Stage2Constant { 

	public static int score = 0;

	Stage3MyPanel myPanel;
	private Timer timer = null;
	private int isHiddenCount;

	static Stage3Block[][] blocks = new Stage3Block[BLOCK_ROWS][BLOCK_COLUMNS]; 

	static Stage3Bar bar;
	boolean isGameFinish = false;
	
	
	static Stage3Ball[] balls = new Stage3Ball[3];

	static int nextBar = bar.x; // 

	public Stage3MainFrame(String title) { 
		super(title);

		this.setVisible(true); 
		this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT); 
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout()); 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		initData();

		myPanel = new Stage3MyPanel();
		add(myPanel);

		setKeyListener();
		startTimer();

	}

	private void initData() {

		for (int i = 0; i < balls.length; ++i) { 
			balls[i] = new Stage3Ball(this);
		}

		for (int i = 0; i < BLOCK_ROWS; i++) { 
			for (int j = 0; j < BLOCK_COLUMNS; j++) {
				blocks[i][j] = new Stage3Block(i, j, balls);
			}
		}

	}

	public void startTimer() {

		JOptionPane.showMessageDialog(null, "확인을 누르면 바로 게임이 시작됩니다");

		for (Stage3Ball b : balls) { // 

			new Thread(b).start();
			repaint();

		}

		for (int i = 0; i < BLOCK_ROWS; i++) { 
			for (int j = 0; j < BLOCK_COLUMNS; j++) {
				new Thread(blocks[i][j]).start();
			}
		}

		timer = new Timer(10, new ActionListener() {
														
			@Override

			public void actionPerformed(ActionEvent e) { 

				repaint();
				isHidden();

				if (isGameOver()) {
					
					JOptionPane.showMessageDialog(null, "게임 오버");
					gameStop();
					

				}
				if (gameClear()) {
					JOptionPane.showMessageDialog(null, "축하합니다 게임을 클리어하셨습니다!");
					gameStop();
				

				}

			}

		});

		timer.start();

	}
	void gameStop() {
		timer.stop();
		setVisible(false);
		new FinishGUI(getName());
	}

	void setKeyListener() {
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
					System.out.println("���� Ű");

					if (bar.x > 0) {
						bar.x -= 20;
					}

				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
					System.out.println("������ Ű");

					if (bar.x + BAR_WIDTH < CANVAS_WIDTH) {
						bar.x += 20;
					}
				}
				nextBar = bar.x;
			}

			
		});

	}

	public void blockHidden() {

		isHiddenCount = 0;
		for (int i = 0; i < BLOCK_ROWS; i++) {
			for (int j = 0; j < BLOCK_COLUMNS; j++) {
				Stage3Block block = blocks[i][j];
				if (block.isHidden) {
					isHiddenCount++;
				}
			}
		}
	}

	boolean isHidden() {           
	

		blockHidden();
		return isHiddenCount == BLOCK_ROWS * BLOCK_COLUMNS;

	}

	public static boolean isGameOver() {
		return (balls[0].y >= CANVAS_HEIGHT && balls[1].y >= CANVAS_HEIGHT 
				&& balls[2].y >= CANVAS_HEIGHT);
	}

	public boolean gameClear() {

		if (isHidden()) {

			isGameFinish = true;

		}
		return isGameFinish;
	}

}
