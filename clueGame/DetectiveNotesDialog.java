package clueGame;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DetectiveNotesDialog extends JDialog {

	public DetectiveNotesDialog() {
		
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		String[] people = { "Miss Scarlet", "Colonel Mustard", "Mr. Green", "Mrs. White", "Mrs. Peacock", "Professor Plum" };
		String[] rooms = { "Study", "Hall", "Lounge", "Library", "Dining Room", "Billiard Room", "Conservatory", "Ballroom", "Kitchen"};
		String[] weapons = { "Candlestick", "Dagger", "Lead Pipe", "Revolver", "Rope", "Wrench" };	
		
		setTitle("Detective Notes");
		setSize(700, 400);
		
		setLayout( new GridLayout(3, 2));
		
		JPanel peoplePanel = new JPanel(new GridLayout(3, 2));
		JPanel personGuessPanel = new JPanel(new GridBagLayout());
		JPanel roomPanel = new JPanel(new GridLayout(3, 3));
		JPanel roomGuessPanel = new JPanel(new GridBagLayout());
		JPanel weaponPanel = new JPanel(new GridLayout(3, 2));
		JPanel weaponGuessPanel = new JPanel(new GridBagLayout());
		
		peoplePanel.setBorder(new TitledBorder (new EtchedBorder(), "People"));
		personGuessPanel.setBorder(new TitledBorder (new EtchedBorder(), "Person Guess"));
		roomPanel.setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
		roomGuessPanel.setBorder(new TitledBorder (new EtchedBorder(), "Room Guess"));
		weaponPanel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		weaponGuessPanel.setBorder(new TitledBorder (new EtchedBorder(), "Weapon Guess"));
		
		for ( int i = 0; i < 6; ++i ) {
			peoplePanel.add(new JCheckBox(people[i]));
		}
		
		JPanel personComboPanel = new JPanel();
		personComboPanel.add( new JComboBox(people));
		personGuessPanel.add(personComboPanel);
		
		for ( int i = 0; i < 9; ++i ) {
			roomPanel.add(new JCheckBox(rooms[i]));
		}
		
		JPanel roomComboPanel = new JPanel();
		roomComboPanel.add( new JComboBox(rooms));
		roomGuessPanel.add(roomComboPanel);
		
		for ( int i = 0; i < 6; ++i ) {
			weaponPanel.add(new JCheckBox(weapons[i]));
		}
		
		JPanel weaponComboPanel = new JPanel();
		weaponComboPanel.add( new JComboBox(weapons));
		weaponGuessPanel.add(weaponComboPanel);
		
		add( peoplePanel );
		add( personGuessPanel );
		add( roomPanel );
		add( roomGuessPanel );
		add( weaponPanel );
		add( weaponGuessPanel );
		
	}
}
