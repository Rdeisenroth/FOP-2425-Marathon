package h05;

public abstract class Plane implements Flying {

    private static final double CONSUMPTION_PER_KM_KG = 1.1494e-4;
    private final String  aircraftRegistration;

    protected final int baseWeight;

    private double currentFuelLevel;

    private final FuelType fuelType;
    private final double fuelCapacity;

    public Plane(String aircraftRegistration, int baseWeight, FuelType fuelType, double fuelCapacity){
        this.aircraftRegistration = aircraftRegistration;
        this.baseWeight = baseWeight;
        this.fuelType = fuelType;
        this.currentFuelLevel = 0;
        this.fuelCapacity = fuelCapacity;
    }


    protected abstract double mass();

    protected double getFuelConsumptionPerKilometer(){
        return CONSUMPTION_PER_KM_KG * mass() * fuelType.getConsumptionMultiplicator();
    }

    public void takeOff(){
        Airspace.get().register(this);
    }

    public void land(){
        Airspace.get().deregister(this);
    }

    public void fly(double distance){
        double neededFuel = distance * getFuelConsumptionPerKilometer();

        // if the plane does not have enough fuel to fly the distance, it will not fly
        if(neededFuel > currentFuelLevel){
            System.out.println("Plane " + aircraftRegistration + " does not have enough fuel to fly " + distance + " km.");
            return;
        }

        currentFuelLevel -= neededFuel;

        System.out.println("Plane " + aircraftRegistration + " flew " + distance + " km and has " + currentFuelLevel + " liters of fuel left.");
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

    public void refuel(double amount){
        currentFuelLevel += amount;

        if(currentFuelLevel > fuelCapacity){
            currentFuelLevel = fuelCapacity;
            System.out.println("The Tank of Plane " + aircraftRegistration + " has overflowed!");
        }
    }

    @Override
    public String getIdentifier(){
        return aircraftRegistration;
    }
}
