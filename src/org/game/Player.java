package org.game;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Player extends JPanel {

	private boolean isHuman = false;
	private ArrayList<Figure> figures = new ArrayList<Figure>();
	private Figure.Color playerColor;
	
	public Player(Figure.Color color) {
		this.playerColor = color;
		initFigures();
	}
	
	public Player(boolean isHuman, Figure.Color color) {
		this.playerColor = color;
		this.setHuman(isHuman);
		initFigures();
	}
	
	public void paint(Graphics g) {
		for (Figure f : figures) {
			f.paint(g);
		}
	}
	
	private void initFigures() {
		int x = Game.WIDTH - 100;;
		int y;
		if (playerColor == Figure.Color.RED) {
			x = Game.cellSize * 3;
			y = Game.cellSize * 8;
		} else if (playerColor == Figure.Color.GREEN) {
			x = Game.cellSize * 3;
			y = Game.cellSize * 3;
		} else if (playerColor == Figure.Color.BLUE) {
			x = Game.cellSize * 8;
			y = Game.cellSize * 8;
		} else {
			x = Game.cellSize * 8;
			y = Game.cellSize * 3;
		}
		
		for (int i = 0; i < 4; i++) {
			if (i == 1) {
				x += Game.cellSize;
			} else if (i == 2){
				y += Game.cellSize;
			} else if (i == 3) {
				x -= Game.cellSize;
			}
			
			this.figures.add(new Figure(x, y, playerColor));
		}
	}
	
	public ArrayList<Figure> getFigures() {
		return this.figures;
	}

	public boolean isHuman() {
		return isHuman;
	}

	public void setHuman(boolean isHuman) {
		this.isHuman = isHuman;
	}
}
