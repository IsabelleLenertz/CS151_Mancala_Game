
public abstract class Hole {
	private int stones;
	
	public Hole() {
		// TODO method stub

	}
	
	public int getStones() {
		return this.stones;
	}
	
	public abstract int removeAllStones();
	
	public int incrementStones() {
		// TODO method stub
		return 0;
	}
	
	public abstract int decrementStones();
	
	
	protected void stoneMutator(int numberToAdd) {
		// TODO method stub
	}
	
}
