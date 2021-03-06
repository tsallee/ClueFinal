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
	
	@Override
	public void createSuggestion(String person, String room, String weapon) {
		suggestion = new Suggestion(person, room, weapon);
	}
	
	public void makeMove(Set<BoardCell> targetList) {
		targets = targetList;
		board.highlightTargets(targets);
		game.setAccusationButtonPushed(false);
	}
	
	public void checkSelectedCell(BoardCell cell) {
		if ( !targets.contains(cell) ) {
			JOptionPane popup = new JOptionPane();
			String message = "ERROR: Invalid location";
			popup.showMessageDialog(game, message, "ERROR", JOptionPane.INFORMATION_MESSAGE);
		} else {
			game.setAccusationButtonPushed(true);
			cellSelected = cell;
			this.location = board.calcIndex(cellSelected.getRow() - 1, cellSelected.getColumn() - 1);
			game.setHumanMustFinish(false);
			board.getHighlightedRectangles().clear();
			board.repaint();
			if ( cellSelected.isRoom() ) {
				SuggestionDialog suggestionDialog = new SuggestionDialog(((RoomCell) cellSelected).getName(), game, this, board);
			}
		}
		board.repaint();
	}
}
