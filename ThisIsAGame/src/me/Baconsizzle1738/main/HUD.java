package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;

public class HUD {
	public static int health = 100;
	public boolean gameStarted = false;
	public Rectangle startButton = new Rectangle(Game.WIDTH/2-128, Game.HEIGHT/2-30, 256, 60);
	
	//check to see if mouse is over button
	boolean isOnButton = false;
	
	//so button colors change when clicked
	Color buttonColor = Color.red;
	Color buttonBorderColor = Color.black;
	
	
	
	
	public void render(Graphics g) {
		//start button
		g.setColor(buttonBorderColor);
		g.drawRect(Game.WIDTH/2-129, Game.HEIGHT/2-31, 257, 61);
		g.setColor(buttonColor);
		g.fillRect(Game.WIDTH/2-128, Game.HEIGHT/2-30, 256, 60);
		
		
		//healthbar outline
		g.setColor(Color.white);
		g.drawRect(9, 9, 201, 11);
		//healthbar background
		g.setColor(Color.gray);
		g.fillRect(10, 10, 200, 10);
		//healthbar health
		g.setColor(Color.red);
		g.fillRect(10 ,10, health*2, 10);
		//reeee
	}
	
	public void tick() {
		health = Game.clamp(health, 0, 100);
		if (isOnButton == true) {
			buttonColor = Color.white;
			buttonBorderColor = Color.red;
		}
		else {
			buttonColor = Color.red;
			buttonBorderColor = Color.black;
		}
		
		
	}
}
