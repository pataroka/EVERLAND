package org.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame{
	
	static final int WIDTH = 590;
	static final int HEIGHT = 590;
	static final int CELL_SIZE = 45;
	private Board board;
	private StartScreen startScreen;
	private ActionListener listener;
	private int countAI = 0;
	private boolean run = false;

	public Game() {
		

		/*listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((e.getSource()) == startScreen.btn) {
					//countAI = Integer.parseInt(startScreen.btn.getText());
					//board.setPlayers(countAI);
					
					startScreen.setVisible(false);
					board.setVisible(true);
					initboadr();
					run=true;
				}
			}
		};*/
		
		//initStartScreen();
//		initBoard();
		this.setStartScreen();
		
	}
	
	public void setStartScreen() {
		startScreen = new StartScreen(this);
		startScreen.setVisible(true);
		init();
		update();
	}
	
	public void setBoard() {
		startScreen.setVisible(false);
		this.remove(startScreen);
		board = new Board(startScreen.playerCount, startScreen.humanCount);
		board.setVisible(true);
		run=true;
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
