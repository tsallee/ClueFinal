package clueGame;

import java.awt.Color;
import java.awt.Graphics;

public class WalkwayCell extends BoardCell {
	
	public WalkwayCell(Integer row, Integer column) {
		super(row, column);
	}

	@Override
	public boolean isWalkway() {
		return true;
	}

	@Override
	public void draw(Graphics g, Board b) {
		x = (column - 1)*b.getWidth()/b.getNumColumns();
		y = (row - 1)*b.getHeight()/(b.getNumRows());
		width = xDimension;
		height = yDimension;
		
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
		g.setColor(Color.WHITE);
		g.drawRect(x, y, width, height);
		
	}
}
