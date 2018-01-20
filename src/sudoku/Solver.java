/**
 * 
 */
package sudoku;

import java.util.ArrayList;

/**
 * @author cesaresassoli
 */
public class Solver {
	Sudoku s;
	Option[][] pos = new Option[9][9];
	char[] possibleValues = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
	ArrayList<Point> pointAdded = new ArrayList<Point>(); 
	Point[] squarePoints = {new Point(0,0),new Point(0,3),new Point(0,6),
							new Point(3,0),new Point(3,3),new Point(3,6),
							new Point(6,0),new Point(6,3),new Point(6,6)};
	
	Point[] linePoints = {new Point(0,0),new Point(0,1),new Point(0,2),
			new Point(0,3),new Point(0,4),new Point(0,5),
			new Point(0,7),new Point(0,7),new Point(0,8)};
	
	Point[] columnPoints = {new Point(0,0),new Point(1,0),new Point(2,0),
			new Point(3,0),new Point(4,0),new Point(5,0),
			new Point(7,0),new Point(7,0),new Point(8,0)};
	
	public Solver() {
	}
	
	public Solver(Sudoku s) {
		this.s = s;
	}

	public char[][] solveMe(char[][] schemaArr) {
		
		initPieces(schemaArr);
		boolean hasFound = true;
		int conter = 0;
		while(hasFound){
			conter++; 
			hasFound = false;
			updatePos();
			pointAdded.clear();
			// Find Alone
			ArrayList<Point> founds = findAlone();
			updatePos();
			
			// Find one match
			ArrayList<Point> founds2 = findOneMatch();
		
			if(founds.size() > 0){
				hasFound = true;
			}			
			
			if(founds2.size() > 0){
				hasFound = true;
			}			
			s.schemaArr = posToSchema();
			//s.printSchemaArr();
		}
		
		return posToSchema();
	}
	
	private char[][] posToSchema(){
		char[][] schemaComplete = new char[9][9];
		for(int y = 0; y < 9; y++){
			for(int x = 0; x < 9;x++){		
				schemaComplete[x][y] = pos[x][y].getValue();		
			}
		}
		return schemaComplete;
		
	}
	
	
	//Cerco per istanze isolate
	public ArrayList<Point> findAlone(){
		ArrayList<Point> points = new ArrayList<Point>();
		for(int y = 0; y < 9; y++){
			for(int x = 0; x < 9;x++){
				
				if(pos[x][y].possibilities.size()==1){
					char val = pos[x][y].possibilities.get(0);
					pos[x][y].possibilities.clear();
					pos[x][y].setValue(val);
					pointAdded.add(new Point(x, y));
					points.add(new Point(x, y)); 
				}
			}
		}
		return points;
	}
	
	//Cerco per istanze che sicuramente sono posizionati x square x line e x col
		public ArrayList<Point> findOneMatch(){
			
			ArrayList<Point> points = new ArrayList<Point>();
							
				//Square Search
				for(char target: possibleValues){
					for(Point sp: squarePoints){
						ArrayList<Point> pall = Square.otherPoints(sp, true);
						Point firstMatch = null;
						for(Point p: pall){
							if(pos[p.x][p.y].possibilities.contains(target)){
								if(firstMatch == null){
									firstMatch = p;
								}else{
									firstMatch = null;
									break;
								}
							}
						}
						if(firstMatch != null){
						
							pos[firstMatch.x][firstMatch.y].possibilities.clear();
							pos[firstMatch.x][firstMatch.y].setValue(target);
							pointAdded.add(new Point(firstMatch.x, firstMatch.y));
							points.add(new Point(firstMatch.x, firstMatch.y));
							updatePos();
						}
					}	
				}
							
				//Line Search
				for(char target: possibleValues){
					for(Point lp: linePoints){
						ArrayList<Point> pall = Line.otherPoints(lp, true);
						Point firstMatch = null;
						for(Point p: pall){
							if(pos[p.x][p.y].possibilities.contains(target)){
								if(firstMatch == null){
									firstMatch = p;
								}else{
									firstMatch = null;
									break;
								}
							}
						}
						if(firstMatch != null){
							
							pos[firstMatch.x][firstMatch.y].possibilities.clear();
							pos[firstMatch.x][firstMatch.y].setValue(target);
							pointAdded.add(new Point(firstMatch.x, firstMatch.y));
							points.add(new Point(firstMatch.x, firstMatch.y));
							updatePos();
						}
					}	
				}
					
					
				//Column Search
				for(char target: possibleValues){
					for(Point cp: columnPoints){
						ArrayList<Point> pall = Line.otherPoints(cp, true);
						Point firstMatch = null;
						for(Point p: pall){
							if(pos[p.x][p.y].possibilities.contains(target)){
								if(firstMatch == null){
									firstMatch = p;
								}else{
									firstMatch = null;
									break;
								}
							}
						}
						if(firstMatch != null){
							
							pos[firstMatch.x][firstMatch.y].possibilities.clear();
							pos[firstMatch.x][firstMatch.y].setValue(target);
							pointAdded.add(new Point(firstMatch.x, firstMatch.y));
							points.add(new Point(firstMatch.x, firstMatch.y));
							updatePos();
						}
					}	
				}
			
			return points;

		}
	
	public void removePointsFromPos(ArrayList<Point> points, char val){		
		for(Point p: points){
			if(!pos[p.x][p.y].possibilities.isEmpty()){				
				
				pos[p.x][p.y].possibilities.remove(Character.valueOf(val));				
				//System.out.println(pos[p.x][p.y].possibilities);
			}	
		}
	}
	
	private void updatePos(){
		for (Point p: pointAdded) {
			
			char val = pos[p.x][p.y].getValue();
			
			//Remove point from square
			ArrayList<Point>square = Square.otherPoints(p, false);			
			removePointsFromPos(square, val);
		
			//Remove point from line
			ArrayList<Point>line = Line.otherPoints(p, false);
			removePointsFromPos(line, val);
			
			//Remove point from column
			ArrayList<Point>column = Column.otherPoints(p, false);
			removePointsFromPos(column, val);
		}
	}
	
	private void initPieces(char[][] schemaArr){
		for(int y = 0; y < 9; y++){		
			for(int x = 0; x < 9; x++){			
				pos[x][y] = new Option();
				char curr = schemaArr[x][y]; 

				if(curr != '.'){
					pos[x][y].possibilities.clear();
					pos[x][y].setValue(curr);
					pointAdded.add(new Point(x, y));
				}
			}
		}
	}
	
	void printCandidates(){
		for(int y = 0; y < 9; y++){		
			for(int x = 0; x < 9; x++){		
				System.out.print(pos[x][y].possibilities + "  ");
				if((x+1)%3==0){
					System.out.print(" | ");
				}
			}
			if(y%3==0){
				System.out.println(" ");
			}
		}
	}
}
