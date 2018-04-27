/**
 * @author Yilin Zhao
 * @date 04/27/18
 * holds the most recent pits for undo
 *
 */
public class UndoStruct {
	int[] holes;
    PlayerEnum whoseTurn;
    public UndoStruct(){
        holes = new int[13];

    }

    /**
     *
     * @param newHoles the most recent set of holes
     */
    public void setHoles(int[] newHoles){
        holes = newHoles;

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
}
