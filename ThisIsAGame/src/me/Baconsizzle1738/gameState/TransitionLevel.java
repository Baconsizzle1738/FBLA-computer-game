package me.Baconsizzle1738.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import me.Baconsizzle1738.main.Game;

/**
 * This transition shows words that make up a sort of storyline.
 * @author Baconsizzle1738
 *
 */
public class TransitionLevel extends Transition{
	
	int index, wait;
	
	boolean focus, fade;
	
	float gradient, alpha;
	
	private String[] text;
	
	public TransitionLevel(String[] text, int lvl) {
		super(lvl);
		
		this.text = new String[text.length];
		index = 0;
		focus = false;
		fade = false;
		gradient = 0.03f;
		alpha = 0f;
		
		for (int i = 0; i<text.length; i++) {
			this.text[i] = text[i];
		
		}
	}
	
	@Override
	public void reset() {
		index = 0;
		focus = false;
		fade = false;
		isStart = false;
		alpha = 0f;
		isComplete = false;
	}
	
	/**
	 * Starts the <code>Transition</code>.
	 */
	public void startTransition() {
		isStart = true;
		focus = true;
	}


	@Override
	public void tick() {
		//System.out.println(alpha);
		if (isStart) {
			//this fades the text into focus and keeps it visible for
			//a certain time determined by the length of the text shown
			if (focus) {
				//fading in
				alpha += gradient;
				//keeps the text visible for a limited time
				if (alpha > 1f) {
					int time = text[index].length()*5;
					alpha = 1f;
					wait++;
					//System.out.println(wait);
					if (wait >= time) {
						//System.out.println(focus);
						focus = false;
						
						wait = 0;
					}
				}
			}
			//System.out.println(focus);
			//Fades the text out
			if (!focus) {
				
				alpha -= gradient;
				if (alpha <= 0) {
					alpha = 0;
					index ++;
					focus = true;
					if (index>=text.length) {
						isComplete = true;
						isStart = false;
					}
				}
			}
		}
	}


	@Override
	public void render(Graphics g) {
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		g.setColor(new Color(0.85f, 0.85f, 0.85f, alpha));
		
		//x and y are placeholders for now
		g.drawString(text[index], Game.WIDTH/2 - g.getFontMetrics().stringWidth(text[index])/2, 250);
	}
	
}
