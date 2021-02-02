package me.Baconsizzle1738.gameState;

import java.awt.Graphics;

import me.Baconsizzle1738.Objects.GameHandler;
import me.Baconsizzle1738.Objects.GameObject;
import me.Baconsizzle1738.main.ID;

/**
 * The Room is what the player must go through and escape.
 * 
 * @author Baconsizzle1738
 *
 */
public abstract class Room {  
	private int initialX, initialY;
	protected int lvl;
	GameHandler handler;
	public boolean complete = false;
	public boolean started = false;
	protected boolean canReset = false;
	protected int resetCool;
	/**
	 * Room is an individual level of the game.
	 * 
	 * @param spawnX	x position of player spawn
	 * @param spawnY	y position of player spawn
	 * @param h			GameHandler to add level objects to
	 * @param lvl		The level number
	 */
	public Room(int spawnX, int spawnY, GameHandler h, int lvl) {
		initialX = spawnX;
		initialY = spawnY;
		handler = h;
		this.lvl = lvl;
		resetCool = 0;
	}
	
	/**
	 * Moves the player to spawn location.
	 */
	public void SetPlayerSpawn() {
		for (int i = 0; i<handler.objects.size(); i++) {
			if (handler.objects.get(i).gettypeID() == ID.Player) {
				GameObject temp = handler.objects.get(i);
				temp.setX(initialX);
				temp.setY(initialY);
			}
		}
	}
	
	/**
	 * 
	 * @return	Returns level number.
	 */
	public int getLevel() {
		return lvl;
	}
	
	
	//to see if the conditions for completing the levels are met
	/**
	 * Starts the level with initial settings
	 */
	public abstract void startLevel();
	
	/**
	 * Resets the level.
	 */
	public abstract void reset();
	
	/**
	 * checks if level is complete
	 * @return		Returns true if complete, false otherwise.
	 */
	public abstract boolean isComplete ();
	
	/**
	 * Updates level logic
	 */
	public abstract void tick();
	
	/**
	 * Renders level specific graphics that are not GameObject
	 * @param g	Graphics board to render on.
	 */
	public abstract void render(Graphics g);
}