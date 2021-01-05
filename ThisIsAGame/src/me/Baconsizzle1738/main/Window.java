package me.Baconsizzle1738.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
/**
 * The window for the Game
 * @author Baconsizzle1738
 *
 */
public class Window extends Canvas {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates a <code>Window</code> for the game to run in.
	 * 
	 * @param width		width of window
	 * @param height	height of window
	 * @param title		title of window
	 * @param game		the <code>Game</code> that runs in the window.
	 */
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
