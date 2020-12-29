package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;

public class DinnerboneButton extends GameObject /*implements KeyListener*/{
    
	//private Player p;
	private String act;
	
	
	/**
	 * The buttons that the player presses to rotate the box on room 2
	 * 
	 * @param x			x position of button
	 * @param y			y position of button
	 * @param typeId	ID of button
	 * @param level		Level the button belongs to
	 * @param action	Action to be performed on the box
	 */
	public DinnerboneButton(int x, int y, ID typeId, int level, /*Player p,*/ String action) {
		super(x, y, typeId, level);
		//this.p = p;
		act = action;
	}
	
	/**
	 * Get action to be performed on the box
	 * @return	Action to be performed on the box
	 */
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
