/**
 * @author Yilin Zhao
 * @date 04/27/18
 * holds the most recent pits for undo
 *
 */
public class UndoStructure {
	private int[] holes;
	private PlayerEnum whoseTurn;
	private int undoCount;
	
	public UndoStructure(){
		holes = new int[14];
		undoCount = 0;
	}
	
	/**
	 *
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
	
	public int getUndoCount() {
		return undoCount;
	}
	
	public void incrementCount() {
		undoCount ++;
	}
	
	public void resetUndoCount() {
		undoCount = 0;
	}
}
