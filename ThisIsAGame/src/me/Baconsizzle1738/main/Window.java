package me.Baconsizzle1738.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = 1L;
	public Window(int width, int height, String title, Game game) {
		//the window frame
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//for scale simplicity
		frame.setResizable(false);
		//starts in middle of screen on startup
		frame.setLocationRelativeTo(null);
		frame.add(game);
		//so it can actually be seen
		frame.setVisible(true);
		game.start();
	}
}
