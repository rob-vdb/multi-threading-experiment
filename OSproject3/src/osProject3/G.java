package osProject3;

public class G {
	
	// location of cheese
	int x;
	int y;
	
	public G() { // G (grid) constructor
		
	}
	
	public G(int x, int y){ //
		this.x = x;
		this.y = y;
	}
	
	// getG() method that returns a 2-D 8x8 array where each entry is a box object.
	// Creates grid where Box.isCheese() is false except for the Box at the given x and y coordinates.
	public static  Box[][] getG(int x, int y){
		Box[][] g = new Box[8][8];
		
		for (int row=0; row<g.length; row++) {
			for (int column=0; column<g[1].length; column++) {
				g[row][column] = new Box(false);
				}
			}
		g[x][y] = new Box(true);
		
		return g;
	}

}
