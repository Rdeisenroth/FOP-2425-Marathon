package h05;

import java.util.Arrays;

public class TankerPlane extends Plane implements Refuelling {

    private final double[] availableAmount = new double[FuelType.values().length];

    public TankerPlane(String aircraftRegistration, int baseWeight, FuelType fuelType, double fuelCapacity) {
        super(aircraftRegistration, baseWeight, fuelType, fuelCapacity);
    }

    @Override
    protected double mass() {
        return getBaseWeight() + Arrays.stream(availableAmount).sum();
    }

    @Override
    public void refuelPlane(Plane plane) {
        var missingFuel = plane.getFuelCapacity() - plane.getCurrentFuelLevel();
        plane.refuel(Math.min(missingFuel, availableAmount[plane.getFuelType().ordinal()]));
    }

    public void loadFuel(FuelType fuelType, double amount) {
        availableAmount[fuelType.ordinal()] += amount;
    }
}
