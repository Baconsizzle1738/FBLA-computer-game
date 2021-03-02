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
	}

}
