import org.junit.Test;

import static org.junit.Assert.*;

public class UndoStructureTest {
	@Test
	public void checkStructureIndependence() {
		// This test is highly contrived.
		UndoStructure us = new UndoStructure();
		
		// Make a completely empty Hole[] with no null instances.
		Hole[] holes = new Hole[14];
		
		for (int i = 0; i < 6; i = i + 1) {
			holes[i] = new Pit();
			holes[i + 7] = new Pit();
		}
		
		holes[6] = new Mancala();
		holes[13] = new Mancala();
		
		// Our test pits
		Pit a = new Pit();
		
		a.incrementStones();
		
		Pit b = new Pit();
		
		b.incrementStones();
		b.incrementStones();
		
		// Put the test pits into the Hole[] we will be saving.
		holes[0] = a;
		holes[1] = b;
		
		// Check they were loaded to holes.
		assertEquals(1, holes[0].getStones()); // Pit a
		assertEquals(2, holes[1].getStones()); // Pit b
		
		// Save the holes. Not just their references.
		us.setHoles(holes);
		
		// Update test Pits a and b through references.
		holes[0].decrementStones();
		holes[1].decrementStones();
		
		// Now we get the earlier copy made before the references were updated.
		int[] copy = us.getHoles(); // Should be different than holes now
		
		// The references were updated
		assertEquals(0, a.getStones()); // Pit a
		assertEquals(1, b.getStones()); // Pit b
		
		// The saved pits were not (i.e. the previous state was preserved and can be duplicated)
		assertEquals(1, copy[0]); // Pit a clone
		assertEquals(2, copy[1]); // Pit b clone
	}
}
