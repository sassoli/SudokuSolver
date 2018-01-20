/**
 * 
 */
package sudoku;

/**
 * @author cesaresassoli
 *
 */
public class Sudoku {
	String schema;
	char[][] schemaArr = new char[9][9];
	Solver solver;
	//Costruttore stringa
	/**
	 * 
	 * @param schema a string with problem schema
	 */
	public Sudoku(String schema){
		schema = cleanSchema(schema);
		this.schema = schema;
		//TODO CHECK validita
	}
	
	public Sudoku(){
	
	}
	
	void solveMe(){
		solver = new Solver(this);
		schemaArr = solver.solveMe(schemaArr);
		
		//solver.printCandidates();
	}
	private String cleanSchema(String schema){	
		//Remove white spaces
		return schema.replaceAll("\\s+","");
	}
	
	public void printSchema(){		
		String out = "";
		int posX = 0;
		int posY = 0;
		for(int i = 0; i < 81; i++){
			char currChar = schema.charAt(i);
			out += currChar;
			schemaArr[posX][posY] = currChar;
			posX++;
			
			if(( i+1 )%9 == 0){
				posX = 0;
				posY++;
				
				out+="\n";
				if((i+1)%27==0){
					out+="\n";
				}
			}else if((i+1)%3==0){
				out+="  ";
			}
		}
		System.out.println(out);
	}
	

	public void printSchemaArr() {
				
		for(int y = 0; y < 9; y++){
			String row = "";
			for(int x = 0; x < 9;x++){
				if((x)%3==0 && x!=0){
					row += "  ";
				}
				row += schemaArr[x][y]; 
			}
			
			if((y)%3==0){
				System.out.println("");
			}
			System.out.println(row);
		}
		
	}

	/**
	 * @return the schema
	 */
	public String getSchema() {
		return schema;
	}

	/**
	 * @param schema the schema to set
	 */
	public void setSchema(String schema) {
		schema = cleanSchema(schema);
		this.schema = schema;		
	}
	
}
