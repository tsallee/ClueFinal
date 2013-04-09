package clueGame;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import clueGame.BoardCell;

public class ComputerPlayer extends Player {
	private char lastRoomVisited;
	private ArrayList<Card> seen;
	private boolean makeAccusation;
	
	public ComputerPlayer() {
		super();
		seen = new ArrayList<Card>();
		makeAccusation = false;
	}
	
	public ComputerPlayer(String name, int location) {
		super(name, location);
		seen = new ArrayList<Card>();
		makeAccusation = false;
	}
	
	public ComputerPlayer(String name, int location, String color) {
		super(name, location, color);
		seen = new ArrayList<Card>();
		makeAccusation = false;
	}
	
	public void makeMove(Set<BoardCell> targets) {
		if (makeAccusation) {
			
		}
		game.setHumanMustFinish(false);
		cellSelected = pickLocation(targets);
		
		if (cellSelected.isRoom()) {
			lastRoomVisited = ((RoomCell) cellSelected).getInitial();
			String currentRoom = ((RoomCell) cellSelected).getName();
			createSuggestion(currentRoom, game.getCards());
			game.getControlPanel().setGuess(suggestion.toString());
			String disprove = game.handleSuggestion(this);
			game.getControlPanel().setResult(disprove);
			String target = suggestion.getPerson();
			for (Player p: game.getPlayers()) {
				if (target.equals(p.getName())) {
					p.setLocation(board.calcIndex(cellSelected.getRow() - 1, cellSelected.getColumn() - 1));
					break;
				}
			}
			
			if (disprove == null) {
				makeAccusation = true;
			}
			board.repaint();
		}
		this.location = board.calcIndex(cellSelected.getRow() - 1, cellSelected.getColumn() - 1);
		board.repaint();
	}
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		Random rand = new Random();
		int r = 0;
		r = rand.nextInt(targets.size());
		
		for ( BoardCell bc : targets ) {
			if ( bc.isRoom() ) {
				RoomCell cell = (RoomCell) bc;
				if ( lastRoomVisited != cell.getInitial() )
					return bc;
			}
		}
		
		int i = 0;
		for ( BoardCell bc : targets ) {
			if ( i == r ) return bc;
			++i;
		}
		
		return null;
	}
	
	public void addCard(Card card) {
		super.addCard(card);
		updateSeen(card);
	}
	
	
	
	public void createSuggestion(String room, ArrayList<Card> cards) {
		Random rand = new Random();
		Card card;
		int r = 0;
		String person = "";
		String weapon = "";
		
		ArrayList<Card> suggestionPool = new ArrayList<Card>(cards);
		
		for( Card cardSeen : seen ) {
			if ( suggestionPool.contains(cardSeen) )
				suggestionPool.remove(cardSeen);
		}
		
		while ( suggestionPool.size() > 0 ) {
			r = rand.nextInt(suggestionPool.size());
			card = suggestionPool.get(r);
			if ( card.getType() == Card.cardType.PERSON && person == "") {
				person = card.getName();
				suggestionPool.remove(card);
			}
			else if ( card.getType() == Card.cardType.WEAPON && weapon == "") {
				weapon = card.getName();
				suggestionPool.remove(card);
			}
			if ( person != "" && weapon != "" )
				break;			
		}
		suggestion = new Suggestion(person, room, weapon);
	}
	
	public void createSuggestion(String person, String room, String weapon) {
		suggestion = new Suggestion(person, room, weapon);
	}
	
	public void updateSeen(Card seen) {
		this.seen.add(seen);
	}
	
	public char getLastRoom() {
		return lastRoomVisited;
	}
	
	public void setLastRoom(char last) {
		lastRoomVisited = last;
	}
}
