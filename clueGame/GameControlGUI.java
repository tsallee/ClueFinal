package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameControlGUI extends JPanel {
	
	private ClueGame game;
	private ControlTopPanel topPanel;
	private ControlBottomPanel bottomPanel;
	
	public GameControlGUI(ClueGame g) {
		this.game = g;
		//Set up frame.
		setSize(800, 200);
		setLayout(new GridLayout(2, 0));
		
		//Set up north panel.
		topPanel = new ControlTopPanel(game);
		add(topPanel);
		
		//Set up south panel.
		bottomPanel = new ControlBottomPanel();
		add(bottomPanel);
		setVisible(true);

	}
	
	public void setWhoseTurn(String s) {
		topPanel.setWhoseTurn(s);
		repaint();
	}
	
	public void setRoll(int roll) {
		bottomPanel.setRoll(roll);
	}
	
	public void setGuess(String guess) {
		bottomPanel.setGuess(guess);
	}
	
	public void setResult(String result) {
		bottomPanel.setResult(result);
	}


}
