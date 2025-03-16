package h09;

import h09.abilities.Swims;
import h09.animals.Animal;
import h09.animals.Lion;
import h09.animals.Penguin;
import org.junit.jupiter.api.Test;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * An example JUnit test class.
 */
public class EnclosureTest {

    @Test
    @StudentImplementationRequired("H9.5.1")
    public void testForEach() {
        Lion lena = new Lion("Lena", 1);
        Lion liob = new Lion("Liob", 2);
        Lion lic = new Lion("Lic", 3);

        GroundEnclosure<Lion> enclosure = new GroundEnclosure<>();
        enclosure.getStack().push(lena);
        enclosure.getStack().push(liob);
        enclosure.getStack().push(lic);

        assertTrue(lena.isHungry());
        assertTrue(liob.isHungry());
        assertTrue(lic.isHungry());

        enclosure.forEach(Animal::eat);

        assertFalse(lena.isHungry());
        assertFalse(liob.isHungry());
        assertFalse(lic.isHungry());
    }


    @Test
    @StudentImplementationRequired("H9.5.2")
    public void testFilter() {
        Penguin penga = new Penguin("Penga", 1, -5);
        Penguin pengb = new Penguin("Penb", 2, -5);

        WaterEnclosure<Penguin> enclosure = new WaterEnclosure<>();
        enclosure.getStack().push(penga);
        enclosure.getStack().push(pengb);

        penga.eat();

        assertFalse(penga.isHungry());
        assertTrue(pengb.isHungry());
        assertFalse(penga.getElevation() > Swims.HIGH_ELEVATION);
        assertFalse(pengb.getElevation() > Swims.HIGH_ELEVATION);

        enclosure
            .filterFunc(WaterEnclosure::new, Animal::isHungry)
            .filterFunc(WaterEnclosure::new, Enclosure.SWIMS_AT_LOW_ELEVATION)
            .forEach(Swims::swimUp);

        assertFalse(penga.getElevation() > Swims.HIGH_ELEVATION);
        assertTrue(pengb.getElevation() > Swims.HIGH_ELEVATION);

        enclosure
            .filterFunc(WaterEnclosure::new, Animal::isHungry)
            .forEach(Enclosure.EAT_AND_SINK());

        assertFalse(penga.getElevation() > Swims.HIGH_ELEVATION);
        assertFalse(pengb.getElevation() > Swims.HIGH_ELEVATION);
        assertFalse(penga.isHungry());
        assertFalse(pengb.isHungry());

    }
}
