package me.Baconsizzle1738.gameState;

import java.awt.Graphics;

/**
 * This is like the "cutscene" in the game.
 * @author Baconsizzle1738
 *
 */
public abstract class Transition {
	
	private int level;
	
	/**
	 * Just a construcotr for <code>Transition</code>, nothing much here.
	 * @param lvl
	 */
	public Transition (int lvl) {
		level = lvl;
	}
	
	protected boolean isComplete = false;
	protected boolean isStart = false;
	
	/**
	 * Starts the transition sequence.
	 */
	public abstract void startTransition();
	
	/**
	 * Resets all values to default.
	 */
	public abstract void reset();
	
	/**
	 * Controls the logic of the transition screen
	 */
	public abstract void tick();
	
	/**
	 * Renders the transition screen.
	 * @param g	Graphics board to render on.
	 */
	public abstract void render (Graphics g);
	/**
	 * Signals the end of transition sequence.
	 */
	public boolean endTransition() {
		return isComplete;
	}
	
	public boolean isStarted() {
		return isStart;
	}
	
}
