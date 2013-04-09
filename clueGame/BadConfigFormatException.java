package clueGame;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class BadConfigFormatException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String filename;
	private String logFile;
	
	/*The error handling for this exception will have to be done briefly in Main()
	 * because the board itself is passed into the error*/
	public BadConfigFormatException(Board b){
		super();
		this.filename = b.getFilename();
		logFile = "ClueLog.txt";
		try{
		FileWriter fstream = new FileWriter(logFile);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write("CLUE ERROR LOG FILE");
		out.write("ROWS: "+ b.getNumRows());
		out.write("COLUMNS: " + b.getNumColumns());
		out.write("CELLS: ");
		for(BoardCell c : b.getCells()){
			out.write("cell #: " + b.calcIndex(c.getRow(), c.getColumn()));
			out.write("row: " + c.getRow());
			out.write("column: " + c.getColumn());
			out.write("Walkway? : " + c.isWalkway());
			out.write("Doorway? : " + c.isDoorway());
			out.write("Room? : " + c.isRoom());
			out.write("------------------------------");
		}
		out.write("ROOMS: ");
		for(Map.Entry<Character, String> entry : b.getRooms().entrySet()){
			out.write(entry.getKey() + "/" + entry.getValue());
		}
		out.close();
		}
		catch(IOException e){
			System.out.println(e.toString());
		}
	}
	
	public BadConfigFormatException(String message) {
		super(message);
	}
	
	@Override
	public String toString(){
		return "ERROR IN THE CONFIGURATION OF " + filename + " LOG FILE WRITTEN TO: " + logFile;
	}

}
