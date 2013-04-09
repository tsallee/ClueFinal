package clueTests;



import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.ClueGame;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;
import clueGame.Suggestion;

public class GameActionTests {
	
	Board board;
	Solution solution;
	ArrayList<BoardCell> cells;
	ClueGame game;
	ArrayList<Player> players;
	ArrayList<Card> cards;
	Card mustardCard;
	Card scarletCard;
	Card plumCard;
	Card whiteCard;
	Card greenCard;
	Card peacockCard;
	Card candlestickCard;
	Card ropeCard;
	Card daggerCard;
	Card wrenchCard;
	Card revolverCard;
	Card pipeCard;
	Card studyCard;
	Card kitchenCard;
	Card libraryCard;
	Card conservatoryCard;
	
	@Before
	public void initialize() {
		board = new Board();
		try{
			board.loadConfigFiles();
		}
		catch(BadConfigFormatException e) {
			e = new BadConfigFormatException(board);
			System.out.println(e.toString());
		}
		
		cells = board.getCells();
		game = new ClueGame();
		//game.loadConfigFiles();
		//game.deal();
		players = game.getPlayers();
		cards = game.getCards();
		
		//Characters
		mustardCard = new Card(Card.cardType.PERSON, "Colonel Mustard");
		scarletCard = new Card(Card.cardType.PERSON, "Miss Scarlet");
		plumCard = new Card(Card.cardType.PERSON, "Professor Plum");
		whiteCard = new Card(Card.cardType.PERSON, "Mrs. White");
		greenCard = new Card(Card.cardType.PERSON, "Mr. Green");
		peacockCard = new Card(Card.cardType.PERSON, "Mrs. Peacock");
		
		//Weapons
		candlestickCard = new Card(Card.cardType.WEAPON, "Candlestick");
		ropeCard = new Card(Card.cardType.WEAPON, "Rope");
		daggerCard = new Card(Card.cardType.WEAPON, "Dagger");
		wrenchCard = new Card(Card.cardType.WEAPON, "Wrench");
		revolverCard = new Card(Card.cardType.WEAPON, "Revolver");
		pipeCard = new Card(Card.cardType.WEAPON, "Lead Pipe");
		
		//Rooms
		kitchenCard = new Card(Card.cardType.ROOM, "Kitchen");
		studyCard = new Card(Card.cardType.ROOM, "Study");
		conservatoryCard = new Card(Card.cardType.ROOM, "Conservatory");
		libraryCard = new Card(Card.cardType.ROOM, "Library");

	}

	@BeforeClass
	public static void setup() {
		
	}

	@Test
	public void TestDeal() {
		int cardsDealt = 0;
		//game.deal();
		//players = game.getPlayers();
		//COUNT THE NUMBER OF CARDS DEALT
		Player mustardPlayer = new Player();
		for ( int i = 0; i < players.size(); ++i ) {
			cardsDealt += players.get(i).getCards().size();
			assertTrue( players.get(i).getCards().size() == 3 );
		}

		//CHECK THAT THE NUMBER OF CARDS DEALT IS CORRECT
		assertEquals(18, cardsDealt);

		//CHECK TO SEE THAT THE SAME CARD WASN'T DEALT TO 2 PEOPLE:
		int mustardCardNumber = 0;
		int studyCardNumber = 0;
		int ropeCardNumber = 0;
		for ( Player play : players ) {
			for ( Card c : play.getCards() ) {
				if (c.getName().equals("Colonel Mustard")) mustardCardNumber++;
				if (c.getName().equals("Study")) studyCardNumber++;
				if (c.getName().equals("Rope")) ropeCardNumber++;
			}
		}
		assertTrue(mustardCardNumber < 2);
		assertTrue(studyCardNumber < 2);
		assertTrue(ropeCardNumber < 2);
	}

	@Test
	public void TestLoadPlayers() {
		
		// CHECK THE SIZE OF THE players ARRAYLIST IS CORRECT
		assertEquals(6, players.size());
		
		// CHECK TO SEE THAT THE NAMES ARE CORRECT FOR EACH PLAYER
		assertEquals("Miss Scarlet", players.get(0).getName());
		assertEquals("Mr. Green", players.get(1).getName());
		assertEquals("Mrs. White", players.get(2).getName());
		assertEquals("Colonel Mustard", players.get(3).getName());
		assertEquals("Professor Plum", players.get(4).getName());
		assertEquals("Mrs. Peacock", players.get(5).getName());

		// CHECK THAT THE COLOR IS CORRECT FOR EACH PLAYER
		assertEquals(Color.red, players.get(0).getColor());
		assertEquals(Color.green, players.get(1).getColor());
		assertEquals(Color.white, players.get(2).getColor());
		assertEquals(Color.yellow, players.get(3).getColor());
		assertEquals(Color.magenta, players.get(4).getColor());
		assertEquals(Color.blue, players.get(5).getColor());
		
	}

