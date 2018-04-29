/**
 * @author Yilin Zhao
 *
 */
public class Pit extends Hole {
	/**
	 * remove all stones in pit
	 * @return number of stone before remove
	 */
	@Override
	public int removeAllStones() {
		int temp = this.getStones();
		this.stoneMutator(0);
		return temp;
	}

	/**
	 * take one stone from pit.
	 * @return number of stone after decrement
	 */
	@Override
	public int decrementStones() {
		this.stoneMutator(this.getStones()-1);
		return this.getStones();
	}

}
