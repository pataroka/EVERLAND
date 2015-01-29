package org.game;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame{
	
	static final int WIDTH = 620;
	static final int HEIGHT = 620;
	static final int cellSize = 53;
	private InGame inGame;

	public Game() {
		inGame = new InGame();
		init();
		update();
	}
	
	private void init() {
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Trouble");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.add(inGame);
		this.setVisible(true);
	}
	
	private void update() {
		while (true) {
			inGame.tick();
			repaint();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
