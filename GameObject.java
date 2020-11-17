package me.Baconsizzle1738.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	//position and speed
	protected int x,y,volX,volY;
	protected ID typeId;
	protected ID levelId;
	
	public GameObject(int x, int y, ID typeId, ID levelId) {
		this.x = x;
		this.y = y;
		this.typeId = typeId;
		this.levelId = levelId;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	//for hitbox, Intersects method
	public abstract Rectangle getBounds();
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void settypeID(ID id) {
		this.typeId = id;
	}
	public void setLevelID(ID id) {
		this.levelId = id;
	}
	public ID gettypeID() {
		return typeId;
	}
	public ID getlevelID() {
		return levelId;
	}
	public void setVolX(int volX) {
		this.volX = volX;
	}
	public void setVolY(int volY) {
		this.volY = volY;
	}
	
}