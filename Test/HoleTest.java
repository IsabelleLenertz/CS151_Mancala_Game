import org.junit.Test;

import static org.junit.Assert.*;

public class HoleTest {


    @Test
    public void getStones() {
        Hole hole = new Pit();
        hole.stoneMutator(4);
        assertEquals(4,hole.getStones());



    }

    @Test
    public void incrementStones() {
        Hole hole = new Pit();
        hole.stoneMutator(4);
        hole.incrementStones();
        assertEquals(5,hole.getStones());
    }

    @Test
    public void stoneMutator() {
        Hole hole = new Pit();
        hole.stoneMutator(4);
        hole.stoneMutator(4);
        assertEquals(8,hole.getStones());
    }
}