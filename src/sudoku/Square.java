package sudoku;
import java.util.ArrayList;


/* 
 *   1 2 3
 *   4 5 6
 *   7 8 9
 */

public class Square {
	static ArrayList<Point> otherPoints(Point p, boolean returnAll){
		ArrayList<Point> points = new ArrayList<Point>();
		/*int[][] quad = {{ 1, 4, 7 }, 
	            { 2, 5, 8 },
	            { 3, 6, 9}}; */
		
		int x = (int) Math.floor(p.x / 3);
		int y = (int) Math.floor(p.y / 3);
		
	//	int currQuad = quad[x][y];
	//	System.out.println("per x,y (" + p.x + ","+ p.y + ") "+ currQuad);
		
		for(int i=0; i < 3; i++){
			for(int k=0; k < 3; k++){
				
				int nx = i + (3*x);
				int ny = k + (3*y);
				
				if(returnAll || (nx != p.x || ny != p.y)){
					points.add(new Point(nx, ny));
				}
				
			}
		}
		
		return points;
		
	}
}
