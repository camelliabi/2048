package userinterface;
import java.awt.Color;
import java.util.ArrayList;
import ecs100.UI;

public class UserInterface {	
	int[][] values = new int[4][4];
	int direction = 0;
	double x1;
	double x2;
	double y1;
	double y2;
	boolean change = false;
	Color lightgrey = new Color(236, 236, 234);
	Color grey = new Color(176, 177, 182);
	double offsetX = 0;
	double offsetY = 0;
	ArrayList<Block> blocks = new ArrayList<Block>();

	
	public void newGame() {
		for(int i = 0; i < 4; i ++) {
			for(int j = 0; j < 4; j ++) {
				values[i][j] = 0;
			}
		}
		this.newBlock();
		this.newBlock();
		UI.setMouseMotionListener(this::doMouse);
	}
	
	public void up() {
		direction = 1;
		column();
	}
	
	public void down() {
		direction = 2;
		column();
	}
	
	public void left() {
		direction = 3;
		row();
	}
	
	public void right() {
		direction = 4;
		row();
	}
	
	public void column() {
		for(int i = 0; i < 4; i ++) {
			moveColumn(i, 0);
			columnMerge(i);
			moveColumn(i, 0);
		}
		this.drawAll();
		if(change == true) {
			this.newBlock();
		}
		change = false;
	}
	
	public void moveColumn(int i, int n) {
		if(n > 3) {
			return;
		}
		for(int j = 0; j < 3-n; j ++) {
			if(direction == 1) {
				if(values[i][j] == 0) {
					if(values[i][j+1] !=0 ) {
						change = true;
						}
					values[i][j] = values[i][j+1];
					values[i][j+1] = 0;	
					moveColumn(i, n+1);
					}
				} else if(direction == 2) {
					if(values[i][3-j] == 0) {
						if(values[i][3-j-1] != 0) {
							change = true;
						}
						values[i][3-j] = values[i][3-j-1];
						values[i][3-j-1] = 0;
						moveColumn(i, n+1);
						}
				}
			}
	}
	
	public void columnMerge(int i) {
		for(int j = 0; j < 3; j ++) {
			if(direction == 1) {
				if(values[i][j] != 0 && values[i][j] == values[i][j+1]) {
					change = true;
					values[i][j] = 2 * values[i][j];
					values[i][j+1] = 0;
					j ++;
				}
			} else if(direction == 2) {
				if(values[i][3-j] != 0 && values[i][3-j] == values[i][3-j-1]) {
					change = true;
					values[i][3-j] = 2 * values[i][3-j];
					values[i][3-j-1] = 0;
					j ++;
				}
			}
		}
	}
	
	public void row() {
		for(int j = 0; j < 4; j ++) {
			moveRow(j, 0);
			mergeRow(j);
			moveRow(j, 0);
		}
		this.drawAll();
		if(change == true) {
			this.newBlock();
		}
		change = false;
	}
	
	public void moveRow(int j, int n) {
		if(n >= 3) {
			return;
		}
		for(int i = 0; i < 3-n; i ++) {
			if(direction == 3) {
				if(values[i][j] == 0) {
					if(values[i+1][j] != 0) {
						change = true;
						}
					values[i][j] = values[i+1][j];
					values[i+1][j] = 0;	
					moveRow(j, n+1);
					
					}
				} else if(direction == 4) {
					if(values[3-i][j] == 0) {
						if(values[3-i-1][j] != 0) {
							change = true;
						}
						values[3-i][j] = values[3-i-1][j];
						values[3-i-1][j] = 0;
						moveRow(j, n+1);
						}
				}
			}
	}
	
	public void mergeRow(int j) {
		for(int i = 0; i < 3; i ++) {
			if(direction == 3) {
				if(values[i][j] != 0 && values[i][j] == values[i+1][j]) {
					change = true;
					values[i][j] = 2 * values[i][j];
					values[i+1][j] = 0;
					i ++;
				}
			} else if(direction == 4) {
				if(values[3-i][j] != 0 && values[3-i][j] == values[3-i-1][j]) {
					change = true;
					values[3-i][j] = 2 * values[3-i][j];
					values[3-i-1][j] = 0;
					i ++;
				}
			}
		}
	}
	
	public void newBlock() {
		if(full()) {
			UI.println("Game Over");
			return;
		}
		int i = (int)(4 * Math.random());
		int j = (int)(4 * Math.random());	
		if(values[i][j] == 0) {
			values[i][j] = 2;
		} else {
			newBlock();
		}
		this.drawAll();
	}
	
	public boolean full() {
		for(int i = 0; i < 4; i ++) {
			for(int j = 0; j < 4; j++) {
				if(values[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void doMouse(String action, double x, double y) {
		if(action.equals("pressed")) {
			this.x1 = x;
			this.y1 = y;
		} else if(action.equals("dragged")) {
			this.x2 = x;
			this.y2 = y;
			if(Math.abs(y2 - y1) > Math.abs(x2 - x1)) {
				offsetX = 0;
				offsetY = y2 - y1;
				this.drawAll();
			} else {
				offsetY = 0;
				offsetX = x2 - x1;
				this.drawAll();
			}
			offsetX = 0;
			offsetY = 0;
			
		} else if (action.equals("released")) {
			this.x2 = x;
			this.y2 = y;
			if(Math.abs(y2 - y1) <= 3 && Math.abs(x2 - x1) <= 3) {
				offsetY = 0;
				offsetX = 0;
				this.drawAll();
				return;
			}
			if(Math.abs(y2 - y1) >= Math.abs(x2 - x1)) {
				if(y2 >= y1) {
					down();
				} else {
					up();
					}
				} else {
					if(x2 >= x1) {
						right();
					} else {
						left();
					}
				}
		}
	}
	
	public void drawAll() {
		UI.clearGraphics();					
		this.background();
		for(int i = 3; i >= 0; i --) {
			for(int j = 3; j >= 0; j --) {
				if(values[i][j] != 0) {
					Block b = new Block(i, j, values[i][j]);
					b.moveBlock(offsetX, offsetY);
					b.drawBlock();
				}
			}
		}
	}

	public void background() {
		for(int n = 0; n < 5; n ++) {
			UI.setColor(lightgrey);
			UI.setLineWidth(1);
			UI.drawLine(0, 100*n, 400, 100*n);
			UI.drawLine(100*n, 0, 100*n, 400);
		}
	}
 

	public UserInterface() {
		UI.initialise();
		//UI.addButton("Background", this::background);
		UI.addButton("New Game", this::newGame);
		UI.addButton("Up", this::up);
		UI.addButton("Down", this::down);
		UI.addButton("Left", this::left);
		UI.addButton("Right", this::right);
	}

	public static void main(String[] args) {
		new UserInterface();

	}

}
