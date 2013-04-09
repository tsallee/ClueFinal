package clueGame;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SuggestionDialog extends JDialog {
	
	private String room;
	private String person;
	private String weapon;
	
	private ClueGame game;
	private Player player;
	private Board board;
	
	private JComboBox peopleBox;
	private JComboBox weaponsBox;
	
	private String[] people = { "Miss Scarlet", "Colonel Mustard", "Mr. Green", "Mrs. White", "Mrs. Peacock", "Professor Plum" };
	private String[] weapons = { "Candlestick", "Knife", "Lead Pipe", "Revolver", "Rope", "Wrench" };
	
	public SuggestionDialog(String room, ClueGame game, Player player, Board board) {
		
		this.room = room;
		this.game = game;
		this.player = player;
		this.board = board;		
		
		peopleBox = new JComboBox(people);
		weaponsBox = new JComboBox(weapons);
		
		setLayout(new GridLayout(4, 2));
		setSize(300, 250);
		setTitle("Make a suggestion");
		
		add(new JLabel("Room guess:"));
		
		add(new JLabel(room));
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
				int personIndex = peopleBox.getSelectedIndex();
				int weaponIndex = weaponsBox.getSelectedIndex();
				person = people[personIndex];
				weapon = weapons[weaponIndex];
				player.createSuggestion(person, room, weapon);
				game.getControlPanel().setGuess(player.getSuggestion().toString());
				String disprove = game.handleSuggestion(player);
				game.getControlPanel().setResult(disprove);
				String target = player.getSuggestion().getPerson();
				for (Player p: game.getPlayers()) {
					if (target.equals(p.getName())) {
						p.setLocation(board.calcIndex(player.getRow(), player.getColumn()));
						board.repaint();
						break;
					}
				}
				dispose();
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
