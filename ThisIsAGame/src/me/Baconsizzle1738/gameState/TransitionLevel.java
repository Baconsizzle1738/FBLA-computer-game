package me.Baconsizzle1738.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TransitionLevel extends Transition{
	
	int index, wait;
	
	boolean focus, fade, started;
	
	float gradient, alpha;
	
	private String[] text;
	
	public TransitionLevel(String[] text, int lvl) {
		super(lvl);
		
		this.text = new String[text.length];
		index = 0;
		focus = false;
		fade = false;
		started = false;
		gradient = 0.02f;
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
		started = false;
		alpha = 0f;
	}
	
	public void startTransition() {
		started = true;
		focus = true;
	}


	@Override
	public void tick() {
		//System.out.println(alpha);
		if (started) {
			//this fades the text into focus and keeps it visible for
			//a certain time determined by the length of the text shown
			if (focus) {
				//fading in
				alpha += gradient;
				//keeps the text visible for a limited time
				if (alpha > 1f) {
					int time = text[index].length()*10;
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
						started = false;
					}
				}
			}
		}
	}


	@Override
	public void render(Graphics g) {
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
		g.setColor(new Color(0.85f, 0.85f, 0.85f, alpha));
		
		//x and y are placeholders for now
		g.drawString(text[index], 100, 200);
	}
	
}
