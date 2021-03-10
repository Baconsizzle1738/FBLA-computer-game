package me.Baconsizzle1738.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import me.Baconsizzle1738.Objects.Button;
import me.Baconsizzle1738.Objects.GameHandler;
import me.Baconsizzle1738.main.Game;
import me.Baconsizzle1738.main.ID;

/**
 * This is the room that the player is sent to when they have no ore lives.
 * @author Baconsizzle1738
 *
 */
public class DeathLevel extends Room{

	private Button menuButton;
	
	private boolean done, started, fade;
	
	private float alpha, inc;
	
	/**
	 * The spawn parameters do not matter here.
	 * @param spawnX	Does not do anything.
	 * @param spawnY	Does not do anything.
	 * @param h			The <code>GameHandler</code> for any objects.
	 * @param lvl		The level of the room.
	 */
	public DeathLevel(int spawnX, int spawnY, GameHandler h, int lvl) {
		super(spawnX, spawnY, h, lvl);
		
		done = false;
		started = false;
		fade = false;
		alpha = 0f;
		inc = 0.005f;
		menuButton = new Button(400, 100, ID.Button, lvl, "YOU TRIED", 100, 20, new Color(160, 160, 160), new Color(120, 120, 120), Color.GRAY, new Font(Font.MONOSPACED, Font.BOLD, 12));
	}
	
	/**
	 * Check to see if the <code>DeathLevel</code> has started.
	 * @return	<code>true</code> if the level is started.
	 */
	public boolean isStarted() {
		return started;
	}

	@Override
	public void startLevel() {
		started = true;
	}

	@Override
	public void reset() {
		
	}

	@Override
	public boolean isComplete() {
		return done;
	}

	@Override
	public void tick() {
		System.out.println(alpha);
		if (started) {
			if (!fade) {
				alpha += inc;
				if (alpha >= 1f) {
					alpha = 1f;
					fade = true;
				}
			}
			else if (fade) {
				alpha -= inc;
				if (alpha <= 0) {
					alpha = 0;
					done = true;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 46));
		g.setColor(new Color(0.8f, 0.8f, 0.8f, alpha));
		g.drawString("YOU DIED", Game.WIDTH/2 - g.getFontMetrics().stringWidth("YOU DIED")/2, 250);
	}

	@Override
	public void hardReset() {
		done = false;
		started = false;
		fade = false;
		alpha = 0;
	}

}
