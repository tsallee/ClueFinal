package clueGame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlTopPanel extends JPanel {
		private JTextField whoseTurnField;
		private ClueGame game;
		
	public ControlTopPanel(ClueGame g) {
		this.game = g;
		setLayout(new GridLayout(0,3));
		JPanel questionPanel = new JPanel();
		questionPanel.setLayout(new GridLayout(3, 0));
		JPanel centeredLabel = new JPanel();
		centeredLabel.setLayout(new GridBagLayout());
		JLabel label = new JLabel("Whose turn?");
		centeredLabel.add(label);
		JPanel centeredText = new JPanel();
		centeredText.setLayout(new GridBagLayout());
		whoseTurnField = new JTextField(15);
		whoseTurnField.setFont(new Font("SansSerif", Font.BOLD, 12));
		whoseTurnField.setEditable(false);
		centeredText.add(whoseTurnField);
		questionPanel.add(centeredLabel);
		questionPanel.add(centeredText);
		add(questionPanel);
		
		//Add the next player button.
		JButton nextPlayer = createNextPlayerButton(game);
		add(nextPlayer);
		
		//Add the accusation button.
		JButton accusationButton = createAccusationButton(game);
		add(accusationButton);
	}
	
	public void setWhoseTurn(String s) {
		whoseTurnField.setText(s);
		repaint();
	}
	
	private JButton createNextPlayerButton(ClueGame g) {
		JButton button = new JButton("Next Player");
		class ButtonListener implements ActionListener {
			private ClueGame game;
			public ButtonListener(ClueGame game) {
				this.game = game;
			}
			public void actionPerformed(ActionEvent e) {
				game.nextTurn();
			}
		}
		button.addActionListener(new ButtonListener(g));
		return button;
	}
	
	private JButton createAccusationButton(ClueGame g) {
		JButton button = new JButton("Make an Accusation");
		class ButtonListener implements ActionListener {
			private ClueGame game;
			public ButtonListener(ClueGame game) {
				this.game = game;
			}
			public void actionPerformed(ActionEvent e) {
				
			}
		}
		button.addActionListener(new ButtonListener(game));
		return button;
	}

}
