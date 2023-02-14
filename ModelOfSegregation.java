import java.util.*;
/**
Ilana Nguyen
AP CS Final Project
Created 5/18/2020
Main class to run model.
*/
public class ModelOfSegregation {
	private static int width=5;
	private static int length=5;
	private static int red=width*2;
	private static int blue=width*2;
	private static double percentSimilar=0.50;
	private static Agent[][] array = new Agent[width+2][length+2]; //add padding
	
	public static void main(String[] args) {
		StdDraw.setCanvasSize(500, 500);

		//0=empty cell
		//1=red cell
		//2=blue cell

		while(red>0) {
			int x=(int)(Math.random()*width)+1; //random number 1-width
			int y=(int)(Math.random()*length)+1;
			if(array[x][y] == null) {
				array[x][y] = new Agent(1,x,y);
				red--;
			}
		}

		while(blue>0) {
			int x=(int)(Math.random()*width)+1;
			int y=(int)(Math.random()*length)+1;
			if(array[x][y] == null) {
				array[x][y] = new Agent(2,x,y);
				blue--;
			}
		}

		for(int i=0;i<width+2;i++){
			for(int j=0;j<length+2;j++) {
				if(array[i][j] == null)
					array[i][j] = new Agent(0,i,j);
			}
		}

		printNumbers();
		drawGrid();
		drawSquares();

		while(true) {
			for(int i=1;i<=width;i++){
				for(int j=1;j<=length;j++) {
					if(!isSatisfied(i,j)) {
						array[i][j].setSatisfied(false);
					}
				}
			}
			relocate();
			printNumbers(); //to test number corresponding to colors in grid
			StdDraw.pause(300); //to delay drawings
			drawSquares();
		}
	}

	public static void relocate(){
		ArrayList<Agent> emptyCells = new ArrayList<Agent>();
		//find empty spaces
		for(int i=1;i<=width;i++){
			for(int j=1;j<=length;j++) {
				if(array[i][j].getType()==0) {
					emptyCells.add(array[i][j]);
				}
			}
		}

		//relocate non-satisfied agents
		for(int i=1;i<=width;i++){
			for(int j=1;j<=length;j++) {
				Agent a=array[i][j];
				if((!a.getSatisfied())&&(emptyCells.size()>0)) {
					int random=(int)(Math.random()*emptyCells.size());
					Agent b=emptyCells.get(random);
					int x=b.getX();
					int y=b.getY();
					array[x][y].setEverything(a.getType(),x,y,true); //set empty cell to unsatisfied agent
					array[i][j].setEverything(0,i,j,true); //set cell of unsatisfied agent to empty
					emptyCells.remove(random);
				} 
			}
		}
	}


	public static boolean isSatisfied(int x, int y) {
		int numNeighbors=0;
		int numSimilar=0;
		Agent a = array[x][y];
		for(int i=y-1;i<y+2;i++) { //row of cells on top
			Agent b = array[x-1][i];
			if(b.getType() != 0) {
				numNeighbors++;
				if(a.getType() == b.getType())
					numSimilar++;
			}
		}
		for(int i=y-1;i<y+2;i++) { //row of cells on bottom
			Agent b = array[x+1][i];
			if(b.getType() != 0) {
				numNeighbors++;
				if(a.getType() == b.getType())
					numSimilar++;
			}
		}

		//two cells on sides
		Agent b = array[x][y-1];
		if(b.getType() != 0) {
			numNeighbors++;
			if(a.getType() == b.getType())
				numSimilar++;
		}
		Agent c = array[x][y+1];
		if(c.getType() != 0) {
			numNeighbors++;
			if(a.getType() == c.getType())
				numSimilar++;
		}


		if(((double)(numSimilar)/numNeighbors) < percentSimilar)
			return false;
		return true;
	}

	/**
	Method that draws black grid lines.
	*/
	public static void drawGrid() {
		StdDraw.setPenRadius(0.005);
		StdDraw.setPenColor(StdDraw.BLACK);
		//draw lines
		for(int i=0;i<=width;i++) {
			StdDraw.line(i*0.2, 0, i*0.2, 1);
		}
		for(int i=0;i<=length;i++) {
			StdDraw.line(0, i*0.2, 1, i*0.2);
		}
	}

	/**
	Method that draws colored squares corresponding to agent types.
	*/
	public static void drawSquares() {
		double x=0.1;
		for(int i=1;i<=width;i++){
			double y=0.9;
			for(int j=1;j<=length;j++) {
				array[i][j].drawSquare(x,y,0.099);
				y-=0.2;
			}
			x+=0.2;
		}
	}

	/**
	Method that prints the grid in number format (for testing purposes).
	*/
	public static void printNumbers() {
		for(int i=0;i<length+2;i++){
			for(int j=0;j<width+2;j++) {
				System.out.print(array[j][i].getType()+" ");
			}
			System.out.println(" ");
			System.out.println(" ");
		}
	}
}