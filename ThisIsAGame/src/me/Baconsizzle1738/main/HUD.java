package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Font;
// import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;
/**
 * <code>HUD</code> is the class that controls the heads up display.
 * 
 * @author Baconsizzle1738
 *
 */
public class HUD {
	/**
	 * Health of the player.
	 */
	public static int health = Game.health;
	
	public static int lives = 1;
	//public boolean gameStarted = false;
	public Rectangle startButton = new Rectangle(Game.WIDTH/2-128, Game.HEIGHT/2-30, 256, 60);
	
	//check to see if mouse is over button
	boolean isOnButton = false;
	
	//so button colors change when clicked
	private Color buttonColor = new Color(230, 0, 0);
	private Color buttonBorderColor = Color.black;
	
	private Levels levels;
	private GameHandler handler;
	
	/**
	 * Constructor takes in <code>lvl</code> class as miuch of what happens in <code>HUD</code> is affected by <code>lvl</code>.
	 * @param lvl
	 */
	public HUD (Levels lvl, GameHandler h) {
		levels = lvl;
		handler = h;
	}
	
	/**
	 * Renders the heads up display.
	 * @param g	Graphics board to render <code>HUD</code> on.
	 */
	public void render(Graphics g) {
		//stuff here only shows when game is not started
		
		if (!Game.gameStarted) {
			//start button
			g.setColor(buttonBorderColor);
			g.drawRect(Game.WIDTH/2-129, Game.HEIGHT/2-31, 257, 61);
			g.setColor(buttonColor);
			g.fillRect(Game.WIDTH/2-128, Game.HEIGHT/2-30, 256, 60);
		}
		
		
		//stuff here only shows when game is started
		//health  bar, level and life indicator
		if (Game.gameStarted && !Game.isdead) {
			//health bar outline
			g.setColor(Color.white);
			g.drawRect(9, 9, 201, 11);
			//health bar background
			g.setColor(Color.gray);
			g.fillRect(10, 10, 200, 10);
			//health bar health
			g.setColor(Color.red);
			g.fillRect(10 ,10, health*2, 10);
			
			
			//level indicator
			g.setColor(new Color(200, 150, 0));
			g.drawString("Level " + Levels.level, 230, 15);
			
			//life indicator
			g.setColor(new Color(230,200,0));
			g.drawString("Lives: " + lives, 230, 25);
		}
		
		//death screen render
		if (Game.isdead) {
			g.setColor(Color.black);
			//completely darken the background
			g.fillRect(0,  0,  Game.WIDTH, Game.HEIGHT);
			
			//Message
			g.setColor(new Color(115, 115, 115));
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 70));
			g.drawString("YOU DIED", 350, 350);
		}
		
	}
	
	/**
	 * Updates logic in <code>HUD</code>.
	 */
	public void tick() {
		health = Game.health;
		//constrains health to be inside healthbar.
		health = Game.clamp(health, 0, 100);
		
		
		//if health is 0 then player loses a life
		//if life is 0 then GG rip.
		if (health <= 0) {
			if (lives>0) {
				lives--;
				Game.health = 100;
				handler.addObject(new PainFlash(-1, -1, null, -1, 0.01f, 0.7f, handler));
				if (lives == 0) {
					Game.isdead = true;
				}
				levels.resetLevel();
			}
		}
		
		//changes button color when clicked
		if (isOnButton == true) {
			buttonColor = new Color(200, 200, 200);
			buttonBorderColor = Color.red;
		}
//		else {
//			buttonColor = Color.red;
//			buttonBorderColor = Color.black;
//		}
		
		
	}
}
