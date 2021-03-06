package me.Baconsizzle1738.Objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;

import me.Baconsizzle1738.main.ID;

/**
 * The Button is a <code>GameObject</code> that is stationary and can be clicked on to perform an action.
 * @author Baconsizzle1738
 *
 */
public class Button extends GameObject/* implements MouseListener*/{
	
	private String message/*, action*/;
	private int width, height/*, tickC = 0*/;
	private boolean isPressed, isReleased;
	private Color color, pressColor, messageColor;
	private Font font;
	
	/**
	 * Buttons will need a message on it and also a defined width and height parameter.
	 * 
	 * @param x				x pos of button.
	 * @param y				y pos of button.
	 * @param typeId		<code>ID</code> of button.
	 * @param level			Level that the button belongs to.
	 * @param message		What the button should say.
	 * @param wid			Width of button.
	 * @param hei			Height of button.
	 * @param color			Default color of button.
	 * @param pressColor	Color of button when pressed/clicked on.
	 * @param messageColor	Color of the message on the button.
	 */
	public Button(int x, int y, ID typeId, int level, String message, int wid, int hei, Color color, Color pressColor, Color messageColor/*, String act*/, Font f) {
		super(x, y, typeId, level);
		this.message = message;
		//action = act;
		width = wid;
		height = hei;
		isPressed = false;
		isReleased = false;
		this.color = color;
		this.pressColor = pressColor;
		this.messageColor = messageColor;
		font = f;
		//System.out.println(color);
	}


	@Override
	public void tick() {
//		if (isReleased) {
//			
//			if (tickC >= 1) {
//				isReleased = false;
//				tickC = 0;
//			}
//			tickC++;
//		}
		//System.out.println(isReleased);
	}


	@Override
	public void render(Graphics g) {
		//center xy pos of text
		
		int fontX = x + (width - g.getFontMetrics(font).stringWidth(message))/2;
		int fontY = (y + height) - (height - (g.getFontMetrics(font).getMaxDescent()))/2;
		
		if (!isPressed) {
			g.setColor(color);
		}
		else {
			g.setColor(pressColor);
		}
		g.fillRect(x, y, width, height);
		g.setColor(messageColor);
		g.setFont(font);
		
		g.drawString(message, fontX, fontY);
		
	}


	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
//	private Rectangle getRelativeBounds() {
//		return new Rectangle (0, 0, width, height);
//	}
	
	public boolean isReleased() {
		return isReleased;
	}
	
	public boolean isPressed() {
		return isPressed;
	}
	
	public void setRelease (boolean b) {
		//System.out.println("Button.SetRelease" + b);
		isReleased = b;
	}
	
	public void setPress (boolean b) {
		//System.out.println("Button.setPress" + b);
		isPressed = b;
	}

//	@Override
//	public void mouseClicked(MouseEvent e) {
//		//no
//	}
//
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//		int x = e.getX();
//		int y = e.getY();
//		if (this.getRelativeBounds().contains(x, y)) {
//			isPressed = true;
//		}
//		System.out.println("reee");
//	}
//
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		if (isPressed) {
//			isPressed = false;
//			isReleased = true;
//		}
//		System.out.println("HEQHEQ");
//	}
//
//
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		//no
//	}
//
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//		//no
//	}
	
}
