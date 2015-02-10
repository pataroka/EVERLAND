package org.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class StartScreen extends JPanel {
	
	private Image startScreen;
	private Image buttonUnclicked;
	private Image buttonClicked;
	public JButton btn;
	public JButton btn2;
	public JButton btn3;
	public JButton btn4;
	private ActionListener listener;

	public StartScreen(ActionListener listener) {
		
		this.listener = listener;
	
		initStartScreen();
		
	}
	
	private void initStartScreen() {

		//loadStartScreen();
		btn = new JButton();
		btn.addActionListener(listener);
		btn.setText("12");
		this.add(btn);
		
		//int width = startScreen.getWidth(this);
		//int height = startScreen.getHeight(this);
		//setPreferredSize(new Dimension(width, height));
	}
	
	private void loadStartScreen() {
		ImageIcon ii = new ImageIcon("assets/START_SCREEN.png");
		ImageIcon ij = new ImageIcon("assets/ButtonClicked.png");
		ImageIcon ik = new ImageIcon("assets/ButtonUnclicked.png");		
		startScreen = ii.getImage();
		buttonClicked = ij.getImage();
		buttonUnclicked = ik.getImage();
		
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(startScreen, 0, 0, null);
		for (int i = 90; i <= 370; i+=130){
			g.drawImage(buttonUnclicked, i , 305, null);
		}
		for (int i = 45; i <= 430; i+=120){
			g.drawImage(buttonUnclicked, i , 505, null);
		}
	}
	
}