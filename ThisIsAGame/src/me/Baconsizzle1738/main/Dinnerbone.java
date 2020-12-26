package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Dinnerbone extends GameObject{
	
	private double orientation;
	private int test = 0;
	
	public Dinnerbone(int x, int y, ID typeId, int level) {
		super(x, y, typeId, level);
		orientation = 45;
	}
	
	@Override
	public void tick() {
		orientation++;
		System.out.println(orientation);
	}

	@Override
	public void render(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g.create();
		g2.translate(-130, -545);
		
		g2.rotate(Math.toRadians(orientation));
		
		g2.setColor(new Color(0, 150, 20));
		g2.fillRect(x, y, 50, 50);
		g2.setColor(new Color(210, 210, 210));
		g2.drawString("This side UP ^", x, y+10);
		g2.translate(475, -175);
		
		
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
