package clueGame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlBottomPanel extends JPanel {
		private JTextField rollField;
		private JTextField guessField;
		private JTextField resultField;
		
	public ControlBottomPanel()
	{
		setLayout(new BorderLayout());
		
		JPanel die = new JPanel();
		JLabel roll = new JLabel("Roll");
		rollField = new JTextField(2);
		rollField.setEditable(false);
		die.add(roll);
		die.add(rollField);
		die.setBorder(new TitledBorder (new EtchedBorder(), "Die"));
		add(die, BorderLayout.WEST);
		
		JPanel guess = new JPanel();
		guess.setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
		JLabel guessText = new JLabel("Guess");
		guess.add(guessText);
		guessField = new JTextField(32);
		guessField.setEditable(false);
		guess.add(guessField);
		add(guess, BorderLayout.CENTER);
		
		JPanel result = new JPanel();
		result.setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));
		JLabel resultText = new JLabel("Response");
		result.add(resultText);
		resultField = new JTextField(16);
		resultField.setEditable(false);
		result.add(resultField);
		add(result, BorderLayout.EAST);
		
	}
	
	public void setRoll(int roll) {
		rollField.setText(Integer.toString(roll));
	}
	
	public void setGuess(String guess) {
		guessField.setText(guess);
	}
	
	public void setResult(String result) {
		resultField.setText(result);
	}

}
