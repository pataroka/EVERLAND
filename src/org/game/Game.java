package org.game;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame{
	
	static final int WIDTH = 597;
	static final int HEIGHT = 620;
	static final int CELL_SIZE = 45;
	private Board board;
	private StartScreen startScreen;
	private boolean run;

	public Game() {
		this.setStartScreen();
	}
	
	public void setStartScreen() {
		run = false;
		if (board != null) {
			this.remove(board);
		}
		
		startScreen = new StartScreen(this);
		startScreen.setVisible(true);
		init();
		update();
	}
	
	public void setBoard() {
		startScreen.setVisible(false);
		this.remove(startScreen);
		board = new Board(startScreen.playerCount, startScreen.humanCount, this);
		board.setVisible(true);
		run = true;
		initBoard();
	}
	
	private void init() {
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Trouble");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.add(startScreen);
		
		//this.add(startScreen);
		this.setVisible(true);
	}
	
	private void initBoard() {
		this.add(board);
	}
	
	private void update() {
		while(true) {
			System.out.print((char) 0);
			while (run) {
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
}
