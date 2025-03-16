package h09;

import h09.animals.Animal;
import h09.animals.Fish;
import h09.animals.Penguin;
import org.junit.jupiter.api.Test;

public class ExampleZooTest {

    @Test
    public void runExampleZoo() {
        // can be used after H9.2
        System.out.println("\nCreating fish enclosure...");
        WaterEnclosure<Fish> fishEnclosure = new WaterEnclosure<>();
        fishEnclosure.getStack().push(new Fish("Fishaaa", 1));
        fishEnclosure.getStack().push(new Fish("Fishbb", 2));
        fishEnclosure.getStack().push(new Fish("Fisch", 3));

        // can be used after H9.3.1
        System.out.println("Get fish elevation...");
        fishEnclosure.forEach(fish -> System.out.println(fish.getName() + ": " + fish.getElevation()));

        System.out.println("\nLet fish swim up...");
        fishEnclosure.forEach(Fish::swimUp);
        fishEnclosure.forEach(Fish::swimUp);
        fishEnclosure.forEach(Fish::swimUp);

        System.out.println("\nFeed the fish...");
        fishEnclosure.feed();

        System.out.println("\nGet fish mean elevation...");
        System.out.println("Mean elevation: " + fishEnclosure.getMeanElevation());

        System.out.println("\nCreating penguin enclosure...");
        GroundEnclosure<Penguin> penguinGroundEnclosure = new GroundEnclosure<>();
        penguinGroundEnclosure.getStack().push(new Penguin("Penga", 5));
        penguinGroundEnclosure.getStack().push(new Penguin("Pengb", 10));
        penguinGroundEnclosure.getStack().push(new Penguin("Pengc", 10));
        penguinGroundEnclosure.getStack().push(new Penguin("Pengd", 30));
        penguinGroundEnclosure.getStack().push(new Penguin("Penge", 30));

        System.out.println("\nMigrating the Penguins to a WaterEnclosure...");
        WaterEnclosure<Penguin> penguinWaterEnclosure = new WaterEnclosure<>();
        while (penguinGroundEnclosure.getStack().size() > 0) {
            Penguin penguin = penguinGroundEnclosure.getStack().pop();
            penguinWaterEnclosure.getStack().push(penguin);
        }


        // can be used after H9.3.2
        System.out.println("\nRemoving old penguins...");
        penguinWaterEnclosure.filterObj(Enclosure.IS_OLD.negate());
        System.out.println(penguinWaterEnclosure.getStack().size() + " young penguin(s) left.");

        System.out.println("\nSleep /age again");
        penguinWaterEnclosure.forEach(Animal::sleep);

        // can be used after H9.3.3
        System.out.println("\nLooking for hungry and old Penguins...");
        System.out.println(penguinWaterEnclosure.countHungry() + " hungry penguins.");
        System.out.println(penguinWaterEnclosure.filterFunc(WaterEnclosure::new, Enclosure.IS_OLD).getStack().size() + " old penguins.");

        System.out.println("\nFeed old penguins and let them sleep...");
        penguinWaterEnclosure.filterFunc(WaterEnclosure::new, Enclosure.IS_OLD).forEach(Enclosure.FEED_AND_SLEEP);
    }
}
