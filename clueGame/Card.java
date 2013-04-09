package clueGame;

public class Card{
	public enum cardType { PERSON, WEAPON, ROOM };
	private cardType type;
	private String name;
	
	public Card() {
		type = cardType.PERSON;
		name = null;
	}
	
	public Card(cardType type, String name) {
		this.type = type;
		this.name = name;
	}
	
	@Override
	public boolean equals(Object o) {
		Card c = (Card) o;
		if(name.equals(c.name) && type.equals(c.type))
			return true;
		return false;
	}
	
	public String toString() {
		return name;
	}
	
	public cardType getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setType(cardType newType) {
		type = newType;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
