package org.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class StartScreen extends JPanel implements MouseListener{
	
	private ArrayList<Button> playersButtons = new ArrayList<Button>();
	private ArrayList<Button> humansButtons = new ArrayList<Button>();
	private Image startScreen;

	public int buttonX;
	public int playerCount;
	public int humanCount;
	private boolean countSelected = false;
	boolean humanSelected = false;
	private Game game;
	

	public StartScreen(Game game) {
		this.game = game;
		initButtons();
		loadStartScreen();
		repaint();
		this.addMouseListener(this);
		this.setFocusable(true);
	}
	
	private void initButtons(){
		int index = 2;
		for (int i = 90; i < 370; i+=130) {
			this.playersButtons.add(new Button(i, 305, index, false));
			index++;
		}
		
		index = 1;
		for (int i = 45; i < 420; i+=120) {
			this.humansButtons.add(new Button(i, 505, index, false));
			index++;
		}
		
	}
	
	private void loadStartScreen() {
		ImageIcon ii = new ImageIcon("assets/START_SCREEN.png");
		startScreen = ii.getImage();
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(startScreen, 0, 0, null);
		for (Button b : playersButtons) {
			b.paint(g);
		}
		
		for (Button b : humansButtons) {
			b.paint(g);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (!countSelected){
			for (Button b : playersButtons){
				if (b.contains(new Point(e.getX(), e.getY()))) {
					b.setButton(true);
					playerCount = b.getCount();
					repaint();
					countSelected = true;
				}
			}
		} else {
			for (Button b : humansButtons){
				if (b.contains(new Point(e.getX(), e.getY()))) {
					b.setButton(true);
					humanCount = b.getCount();
					game.setBoard();
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
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