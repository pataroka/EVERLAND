package org.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener {

	private Image board;
	private static Image dice;
	public Figure.Color color = null;
	public int currentXY;
	private ArrayList<Player> players = new ArrayList<Player>();
	private static int diceNumber = 1;
	private boolean rollDice;
	private boolean moved;
	private Figure forRemove;
	private Player currentPlayer;
	private Color currentPlayerColor;
	private boolean gameEnd = false;
	private String turnPlayerLabel;
	private Player playerToRemoveFrom;
	private Random random = new Random();

	public Board() {

		initBoard();
		Figure.setBoardArray();

		this.addMouseListener(this);
		this.setFocusable(true);

		players.add(new Player(false, Figure.Color.GREEN));
		players.add(new Player(false, Figure.Color.YELLOW));
		players.add(new Player(false, Figure.Color.BLUE));
		players.add(new Player(true, Figure.Color.RED));

	}

	private void changePlayersTurnLabel(Player player) {
		switch (player.getPlayerColor()) {
		case GREEN:
			this.currentPlayer = player;
			this.currentPlayerColor = Color.GREEN;
			break;
		case YELLOW:
			this.currentPlayer = player;
			this.currentPlayerColor = Color.YELLOW;
			break;
		case BLUE:
			this.currentPlayer = player;
			this.currentPlayerColor = Color.BLUE;
			break;
		case RED:
			this.currentPlayer = player;
			this.currentPlayerColor = Color.RED;
			break;
		}
	}

	private boolean playerHasMove(Player player) {
		int defaultFiguresCount = 0;
		int figuresCantMoveCount = 0;
		for (Figure f : player.getFigures()) {
			if (f.isDefault()) {
				defaultFiguresCount++;
			}

			if (checkForFigureWithSameColor(player, f)) {
				figuresCantMoveCount++;
			}
		}
		
		if ((defaultFiguresCount == player.getFigures().size() && diceNumber != 6) || figuresCantMoveCount == player.getFigures().size()) {
			return false;
		}
		
		return true;
	}
	
	public void tick() {
		for (int i = 0; i < players.size(); i++) {
			changePlayersTurnLabel(players.get(i));
			repaint();

			rollDice = false;
			moved = false;
			synchronized (this) {
				if (players.get(i).isHuman()) {
					do {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} while (!rollDice);
				} else {
					dice();
				}

				if (!playerHasMove(players.get(i))) {
					continue;
				}

				do {
					if (players.get(i).isHuman()) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						currentXY = this.currentPlayer.getFigures().get(random.nextInt(this.currentPlayer.getFigures().size())).getPosition();
					}

					for (Figure f : players.get(i).getFigures()) {
						// game logic here
						boolean cantMove = false;
						if (currentXY == f.getPosition()) {
							if (diceNumber == 6) {
								checkPosition(f, diceNumber, cantMove, players.get(i));
								if (moved) {
									i--;
								}
							} else if (f.isDefault()) {
								continue;
							} else {
								checkPosition(f, diceNumber, cantMove, players.get(i));
								// rollDice = true;
							}
						}
					}

					if (forRemove != null) {
						playerToRemoveFrom.removeFigure(forRemove);
						checkWin(playerToRemoveFrom);
					}

				} while (!moved);
			}
		}
	}

	private void checkWin(Player player) {
		if (player.getFigures().isEmpty()) {
			gameEnd = true;
		}
	}

	private boolean checkForFigureWithSameColor(Player p, Figure f) {
		boolean cantMove = f.getNextPosition(diceNumber) != f.getPosition();
		for (Figure fig : p.getFigures()) {
			if (fig.getPosition() != f.getNextPosition(diceNumber) && cantMove) {
				return false;
			}
		}

		return true;
	}

	private void checkPosition(Figure f, int diceNumber, boolean cantMove, Player p) {
		for (Player pl : players) {
			for (Figure fig : pl.getFigures()) {
				if (fig.getPosition() == f.getNextPosition(diceNumber)
						&& fig.getColor() != f.getColor()) {
					fig.setDefault();
					moved = true;
					break;
				} else if (fig.getPosition() == f.getNextPosition(diceNumber)
						&& fig.getColor() == f.getColor()) {
					cantMove = true;
					moved = false;
					break;
				}

			}
			// if (moved || cantMove){
			// break;
			// }
		}
		if (!cantMove) {
			if (f.getNextPosition(diceNumber) == -1) {
				forRemove = f;
				playerToRemoveFrom = p;
			} else {
				f.setMove(diceNumber);
				forRemove = null;
			}

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

	public static void dice() {
		Random rnd = new Random();
		diceNumber = 1 + rnd.nextInt(6);
		getDiceImage(diceNumber);

	}

	public static void getDiceImage(int diceNumber) {
		ImageIcon id1 = new ImageIcon("assets/Dice1.png");
		ImageIcon id2 = new ImageIcon("assets/Dice2.png");
		ImageIcon id3 = new ImageIcon("assets/Dice3.png");
		ImageIcon id4 = new ImageIcon("assets/Dice4.png");
		ImageIcon id5 = new ImageIcon("assets/Dice5.png");
		ImageIcon id6 = new ImageIcon("assets/Dice6.png");

		switch (diceNumber) {
		case 1:
			dice = id1.getImage();
			break;
		case 2:
			dice = id2.getImage();
			break;
		case 3:
			dice = id3.getImage();
			break;
		case 4:
			dice = id4.getImage();
			break;
		case 5:
			dice = id5.getImage();
			break;
		case 6:
			dice = id6.getImage();
			break;
		}
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(this.currentPlayerColor);
		g2.setFont(new Font("Arial", Font.PLAIN, 24));
		if (gameEnd) {
			turnPlayerLabel = this.currentPlayer.getPlayerColor() + " player's win";
		} else {
			turnPlayerLabel = this.currentPlayer.getPlayerColor() + " player's turn";
		}
		
		g2.drawString(turnPlayerLabel, Game.CELL_SIZE * 4, 34);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(board, 0, 0, null);
		g.drawImage(dice, Game.CELL_SIZE * 6 + 3, Game.CELL_SIZE * 6 + 4, null);
		for (Player p : players) {
			p.paint(g);
		}

		paintComponent(g);
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
		synchronized (this) {
			if (!rollDice) {
				if (e.getX() > Game.CELL_SIZE * 6
						&& e.getX() < Game.CELL_SIZE * 7
						&& e.getY() > Game.CELL_SIZE * 6
						&& e.getY() < Game.CELL_SIZE * 7) {
					dice();
					repaint();
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
