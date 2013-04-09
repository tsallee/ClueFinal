package clueGame;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AccusationDialog extends JDialog {

	private String room;
	private String person;
	private String weapon;

	private ClueGame game;
	private Player player;
	private Board board;

	private JComboBox peopleBox;
	private JComboBox weaponsBox;
	private JComboBox roomsBox;

	private String[] rooms = { "Study", "Hall", "Lounge", "Library", "Dining Room", "Billiard Room", "Conservatory", "Ballroom", "Kitchen"};
	private String[] people = { "Miss Scarlet", "Colonel Mustard", "Mr. Green", "Mrs. White", "Mrs. Peacock", "Professor Plum" };
	private String[] weapons = { "Candlestick", "Knife", "Lead Pipe", "Revolver", "Rope", "Wrench" };

	public AccusationDialog (ClueGame game, Player player, Board board) {

		this.game = game;
		this.player = player;
		this.board = board;		

		roomsBox = new JComboBox(rooms);
		peopleBox = new JComboBox(people);
		weaponsBox = new JComboBox(weapons);

		setLayout(new GridLayout(4, 2));
		setSize(300, 250);
		setTitle("Make an accusation");

		add(new JLabel("Room guess:"));
		add(roomsBox);
		add(new JLabel("Person guess:"));
		add(peopleBox);
		add(new JLabel("Weapon guess:"));
		add(weaponsBox);
		add(createSubmitButton(game));
		add(createCancelButton(game));

		setVisible(true);

	}

	private JButton createSubmitButton(ClueGame g) {
		JButton button = new JButton("Submit");
		class ButtonListener implements ActionListener {
			private ClueGame game;
			public ButtonListener(ClueGame game) {
				this.game = game;
			}
			public void actionPerformed(ActionEvent e) {
				if ( !game.accusationButtonPushed() ) {
					int personIndex = peopleBox.getSelectedIndex();
					int weaponIndex = weaponsBox.getSelectedIndex();
					int roomIndex = roomsBox.getSelectedIndex();

					person = people[personIndex];
					room = rooms[roomIndex];
					weapon = weapons[weaponIndex];

					player.createSuggestion(person, room, weapon);
					game.getControlPanel().setGuess(player.getSuggestion().toString());
					String disprove = game.handleSuggestion(player);
					game.getControlPanel().setResult(disprove);

					player.setAccusation(new Solution(person, room, weapon));
					
					game.setAccusationButtonPushed(true);
					
					if ( game.checkAccusation(player.getAccusation()) ) {
						JOptionPane popup = new JOptionPane();
						popup.showMessageDialog(game, "Congratulations. " + player.getName() + " wins.", "YOU WIN!!", JOptionPane.INFORMATION_MESSAGE);
					}
					game.nextTurn();
					dispose();
				}
			}
		}
		
		button.addActionListener(new ButtonListener(game));
		return button;
	}

	private JButton createCancelButton(ClueGame g) {
		JButton button = new JButton("Cancel");
		class ButtonListener implements ActionListener {
			private ClueGame game;
			public ButtonListener(ClueGame game) {
				this.game = game;
			}
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		}
		button.addActionListener(new ButtonListener(game));
		return button;
	}
}

