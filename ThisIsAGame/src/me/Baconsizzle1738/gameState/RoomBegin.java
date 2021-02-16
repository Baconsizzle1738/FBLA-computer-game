package me.Baconsizzle1738.gameState;

import java.awt.Color;
import java.awt.Graphics;

import me.Baconsizzle1738.Objects.Button;
import me.Baconsizzle1738.Objects.GameHandler;
import me.Baconsizzle1738.main.Game;
import me.Baconsizzle1738.main.ID;
/**
 * This is the main title screen.
 * @author Baconsizzle1738
 *
 */
public class RoomBegin extends Room {
	
	private Button startButton = new Button(300, 300, ID.Button, lvl, "ESCAPE", 50, 50, Color.red, Color.gray, Color.yellow),
			controlsButton = new Button(300, 400, ID.Button, lvl, "CONTROLS", 50, 50, Color.red, Color.gray, Color.yellow),
			leadButton = new Button(300, 500, ID.Button, lvl, "LEADERBOARDS", 50, 50, Color.red, Color.gray, Color.yellow);
	
	public RoomBegin(int spawnX, int spawnY, GameHandler h, int lvl) {
		super(spawnX, spawnY, h, lvl);
		
		
		
	}

	@Override
	public void startLevel() {
		/*startButton = new Button(300, 300, ID.Button, lvl, "ESCAPE", 50, 50, Color.red, Color.gray, Color.yellow);*/
		/*controlsButton = new Button(300, 400, ID.Button, lvl, "CONTROLS", 50, 50, Color.red, Color.gray, Color.yellow);*/
		/*leadButton = new Button(300, 500, ID.Button, lvl, "LEADERBOARDS", 50, 50, Color.red, Color.gray, Color.yellow);*/
		
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
		return startButton.isReleased();
	}
	
	/**
	 * Checks if the controls button is pressed
	 * @return	<code>true</code> if pressed.
	 */
	public boolean goControls() {
		return controlsButton.isReleased();
	}
	
	/**
	 * Checks if the leaderboards button is pressed
	 * @return	<code>true</code> if pressed.
	 */
	public boolean goLead() {
		return leadButton.isReleased();
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void reset() {
		
	}



}
