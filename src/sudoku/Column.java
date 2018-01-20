package sudoku;
import java.util.ArrayList;

public class Column {

	static ArrayList<Point> otherPoints(Point p, boolean returnAll){
		ArrayList<Point> points = new ArrayList<Point>();
		for(int i=0; i<9; i++){			
			Point np = new Point(p.x, i);
			if(i != p.y || returnAll){
				points.add(np);
			}
		}
		return points;
	}
}
