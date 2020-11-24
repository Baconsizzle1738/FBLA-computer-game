package me.Baconsizzle1738.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameMouseListener implements MouseListener{
	//probably won't need this
	
	
	private HUD hud;
	
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
		if (hud.startButton.contains(x, y)) {
			hud.isOnButton = true;
			//System.out.println("yeet");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (hud.startButton.contains(x, y)) {
			hud.isOnButton = false;
			hud.gameStarted = true;
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
