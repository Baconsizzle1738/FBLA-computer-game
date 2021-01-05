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
	private GameHandler handler;
	private Levels levels;
	private HUD hud;
	
	//in order of w, a, s, d
	public boolean[] keys = {false,false,false,false};
	
	/**
	 * Key inputs may effect things in the <code>GameHandler</code> or the <code>Levels</code>.
	 * 
	 * @param h	The <code>GameHandler</code> to modify <code>GameObjects</code>.
	 * @param l	The <code>Levels</code> to modify <code>Room</code>.
	 */
	public GameKeyListener(GameHandler h, Levels l, HUD hud) {
		handler = h;
		levels = l;
		this.hud = hud;
	}
	
	/**
	 * Resets all movement keys to false
	 */
	public void reset() {
		for (int i = 0; i<keys.length; i++) {
			keys[i] = false;
		}
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
		if (e.getKeyCode() == KeyEvent.VK_P && Game.gameStarted && !Game.isdead) {
			Game.paused = !Game.paused;
			//System.out.println("PAUSE REEE");
		}
		
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
		for (int i = 0; i<handler.objects.size(); i++) {
			if (handler.objects.get(i).gettypeID() == ID.Player) {
				
				Player player = (Player) handler.objects.get(i);
				
				
				
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
		
		//for the name input
		if (Game.takingInput) {
			if (e.getKeyChar() != e.CHAR_UNDEFINED && hud.playerName.length()<=16 && e.getKeyCode() != KeyEvent.VK_SPACE) {
				hud.playerName += e.getKeyChar();
			}
			if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				try {
					hud.playerName = hud.playerName.substring(0, hud.playerName.length()-2);
				}
				catch(Exception ex) {
					
				}
			}
		}
		
	}
}
