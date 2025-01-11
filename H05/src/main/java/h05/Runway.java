package h05;

public class Runway {
    private final int runwayLength;

    public Runway(int runwayLength) {
        this.runwayLength = runwayLength;
    }

    public int getRunwayLength() {
        return runwayLength;
    }

    public static double calculateLandingDistance(double mass) {
        return mass / 40;
    }

    public boolean canLand(Plane plane) {
        return calculateLandingDistance(plane.mass()) <= runwayLength;
    }

    public void land(Plane plane) {
        if(canLand(plane)) {
            plane.land();
            System.out.printf("Plane %s has landed successfully.", plane.getIdentifier());
        } else {
            System.out.printf("Plane %s could not land. The runway is too short.", plane.getIdentifier());
        }
    }
}
