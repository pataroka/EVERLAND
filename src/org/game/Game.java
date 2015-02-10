package org.game;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame{
	
	static final int WIDTH = 590;
	static final int HEIGHT = 590;
	static final int CELL_SIZE = 45;
	private Board board;
	private StartScreen startScreen;

	public Game() {
		
		startScreen = new StartScreen();		
		board= new Board();
		initStartScreen();
//		initBoard();
		update();
	}
	
	private void initStartScreen() {
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Trouble");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.add(startScreen);
		this.setVisible(true);
	}
	
	private void initBoard() {
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
