package org.game;

import java.util.ArrayList;

public class Player {

	private boolean isHuman = false;
	private ArrayList<Figure> figures;
	private int playerPosition;
	
	public Player(int position) {
		this.playerPosition = position;
		initFigures();
	}
	
	public Player(boolean isHuman, int position) {
		this.playerPosition = position;
		this.isHuman = isHuman;
		initFigures();
	}
	
	private void initFigures() {
		for (int i = 0; i < 4; i++) {
			if (i > 1) {
				this.figures.add(new Figure(5,5));
			} else {
				this.figures.add(new Figure(5,5));
			}
			
		}
	}
	
	public ArrayList<Figure> getFigures() {
		return this.figures;
	}
}
