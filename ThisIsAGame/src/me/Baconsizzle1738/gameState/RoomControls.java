package me.Baconsizzle1738.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import me.Baconsizzle1738.Objects.Button;
import me.Baconsizzle1738.Objects.GameHandler;
import me.Baconsizzle1738.main.ID;

public class RoomControls extends Room{
	
	private Button backButton;
	
	public RoomControls(int spawnX, int spawnY, GameHandler h, int lvl) {
		super(spawnX, spawnY, h, lvl);
		
		Font f = new Font(Font.MONOSPACED, Font.BOLD, 10);
		
		backButton = new Button(200, 200, ID.Button, lvl, "BACK", 30, 10, Color.red, new Color(160, 0, 0), new Color(200, 200, 200), f);
	}
	
	public Button getButton() {
		return backButton;
	}

	@Override
	public void startLevel() {
		
		handler.objects.add(backButton);
		backButton.setRelease(false);
	}

	@Override
	public void reset() {
		//nothing happen
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
