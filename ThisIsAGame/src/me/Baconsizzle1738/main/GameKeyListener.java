package me.Baconsizzle1738.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.util.EventListener;

/**
 * Listens to the keyboard for any inputs that may affect the game.
 * 
 * @author Baconsizzle1738
 *
 */
public class GameKeyListener implements KeyListener{
	GameHandler handler;
	Levels levels;
	
	//in order of w, a, s, d
	boolean[] keys = {false,false,false,false};
	
	/**
	 * Key inputs may effect things in the <code>GameHandler</code> or the <code>Levels</code>.
	 * 
	 * @param h	The <code>GameHandler</code> to modify <code>GameObjects</code>.
	 * @param l	The <code>Levels</code> to modify <code>Room</code>.
	 */
	public GameKeyListener(GameHandler h, Levels l) {
		handler = h;
		levels = l;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		//R key is for resetting level
		if (e.getKeyCode() == KeyEvent.VK_R) {
			levels.resetLevel();
		}
		
		
		//handle player actions
		for (int i = 0; i<handler.objects.size(); i++) {
			if (handler.objects.get(i).gettypeID() == ID.Player) {
				
				Player player = (Player) handler.objects.get(i);
				
				//movement
				if (e.getKeyCode() == KeyEvent.VK_W) {
					player.setVolY(-2);
					keys[0] = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_A) {
					player.setVolX(-2);
					keys[1] = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					player.setVolY(2);
					keys[2] = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_D) {
					player.setVolX(2);
					keys[3] = true;
				}
				
				//usage(button pressing etc)
				if (e.getKeyCode() == KeyEvent.VK_E) {
					player.using = true;
				}
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		//P is for pause
		if (e.getKeyCode() == KeyEvent.VK_P) {
			Game.paused = !Game.paused;
			System.out.println("PAUSE REEE");
		}
		
		for (int i = 0; i<handler.objects.size(); i++) {
			if (handler.objects.get(i).gettypeID() == ID.Player) {
				
				Player player = (Player) handler.objects.get(i);
				//movement
				if (e.getKeyCode() == KeyEvent.VK_W) {
					keys[0] = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_A) {
					keys[1] = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					keys[2] = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_D) {
					keys[3] = false;
				}
				
				
				if (!keys[0] && !keys[2]) {
					player.setVolY(0);
				}
				if (!keys[1] && !keys[3]) {
					player.setVolX(0);
				}
				
				
				//usage
				if (e.getKeyCode() == KeyEvent.VK_E) {
					player.using = false;
				}
			}
		}
		
	}
}
