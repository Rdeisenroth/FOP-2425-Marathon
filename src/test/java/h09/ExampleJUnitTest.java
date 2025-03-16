package h09;

import h09.abilities.Swims;
import h09.animals.Fish;
import h09.animals.Lion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * An example JUnit test class.
 */
public class ExampleJUnitTest {

    @Test
    public void testAddition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testGroundEnclosure() {
        // crating lions
        Lion lia = new Lion("Lia", 1);
        Lion lib = new Lion("Lib", 1);
        Lion lic = new Lion("Lic", 1);

        // Creating lion enclosure...
        GroundEnclosure<Lion> lionEnclosure = new GroundEnclosure<>();
        lionEnclosure.getStack().push(lia);
        lionEnclosure.getStack().push(lib);
        lionEnclosure.getStack().push(lic);

        // Counting legs of lions...
        assertEquals(3 * 4, lionEnclosure.countLegs());

        // Feed the lions...
        assertTrue(lia.isHungry());
        lionEnclosure.feed();
        assertFalse(lia.isHungry());
    }

    @Test
    public void testWaterEnclosure() {
        // crating fish
        Fish fisha = new Fish("Fishaaa", 1);
        Fish fib = new Fish("Fishbb", 1);
        Fish fisch = new Fish("Fisch", 1);

        // Creating fish enclosure...
        WaterEnclosure<Fish> fishEnclosure = new WaterEnclosure<>();
        fishEnclosure.getStack().push(fisha);
        fishEnclosure.getStack().push(fib);
        fishEnclosure.getStack().push(fisch);

        // Check mean elevation / hungry
        float expected1 = Swims.MAX_ELEVATION -
            ((Swims.MAX_ELEVATION - Swims.MIN_ELEVATION) / (fisha.getName().length() + 1)
                + (Swims.MAX_ELEVATION - Swims.MIN_ELEVATION) / (fib.getName().length() + 1)
                + (Swims.MAX_ELEVATION - Swims.MIN_ELEVATION) / (fisch.getName().length() + 1)) / 3;
        assertEquals(expected1, fishEnclosure.getMeanElevation());
        assertTrue(fisch.isHungry());

        // feed the fish (they swim down, when they have eaten)
        fishEnclosure.feed();

        // Check mean elevation / hungry
        float expected2 = Swims.MAX_ELEVATION -
            ((Swims.MAX_ELEVATION - Swims.MIN_ELEVATION) / (fisha.getName().length() + 1)
                + (Swims.MAX_ELEVATION - Swims.MIN_ELEVATION) / (fib.getName().length() + 1)
                + (Swims.MAX_ELEVATION - Swims.MIN_ELEVATION) / (fisch.getName().length() + 1)) / 3;
        assertEquals(expected2, fishEnclosure.getMeanElevation());
        assertFalse(fisch.isHungry());
    }
}
