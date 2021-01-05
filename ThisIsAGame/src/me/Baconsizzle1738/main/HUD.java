package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Font;
// import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
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
	
	public int score;

	private int ticks;
	
	public String playerName;
	
	public static int health = Game.health;
	
	public static int lives = 3;
	//public boolean gameStarted = false;
	
	//button hitboxes
	public Rectangle startDeathButton = new Rectangle(Game.WIDTH/2-128, Game.HEIGHT/2-30, 256, 60),
			controlsButton = new Rectangle(Game.WIDTH/2-128, Game.HEIGHT/2+50, 256, 60),
			controlBackButton = new Rectangle(150, 400, 100, 25),
			winButton = new Rectangle (Game.WIDTH/2-129, Game.HEIGHT/2+49, 257, 61),
			leadButton = new Rectangle (Game.WIDTH/2-129, Game.HEIGHT/2+129, 257, 61),
			leadBackButton = new Rectangle(450, 450, 100, 25);
	
	//check to see if mouse is over button
	public boolean isOnStartButton = false, isOnDeathButton = false, isOnControlsButton = false,
			isOnControlBackButton = false, isOnWinButton = false, isOnLeadButton = false, isOnLeadBackButton = false;
	
	
	
	//so button colors change when clicked
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
	
	//win screen button
	private Color winButtonColor = new Color(0, 150, 0);
	private Color winButtonBorderColor = new Color(0, 230, 0);
	
	//leaderboard button
	private Color leadButtonColor = new Color(230, 0, 0);
	private Color leadButtonBorderColor = Color.black;
	
	//back button for leaderboard
	private Color leadBackButtonColor = new Color(230, 0, 0);
	private Color leadBackButtonBorderColor = Color.black;
	
	
	
	private Levels levels;
	private GameHandler handler;
	
	/**
	 * Constructor takes in <code>lvl</code> class as much
	 * of what happens in <code>HUD</code> is affected by
	 * <code>lvl</code>.
	 * @param lvl
	 */
	public HUD (Levels lvl, GameHandler h) {
		levels = lvl;
		handler = h;
		playerName = "";
		score = 1;
		ticks = 1;
	}
	
	/**
	 * Renders the heads up display.
	 * @param g	Graphics board to render <code>HUD</code> on.
	 */
	public void render(Graphics g) {
		
		
		//HUD renders based on game state.
		
		//Game main menu screen
		if (!Game.gameStarted && !Game.control && !Game.leaderboard) {
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
			
			//leaderboard button
			g.setColor(leadButtonBorderColor);
			g.drawRect(Game.WIDTH/2-129, Game.HEIGHT/2+129, 257, 61);
			g.setColor(leadButtonColor);
			g.fillRect(Game.WIDTH/2-128, Game.HEIGHT/2+130, 256, 60);
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
			g.setColor(new Color(80, 80, 80));
			g.drawString("LEADERBOARD", 420, 468);
			
		}
		
		//for the controls screen
		if (Game.control) {
			
			g.setColor(new Color(200, 200, 200));
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
			g.drawString("CONTROLS", 150, 150);
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
			g.drawString("W - MOVE UP", 150, 180);
			g.drawString("S - MOVE DOWN", 150, 200);
			g.drawString("A - MOVE LEFT", 150, 220);
			g.drawString("D - MOVE RIGHT", 150, 240);
			g.drawString("E - USE/INTERACT", 150, 260);
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
		if (Game.gameStarted && !Game.isdead && !Game.win) {
			g.setFont(new Font(Font.MONOSPACED, 0, 12));
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
			//do not show level for room 3
			if (Levels.level == 3) {
				g.drawString("Room: MISSINGNO", 230, 15);
			}
			else {
				g.drawString("Room: " + Levels.level, 230, 15);
			}
			
			//life indicator
			g.setColor(new Color(230,200,0));
			g.drawString("Lives: " + lives, 230, 25);
			
			//pause indicator
			if (Game.paused) {
				g.setColor(Color.red);
				g.drawString("PAUSED", 20, 50);
			}
			
			//controls display
			g.setColor(Color.white);
			g.drawString("WASD - MOVE", 10, 500);
			g.drawString("P - PAUSE", 10, 510);
			g.drawString("R - RESET", 10, 520);
			//the E - INTERACT only should show when the player is on an interactive object
		}
		
		//death screen render
		if (Game.isdead) {
			g.setColor(Color.black);
			//completely darken the background
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			
			//Message
			g.setColor(new Color(115, 115, 115));
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 70));
			g.drawString("YOU DIED", 335, 200);
			//dead players get no score
			
			//reset button
			g.setColor(deathButtonBorderColor);
			g.drawRect(Game.WIDTH/2-129, Game.HEIGHT/2-31, 257, 61);
			g.setColor(deathButtonColor);
			g.fillRect(Game.WIDTH/2-128, Game.HEIGHT/2-30, 256, 60);
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
			g.setColor(new Color(30, 30, 30));
			g.drawString("AT LEAST YOU TRIED", 392, 305);
		}
		
		
		//Win screen render
		if (Game.win) {
			g.setColor(new Color(160, 160, 160));
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			
			//Message
			g.setColor(new Color(20, 150, 20));
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 70));
			g.drawString("YOU ESCAPED", 280, 200);
			
			//player enters name here
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
			g.drawString(playerName + " ESCAPED WITH A SCORE OF "+score, 250, 300);
			
			//limitations for naming
			g.setFont(new Font(Font.MONOSPACED, 0, 15));
			g.setColor(new Color(200, 200, 200));
			g.drawString("NAME CAN BE AT MOST 16 CHARACTERS LONG", 200, 315);
			g.drawString("NAME MUST BE AT LEAST 1 CHARACTER LONG", 200, 330);
			g.drawString("NO SPACES", 200, 345);
			
			//button
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
			g.setColor(winButtonBorderColor);
			g.drawRect(Game.WIDTH/2-129, Game.HEIGHT/2+49, 257, 61);
			g.setColor(winButtonColor);
			g.fillRect(Game.WIDTH/2-128, Game.HEIGHT/2+50, 256, 60);
			g.setColor(new Color(100, 100, 100));
			g.drawString("FREE AT LAST", 433, 385);
			
		}
		
		//Leaderboard render
		if (Game.leaderboard) {
			
			//header
			g.setColor(new Color(200, 0, 0));
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
			g.drawString("LEADERBOARD", 340, 100);
			
			//positions
			g.setColor(new Color(175, 175, 175));
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
			g.drawString("1.", 200, 150);
			g.drawString("2.", 200, 175);
			g.drawString("3.", 200, 200);
			g.drawString("4.", 200, 225);
			g.drawString("5.", 200, 250);
			g.drawString("6.", 200, 275);
			g.drawString("7.", 200, 300);
			g.drawString("8.", 200, 325);
			g.drawString("9.", 200, 350);
			g.drawString("10.", 200, 375);
			
			
			//displays only the top 10 scores.
			int cnt = 0;
			for (ScoreData s : Game.scoreData) {
				//System.out.println(cnt);
				g.drawString(s.getName(), 235, 150+25*cnt);
				g.drawString(s.getScore()+"", 700, 150+25*cnt);
				cnt++;
				g.drawString("You must escape at least once to view leaderboards", 210, 555+25*cnt);
				if (cnt>= 10) {
					cnt = 0;
					break;
				}
			}
			g.drawString("You must escape at least once to view leaderboards", 210, 555+25*Game.scoreData.size());
			
			
			
			//back button
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
			g.setColor(leadBackButtonBorderColor);
			g.drawRect(450, 450, 100, 25);
			g.setColor(leadBackButtonColor);
			g.fillRect(451, 451, 99, 24);
			g.setColor(new Color(80, 80, 80));
			g.drawString("BACK", 481, 467);
			
		}
		
	}
	
	/**
	 * Updates logic in <code>HUD</code>.
	 */
	public void tick() {
		health = Game.health;
		//constrains health to be inside healthbar.
		health = Game.clamp(health, 0, 100);
		
		//update score while game is playing
		if (Game.gameStarted && !Game.win && !Game.isdead && !Game.paused) {
			ticks++;
			score = (20000000/ticks)*lives+(health*12);
		}
		
		
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
		
		//changes button color when clicked, also resetting the score and player name
		//start button
		if (isOnStartButton) {
			startButtonColor = new Color(200, 200, 200);
			startButtonBorderColor = Color.red;
			//reset score and name
			score = 0;
			playerName = "";
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
		
		//for win button
		if (isOnWinButton) {
			winButtonColor = new Color(0, 250, 0);
			winButtonBorderColor = new Color(0, 150, 0);
			levels.resetDefault();
			lives = 3;
			ticks = 0;
		}
		else {
			winButtonColor = new Color(0, 150, 0);
			winButtonBorderColor = new Color(0, 230, 0);
		}
		
		//for leaderboard button
		if (isOnLeadButton) {
			leadButtonColor = new Color(200, 200, 200);
			leadButtonBorderColor = Color.red;
		}
		else {
			leadButtonColor = Color.red;
			leadButtonBorderColor = Color.black;
		}
		
		//for back button on leaderboard screen
		if (isOnLeadBackButton) {
			leadBackButtonColor = new Color(200, 200, 200);
			leadBackButtonBorderColor = Color.red;
		}
		else {
			leadBackButtonColor = Color.red;
			leadBackButtonBorderColor = Color.black;
		}
	}
}
