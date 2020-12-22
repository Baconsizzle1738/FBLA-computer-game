package me.Baconsizzle1738.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.util.EventListener;

public class GameKeyListener implements KeyListener{
	GameHandler handler;
	Levels levels;
	
	//in order of w, a, s, d
	boolean[] keys = {false,false,false,false};
	
	public GameKeyListener(GameHandler h, Levels l) {
		handler = h;
		levels = l;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		
		if (e.getKeyCode() == KeyEvent.VK_R) {
			levels.resetLevel();
		}
		
		for (int i = 0; i<handler.objects.size(); i++) {
			if (handler.objects.get(i).gettypeID() == ID.Player) {
				
				GameObject player = handler.objects.get(i);
				
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
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		for (int i = 0; i<handler.objects.size(); i++) {
			if (handler.objects.get(i).gettypeID() == ID.Player) {
				
				GameObject player = handler.objects.get(i);
				
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
			}
		}
		
	}
	//yeet
}
