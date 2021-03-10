package me.Baconsizzle1738.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import me.Baconsizzle1738.Objects.Button;
import me.Baconsizzle1738.Objects.GameHandler;
//import me.Baconsizzle1738.main.Game;
import me.Baconsizzle1738.main.ID;
/**
 * This is the main title screen.
 * @author Baconsizzle1738
 *
 */
public class RoomBegin extends Room {
	
	private Button startButton, controlsButton, leadButton;
	private Font buttonFont;
	private boolean hecc;
	
	public RoomBegin(int spawnX, int spawnY, GameHandler h, int lvl) {
		super(spawnX, spawnY, h, lvl);
		
		hecc = false;
		
		buttonFont = new Font(Font.MONOSPACED, Font.BOLD, 20);
		
		startButton = new Button(370, 300, ID.Button, lvl, "ESCAPE", 230, 50, Color.red, Color.gray, Color.yellow, buttonFont);
		controlsButton = new Button(370, 400, ID.Button, lvl, "CONTROLS", 230, 50, Color.red, Color.gray, Color.yellow, buttonFont);
		leadButton = new Button(370, 500, ID.Button, lvl, "LEADERBOARDS", 230, 50, Color.red, Color.gray, Color.yellow, buttonFont);
	}

	@Override
	public void startLevel() {
		//startButton = new Button(300, 300, ID.Button, lvl, "ESCAPE", 50, 50, Color.red, Color.gray, Color.yellow);
		//controlsButton = new Button(300, 400, ID.Button, lvl, "CONTROLS", 50, 50, Color.red, Color.gray, Color.yellow);
		//leadButton = new Button(300, 500, ID.Button, lvl, "LEADERBOARDS", 50, 50, Color.red, Color.gray, Color.yellow);
		
//		startButton.setRelease(false);
//		controlsButton.setRelease(false);
//		leadButton.setRelease(false);
		
		startButton.setRelease(false);
		controlsButton.setRelease(false);
		leadButton.setRelease(false);
		
		
		handler.addObject(startButton);
		handler.addObject(controlsButton);
		handler.addObject(leadButton);
		
		
	}
	
	
	/**
	 * Returns one of 3 buttons on the main menu screen
	 * 
	 * @param button	Type of button to return, "controls" for <code>controlsButton</code>, "lead" for <code>leadButton</code>, <code>startButton</code> returns by default.
	 * @return	one of 3 buttons.
	 */
	public Button getButton(String button) {
		if (button.equals("controls")) {
			return controlsButton;
		}
		if (button.equals("lead")) {
			return leadButton;
		}
		return startButton;
	}

	@Override
	public boolean isComplete() {
//		if (startButton.isReleased()) {
//			System.out.println("startButton.setRelease" + false);
//			startButton.setRelease(false);
//			System.out.println("RoomBegin.isComplete");
//			return true;
//		}
		return startButton.isReleased();
	}
	
	/**
	 * Checks if the controls button is pressed
	 * @return	<code>true</code> if pressed.
	 */
	public boolean goControls() {
		if (controlsButton.isReleased()) {
			System.out.println("controlsButton.setRelease" + false);
			controlsButton.setRelease(false);
			System.out.println("RoomBegin.goControls");
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the leaderboards button is pressed
	 * @return	<code>true</code> if pressed.
	 */
	public boolean goLead() {
		//System.out.println(handler.objects);
		return leadButton.isReleased();
	}

	@Override
	public void tick() {
		
		if (startButton.isReleased()) {
			hecc = true;
		}
		//System.out.println(controlsButton.isReleased());
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(200, 0, 0));
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 75));
		g.drawString("Don't Get Gamed!", 150, 200);
	}

	@Override
	public void reset() {
		
	}

	@Override
	public void hardReset() {
		startButton.setRelease(false);
		controlsButton.setRelease(false);
		leadButton.setRelease(false);
	}

}
