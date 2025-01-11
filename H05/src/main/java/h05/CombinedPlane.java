package h05;

public class CombinedPlane extends PassengerPlane implements CarriesCargo {

    private CargoStack containers = new CargoStack();

    public CombinedPlane(String aircraftRegistration, int baseWeight, FuelType fuelType, double fuelCapacity, int crewCount) {
        super(aircraftRegistration, baseWeight, fuelType, fuelCapacity, crewCount);
    }

    @Override
    public void loadContainer(int cargoWeight) {
        containers.push(cargoWeight);
    }

    @Override
    public boolean hasFreightLoaded() {
        return !containers.empty();
    }

    @Override
    public int unloadNextContainer() {
        return containers.pop();
    }

    @Override
    protected double mass() {
        return super.mass() + containers.getSum();
    }
}
