import java.util.ArrayList;
import java.util.Stack;
import java. util.List;

/**
 * Class that solves maze problems with backtracking.
 * @author Jessica Kamman
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        // COMPLETE HERE FOR PROBLEM 1
    	if(x < 0 || y<0 || x>= maze.getNCols() || y >= maze.getNRows()) {
    		//return false if cell is out of bounds
    		return false; 
    	}
    	else if (maze.getColor(x, y).equals(PATH) || maze.getColor(x, y).equals(TEMPORARY) || maze.getColor(x, y).equals(BACKGROUND)) {
    		//return false if Cell is at the eand or on barrier
    		return false;
    	}
    	else if(x== maze.getNCols()-1 && y== maze.getNRows()-1) {
    		//cell is on path
    		maze.recolor(x, y, PATH);
    		//return ture and the maze will exit
    		return true;
    	}
    	else {
    		//add recursive case
    		//find a path from neighbor, and mark cells on path
    		maze.recolor(x, y, PATH);
    		if(findMazePath(x -1, y) || findMazePath(x + 1, y) || findMazePath(x, y-1) || findMazePath(x, y +1 )) {
    			return true;
    		}
    		else {
    			//else case will be use if the maze is a dead end
    			maze.recolor(x, y, TEMPORARY); 
    			return false;
    		}
    	}
   
    }

    // ADD METHOD FOR PROBLEM 2 HERE
    //Recursive algorithm that list of all the solutions to the maze is returned. 
    //if not solution is found, the resulting list should have the empty list as only one element returned. 
    
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y){
    	ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
    	Stack<PairInt> trace = new Stack<>();
    	findMazePathStackBased(0,0, result, trace);
    	return result;
    }
    
    
    //findMazePathsStackBased function
private void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    	if(x<0 || y<0 || x>= maze.getNCols() || y>=maze.getNRows()){
    		return;
  
    	}
    	else if(!maze.getColor(x, y).equals(NON_BACKGROUND)) {
    		return;

    }
    	else if(x==maze.getNCols()-1 && y==maze.getNRows()-1) {
    		trace.push(new PairInt(x,y));
    		ArrayList list = new ArrayList(trace);
    		result.add(list);
    		trace.pop();
 
    		
    	}
    	else {
    		maze.recolor(x, y , PATH);
    		trace.push(new PairInt(x,y));
    		findMazePathStackBased(x-1,y, result, trace);
    		findMazePathStackBased(x, y-1, result, trace);
    		findMazePathStackBased(x+1, y, result, trace);
    		findMazePathStackBased(x, y+1, result, trace);
    		trace.pop();
    		maze.recolor(x, y, NON_BACKGROUND);
    		
    	}
   
    	}
  
    
    
   
	// ADD METHOD FOR PROBLEM 3 HERE

    	public ArrayList<PairInt> findMazePathMin(int x, int y){
    		ArrayList<PairInt> result = new ArrayList<>();
    		int i=0, size;
    		ArrayList <PairInt> min = new ArrayList<>();
    		Stack<PairInt> trace = new Stack <>();
    		ArrayList<ArrayList<PairInt>> allMazePaths = findAllMazePaths(0,0);
    		//findMazePathMinHelper(0,0,result,trace);
    		int minimum= Integer.MAX_VALUE;
    		for(ArrayList<PairInt> list:allMazePaths) {
    			if(minimum> list.size()) {
    				minimum= list.size();
    				result=list;
    				
    			}
    		}
    		return result;
    	}
    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
