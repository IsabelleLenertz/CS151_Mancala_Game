
public class Mancala extends Hole{

	@Override
	public int removeAllStones() {
		return 0;
	}

	@Override
	public int decrementStones() {
		return 0;
	}

	public int addStones(int stonesToAdd) {
		this.stoneMutator(stonesToAdd);
		return this.getStones();
	}
}
