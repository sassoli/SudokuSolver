package sudoku;

import java.util.ArrayList;

public class Line {
	static ArrayList<Point> otherPoints(Point p, boolean returnAll){
		ArrayList<Point> points = new ArrayList<Point>();
		for(int i=0; i<9; i++){			
			Point np = new Point(i, p.y);
			if(i != p.x || returnAll){
				points.add(np);
			}
		}
		return points;
	}
}
