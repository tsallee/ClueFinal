package clueGame;

public class Solution {
	private String person;
	private String room;
	private String weapon;
	
	public Solution() {
		person = new String();
		room = new String();
		weapon = new String();
	}
	
	public Solution( String person, String room, String weapon ) {
		this.person = person;
		this.room = room;
		this.weapon = weapon;
	}
	
	public String toString() {
		return person + " " + room + " " + weapon;
	}
	
	@Override
	public boolean equals(Object o) {
		Solution s = (Solution) o;
		if(person.equals(s.person) && room.equals(s.room) && weapon.equals(s.weapon))
			return true;
		return false;
	}

	public String getPerson() {
		return person;
	}

	public String getRoom() {
		return room;
	}

	public String getWeapon() {
		return weapon;
	}
}