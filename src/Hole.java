/**
 * abstract class of pit and mancala
 * @author Yilin Zhao
 */
public abstract class Hole {
	private int stones;

	/**
	 *  set number of stones to 0
	 */
	public Hole() {
		stones = 0;

	}

	/**
	 * get number of stone in Hole
	 * @return number of stone
	 */
	public int getStones() {
		return this.stones;
	}

	public abstract int removeAllStones();

	/**
	 * add one stone to the hole
	 * @return dummy number
	 */
	public int incrementStones() {
		stones++;
		return 0;
	}


	public abstract int decrementStones();

	/**
	 * set number of stone to numberToAdd
	 * @param numberToAdd new number of stone in the Hole
	 */
	protected void stoneMutator(int numberToAdd) {
		stones = numberToAdd;
	}
	
} 