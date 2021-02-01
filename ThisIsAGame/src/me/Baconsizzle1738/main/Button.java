package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends GameObject implements MouseListener{
	
	private String message, action;
	private int width, height;
	private boolean isPressed;
	private Color color, pressColor;
	
	public Button(int x, int y, ID typeId, int level, String message, int wid, int hei, Color color, Color pressColor/*, String act*/) {
		super(x, y, typeId, level);
		this.message = message;
		//action = act;
		width = wid;
		height = hei;
		isPressed = false;
		this.color = color;
		this.pressColor = pressColor;
	}


	@Override
	public void tick() {
		
	}


	@Override
	public void render(Graphics g) {
		if (!isPressed) {
			g.setColor(color);
		}
		else {
			g.setColor(pressColor);
		}
		g.fillRect(x,  y,  width, height);
		g.drawString(message, x, y);
		
	}


	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public boolean isPressed() {
		return isPressed;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (this.getBounds().contains(x, y)) {
			isPressed = true;
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		//no
	}


	@Override
	public void mouseExited(MouseEvent e) {
		//no
	}
	
}
