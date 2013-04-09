package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clueGame.Card.cardType;

public class ClueGame extends JFrame {
	private GameControlGUI controlPanel;
	private ShowHumanCardsGUI humanCardsPanel = new ShowHumanCardsGUI();
	private Solution solution;
	private ArrayList<Card> cards;
	private ArrayList<Player> players;
	private int whoseTurn;
	private Board board;
	private boolean humanMustFinish = false;
	private Player currentPlayer;

	public Board getBoard() {
		return board;
	}

	public ClueGame() {
		solution = new Solution();
		players = new ArrayList<Player>();
		cards = new ArrayList<Card>();
		board = new Board();
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		
		setLayout(new BorderLayout() );
		setSize(800, 800);
		setTitle("Clue Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loadConfigFiles();
		deal();
		
		controlPanel = new GameControlGUI(this);
		
		add(board, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.SOUTH);
		add(humanCardsPanel, BorderLayout.EAST);
		addComponentListener(new ClueComponentListener() );
		
		setVisible(true);
		
	}
	
	public void loadConfigFiles() {
		try {
			board.loadConfigFiles();
		} catch (BadConfigFormatException e) {
			System.out.println(e);
		}
		loadPeople();
		loadCards();
		board.setPlayers(players);
	}
	
	public void loadPeople() {
		try {
			boolean flag = true;
			String input, name, color;
			int row, column, startPosition;
			String[] sep;
			FileReader reader = new FileReader("CluePeople.txt");
			Scanner scanner = new Scanner(reader);
			while ( scanner.hasNext()) {
				input = scanner.nextLine();
				sep = input.split(", ");
				name = sep[0];
				color = sep[1];
				row = Integer.parseInt(sep[2]);
				column = Integer.parseInt(sep[3]);
				startPosition = board.calcIndex(row, column);
				if ( flag ) {
					players.add(new HumanPlayer(name, startPosition, color));
					flag = false;
				} else {
					players.add(new ComputerPlayer(name, startPosition, color));
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void loadCards() {
		try {
			String input, name, type;
			String[] sep;
			FileReader reader = new FileReader("ClueCards.txt");
			Scanner scanner = new Scanner(reader);
			while ( scanner.hasNext()) {
				input = scanner.nextLine();
				sep = input.split(", ");
				name = sep[0];
				type = sep[1];
				if ( type.equals("person") ) {
					cards.add(new Card(Card.cardType.PERSON, name));
				} else if (type.equals("room") ) {
					cards.add(new Card(Card.cardType.ROOM, name));
				} else if (type.equals("weapon") ) {
					cards.add(new Card(Card.cardType.WEAPON, name));
				} else
					throw new BadConfigFormatException("Card file has bad format");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
		
	public void deal() {
		Card card;
		String person = "", weapon = "", room = "";
		Random r = new Random();
		ArrayList<Card> c = new ArrayList<Card>(cards);
		int rand = 0;
		while ( true ) {
			rand = r.nextInt(c.size());
			card = c.get(rand);
			if ( card.getType() == Card.cardType.PERSON && person == "") {
				person = card.getName();
				c.remove(rand);
			}
			else if ( card.getType() == Card.cardType.ROOM && room == "") {
				room = card.getName();
				c.remove(rand);
			}
			else if ( card.getType() == Card.cardType.WEAPON && weapon == "") {
				weapon = card.getName();
				c.remove(rand);
			}
			if ( person != "" && room != "" && weapon != "" )
				break;			
		}
		
		solution = new Solution(person, room, weapon);
			
		int i = 0;
		while( c.size() > 0) {
			rand = r.nextInt(c.size());
			players.get(i).addCard(c.get(rand));
			c.remove(rand);
			i = (i + 1) % 6;
		}
	}
		
	public void selectAnswer(Solution s) {
		solution = s;
	}

	public boolean checkAccusation(Solution solution) {
		if ( this.solution.equals(solution))
			return true;
		return false;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}
	
	public Solution getSolution() {
		return solution;
	}
	
	public void setWhoseTurn(String name) {
		
	}
	
	public String handleSuggestion(Player player) {
		Random rand = new Random();
		String disprove;
		int index = 0;
		int r = 0;
		ArrayList<Player> p = new ArrayList<Player>(players);
		p.remove(player);
		r = rand.nextInt(p.size());
		for ( int i = 0; i < p.size(); ++i ) {
			index = (i + r) % p.size();
			disprove = p.get(index).disproveSuggestion(player.getSuggestion());
			if ( disprove != null)
				return disprove;
		}
		return null;
	}
	
	private JMenu createFileMenu() {
		  JMenu menu = new JMenu("File");
		  menu.add(createDetectiveNotesItem());
		  menu.add(createFileExitItem());
		  return menu;
		}
	
	private JMenuItem createFileExitItem() {
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}
	
	private JMenuItem createDetectiveNotesItem() {
		JMenuItem item = new JMenuItem("Detective Notes");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DetectiveNotesDialog detectiveDialog = new DetectiveNotesDialog();
				detectiveDialog.setVisible(true);
			}
	    });
		return item;
	}
	
	public class ClueComponentListener implements ComponentListener {

		@Override
		public void componentHidden(ComponentEvent arg0) {
		}

		@Override
		public void componentMoved(ComponentEvent arg0) {			
		}

		@Override
		public void componentResized(ComponentEvent arg0) {
			Board theBoard = ( (ClueGame) arg0.getComponent()).board;
			theBoard.updateXPixels(theBoard.getWidth());
			theBoard.updateYPixels(theBoard.getHeight());
		}

		@Override
		public void componentShown(ComponentEvent arg0) {
		}
	}
	
	public String displayWhoseTurn() {
		return players.get(whoseTurn).getName();
	}
	
	public int roll() {
		Random rand = new Random();
		int r = 0;
		r = rand.nextInt(6) + 1;
		return r;
	}
	
	public void nextTurn() {
		if (!humanMustFinish) {
			humanMustFinish = true;
			int roll = roll();
			controlPanel.setRoll(roll);
			currentPlayer = players.get(whoseTurn);
			controlPanel.setWhoseTurn(displayWhoseTurn());
			this.board.calcTargets(currentPlayer.getRow(), currentPlayer.getColumn(), roll);
			Set<BoardCell> targets = this.board.getTargets();
			currentPlayer.makeMove(targets);
			if (whoseTurn < 5) {
				++whoseTurn;
			} else if ( whoseTurn == 5 ) {
				whoseTurn = 0;
			}
		}
	}
	
	
	public GameControlGUI getControlPanel() {
		return controlPanel;
	}

	public void setup() {
		humanCardsPanel.setHumanCards(players.get(0).getCards());
		for ( Player p : players ) {
			p.setBoard(board);
			p.setGame(this);
		}
		board.setGame(this);
		board.repaint();
		JOptionPane popup = new JOptionPane();
		String message = "You are Miss Scarlet. Press Next Player to begin.\n Hint: Use File > Detective Notes to help you win!";
		popup.showMessageDialog(this, message, "Welcome to Clue!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void play() {		
		//Each turn
		//setUpTurn();
	}
	
	public static void main(String[] args) {
		ClueGame game = new ClueGame();
		game.setup();
		game.play();
	}
	
	public void setHumanMustFinish(boolean humanMustFinish) {
		this.humanMustFinish = humanMustFinish;
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
}
