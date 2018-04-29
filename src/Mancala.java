/**
 * @author Yilin Zhao
 */
public class Mancala extends Hole{
	/**
	 * do nothing
	 * @return dummy number;
	 */
	@Override
	public int removeAllStones() {
		return 0;
	}

	/**
	 * do nothing
	 * @return dummy number
	 */
	@Override
	public int decrementStones() {
		return 0;
	}

	/**
	 * add stone to mancala
	 * @param stonesToAdd amount of stone to add
	 * @return total number of stone after adding
	 */
	public int addStones(int stonesToAdd) {
		this.stoneMutator(stonesToAdd+this.getStones());
		return this.getStones();
	}
}
