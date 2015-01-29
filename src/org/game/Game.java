package org.game;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame{
	
	static final int WIDTH = 620;
	static final int HEIGHT = 620;
	static final int cellSize = 45;
	private Board board;

	public Game() {
		board= new Board();
		init();
		update();
	}
	
	private void init() {
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Trouble");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.add(board);
		this.setVisible(true);
	}
	
	private void update() {
		while (true) {
			board.tick();
			repaint();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
