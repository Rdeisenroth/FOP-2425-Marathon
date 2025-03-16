package h09;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import h09.abilities.Swims;
import h09.animals.Animal;
import h09.animals.Lion;
import h09.animals.Penguin;
import org.junit.jupiter.api.Test;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * An example JUnit test class.
 */
public class EnclosureTest {

    @Test
    @StudentImplementationRequired("H9.5.1")
    public void testForEach() {
        // crating lions
        Lion lia = new Lion("Lia", 1);
        Lion lib = new Lion("Lib", 1);
        Lion lic = new Lion("Lic", 1);

        // Creating lion enclosure...
        GroundEnclosure<Lion> lionEnclosure = new GroundEnclosure<>();
        lionEnclosure.getStack().push(lia);
        lionEnclosure.getStack().push(lib);
        lionEnclosure.getStack().push(lic);

//        lionEnclosure.forEach(l -> {
//            assertTrue(l.isHungry());
//        });
        assertTrue(lia.isHungry());
        assertTrue(lib.isHungry());
        assertTrue(lic.isHungry());

        lionEnclosure.forEach(Lion::eat);

//        lionEnclosure.forEach(l -> {
//            assertFalse(l.isHungry());
//        });
        assertFalse(lia.isHungry());
        assertFalse(lib.isHungry());
        assertFalse(lic.isHungry());
    }


    @Test
    @StudentImplementationRequired("H9.5.2")
    public void testFilter() {
        // crating fish
        Penguin skipper = new Penguin("Skipper", 10, -5);
        Penguin riko = new Penguin("Riko", 10, -5);

        // Creating fish enclosure...
        WaterEnclosure<Penguin> penguinnclosure = new WaterEnclosure<>();
        penguinnclosure.getStack().push(skipper);
        penguinnclosure.getStack().push(riko);

        skipper.eat();

        assertFalse(skipper.isHungry());
        assertTrue(riko.isHungry());

//        penguinnclosure.forEach(p -> {
//            assertTrue(p.getElevation() < Swims.HIGH_ELEVATION);
//        });
        assertTrue(skipper.getElevation() < Swims.HIGH_ELEVATION);
        assertTrue(riko.getElevation() < Swims.HIGH_ELEVATION);

        penguinnclosure.filterFunc(WaterEnclosure::new, Animal::isHungry)
            .filterFunc(WaterEnclosure::new, Enclosure.SWIMS_AT_LOW_ELEVATION)
            .forEach(Penguin::swimUp);

        assertTrue(riko.getElevation() >= Swims.HIGH_ELEVATION);
        assertTrue(skipper.getElevation() < Swims.HIGH_ELEVATION);

        penguinnclosure.filterFunc(WaterEnclosure::new, Animal::isHungry)
            .forEach(Enclosure.EAT_AND_SINK());

//        penguinnclosure.forEach(p -> {
//            assertTrue(p.getElevation() < Swims.HIGH_ELEVATION);
//        });
        assertTrue(skipper.getElevation() < Swims.HIGH_ELEVATION);
        assertTrue(riko.getElevation() < Swims.HIGH_ELEVATION);
        assertFalse(skipper.isHungry());
        assertFalse(riko.isHungry());
    }
}
