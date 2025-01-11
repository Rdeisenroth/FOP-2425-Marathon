package h05;

public class PassengerPlane extends Plane implements CarriesPassengers {

    private int passengerCount;
    private int crewCount;
    protected final static char AVERAGE_PEOPLE_WEIGHT = 100;
    protected final static char AVERAGE_LUGGAGE_WEIGHT = 15;


    public PassengerPlane(String aircraftRegistration, int baseWeight, FuelType fuelType, double fuelCapacity, int crewCount) {
        super(aircraftRegistration, baseWeight, fuelType, fuelCapacity);
        this.crewCount = crewCount;
    }

    @Override
    protected double mass() {
        return AVERAGE_PEOPLE_WEIGHT * (getPassengerCount() + crewCount) + getPassengerCount() * AVERAGE_LUGGAGE_WEIGHT;
    }

    @Override
    public void board(int peopleCount) {
        passengerCount += peopleCount;
    }

    @Override
    public void disembark() {
        passengerCount = 0;
    }

    @Override
    public int getPassengerCount() {
        return passengerCount;
    }

}
