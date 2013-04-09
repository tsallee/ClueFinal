package clueGame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ShowHumanCardsGUI extends JPanel {
	
	private JPanel cardsPanel = new JPanel(new GridLayout(2, 0));
	private JPanel peoplePanel = new JPanel(new GridLayout(3, 0));
	private JPanel roomsPanel = new JPanel(new GridLayout(3, 0));
	private JPanel weaponsPanel = new JPanel(new GridLayout(3, 0));
	
	public ShowHumanCardsGUI() {
		setSize(200, 600);
		setLayout(new GridLayout(8, 0));
		
		setBorder(new EtchedBorder());
		
		peoplePanel.setBorder(new TitledBorder( new EtchedBorder(), "People"));
		roomsPanel.setBorder(new TitledBorder( new EtchedBorder(), "Rooms"));
		weaponsPanel.setBorder(new TitledBorder( new EtchedBorder(), "Weapons"));
		
		JLabel label = new JLabel();
		label.setText("[            My Cards:             ]");
		JPanel labelPanel = new JPanel(new GridBagLayout());
		labelPanel.add(label);
		
		add(new JPanel());
		add(labelPanel);
		add(peoplePanel);
		add(new JPanel());
		add(roomsPanel);
		add(new JPanel());
		add(weaponsPanel);
		
		setVisible(true);
	}
	
	public void setHumanCards(ArrayList<Card> cards) {
		for ( Card c : cards ) {
			if ( c.getType().equals(Card.cardType.PERSON) )
				peoplePanel.add(addTextField(c.getName()));
			else if ( c.getType().equals(Card.cardType.ROOM) )
				roomsPanel.add(addTextField(c.getName()));
			else if ( c.getType().equals(Card.cardType.WEAPON) )
				weaponsPanel.add(addTextField(c.getName()));
		}
		repaint();
	}

	private JTextField addTextField(String content) {
		JTextField textField = new JTextField();
		textField.setEditable(false);
		textField.setVisible(true);
		textField.setText(content);
		return textField;
	}

}
