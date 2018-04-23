import org.junit.Test;

import static org.junit.Assert.*;

public class PitTest {
    @Test
    public void removeAllStones() {
        Pit pit = new Pit();
        pit.stoneMutator(4);
        assertEquals(4,pit.removeAllStones());
        assertEquals(0,pit.getStones());

    }

    @Test
    public void decrementStones() {
        Pit pit = new Pit();
        pit.stoneMutator(3);
        assertEquals(2,pit.decrementStones());
        assertEquals(2,pit.getStones());

    }
}