
public abstract class Hole {
	private int stones;
	
	public Hole() {
		stones = 0;

	}
	
	public int getStones() {
		return this.stones;
	}
	
	public abstract int removeAllStones();
	
	public int incrementStones() {
		stones++;
		return 0;
	}
	
	public abstract int decrementStones();
	
	
	protected void stoneMutator(int numberToAdd) {
		stones = numberToAdd;
	}
	
} 