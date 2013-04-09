package clueTests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.RoomCell;
import clueGame.WalkwayCell;
import clueGame.RoomCell.DoorDirection;

public class AdjacencyTargetTests {

static Board b;
	
	
	@BeforeClass
	public static void setUp(){
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
	public void testAdjInRoom() {
		// Test a corner
		LinkedList<Integer> testList = b.getAdjList(b.calcIndex(0, 0));
		Assert.assertEquals(0, testList.size());

		testList = b.getAdjList(b.calcIndex(3, 0));
		Assert.assertEquals(0, testList.size());

		testList = b.getAdjList(b.calcIndex(21, 11));
		Assert.assertEquals(0, testList.size());

		testList = b.getAdjList(b.calcIndex(18, 13));
		Assert.assertEquals(0, testList.size());

		testList = b.getAdjList(b.calcIndex(19, 21));
		Assert.assertEquals(0, testList.size());
	}
	
	@Test
	public void testAdjExits() {
		
		assertTrue(b.getAdjList(b.calcIndex(3, 6)).contains(Integer.valueOf(b.calcIndex(4, 6))));
		
		assertTrue(b.getAdjList(b.calcIndex(13, 0)).contains(Integer.valueOf(b.calcIndex(12, 0))));
		
		assertTrue(b.getAdjList(b.calcIndex(16, 5)).contains(Integer.valueOf(b.calcIndex(16, 6))));
		
		assertTrue(b.getAdjList(b.calcIndex(20, 8)).contains(Integer.valueOf(b.calcIndex(20, 7))));
		
	}
	
	@Test
	public void testAdjEnterance() {
		
		assertTrue(b.getAdjList(b.calcIndex(6, 17)).contains(Integer.valueOf(b.calcIndex(5, 17))));
		assertTrue(b.getAdjList(b.calcIndex(6, 17)).contains(Integer.valueOf(b.calcIndex(7, 17))));
		assertTrue(b.getAdjList(b.calcIndex(6, 17)).contains(Integer.valueOf(b.calcIndex(6, 16))));
		assertTrue(b.getAdjList(b.calcIndex(6, 17)).contains(Integer.valueOf(b.calcIndex(6, 18))));
		
		assertTrue(b.getAdjList(b.calcIndex(8, 7)).contains(Integer.valueOf(b.calcIndex(8, 6))));
		assertTrue(b.getAdjList(b.calcIndex(8, 7)).contains(Integer.valueOf(b.calcIndex(8, 8))));
		assertTrue(b.getAdjList(b.calcIndex(8, 7)).contains(Integer.valueOf(b.calcIndex(7, 7))));
		assertTrue(b.getAdjList(b.calcIndex(8, 7)).contains(Integer.valueOf(b.calcIndex(9, 7))));
		
		assertTrue(b.getAdjList(b.calcIndex(8, 17)).contains(Integer.valueOf(b.calcIndex(7, 17))));
		assertTrue(b.getAdjList(b.calcIndex(8, 17)).contains(Integer.valueOf(b.calcIndex(9, 17))));
		assertTrue(b.getAdjList(b.calcIndex(8, 17)).contains(Integer.valueOf(b.calcIndex(8, 16))));
		assertTrue(b.getAdjList(b.calcIndex(8, 17)).contains(Integer.valueOf(b.calcIndex(8, 18))));
		
		assertTrue(b.getAdjList(b.calcIndex(13, 15)).contains(Integer.valueOf(b.calcIndex(13, 14))));
		assertTrue(b.getAdjList(b.calcIndex(13, 15)).contains(Integer.valueOf(b.calcIndex(13, 16))));
		assertTrue(b.getAdjList(b.calcIndex(13, 15)).contains(Integer.valueOf(b.calcIndex(12, 15))));
		assertTrue(b.getAdjList(b.calcIndex(13, 15)).contains(Integer.valueOf(b.calcIndex(14, 15))));
	}
	
	@Test
	public void testWalkwayAll() {
		
		assertTrue(b.getAdjList(b.calcIndex(16, 8)).contains(Integer.valueOf(b.calcIndex(16, 7))));
		assertTrue(b.getAdjList(b.calcIndex(16, 8)).contains(Integer.valueOf(b.calcIndex(16, 9))));
		assertTrue(b.getAdjList(b.calcIndex(16, 8)).contains(Integer.valueOf(b.calcIndex(15, 8))));
		assertTrue(b.getAdjList(b.calcIndex(16, 8)).contains(Integer.valueOf(b.calcIndex(17, 8))));
		
	}
	
	@Test
	public void testWalkwayEdgesDoorWay() {
		
		assertTrue(b.getAdjList(b.calcIndex(0, 16)).contains(Integer.valueOf(b.calcIndex(0, 15))));
		assertTrue(b.getAdjList(b.calcIndex(0, 16)).contains(Integer.valueOf(b.calcIndex(1, 16))));
		
		assertTrue(b.getAdjList(b.calcIndex(5, 0)).contains(Integer.valueOf(b.calcIndex(4, 0))));
		assertTrue(b.getAdjList(b.calcIndex(5, 0)).contains(Integer.valueOf(b.calcIndex(5, 1))));
		
		assertTrue(b.getAdjList(b.calcIndex(17, 23)).contains(Integer.valueOf(b.calcIndex(17, 22))));
		assertTrue(b.getAdjList(b.calcIndex(17, 23)).contains(Integer.valueOf(b.calcIndex(18, 23))));
		
		assertTrue(b.getAdjList(b.calcIndex(24, 8)).contains(Integer.valueOf(b.calcIndex(24, 7))));
		assertTrue(b.getAdjList(b.calcIndex(24, 8)).contains(Integer.valueOf(b.calcIndex(24, 9))));
		
	}
	
	@Test
	public void testWalkwayEdges() {
		
		assertTrue(b.getAdjList(b.calcIndex(5, 4)).contains(Integer.valueOf(b.calcIndex(5, 3))));
		assertTrue(b.getAdjList(b.calcIndex(5, 4)).contains(Integer.valueOf(b.calcIndex(5, 5))));
		assertTrue(b.getAdjList(b.calcIndex(5, 4)).contains(Integer.valueOf(b.calcIndex(4, 4))));
		
		assertTrue(b.getAdjList(b.calcIndex(10, 15)).contains(Integer.valueOf(b.calcIndex(10, 14))));
		assertTrue(b.getAdjList(b.calcIndex(10, 15)).contains(Integer.valueOf(b.calcIndex(9, 15))));
		assertTrue(b.getAdjList(b.calcIndex(10, 15)).contains(Integer.valueOf(b.calcIndex(11, 15))));
		
	}
	
	@Test
	public void testTargets() {
		
		b.calcTargets(5, 1, 3);
		Set<BoardCell> targets =  b.getTargets();
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(5, 0))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(4, 1))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(5, 2))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(4, 3))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(5, 4))));
		
		b.calcTargets(7, 23, 2);
		targets =  b.getTargets();
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(6, 22))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(8, 22))));
		
		b.calcTargets(12, 8, 2);
		targets =  b.getTargets();
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(10, 8))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(11, 7))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(12, 6))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(13, 7))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(14, 8))));
		
		b.calcTargets(18, 2, 2);
		targets =  b.getTargets();
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(18, 0))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(19, 1))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(19, 3))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(18, 4))));
	}
	
	@Test
	public void testTargetsToDoor() {
		
		b.calcTargets(7, 11, 1);
		Set<BoardCell> targets =  b.getTargets();
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(7, 10))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(7, 12))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(6, 11))));
		
		b.calcTargets(21, 16, 2);
		targets =  b.getTargets();
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(23, 16))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(22, 17))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(20, 17))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(19, 16))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(20, 15))));
		
	}
	
	@Test
	public void testTargetsFromDoor() {
		
		b.calcTargets(6, 13, 2);
		Set<BoardCell> targets =  b.getTargets();
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(7, 12))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(8, 13))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(7, 14))));
		
		b.calcTargets(18, 9, 3);
		targets =  b.getTargets();
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(17, 7))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(16, 8))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(15, 9))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(16, 10))));
		Assert.assertTrue(targets.contains(b.getCellAt(b.calcIndex(17, 11))));
		
	}
	
	
}
