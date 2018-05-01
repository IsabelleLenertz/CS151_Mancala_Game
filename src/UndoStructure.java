/**
 * @author Yilin Zhao
 * @date 04/27/18
 * holds the most recent pits for undo
 *
 */
public class UndoStructure {
	private Hole[] holes;
	private PlayerEnum whoseTurn;
	
	public UndoStructure(){
		holes = new Hole[13];
	}
	
	/**
	 *
	 * @param newHoles the most recent set of holes
	 */
	public void setHoles(Hole[] holes){
		this.holes = holes;
	}
	
	/**
	 *
	 * @return return most recent set of holes
	 */
	public Hole[] getHoles(){
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
}
