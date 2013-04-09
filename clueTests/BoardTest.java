package clueTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.RoomCell;
import clueGame.RoomCell.DoorDirection;
import clueGame.WalkwayCell;


public class BoardTest {

	Board b;
	
	
	@Before
	public void setUp(){
		b = new Board("ClueBoard.csv", "Legend.txt");
		try{
		b.loadConfigFiles();
		}
		catch(BadConfigFormatException e){
			e = new BadConfigFormatException(b);
			System.out.println(e.toString());
		}
	}
	
	@Test
	public void testRooms() {
		assertEquals(11, b.getRooms().size());
		
		assertEquals(b.getRooms().get('C'), "Conservatory");
		assertEquals(b.getRooms().get('A'), "Ballroom");
		assertEquals(b.getRooms().get('O'), "Lounge");
		assertEquals(b.getRooms().get('K'), "Kitchen");
		
	}
	
	@Test
	public void testBoard(){
		assertEquals(25, b.getNumRows());
		assertEquals(24, b.getNumColumns());
		
		assertEquals(DoorDirection.UP, ((RoomCell) b.getCells().get(312)).getDoorDirection());
		assertEquals(DoorDirection.DOWN, ((RoomCell) b.getCells().get(78)).getDoorDirection());
		assertEquals(DoorDirection.LEFT, ((RoomCell) b.getCells().get(105)).getDoorDirection());
		assertEquals(DoorDirection.RIGHT, ((RoomCell) b.getCells().get(198)).getDoorDirection());
		assertEquals(false, ((WalkwayCell) b.getCells().get(123)).isDoorway());
		
		assertEquals('C', ((RoomCell) b.getCells().get(480)).getInitial());
		assertEquals('X', ((RoomCell) b.getCells().get(273)).getInitial());
		assertEquals('O', ((RoomCell) b.getCells().get(143)).getInitial());
		assertEquals('K', ((RoomCell) b.getCells().get(599)).getInitial());
		
		assertEquals(b.calcIndex(3, 3), 75);
		assertEquals(b.calcIndex(13, 17), 329);
		assertEquals(b.calcIndex(23, 23), 575);
	}
	
	@Test
	public void testDoorDirection(){
		// Make sure walkway isn't door
		assertFalse(b.getCellAt(b.calcIndex(0, 8)).isDoorway());
		
		// Room spaces
		assertEquals(b.getRoomCellAt(2, 20).getDoorDirection(), DoorDirection.NONE);
		assertEquals(b.getRoomCellAt(5, 17).getDoorDirection(), DoorDirection.DOWN);
		assertEquals(b.getRoomCellAt(8, 6).getDoorDirection(), DoorDirection.RIGHT);
		assertEquals(b.getRoomCellAt(9, 17).getDoorDirection(), DoorDirection.UP);
		assertEquals(b.getRoomCellAt(13, 16).getDoorDirection(), DoorDirection.LEFT);
	}
	
	@Test(expected = BadConfigFormatException.class)
	public void testBadConfigFormat() throws BadConfigFormatException{
		
		b = new Board("BadClueBoard.csv", "Legend.txt");
		b.loadConfigFiles();
		
	}

}
