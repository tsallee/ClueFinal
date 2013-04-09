package clueGame;

public class Suggestion {
	private String room, weapon, person;

	public Suggestion() {
		room = "";
		weapon = "";
		person = "";
	}
	
	public Suggestion(String person, String room, String weapon) {
		this.room = room;
		this.weapon = weapon;
		this.person = person;
	}
	
	public String getRoom() {
		return room;
	}
	
	public String getWeapon() {
		return weapon;
	}
	
	public String getPerson() {
		return person;
	}
	
	public String toString() {
		return person + " in the " + room + " with the " + weapon;
	}
}
