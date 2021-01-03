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
	
	public static int lives = 3;
	//public boolean gameStarted = false;
	
	//start and death buttons are the same hitbox
	public Rectangle startDeathButton = new Rectangle(Game.WIDTH/2-128, Game.HEIGHT/2-30, 256, 60),
			controlsButton = new Rectangle(Game.WIDTH/2-128, Game.HEIGHT/2+50, 256, 60),
			controlBackButton = new Rectangle(150, 400, 100, 25);
	
	//check to see if mouse is over button
	public boolean isOnStartButton = false, isOnDeathButton = false, isOnControlsButton = false,
			isOnControlBackButton = false;
	
	//so button colors change when clicked (start button)
	//start
	private Color startButtonColor = new Color(230, 0, 0);
	private Color startButtonBorderColor = Color.black;
	//controls
	private Color controlButtonColor = new Color(230, 0, 0);
	private Color controlButtonBorderColor = Color.black;
	
	//back button for controls
	private Color controlBackButtonColor = new Color(230, 0, 0);
	private Color controlBackButtonBorderColor = Color.black;
	
	//Death reset button
	private Color deathButtonColor = new Color(135, 135, 135);
	private Color deathButtonBorderColor = new Color(180, 180, 180);
	
	private Levels levels;
	private GameHandler handler;
	
	/**
	 * Constructor takes in <code>lvl</code> class as much of what happens in <code>HUD</code> is affected by <code>lvl</code>.
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
		
		if (!Game.gameStarted && !Game.control) {
			//start button
			g.setColor(startButtonBorderColor);
			g.drawRect(Game.WIDTH/2-129, Game.HEIGHT/2-31, 257, 61);
			g.setColor(startButtonColor);
			g.fillRect(Game.WIDTH/2-128, Game.HEIGHT/2-30, 256, 60);
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
			g.setColor(new Color(80, 80, 80));
			g.drawString("ESCAPE", 455, 310);
			
			//instructions button
			g.setColor(controlButtonBorderColor);
			g.drawRect(Game.WIDTH/2-129, Game.HEIGHT/2+49, 257, 61);
			g.setColor(controlButtonColor);
			g.fillRect(Game.WIDTH/2-128, Game.HEIGHT/2+50, 256, 60);
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
			g.setColor(new Color(80, 80, 80));
			g.drawString("CONTROLS", 443, 388);
			
		}
		
		//for the controls
		if (Game.control) {
			
			g.setColor(new Color(200, 200, 200));
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
			g.drawString("CONTROLS", 150, 150);
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
			g.drawString("W - MOVE UP", 150, 180);
			g.drawString("S - MOVE DOWN", 150, 200);
			g.drawString("A - MOVE LEFT", 150, 220);
			g.drawString("D - MOVE RIGHT", 150, 240);
			g.drawString("E - USE", 150, 260);
			g.drawString("R - RESET ROOM", 150, 280);
			g.drawString("P - PAUSE", 150, 300);
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
			g.setColor(new Color(185, 0, 0));
			g.drawString("REFERENCES", 600, 350);
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
			g.drawString("ROOM 1 - KONAMI CODE", 600,380);
			g.drawString("ROOM 2 - MINECRAFT DINNERBONE", 492, 400);
			g.drawString("ROOM 3 - MISSINGNO POKEMON", 529, 420);
			
			//back button
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
			g.setColor(controlBackButtonBorderColor);
			g.drawRect(150, 400, 100, 25);
			g.setColor(controlBackButtonColor);
			g.fillRect(151, 401, 99, 24);
			g.setColor(new Color(80, 80, 80));
			g.drawString("BACK", 181, 417);
			
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
			if (Levels.level == 3) {
				g.drawString("Level MISSINGNO", 230, 15);
			}
			else {
				g.drawString("Level " + Levels.level, 230, 15);
			}
			
			
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
			g.drawString("YOU DIED", 335, 200);
			
			//reset button
			g.setColor(deathButtonBorderColor);
			g.drawRect(Game.WIDTH/2-129, Game.HEIGHT/2-31, 257, 61);
			g.setColor(deathButtonColor);
			g.fillRect(Game.WIDTH/2-128, Game.HEIGHT/2-30, 256, 60);
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
				if (lives == 0) {
					Game.isdead = true;
				}
				levels.resetLevel();
				handler.addObject(new PainFlash(-1, -1, null, -1, 0.01f, 0.7f, handler));
			}
		}
		
		//changes button color when clicked
		if (isOnStartButton) {
			startButtonColor = new Color(200, 200, 200);
			startButtonBorderColor = Color.red;
		}
		else {
			startButtonColor = Color.red;
			startButtonBorderColor = Color.black;
		}
		
		//for the tutorial/controls
		if (isOnControlsButton) {
			controlButtonColor = new Color(200, 200, 200);
			controlButtonBorderColor = Color.red;
		}
		else {
			controlButtonColor = Color.red;
			controlButtonBorderColor = Color.black;
		}
		
		//for controls back button
		if (isOnControlBackButton) {
			controlBackButtonColor = new Color(200, 200, 200);
			controlBackButtonBorderColor = Color.red;
		}
		else {
			controlBackButtonColor = Color.red;
			controlBackButtonBorderColor = Color.black;
		}
		
		//same thing but for death button
		if (isOnDeathButton) {
			deathButtonColor = new Color(180, 180, 180);
			deathButtonBorderColor = new Color(135, 135, 135);
			levels.resetDefault();
		}
		else {
			deathButtonColor = new Color(135, 135, 135);
			deathButtonBorderColor = new Color(180, 180, 180);
		}
		
		
	}
}
