package h05;

/**
 * Main entry point in executing the program.
 */
public class Main {
    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        // TODO: H5.6
        Airspace.get().scanAirspace();
        var r1 = new Runway(2000);
        var r2 = new Runway(4000);
        var wb = new WeatherBalloon(99);
        wb.start();
        var t1 = new Tank(FuelType.JetA);
        var t2 = new Tank(FuelType.JetB);
        var tp = new TankerPlane("D-ABCD", 10000, FuelType.JetA, 1000);
        var pp = new PassengerPlane("GAG-67", 10000, FuelType.JetA, 1700, 5);

        t1.refuelPlane(pp);
        pp.board(100);
        pp.takeOff();

        Airspace.get().scanAirspace();

        var cp = new CargoPlane("D-AFFF", 8000, FuelType.JetB, 1500);
        cp.loadContainer(1000);
        t2.refuelPlane(cp);
        pp.disembark();

        Airspace.get().scanAirspace();

        cp.takeOff();
        cp.fly(1000);

        Airspace.get().scanAirspace();

        var comP = new CombinedPlane("D-ABBB", 9000, FuelType.AvGas, 10700, 5);
        tp.refuelPlane(comP);

        comP.board(30);
        comP.loadContainer(400);
        comP.takeOff();
        comP.fly(3000)

        ;

        Airspace.get().scanAirspace();

        r1.land(comP);
        r2.land(cp);

        Airspace.get().scanAirspace();

        wb.pop();

        Airspace.get().scanAirspace();

        Airspace.get().scanAirspace();
    }
}
