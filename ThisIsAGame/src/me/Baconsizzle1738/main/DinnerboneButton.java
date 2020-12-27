package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;

public class DinnerboneButton extends GameObject /*implements KeyListener*/{
	private int cool;
	private boolean pressed, canPress;
	private Player p;
	private String act;
	public DinnerboneButton(int x, int y, ID typeId, int level, Player p, String action) {
		super(x, y, typeId, level);
		pressed = false;
		canPress = true;
		this.p = p;
		cool = 0;
		act = action;
	}
	
	public String getAction() {
		return act;
	}
	
	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(206, 119, 42));
		g.fillRect(x, y, 15, 15);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 15, 15);
	}

//	@Override
//	public void keyTyped(KeyEvent e) {
//		
//	}
//
//	@Override
//	public void keyPressed(KeyEvent e) {
//		if (e.getKeyCode() == KeyEvent.VK_E) {
//			pressed = true;
//		}
//	}
//
//	@Override
//	public void keyReleased(KeyEvent e) {
//		if (e.getKeyCode() == KeyEvent.VK_E) {
//			pressed = false;
//		}
//	}

}
