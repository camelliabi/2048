package userinterface;

import java.awt.Color;

import ecs100.UI;

public class Block {
	private double size = 100;
	private int x0;
	private int y0;
	private double x;
	private double y;
	private int ID;
	Color lightgrey = new Color(236, 236, 234);
	Color darkpurple = new Color(104, 103, 137);
	Color red = new Color(171, 84, 90);
	Color green = new Color(154, 166, 144);
	Color lightgreen = new Color(188, 203, 178);
	Color orange = new Color(216, 156, 122);
	Color pink = new Color(224, 205, 207);
	Color yellow = new Color(214, 195, 139);
	Color beige = new Color(207, 195, 169);
	Color blue = new Color(146, 172, 209);
	Color grey = new Color(176, 177, 182);
	Color purple = new Color(201, 192, 211);
	
	
	public Block(int x0, int y0, int ID) {
		this.x0 = x0;
		this.y0 = y0;
		this.ID = ID;
		this.x = x0 * 100 + 50;
		this.y = y0 * 100 + 50;
	}
	
	public void setID(int ID0) {
		ID = ID0;
	}
	
	public int getID() {
		return ID;
	}
	
	public void moveBlock(double offsetX, double offsetY) {
		double offX = Math.min(offsetX, 25);
		double offY = Math.min(offsetY, 25);
		offX = Math.max(offX, -25);
		offY = Math.max(offY, -25);
		this.x = x0 * 100 + 50 + offX;
		if(x>350) this.x = 350;
		if(x<50) this.x = 50;
		this.y = y0 * 100 + 50 + offY;
		if(y>350) this.y = 350;
		if(y<50) this.y = 50;
	}
	
	public void drawBlock() {
		UI.setColor(color());
		UI.fillRect(x - size/2, y - size/2, size, size);
		java.awt.Color color2 = Color.white;
		UI.setColor(color2);
		String s = String.valueOf(ID);
		int fontSize = 30;
		UI.setFontSize(fontSize);
		int textW = (int)(s.length()*fontSize*0.6);
		int textH = fontSize;
		UI.drawString(s, x-textW/2, y+textH/2-4);
		UI.setColor(lightgrey);
		UI.setLineWidth(3);
		UI.drawRect(x - size/2, y - size/2, size, size);
	}
	
	public Color color() {
		Color col = Color.black;
		if(ID == 2) {
			col = grey;
		} else if(ID == 4) {
			col = blue;
		} else if(ID == 8) {
			col = green;
		} else if(ID == 16) {
			col = red;
		} else if(ID == 32) {
			col = purple;
		} else if(ID == 64) {
			col = orange;
		} else if(ID == 128) {
			col = lightgreen;
		} else if(ID == 256) {
			col = darkpurple;
		} else if(ID == 512) {
			col = pink;
		}
		return col;
	}

}

