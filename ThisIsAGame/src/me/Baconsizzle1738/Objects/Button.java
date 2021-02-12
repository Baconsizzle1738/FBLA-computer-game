package me.Baconsizzle1738.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import me.Baconsizzle1738.main.ID;

/**
 * The Button is a <code>GameObject</code> that is stationary and can be clicked on to perform an action.
 * @author Baconsizzle1738
 *
 */
public class Button extends GameObject implements MouseListener{
	
	private String message/*, action*/;
	private int width, height;
	private boolean isPressed, isReleased;
	private Color color, pressColor, messageColor;
	
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
	public Button(int x, int y, ID typeId, int level, String message, int wid, int hei, Color color, Color pressColor, Color messageColor/*, String act*/) {
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
		System.out.println(color);
	}


	@Override
	public void tick() {
		//isReleased = false;
	}


	@Override
	public void render(Graphics g) {
		if (!isPressed) {
			g.setColor(color);
		}
		else {
			g.setColor(pressColor);
		}
		g.fillRect(x, y, width, height);
		g.setColor(messageColor);
		g.drawString(message, x, y);
		
	}


	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	private Rectangle getRelativeBounds() {
		return new Rectangle (0, 0, width, height);
	}
	
	public boolean isReleased() {
		return isReleased;
	}
	
	public void setRelease(boolean b) {
		isReleased = b;
	}
	
	public void setPress (boolean b) {
		isPressed = b;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//no
	}


	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (this.getRelativeBounds().contains(x, y)) {
			isPressed = true;
		}
		System.out.println("reee");
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		if (isPressed) {
			isPressed = false;
			isReleased = true;
		}
		System.out.println("HEQHEQ");
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		//no
	}


	@Override
	public void mouseExited(MouseEvent e) {
		//no
	}
	
}
