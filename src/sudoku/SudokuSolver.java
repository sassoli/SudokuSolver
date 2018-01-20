/**
 * 
 */
package sudoku;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author cesaresassoli
 *
 */

public class SudokuSolver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//PARAMETERS
		// -h print parameters list
		// -f {filename} read input sudoku from file
		// -s just a string with dot and number as input sudoku  
		
		Sudoku s = new Sudoku();

		if(args.length > 0){
			
			if(args[0].equalsIgnoreCase("-h") || args[0].equalsIgnoreCase("--help")){
				
				System.out.println("\033[1mUsage:\033[0m");
				System.out.println("java sudokuSolver [options] [arguments]");
				System.out.println("");
				System.out.println("\033[1mOptions:\033[0m");
				System.exit(0);
			}
			
			if(args[0].equalsIgnoreCase("-f") || args[0].equalsIgnoreCase("--filename")){
				if(args.length == 1){
					System.out.println("error: filename missing");
					System.exit(0);
				}
				try {
					String schema = readFile(args[1]);
					s.setSchema(schema);
				} catch (FileNotFoundException e) {
					System.out.println("error: file " + args[1] + "not found");
					System.exit(0);
				}	
			}
			
			if(args[0].equalsIgnoreCase("-s")|| args[0].equalsIgnoreCase("--sudoku")){
				if(args.length == 1){
					System.out.println("error: sudoku missing");
					System.exit(0);
				}
				
				s = new Sudoku(args[1]);
			}	
		}
		
		//
		//Sudoku s = new Sudoku("2.. 5.. 4.3 .3. 4.9 .2. ..4 .23 859 85. .9. .7. ..6 3.8 1.. .4. .1. .68 4.2 .3. 5.. ... 9.2 .3. 7.3 ..6 ..4");		
		//http://qqwing.com/generate.html
		//s = new Sudoku(".......8.578...91.......2.595.3.76.....61....8...2.5.......9.23.9....1..3.21..8..");
		
		//s = new Sudoku("....8.94..67..5..8..192..655....2....12..4....7.5.8..2..5.......841......2....4..");
		//cannot be solved (for now)
		//s.setSchema("2.. 5.. 4.3 .3. 4.9 .2. ..4 .23 859 85. .9. .7. ..6 3.8 1.. .4. .1. .68 4.2 .3. 5.. ... 9.2 .3. 7.3 ..6 ..4");
		
		if(s.schema == null){
			System.out.println("error: i not received a valid input");
			System.exit(0);
		}
		s.printSchema();
		s.solveMe();
		s.printSchemaArr();	
	}
	
	private static String readFile(String filename) throws FileNotFoundException{
		
		//check if file exists
		String schema = "";
		
		FileInputStream fstream = new FileInputStream(filename);
		// or using Scaner
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		
		//Read File Line By Line
		try {
			while ((strLine = br.readLine()) != null) {
			  // split string and call your function
				schema += strLine;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		
		return schema;
		
		
	}
	
	
}