	@Test
	public void TestLoadCards() {
		//CHECK THAT DECK HAS CORRECT NUMBER OF CARDS
		assertEquals(21, cards.size());
		int numWeapons = 0;
		int numPeople = 0;
		int numRooms = 0;
		//CHECK THAT DECK HAS CORRECT NUMBER OF EACH TYPE OF CARD
		for ( Card c : cards ) {
			if ( c.getType() == Card.cardType.WEAPON )
				++numWeapons;
			else if ( c.getType() == Card.cardType.PERSON )
				++numPeople;
			else if ( c.getType() == Card.cardType.ROOM )
				++numRooms;
		}

		assertEquals(6, numWeapons);
		assertEquals(6, numPeople);
		assertEquals(9, numRooms);

		//CHECK THAT DECK CONTAINS 3 SPECIFIC NAMED CARDS
		int mustardCardNumber = 0;
		int studyCardNumber = 0;
		int ropeCardNumber = 0;
		
		for ( Card c : cards ) {
			if (c.getName().equals("Colonel Mustard")) mustardCardNumber++;
			if (c.getName().equals("Study")) studyCardNumber++;
			if (c.getName().equals("Rope")) ropeCardNumber++;
		}
		
		assertEquals(1, mustardCardNumber);
		assertEquals(1, studyCardNumber);
		assertEquals(1, ropeCardNumber);


	}

	@Test
	public void TestCheckAccusation() {
		game.setSolution( new Solution("Miss Scarlet", "Study", "Candlestick") );
		//ACCUSATION IS CORRECT
		assertTrue(game.checkAccusation(new Solution("Miss Scarlet", "Study", "Candlestick")));

		//ACCUSATION IS INCORRECT - PERSON IS WRONG
		assertFalse(game.checkAccusation(new Solution("a", "Study", "Candlestick")));

		//ACCUSATION IS INCORRECT - ROOM IS WRONG
		assertFalse(game.checkAccusation(new Solution("Miss Scarlet", "b", "Candlestick")));

		//ACCUSATION IS INCORRECT - WEAPON IS WRONG
		assertFalse(game.checkAccusation(new Solution("Miss Scarlet", "Study", "c")));

		//ACCUSATION IS INCORRECT - ALL THREE ARE WRONG
		assertFalse(game.checkAccusation(new Solution("a", "b", "c")));
	}

	@Test
	public void TestTargetRandomSelection() {
		ComputerPlayer player = new ComputerPlayer();
		// Pick a location with no rooms in target, just four targets
		board.calcTargets(2, 7, 2);
		int loc_0_7_Tot = 0;
		int loc_1_8_Tot = 0;
		int loc_3_8_Tot = 0;
		int loc_4_7_Tot = 0;
				
		// Run the test 100 times
		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(board.getTargets());
			if (selected == board.getCellAt(board.calcIndex(0, 7)))
				loc_0_7_Tot++;
			else if (selected == board.getCellAt(board.calcIndex(1, 8)))
				loc_1_8_Tot++;
			else if (selected == board.getCellAt(board.calcIndex(3, 8)))
				loc_3_8_Tot++;
			else if (selected == board.getCellAt(board.calcIndex(4, 7)))
				loc_4_7_Tot++;
			else
				fail("Invalid target selected");
		}
		
		// Ensure we have 100 total selections (fail should also ensure)
		assertEquals(100, loc_0_7_Tot + loc_1_8_Tot + loc_3_8_Tot + loc_4_7_Tot);
		
