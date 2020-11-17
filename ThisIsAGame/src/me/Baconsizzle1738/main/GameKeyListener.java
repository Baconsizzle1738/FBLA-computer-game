package me.Baconsizzle1738.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.util.EventListener;

public class GameKeyListener implements KeyListener{
	GameHandler handler;
	GameObject player;
	
	//in order of w, a, s, d
	boolean[] keys = {false,false,false,false};
	
	public GameKeyListener(GameHandler h) {
		handler = h;
		//searches for player ID GameObject
		for (int i = 0; i<handler.objects.size(); i++) {
			if (handler.objects.get(i).gettypeID()==ID.Player) {
				player = handler.objects.get(i);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//nothing here for now
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	
	
	
	
}
