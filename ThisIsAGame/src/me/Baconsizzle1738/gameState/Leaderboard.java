package me.Baconsizzle1738.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import me.Baconsizzle1738.Objects.Button;
import me.Baconsizzle1738.Objects.GameHandler;
import me.Baconsizzle1738.main.ID;

public class Leaderboard extends Room{

	private Button backButton;
	
	public Leaderboard(int spawnX, int spawnY, GameHandler h, int lvl) {
		super(spawnX, spawnY, h, lvl);
		
		Font f = new Font(Font.MONOSPACED, Font.BOLD, 10);
		
		backButton = new Button(400, 200, ID.Button, lvl, "BACK", 100, 30, Color.red, new Color(160, 0, 0), new Color(200, 200, 200), f);
	}

	@Override
	public void startLevel() {
		handler.addObject(backButton);
		backButton.setRelease(false);
	}

	@Override
	public void reset() {
		
	}

	@Override
	public boolean isComplete() {
		return backButton.isReleased();
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

}
