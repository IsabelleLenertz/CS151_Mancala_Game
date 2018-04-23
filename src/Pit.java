
public class Pit extends Hole {

	@Override
	public int removeAllStones() {
		int temp = this.getStones();
		this.stoneMutator(0-temp);
		return temp;
	}

	@Override
	public int decrementStones() {
		this.stoneMutator(-1);
		return this.getStones();
	}

}
