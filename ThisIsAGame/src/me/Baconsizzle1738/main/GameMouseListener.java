package me.Baconsizzle1738.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listens to mouse inputs by the user for the game.
 * @author Baconsizzle1738
 *
 */
public class GameMouseListener implements MouseListener{
	
	
	private HUD hud;
	
	/**
	 * Listens to mouse actions in <code>HUD</code>.
	 * @param h	The <code>HUD</code> where there is mouse functionality needed.
	 */
	public GameMouseListener(HUD h) {
		hud = h;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		//the start button
		if (hud.startButton.contains(x, y) && !Game.gameStarted) {
			hud.isOnButton = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		//the start button
		if (hud.startButton.contains(x, y) && !Game.gameStarted) {
			hud.isOnButton = false;
			Game.gameStarted = true;
			//System.out.println("yeet");
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
	
}
