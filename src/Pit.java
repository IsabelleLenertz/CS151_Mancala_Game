
public class Pit extends Hole {

	@Override
	public int removeAllStones() {
		int temp = this.getStones();
		this.stoneMutator(0);
		return temp;
	}

	@Override
	public int decrementStones() {
		this.stoneMutator(this.getStones()-1);
		return this.getStones();
	}

}
