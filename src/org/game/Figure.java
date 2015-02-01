package org.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Figure extends JPanel{

	private Point currentCoordinates;
	private int position;
	private Image pawn;
	public static ArrayList<Point> positions;
	enum Color {BLUE, RED, YELLOW, GREEN};
	private Color color;
	private Point defaultCoordinates;
	private int startingPosition;

	public Figure(int defaultPosition, Color color, int startPosition) {
		this.color = color;
		this.position = defaultPosition;
		this.defaultCoordinates = new Point(Figure.positions.get(defaultPosition));
		this.currentCoordinates = new Point(defaultCoordinates);
		this.startingPosition = startPosition;
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
		g.drawImage(pawn, currentCoordinates.x + 6, currentCoordinates.y - 21, null);
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
		this.currentCoordinates = new Point(defaultCoordinates);
		//this.position = 56;
	}
		
	public Color getColor() {
		return this.color;
	}
	
	public int getX() {
		return this.currentCoordinates.x;
	}
	
	public int getY() {
		return this.currentCoordinates.y;
	}
	
	public Point getCurrentCoordinates() {
		return this.currentCoordinates;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public int getNextPosition(int dice) {
		
		if (dice == 6 && isDefault()) {
			return startingPosition;
		}
		
		return (this.position + dice) % 40;
	}
	
	public void setMove(int diceNumber) {
		
		if (diceNumber == 6 && isDefault()) {
			this.currentCoordinates.x = positions.get(startingPosition).x;
			this.currentCoordinates.y = positions.get(startingPosition).y;
			this.position = startingPosition;
			return;
		}
		
		this.position = (this.position + diceNumber) % 40;
		currentCoordinates = positions.get(position);
		
		
		//this.currentCoordinates = positions.get(position);
	}
	
	public boolean isDefault() {System.out.println(this.currentCoordinates.equals(this.defaultCoordinates));
		return this.currentCoordinates.equals(this.defaultCoordinates);
	}
}
