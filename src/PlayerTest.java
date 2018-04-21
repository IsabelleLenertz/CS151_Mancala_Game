import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {
	private Player player;

	/**
	 * Set up a player
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		Hole[] holes = new Hole[14];
		int i = 0;
		for (; i < 6; i++) {
			holes[i] = new Pit();
		}
		holes[i] = new Mancala();
		i++;
		for(;i < 13; i++) {
			holes[i] = new Pit();
		}
		holes[i] = new Mancala();
		
		Pit pits[] = new Pit[6];
		for(i = 0; i < 6; i++) {
			pits[i] = (Pit) holes[i];
		}

		Mancala mancala = (Mancala) holes[6];
		
		player = new Player(mancala, pits, holes);
	}

	@Test
	void testGetScore() {
		assertEquals(0, player.getScore());
	}

	@Test
	void testIsTurn() {
		assertFalse(player.isTurn());
	}

	@Test
	void testStartTurn() {
		player.startTurn();
		assertTrue(player.isTurn());
	}

	@Test
	void testPlay() {
		fail("Not yet implemented");
	}

}
