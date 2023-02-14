import java.util.*;

/**
Ilana Nguyen
AP CS Final Project
Created 5/18/2020
Helper class to use agents in model.
*/
public class Agent {
	private int type;
	private boolean satisfied;
	private int x;
	private int y;

	/**
	Constructor
	@param t Type, either 0, 1, or 2
	@param xcor The row index of the agent within the 2d array
	@param ycor The column index of the agent within the 2d array
	*/
	public Agent(int t,int xcor, int ycor){
		type=t;
		x=xcor;
		y=ycor;
		satisfied=true;
	}

	/**
	Accessor method for type
	@return int variable type
	*/
	public int getType(){
		return type;
	}

	/**
	Accessor method for satisfied
	@return boolean variable satisfied
	*/
	public boolean getSatisfied(){
		return satisfied;
	}

	/**
	Accessor method for row index x
	@return int variable x
	*/
	public int getX(){
		return x;
	}

	/**
	Accessor method for column index y
	@return int variable y
	*/
	public int getY(){
		return y;
	}

	/**
	Setter method for type
	@param t Integer indicating type (0, 1, or 2)
	*/
	public void setType(int t){
		type=t;
	}

	/**
	Setter method for satisfied
	@param s Boolean indicating whether satisfied or not
	*/
	public void setSatisfied(boolean s){
		satisfied=s;
	}

	/**
	Setter method for x
	@param xcor Integer indicating row index
	*/
	public void setX(int xcor) {
		x=xcor;
	}

	/**
	Setter method for y
	@param ycor Integer indicating column index
	*/
	public void setY(int ycor) {
		y=ycor;
	}

	/**
	Setter method for all four instance variables
	@param t Integer indicating type (0, 1, or 2)
	@param xcor Integer indicating row index
	@param ycor Integer indicating column index
	@param s Boolean indicating whether satisfied or not
	*/
	public void setEverything(int t, int xcor, int ycor, boolean s) {
		type=t;
		x=xcor;
		y=ycor;
		satisfied=s;
	}

	/**
	Method to draw a square for an agent
	@param x Double indicating x coordinate of the center of the square
	@param y Double indicating y coordinate of the center of the square
	@param h Double indicating half the height of the square
	*/
	public void drawSquare(double x, double y, double h) {
		if(type==0) {
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledSquare(x,y,h);
		}
		else if(type==1) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.filledSquare(x,y,h);
		}
		else{
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.filledSquare(x,y,h);
		}
	}
 
}