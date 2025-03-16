package h09.animals;

/**
 * An object of a class {@link Animal} represents an animal.
 */
public class Animal {
    /**
     * Name of the animal.
     */
    protected String name;
    /**
     * Age of the animal.
     */
    private int age;

    /**
     * A 'true' value represents that the animal is hungry.
     */
    private boolean isHungry = true;

    /**
     * Constructs an Animal with the given name and age.
     *
     * @param name the name of the animal
     * @param age  the age of the animal
     */
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * @return the name of the animal
     */
    public String getName() {
        return name;
    }

    /**
     * Lets the animal eat. This prints a message to the system output noting that the animal has eaten and sets the
     * animal to not hungry.
     */
    public void eat() {
        System.out.println(name + " ate...");
        isHungry = false;
    }

    /**
     * Lets the animal sleep. This prints a message to the system output noting that the animal has eaten. It also
     * increases the age of the animal and sets the animal to hungry.
     */
    public void sleep() {
        System.out.println(name + " slept...");
        isHungry = true;
        age++;
    }

    /**
     * @return true if the animal is hungry; false otherwise
     */
    public boolean isHungry() {
        return isHungry;
    }

    /**
     * @return the age of the animal
     */
    public int getAge() {
        return age;
    }
}
