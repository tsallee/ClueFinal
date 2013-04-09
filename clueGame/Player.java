package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import javax.swing.JComponent;

public class Player {
	
	protected int row, column;
	protected int width, height, x, y;
	
	protected String name;
	protected ArrayList<Card> cards;
	protected Color color;
	protected int location;
	protected Suggestion suggestion;
	
	protected int yDimension;
	protected int xDimension;
	
	protected BoardCell cellSelected;
	protected Set<BoardCell> targets;
	
	protected Board board;
	protected ClueGame game;
	
	protected Solution accusation;
	
	public Player() {
		name = null;
		cards = new ArrayList<Card>();
	}

	public Player(String name, int location ) {
		this.name = name;
		this.location = location;
		cards = new ArrayList<Card>();
	}
	
	public Player(String name, int location, String color) {
		this.name = name;
		this.location = location;
		cards = new ArrayList<Card>();
		this.color = convertColor(color);
	}
	
	public Player(Player other) {
		this.name = other.name;
		this.location = other.location;
		this.color = other.color;
		this.cards = new ArrayList<Card>(other.cards);
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public String disproveSuggestion(Card person, Card room, Card weapon) {
		Random rand = new Random();
		int r = 0;
		ArrayList<Card> temp = new ArrayList<Card>();
				
		// If the player has any of the cards, add them to a temporary list.
		if ( cards.contains(person) )
			temp.add(person);
		if ( cards.contains(room) )
			temp.add(room);
		if ( cards.contains(weapon) )
			temp.add(weapon);
		
		// If the player has any of the cards, return one of them (randomly pick)
		if ( temp.size() > 0) {	
			r = rand.nextInt(temp.size());
			return temp.get(r).getName();
		}
		// Else don't return any cards
		
		return null;
	}
	
	public String disproveSuggestion(Suggestion suggestion) {
		Card person = new Card(Card.cardType.PERSON, suggestion.getPerson());
		Card room = new Card(Card.cardType.ROOM, suggestion.getRoom());
		Card weapon = new Card(Card.cardType.WEAPON, suggestion.getWeapon());
		return disproveSuggestion(person, room, weapon);
	}
	
	@Override
	public boolean equals(Object o) {
		Player p = (Player) o;
		if ( name.equals(p.name) )
			return true;
		return false;
	}
	
	public void createSuggestion(String person, String room, String weapon) {
		
	}

	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return color;
	}
	
	//Only for testing.
	public void clearCards() {
		cards = new ArrayList<Card>();
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public Suggestion getSuggestion() {
		return suggestion;
	}
	
	public void makeMove(Set<BoardCell> targetList) {
		
	}
	
	public Color convertColor(String strColor) {
		Color color; 
		try {     
			// We can use reflection to convert the string to a color
			java.lang.reflect.Field field = Class.forName("java.awt.Color").getField(strColor.trim());     
			color = (Color)field.get(null); } 
		catch (Exception e) {  
			color = null; // Not defined } 
		}
		return color;
	}
	
	public void draw(Graphics g, Board b) {
		
		row = calcRow(location, b);
		column = calcColumn(location, b);
		x = (column)*b.getWidth()/b.getNumColumns();
		y = (row)*b.getHeight()/(b.getNumRows());
		width = xDimension;
		height = yDimension;
		
		g.setColor(color);
		g.fillOval(x, y, width, height);
	}
		
	public void setLocation(int location) {
		this.location = location;
	}

	public int calcRow(int location, Board b) {
		return location / b.getNumColumns();
	}
	
	public int getLocation() {
		return location;
	}

	public int calcColumn(int location, Board b) {
		return location % b.getNumColumns();
	}
	
	public int getxDimension() {
		return xDimension;
	}

	public void setxDimension(int xDimension) {
		this.xDimension = xDimension;
	}
	
	public int getyDimension() {
		return yDimension;
	}

	public void setyDimension(int yDimension) {
		this.yDimension = yDimension;
	}
	
	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	public void setGame(ClueGame game) {
		this.game = game;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void checkSelectedCell(BoardCell cell) {
		
	}
	
	public boolean isComputer() {
		return false;
	}
	
	public Solution getAccusation() {
		if ( accusation == null ) {
			return new Solution("", "", "");
		}
		return accusation;
	}
	
	public void setAccusation(Solution accusation) {
		this.accusation = accusation;
	}
	
}
