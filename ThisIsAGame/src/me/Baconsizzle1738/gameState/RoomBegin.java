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

	public RoomBegin(int spawnX, int spawnY, GameHandler h, int lvl) {
		super(spawnX, spawnY, h, lvl);
		
	}

	@Override
	public void startLevel() {
		handler.addObject(new Button(300, 300, ID.Button, lvl, "ree", 50, 50, Color.red, Color.cyan, Color.yellow));
	}

	@Override
	public boolean isComplete() {
		if (Game.gameStarted) {
			return true;
		}
		return false;
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
