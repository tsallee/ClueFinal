package clueGame;

import java.awt.Graphics;

public class BoardCell {
	
	protected int yDimension;
	protected int xDimension;

	Integer row, column;
	protected int width, height, x, y;

	public BoardCell() {
		row = -1;
		column = -1;
	}

	public BoardCell(Integer row, Integer column){
		this.row = row;
		this.column = column;
	}
	
	public void draw(Graphics g, Board b) {
		
	}
	
	public boolean isWalkway(){
		return false;
	}
	
	public boolean isRoom(){
		return false;
	}
	
	public boolean isDoorway(){
		return false;
	}
	
	public Integer getRow() {
		return row;
	}

	public Integer getColumn() {
		return column;
	}
	
	public int getyDimension() {
		return yDimension;
	}

	public void setyDimension(int yDimension) {
		this.yDimension = yDimension;
	}

	public int getxDimension() {
		return xDimension;
	}

	public void setxDimension(int xDimension) {
		this.xDimension = xDimension;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object o) {
		BoardCell cell = (BoardCell) o;
		if(row.equals(cell.row) && column.equals(cell.row))
			return true;
		return false;
	}

}
