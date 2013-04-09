package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Set;

import javax.swing.JOptionPane;

public class HumanPlayer extends Player {
	
	public HumanPlayer() {
		super();
	}
	
	public HumanPlayer(String name, int location) {
		super(name, location);
	}
	
	public HumanPlayer(String name, int location, String color) {
		super(name, location, color);
	}
	
	public void createSuggestion(String person, String room, String weapon) {
		suggestion = new Suggestion(person, room, weapon);
	}
	
	public void makeMove(Set<BoardCell> targetList) {
		targets = targetList;
		board.highlightTargets(targets);
	}
	
	public void checkSelectedCell(BoardCell cell) {
		if ( !targets.contains(cell) ) {
			JOptionPane popup = new JOptionPane();
			String message = "Error: Invalid location";
			popup.showMessageDialog(game, message, "Error", JOptionPane.INFORMATION_MESSAGE);
		} else {
			cellSelected = cell;
			this.location = board.calcIndex(cellSelected.getRow() - 1, cellSelected.getColumn() - 1);
			game.setHumanMustFinish(false);
			board.getHighlightedRectangles().clear();
		}
		board.repaint();
	}
}
