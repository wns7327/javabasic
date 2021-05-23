package stage3;

import javax.swing.JPanel;

import stage2.Stage2Constant;

public class Stage3Block extends JPanel implements Stage2Constant, Runnable {
	Stage3MainFrame rootComponent;

	int x = 0;
	int y = 0;
	int i, j;
	int width = BLOCK_WIDTH;
	int height = BLOCK_HEIGHT;
	int color = 0;
	boolean isHidden;

	public Stage3Block(int i, int j, Stage3Ball[] balls) {
		this.i = i;
		this.j = j;
		rootComponent.balls = balls;
		x = BLOCK_WIDTH * j + BLOCK_GAP * j; //
		y = 100 + i * BLOCK_HEIGHT + BLOCK_GAP * i;
		width = BLOCK_WIDTH;
		height = BLOCK_HEIGHT;
		color = 4 - i;

	}

	@Override
	public void run() {
		try {
			while (!isHidden) {
				checkCollisionWithBall();
				Thread.sleep(20);

				if (isHidden || rootComponent.isGameOver()) {
					break;
				}

				System.out.println("dddd");
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void checkCollisionWithBall() {

		for (Stage3Ball b : rootComponent.balls) {
			if (b.isCollideWithBlock(this)) {
				for (int i = 0; i < BLOCK_ROWS; i++) {
					for (int j = 0; j < BLOCK_COLUMNS; j++) {
						Stage3Block block1 = rootComponent.blocks[i][j];

						if (isHidden == false) {
							if (b.getRightCenter().x > x) {
								b.x_direction *= -1;
								isHidden = true;
								rootComponent.score += (color + 1) * 10;

							}

							if (b.getBottomCenter().y > y + BLOCK_HEIGHT) {
								b.y_direction *= -1;
								isHidden = true;
								rootComponent.score += (color + 1) * 10;
							}

							if (b.getLeftCenter().x < x + BLOCK_WIDTH) {
								b.x_direction *= -1;
								isHidden = true;
								rootComponent.score += (color + 1) * 10;

							}

							if (b.getTopCenter().y < y) {
								b.y_direction *= -1;
								isHidden = true;
								rootComponent.score += (color + 1) * 10;
							}

						}
					}
				}

			}

		}
	}

}
