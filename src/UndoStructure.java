/**
 * @author Yilin Zhao
 * @date 04/27/18
 * holds the most recent pits for undo
 *
 */
public class UndoStructure {
	private int[] holes; // list of numbers of stones in holes
	private PlayerEnum whoseTurn; 
	private int undoCount;// keeps count of player's undo times
	/**
	 * initial all holes to have 0 stone
	 * initial undo count to 0
	 */
	public UndoStructure(){
		holes = new int[14];
		undoCount = 0;
	}
	
	/**
	 *update set the array of holes
	 * @param newHoles the most recent set of holes
	 */
	public void setHoles(Hole[] newHoles){
		for (int i = 0; i < newHoles.length; i = i + 1) {
			holes[i] = newHoles[i].getStones();
		}
	}
	
	/**
	 * 
	 * @return return most recent set of holes
	 */
	public int[] getHoles(){
	    return holes;
	}
	
	/**
	 *
	 * @param p player that's taking turn
	 */
	public void setWhoseTurn(PlayerEnum p){
	    whoseTurn = p;
	}
	
	/**
	 *
	 * @return player that's taking turn
	 */
	public PlayerEnum getWhoseTurn() {
		return whoseTurn;
	}
	/**
	 * 
	 * @return undo count
	 */
	public int getUndoCount() {
		return undoCount;
	}
	/**
	 *  add undocount by 1
	 */
	public void incrementCount() {
		undoCount ++;
	}
	/**
	 * set undocount to 0
	 */
	public void resetUndoCount() {
		undoCount = 0;
	}
}
