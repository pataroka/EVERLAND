package org.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Figure extends JPanel{

	private int position;
	private Image pawn;
	public static ArrayList<Point> positions;
	enum Color {BLUE, RED, YELLOW, GREEN};
	private Color color;
	private int startingPosition;
	private int defaultPosition;
	private int mainBoardEndPosition;
	private int additionalPositionsStart;
	private int moveCount = 0;

	public Figure(int defaultPosition, Color color, int startPosition, int additionalPosition) {
		this.color = color;
		this.position = defaultPosition;
		this.defaultPosition = defaultPosition;
		this.startingPosition = startPosition;
		this.additionalPositionsStart = additionalPosition;
		this.mainBoardEndPosition = (startPosition + 35) % 40;
		getImage(color);
	}
	
	private void getImage(Color color) {
		ImageIcon ib = new ImageIcon("assets/Blue_Pawn36x64.png");
		ImageIcon ir = new ImageIcon("assets/Red_Pawn36x64.png");
		ImageIcon iy = new ImageIcon("assets/Yellow_Pawn36x64.png");
		ImageIcon ig = new ImageIcon("assets/Green_Pawn36x64.png");
		
		switch (color){
			case BLUE: pawn = ib.getImage(); break;
			case RED: pawn = ir.getImage(); break;
			case YELLOW: pawn = iy.getImage(); break;
			case GREEN: pawn = ig.getImage(); break;
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(pawn, positions.get(position).x + 6, positions.get(position).y - 21, null);
	}
	
	public static void setBoardArray() {
		positions = new ArrayList<Point>();
		int currentX = Game.CELL_SIZE;
		int currentY = Game.CELL_SIZE;
		for (int i = 0; i < 40; i++) {
			
			positions.add(new Point(currentX, currentY));
			
			if (i < 10) {
				currentX += Game.CELL_SIZE;
			} else if(i < 20) {
				currentY += Game.CELL_SIZE;
			} else if (i < 30) {
				currentX -= Game.CELL_SIZE;
			} else {
				currentY -= Game.CELL_SIZE;
			}
		}
		
		currentX = Game.CELL_SIZE * 2;
		for (int i = 40; i < 44; i++) {
			
			positions.add(new Point(currentX, Game.CELL_SIZE * 6));
			
			currentX += Game.CELL_SIZE; 
		}
		
		currentY = Game.CELL_SIZE * 2;
		for (int i = 44; i < 48; i++) {
			
			positions.add(new Point(Game.CELL_SIZE * 6, currentY));
			
			currentY += Game.CELL_SIZE; 
		}
		
		currentX = Game.CELL_SIZE * 10;
		for (int i = 48; i < 52; i++) {
			
			positions.add(new Point(currentX, Game.CELL_SIZE * 6));
			
			currentX -= Game.CELL_SIZE; 
		}
		
		currentY = Game.CELL_SIZE * 10;
		for (int i = 52; i < 56; i++) {
			
			positions.add(new Point(Game.CELL_SIZE * 6, currentY));
			
			currentY -= Game.CELL_SIZE; 
		}
		
		currentX = Game.CELL_SIZE * 3;
		currentY = Game.CELL_SIZE * 3;
		for (int i = 56; i < 60; i++) {
			
			if (i == 57) {
				currentX += Game.CELL_SIZE;
			} else if (i == 58){
				currentY += Game.CELL_SIZE;
			} else if (i == 59) {
				currentX -= Game.CELL_SIZE;
			}
			
			positions.add(new Point(currentX, currentY));
		}
		
		currentX = Game.CELL_SIZE * 8;
		currentY = Game.CELL_SIZE * 3;
		for (int i = 60; i < 64; i++) {
			
			if (i == 61) {
				currentX += Game.CELL_SIZE;
			} else if (i == 62){
				currentY += Game.CELL_SIZE;
			} else if (i == 63) {
				currentX -= Game.CELL_SIZE;
			}
			
			positions.add(new Point(currentX, currentY));
		}
		
		currentX = Game.CELL_SIZE * 8;
		currentY = Game.CELL_SIZE * 8;
		for (int i = 64; i < 68; i++) {
			
			if (i == 65) {
				currentX += Game.CELL_SIZE;
			} else if (i == 66){
				currentY += Game.CELL_SIZE;
			} else if (i == 67) {
				currentX -= Game.CELL_SIZE;
			}
			
			positions.add(new Point(currentX, currentY));
		}
		
		currentX = Game.CELL_SIZE * 3;
		currentY = Game.CELL_SIZE * 8;
		for (int i = 68; i < 72; i++) {
			
			if (i == 69) {
				currentX += Game.CELL_SIZE;
			} else if (i == 70){
				currentY += Game.CELL_SIZE;
			} else if (i == 71) {
				currentX -= Game.CELL_SIZE;
			}
			
			positions.add(new Point(currentX, currentY));
		}
	}
	
	public void setDefault() {
		this.position = this.defaultPosition;
		this.moveCount = 0;
		//this.position = 56;
	}
		
	public Color getColor() {
		return this.color;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public int getNextPosition(int diceNumber) {

		//System.out.println("before " + moveCount);
		if (diceNumber == 6 && this.isDefault()) {

		//System.out.println("before " + moveCount);

			moveCount = 1;
			return startingPosition;
		} else if (this.isDefault()) {
			return this.position;
		}
		
		int currentPosition = (this.position + diceNumber) % 40;
		//if ((position == mainBoardEndPosition || position == mainBoardEndPosition - 1) && diceNumber == 6) {
			
		//}
		if (moveCount + diceNumber <= 36) {
			return currentPosition;
		}
		
		currentPosition = additionalPositionsStart + (moveCount + diceNumber) - 37;
		//if (currentPosition >= additionalPositionsStart + 4) {
		//	return 0;
		//}
		
		if (currentPosition == additionalPositionsStart + 4) {
			return -1;
		}
		
		if (currentPosition > additionalPositionsStart + 4) {
			return this.position;
		}
		
		return currentPosition;
		
	}
	
	public void setMove(int diceNumber) {
		
		int currentPosition = getNextPosition(diceNumber);
		if (currentPosition != startingPosition) {
			moveCount += diceNumber;
		}

		//System.out.println("afiter " + moveCount);
		this.position = currentPosition;

		
		//this.position = (this.position + diceNumber) % 40;
		//currentCoordinates = positions.get(position);
		

		//this.currentCoordinates = positions.get(position);
	}
	
	public boolean isDefault() {
		return this.position == this.defaultPosition;
	}
}
