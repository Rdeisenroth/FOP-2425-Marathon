package h05;

public abstract class Plane implements Flying {
    private String aircraftRegistration;
    private int baseWeight;
    private FuelType fuelType;
    private double fuelCapacity;
    private double currentFuelLevel;
    public final double CONSUMPTION_PER_KM_KG = 1.1494e-4;

    @Override
    public String getIdentifier() {
        return aircraftRegistration;
    }

    public Plane(String aircraftRegistration, int baseWeight, FuelType fuelType, double fuelCapacity) {
        this.aircraftRegistration = aircraftRegistration;
        this.baseWeight = baseWeight;
        this.fuelType = fuelType;
        this.fuelCapacity = fuelCapacity;
        this.currentFuelLevel = 0;
    }

    public void refuel(double amount) {
        if (currentFuelLevel + amount > fuelCapacity) {
            currentFuelLevel = fuelCapacity;
            System.out.printf("The Tank of Plane %s has overflowed!", aircraftRegistration);
            return;
        }
        currentFuelLevel += amount;
    }

    protected abstract double mass();

    protected double getFuelConsumptionPerKilometer() {
        return mass() * CONSUMPTION_PER_KM_KG * fuelType.getConsumptionMultiplicator();
    }

    public void fly(double distance) {
        var verbrauch = getFuelConsumptionPerKilometer() * distance;
        if (verbrauch > currentFuelLevel) {
            System.out.printf("Plane %s does not have enough fuel to fly %s km.", aircraftRegistration, distance);
            return;
        }
        currentFuelLevel -= verbrauch;
        System.out.printf("Plane %s flew %s km and has %s liters of fuel left.", aircraftRegistration, distance, currentFuelLevel);
    }

    public void takeOff() {
        Airspace.get().register(this);
    }

    ;

    public void land() {
        Airspace.get().register(this);
    }

    ;

    public int getBaseWeight() {
        return baseWeight;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public double getCurrentFuelLevel() {
        return currentFuelLevel;
    }
}
