package h05;

public class Tank implements Refuelling{
    private final FuelType fuelType;

    public Tank(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public void refuelPlane(Plane plane) {
        if(plane.getFuelType() == fuelType) {
            var missingFuel = plane.getFuelCapacity() - plane.getCurrentFuelLevel();
            plane.refuel(missingFuel);
        } else {
            System.out.println("Diese Nachricht beschreibt mit einem selbst ausgedachten String, dass das Auftanken nicht geklappt hat.");
        }
    }
}
