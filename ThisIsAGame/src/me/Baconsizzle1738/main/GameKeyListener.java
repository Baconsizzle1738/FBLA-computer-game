package me.Baconsizzle1738.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.util.EventListener;

public class GameKeyListener implements KeyListener{
	GameHandler handler;
	
	//in order of w, a, s, d
	boolean[] keys = {false,false,false,false};
	
	public GameKeyListener(GameHandler h) {
		handler = h;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
