import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tester for the Player class
 * Depends on Hole, Pit, and Mancala classes
 * part of a group project
 * @author Isabelle Delmas
 * @Date: 04-21-2018
 * @Revision:	04-22 and 04-23-18		Reason: fixed initial bugs after Hole, Pit and Mancala classes were working properly
 *
 */
class PlayerTest {
    private Player player;
    Hole[] holes;

    /**
     * Set up a player
     */
    @BeforeEach
    void setUp() {
        holes = new Hole[14];
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
        // Set up a board with 4 stones in each holes
        int i = 0;
        for (; i < 6; i++) {
            for (int j = 0; j < 4; j++)
                holes[i].incrementStones();
        }
        i++;
        for(;i < 13; i++) {
            for (int j = 0; j < 4; j++)
                holes[i].incrementStones();
        }
        
        // Make sure the board is properly initiliazed
        assertEquals(4, holes[0].getStones());
        assertEquals(4, holes[1].getStones());
        assertEquals(4, holes[2].getStones());
        assertEquals(4, holes[3].getStones());
        assertEquals(4, holes[4].getStones());
        assertEquals(4, holes[5].getStones());
        assertEquals(0, holes[6].getStones());
        assertEquals(4, holes[7].getStones());
        assertEquals(4, holes[8].getStones());
        assertEquals(4, holes[9].getStones());
        assertEquals(4, holes[10].getStones());
        assertEquals(4, holes[11].getStones());
        assertEquals(4, holes[12].getStones());
        assertEquals(0, holes[13].getStones());

        // Allow the Player to play, using the hole #2
        player.startTurn();
        player.play(2);

        // Check if the board was properly updated
        assertEquals(4, holes[0].getStones());
        assertEquals(4, holes[1].getStones());
        assertEquals(0, holes[2].getStones());
        assertEquals(5, holes[3].getStones());
        assertEquals(5, holes[4].getStones());
        assertEquals(5, holes[5].getStones());
        assertEquals(1, holes[6].getStones());
        assertEquals(4, holes[7].getStones());
        assertEquals(4, holes[8].getStones());
        assertEquals(4, holes[9].getStones());
        assertEquals(4, holes[10].getStones());
        assertEquals(4, holes[11].getStones());
        assertEquals(4, holes[12].getStones());
        assertEquals(0, holes[13].getStones());

        // Check is the player's scores are up to date
        assertTrue(player.isTurn());
        assertEquals(1, player.getScore());

        // Player the second turn (player should have gotten a second turn)
        player.play(5);
        // Check if the board was properly updated
        assertEquals(4, holes[0].getStones());
        assertEquals(4, holes[1].getStones());
        assertEquals(0, holes[2].getStones());
        assertEquals(5, holes[3].getStones());
        assertEquals(5, holes[4].getStones());
        assertEquals(0, holes[5].getStones());
        assertEquals(2, holes[6].getStones());
        assertEquals(5, holes[7].getStones());
        assertEquals(5, holes[8].getStones());
        assertEquals(5, holes[9].getStones());
        assertEquals(5, holes[10].getStones());
        assertEquals(4, holes[11].getStones());
        assertEquals(4, holes[12].getStones());
        assertEquals(0, holes[13].getStones());

        // Check is the player's scores are up to date
        assertFalse(player.isTurn());
        assertEquals(2, player.getScore());

        // Changes the board to test corner cases
        // Check if the other player's mancala is skipped
        holes[0].stoneMutator(0);
        holes[1].stoneMutator(0);
        holes[2].stoneMutator(0);
        holes[3].stoneMutator(0);
        holes[4].stoneMutator(0);
        holes[5].stoneMutator(9);
        holes[6].stoneMutator(0);
        holes[7].stoneMutator(0);
        holes[8].stoneMutator(0);
        holes[9].stoneMutator(0);
        holes[10].stoneMutator(0);
        holes[11].stoneMutator(0);
        holes[12].stoneMutator(0);
        holes[13].stoneMutator(0);

        player.startTurn();
        player.play(5);
        // Check if the board was properly updated
        assertEquals(1, holes[0].getStones());
        assertEquals(0, holes[1].getStones());
        assertEquals(0, holes[2].getStones());
        assertEquals(0, holes[3].getStones());
        assertEquals(0, holes[4].getStones());
        assertEquals(0, holes[5].getStones());
        assertEquals(3, holes[6].getStones());
        assertEquals(1, holes[7].getStones());
        assertEquals(1, holes[8].getStones());
        assertEquals(1, holes[9].getStones());
        assertEquals(1, holes[10].getStones());
        assertEquals(0, holes[11].getStones());
        assertEquals(1, holes[12].getStones());
        assertEquals(0, holes[13].getStones());

        // Changes the board to test corner cases
        // Check if the other player's stones are stolen if the player ends in one of his empty pits
        holes[0].stoneMutator(2);
        holes[1].stoneMutator(0);
        holes[2].stoneMutator(0);
        holes[4].stoneMutator(0);
        holes[5].stoneMutator(0);
        holes[6].stoneMutator(0);
        holes[7].stoneMutator(0);
        holes[8].stoneMutator(0);
        holes[9].stoneMutator(0);
        holes[10].stoneMutator(4);
        holes[11].stoneMutator(0);
        holes[12].stoneMutator(0);
        holes[13].stoneMutator(0);

        player.startTurn();
        player.play(0);

        // Check if the board was properly updated
        assertEquals(0, holes[0].getStones());
        assertEquals(1, holes[1].getStones());
        assertEquals(0, holes[2].getStones());
        assertEquals(0, holes[3].getStones());
        assertEquals(0, holes[4].getStones());
        assertEquals(0, holes[5].getStones());
        assertEquals(5, holes[6].getStones());
        assertEquals(0, holes[7].getStones());
        assertEquals(0, holes[8].getStones());
        assertEquals(0, holes[9].getStones());
        assertEquals(0, holes[10].getStones());
        assertEquals(0, holes[11].getStones());
        assertEquals(0, holes[12].getStones());
        assertEquals(0, holes[13].getStones());
    }

}
