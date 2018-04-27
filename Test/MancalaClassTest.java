import org.junit.Test;

import static org.junit.Assert.*;

public class MancalaClassTest {

    @Test
    public void removeAllStones() {
        Mancala mancala = new Mancala();
        mancala.stoneMutator(4);
        assertEquals(0,mancala.removeAllStones());
        assertEquals(4,mancala.getStones());
    }

    @Test
    public void decrementStones() {
        Mancala mancala = new Mancala();
        mancala.stoneMutator(4);
        assertEquals(0,mancala.decrementStones());
        assertEquals(4,mancala.getStones());
    }

    @Test
    public void addStones() {
        Mancala mancala = new Mancala();
        mancala.stoneMutator(4);
        assertEquals(5,mancala.addStones(1));
        assertEquals(5,mancala.getStones());
    }
}