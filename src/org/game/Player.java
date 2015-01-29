package org.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Player extends JPanel {

	private boolean isHuman = false;
	private ArrayList<Figure> figures = new ArrayList<Figure>();
	private Color playerColor;
	
	public Player(Color color) {
		this.playerColor = color;
		initFigures();
	}
	
	public Player(boolean isHuman, Color color) {
		this.playerColor = color;
		this.isHuman = isHuman;
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
		if (playerColor == Color.red) {
			x = Game.cellSize * 3;
			y = Game.cellSize * 8;
		} else if (playerColor == Color.green) {
			x = Game.cellSize * 3;
			y = Game.cellSize * 3;
		} else if (playerColor == Color.blue) {
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
}
