package org.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener{
	
	private Image board;
	private Image dice;
	public int currentXY;
	private ArrayList<Player> players = new ArrayList<Player>();
	private static int diceNumber = 1;
	private boolean rollDice;
	private boolean moved;
	
	public Board() {
		
		initBoard();
		Figure.setBoardArray();
		
		this.addMouseListener(this);
		this.setFocusable(true);
		
		players.add(new Player(true, Figure.Color.GREEN));
		players.add(new Player(true, Figure.Color.YELLOW));
		players.add(new Player(true, Figure.Color.BLUE));
		players.add(new Player(true, Figure.Color.RED));
		
		
	}
	
	

	public void tick() {
		for (Player player : players) {
			
			if (!player.isHuman()) {
				// AI logic here
				continue;
			}
			
			rollDice = false;
			moved = true;
			synchronized (this) {
				do {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} while (!rollDice);
				
				do {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					for (Figure f : player.getFigures()) {
						// game logic herez
						boolean cantMove = false;System.out.println(currentXY);System.out.println(f.getPosition());
						if (currentXY == f.getPosition()){
							if (diceNumber == 6){
								if (f.isDefault()){
									checkPosition(f, diceNumber, cantMove);
									//f.setMove(diceNumber);
									break;
									//rollDice = false;
								}
								else if (f.getPosition() < 30){
									checkPosition(f, diceNumber, cantMove);
									rollDice = false;
								}
								else {
									for (Figure fig : player.getFigures()){
										if (f.getPosition() + diceNumber == fig.getPosition()){
											break;
										}
										else {
											f.setMove(diceNumber);
											checkWin(player);
											rollDice = false;
										}
									}
								}
							}
							else if (!f.isDefault()){
									checkPosition(f, diceNumber, cantMove);
									checkWin(player);
							}
							else {
								rollDice = true;
								moved = true;
							}
						} else if (diceNumber == 6) {
							moved = false;
						}
					}
				} while (!moved);
			}
			
			repaint();
			
		}
		
	}



	private void checkWin(Player player) {
		int win = 0;
		for (Figure f: player.getFigures()){
			win += f.getPosition();
		}
		if (win == 150){
			//Player wins
		}
	}



	private void checkPosition(Figure f, int diceNumber, boolean cantMove) {
		for (Player pl : players){
			for (Figure fig : pl.getFigures()){
				fig.getPosition();
				fig.getColor();
				if (fig.getPosition() == f.getPosition() + diceNumber && fig.getColor() != f.getColor()) {
					fig.setDefault();
					f.setMove(diceNumber);
					rollDice = true;
					moved = true;
					break;
				} else if (fig.getPosition() == f.getPosition() + diceNumber && fig.getColor() == f.getColor()) {
					cantMove = true;
					break;
				} 
			}
			if (moved || cantMove){
				break;
			} 
		}
		if (!cantMove){
			f.setMove(diceNumber);
			rollDice = true;
			moved = true;
		}
	}
	
	private void initBoard() {
        
        loadImage();
		getDiceImage(diceNumber);
        int width = board.getWidth(this);
        int height = board.getHeight(this);
        setPreferredSize(new Dimension(width, height));
    }
	
	private void loadImage() {
		ImageIcon ii = new ImageIcon("assets/field34x34.png");
		board = ii.getImage();
	}
	
	public static void dice () {
		Random rnd = new Random();
		diceNumber = 1 + rnd.nextInt(6);
    }
	
	public void getDiceImage(int diceNumber) {
		ImageIcon id1 = new ImageIcon("assets/Dice1.png");
		ImageIcon id2 = new ImageIcon("assets/Dice2.png");
		ImageIcon id3 = new ImageIcon("assets/Dice3.png");
		ImageIcon id4 = new ImageIcon("assets/Dice4.png");
		ImageIcon id5 = new ImageIcon("assets/Dice5.png");
		ImageIcon id6 = new ImageIcon("assets/Dice6.png");
		
		switch (diceNumber){
			case 1: dice = id1.getImage(); break;
			case 2: dice = id2.getImage(); break;
			case 3: dice = id3.getImage(); break;
			case 4: dice = id4.getImage(); break;
			case 5: dice = id5.getImage(); break;
			case 6: dice = id6.getImage(); break;
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(board, 0, 0, null);
		g.drawImage(dice, Game.CELL_SIZE * 6 + 3, Game.CELL_SIZE * 6 + 4, null);
		for (Player p : players) {
			p.paint(g);
		}
	}

	private int getPositionIndex(int x, int y) {
		
		int cellX;
		int cellY;
		cellX = x / Game.CELL_SIZE;
		cellX *= Game.CELL_SIZE;
		cellY = y / Game.CELL_SIZE;
		cellY *= Game.CELL_SIZE;
		
		return Figure.positions.indexOf(new Point(cellX, cellY));
	}
	
	public int getDiceNumber() {
		return Board.diceNumber;
	}
	
	public void mouseClicked(MouseEvent e) {
		//System.out.println(getPositionIndex(e.getX(), e.getY()));
		synchronized (this) {
			if (!rollDice) {	
				// roll the dice here
				if (e.getX() > Game.CELL_SIZE * 6 && e.getX() < Game.CELL_SIZE * 7 && 
						e.getY() > Game.CELL_SIZE * 6 && e.getY() < Game.CELL_SIZE * 7) {
					dice();
					getDiceImage(diceNumber);
					repaint();System.out.println("aaaaaaaa");
					rollDice = true;
				}
			} else {
				currentXY = getPositionIndex(e.getX(), e.getY());
			}
	
			this.notify();
		}
		
	}

	
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