		// Ensure each target was selected more than once
		assertTrue(loc_0_7_Tot > 1);
		assertTrue(loc_1_8_Tot > 1);
		assertTrue(loc_3_8_Tot > 1);
		assertTrue(loc_4_7_Tot > 1);
	}

	@Test
	public void TestDisproveSuggestionOnePlayerOneMatch() {
		//test one player, one correct match
		Player player = new ComputerPlayer();
		
		//deal six cards to player
		player.addCard(mustardCard);
		player.addCard(scarletCard);
		player.addCard(pipeCard);
		player.addCard(candlestickCard);
		player.addCard(studyCard);
		player.addCard(kitchenCard);
		
		//disproveSuggestion tests
		//Returns player
		assertEquals("Colonel Mustard", player.disproveSuggestion(mustardCard, conservatoryCard, revolverCard));
		//Returns room
		assertEquals("Kitchen", player.disproveSuggestion(plumCard, kitchenCard, revolverCard));
		//Returns weapon
		assertEquals("Lead Pipe", player.disproveSuggestion(plumCard, conservatoryCard, pipeCard));
		//Returns null
		assertEquals(null , player.disproveSuggestion(plumCard, conservatoryCard, revolverCard));
		
	}
	
	@Test
	public void TestDisproveSuggestionOnePlayerMultipleMatch() {
		Player player = new ComputerPlayer();
		
		//test one player, multiple possible matches
				player = new ComputerPlayer();
				player.addCard(mustardCard);
				player.addCard(kitchenCard);
				player.addCard(pipeCard);
				
				//Run 100 times
				int mustardDisp = 0;
				int kitchenDisp = 0;
				int pipeDisp = 0;
				
				for(int i = 0; i < 100; ++i) {
					String dispCard = player.disproveSuggestion(mustardCard, kitchenCard, pipeCard);
					if ( dispCard == mustardCard.getName() )
						mustardDisp++;
					else if ( dispCard == kitchenCard.getName() )
						kitchenDisp++;
					else if ( dispCard == pipeCard.getName() ) 
						pipeDisp++;
					else 
						fail("Gave card player does not have");
				}
				
				//check that each card was given at least once
				assertTrue( mustardDisp > 1 );
				assertTrue( kitchenDisp > 1 );
				assertTrue( pipeDisp > 1 );
	}
	
	@Test
	public void TestPlayersQueried() {
		
		//Test a suggestion that nobody can disprove
		HumanPlayer p = (HumanPlayer) players.get(0);
		String person = game.getSolution().getPerson();
		String room = game.getSolution().getRoom();
		String weapon = game.getSolution().getWeapon();
		p.createSuggestion(person, room, weapon);
		assertEquals(null, game.handleSuggestion(p));
		
		//Test a suggestion that only a human can disprove
		ComputerPlayer pl = (ComputerPlayer) players.get(1);
		Card c = p.getCards().get(0);
		if (c.getType() == Card.cardType.PERSON) {
			pl.createSuggestion(c.getName(), room, weapon);
		} else if (c.getType() == Card.cardType.ROOM) {
			pl.createSuggestion(person, c.getName(), weapon);
		} else if (c.getType() == Card.cardType.WEAPON) {
			pl.createSuggestion(person, room, c.getName());
		} else {
			fail("Card has invalid type");
		}
		assertEquals(c.getName(), game.handleSuggestion(pl));
		
		//Test a suggestion that can only be disproved by the person suggesting it
		Card d = pl.getCards().get(0);
		if (d.getType() == Card.cardType.PERSON) {
			pl.createSuggestion(d.getName(), room, weapon);
		} else if (d.getType() == Card.cardType.ROOM) {
			pl.createSuggestion(person, d.getName(), weapon);
		} else if (d.getType() == Card.cardType.WEAPON) {
			pl.createSuggestion(person, room, d.getName());
		} else {
			fail("Card has invalid type");
		}
		assertEquals(null, game.handleSuggestion(pl));
		
		p.getCards().remove(2);
		pl.getCards().remove(2);
		p.addCard(new Card(Card.cardType.ROOM, room));
		pl.addCard(new Card(Card.cardType.PERSON, person));
		
		ComputerPlayer newPlayer = (ComputerPlayer) players.get(2);
		
		newPlayer.createSuggestion(person, room, weapon);
		
		int p1 = 0, p2 = 0;
		
		for ( int i = 0; i < 100; ++ i) {
			String disprove = game.handleSuggestion(newPlayer);
			if ( disprove.equals(person) )
				++p1;
			else if ( disprove.equals(room) )
				++p2;
			else
				fail("Wrong player gave a card");
		}
		assertTrue(p1 > 0);
		assertTrue(p2 > 0);
		
		
	}
			
	@Test
	public void TestCreateSuggestion() {
		ComputerPlayer player = new ComputerPlayer("Miss. Scarlet", board.calcIndex(18, 3));
		
		//Show player all but two of each card.
		player.updateSeen(scarletCard);
		player.updateSeen(plumCard);
		player.updateSeen(peacockCard);
		player.updateSeen(whiteCard);
		player.updateSeen(pipeCard);
		player.updateSeen(ropeCard);
		player.updateSeen(candlestickCard);
		player.updateSeen(wrenchCard);
		player.updateSeen(daggerCard);
		
		int greenPick = 0;
		int mustardPick = 0;
		
		for(int i = 0; i < 100; ++i) {
			player.createSuggestion("Kitchen", game.getCards());
			if(player.getSuggestion().getPerson().equals("Mr. Green"))
				greenPick++;
			if(player.getSuggestion().getPerson().equals("Colonel Mustard"))
				mustardPick++;
		}
		
		assertTrue(greenPick > 0);
		assertTrue(mustardPick > 0);
		
		assertEquals("Revolver", player.getSuggestion().getWeapon());
		assertEquals("Kitchen", player.getSuggestion().getRoom());
	}
}