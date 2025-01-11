package h05;

import org.sourcegrade.jagr.api.rubric.*;
import org.sourcegrade.jagr.api.testing.RubricConfiguration;
import org.tudalgo.algoutils.transform.SolutionMergingClassTransformer;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSet;

import static org.tudalgo.algoutils.tutor.general.jagr.RubricUtils.criterion;

@SuppressWarnings("unused")
public class H05_RubricProvider implements RubricProvider {

    private static final Criterion H5_1_1 = Criterion.builder()
        .shortDescription("H5.1.1 | Flying Interface")
        .maxPoints(1)
        .addChildCriteria(
            criterion("Das Flying-Interface ist korrekt implementiert.",
                JUnitTestRef.ofMethod(() -> FlyingTest.class.getDeclaredMethod("testHeader")),
                JUnitTestRef.ofMethod(() -> FlyingTest.class.getDeclaredMethod("testMethods")))
        )
        .build();

    private static final Criterion H5_1_2 = Criterion.builder()
        .shortDescription("H5.1.2 | FuelType")
        .maxPoints(2)
        .addChildCriteria(
            criterion("Das FuelType-Enum ist korrekt implementiert und enthält die richtigen Werte.",
                JUnitTestRef.ofMethod(() -> FuelTypeTest.class.getDeclaredMethod("testHeader")),
                JUnitTestRef.ofMethod(() -> FuelTypeTest.class.getDeclaredMethod("testFields")),
                JUnitTestRef.ofMethod(() -> FuelTypeTest.class.getDeclaredMethod("testEnumConstants", JsonParameterSet.class))),
            criterion("Der Konstruktor des FuelType-Enums ist korrekt implementiert.",
                JUnitTestRef.ofMethod(() -> FuelTypeTest.class.getDeclaredMethod("testConstructor")))
        )
        .build();

    private static final Criterion H5_1_3 = Criterion.builder()
        .shortDescription("H5.1.3 | Flugzeug")
        .maxPoints(6)
        .addChildCriteria(
            criterion("Das Interface ist korrekt in die Flugzeugklasse implementiert.",
                JUnitTestRef.ofMethod(() -> PlaneTest.class.getDeclaredMethod("testHeader"))),
            criterion("Die Flugzeugklasse hat die richtigen Attribute.",
                JUnitTestRef.ofMethod(() -> PlaneTest.class.getDeclaredMethod("testFields"))),
            criterion("Methode mass funktioniert korrekt.",
                JUnitTestRef.ofMethod(() -> PlaneTest.class.getDeclaredMethod("testMass"))),
            criterion("Methode refuel gibt die richtige Nachricht aus.",
                JUnitTestRef.ofMethod(() -> PlaneTest.class.getDeclaredMethod("testRefuel"))),
            criterion("Methode getFuelConsumptionPerKilometer ist korrekt implementiert.",
                JUnitTestRef.ofMethod(() -> PlaneTest.class.getDeclaredMethod("testGetFuelConsumptionPerKilometer"))),
            criterion("Die Methoden fly und takeOff sind korrekt implementiert.",
                JUnitTestRef.ofMethod(() -> PlaneTest.class.getDeclaredMethod("testFly")),
                JUnitTestRef.ofMethod(() -> PlaneTest.class.getDeclaredMethod("testTakeOff")))
        )
        .build();

    private static final Criterion H5_1_4 = Criterion.builder()
        .shortDescription("H5.1.4 | Wetterballon")
        .maxPoints(2)
        .addChildCriteria(
            criterion("Die WeatherBalloon-Klasse ist korrekt implementiert.",
                JUnitTestRef.ofMethod(() -> WeatherBalloonTest.class.getDeclaredMethod("testHeader"))),
            criterion("Die Methoden der Klasse WeatherBalloon-Klasse sind korrekt implementiert.",
                JUnitTestRef.ofMethod(() -> WeatherBalloonTest.class.getDeclaredMethod("testStart")),
                JUnitTestRef.ofMethod(() -> WeatherBalloonTest.class.getDeclaredMethod("testPop")),
                JUnitTestRef.ofMethod(() -> WeatherBalloonTest.class.getDeclaredMethod("testGetIdentifier")))
        )
        .build();

    private static final Criterion H5_1 = Criterion.builder()
        .shortDescription("H5.1 | Basis")
        .addChildCriteria(H5_1_1, H5_1_2, H5_1_3, H5_1_4)
        .build();

    private static final Criterion H5_2_1 = Criterion.builder()
        .shortDescription("H5.2.1 | CarriesPassengers Interface")
        .maxPoints(4)
        .addChildCriteria(
            criterion("Das CarriesPassengers-Interface ist korrekt implementiert.",
                JUnitTestRef.ofMethod(() -> CarriesPassengersTest.class.getDeclaredMethod("testClassHeader")),
                JUnitTestRef.ofMethod(() -> CarriesPassengersTest.class.getDeclaredMethod("testMethodHeaders"))),
            criterion("Die PassengerPlane-Klasse implementiert das CarriesPassengers-Interface korrekt.",
                JUnitTestRef.ofMethod(() -> PassengerPlaneTest.class.getDeclaredMethod("testClassHeader"))),
            criterion("Die Methoden board, disembark und getPassengerCount sind korrekt implementiert.",
                JUnitTestRef.ofMethod(() -> PassengerPlaneTest.class.getDeclaredMethod("testBoard")),
                JUnitTestRef.ofMethod(() -> PassengerPlaneTest.class.getDeclaredMethod("testDisembark")),
                JUnitTestRef.ofMethod(() -> PassengerPlaneTest.class.getDeclaredMethod("testGetPassengerCount"))),
            criterion("Die Konstanten und der Konstruktor sind korrekt implementiert.",
                JUnitTestRef.ofMethod(() -> PassengerPlaneTest.class.getDeclaredMethod("testConstants")),
                JUnitTestRef.ofMethod(() -> PassengerPlaneTest.class.getDeclaredMethod("testConstructor")))
        )
        .build();

    private static final Criterion H5_2_2 = Criterion.builder()
        .shortDescription("H5.2.2 | CarriesCargo Interface")
        .maxPoints(4)
        .addChildCriteria(
            criterion("Das CarriesCargo-Interface ist korrekt implementiert.",
                JUnitTestRef.ofMethod(() -> CarriesCargoTest.class.getDeclaredMethod("testClassHeader")),
                JUnitTestRef.ofMethod(() -> CarriesCargoTest.class.getDeclaredMethod("testMethodHeaders"))),
            criterion("Die CargoPlane-Klasse implementiert das CarriesCargo-Interface korrekt.",
                JUnitTestRef.ofMethod(() -> CargoPlaneTest.class.getDeclaredMethod("testClassHeader"))),
            criterion("Die Masse-Methode und der Konstruktor sind korrekt implementiert.",
                JUnitTestRef.ofMethod(() -> CargoPlaneTest.class.getDeclaredMethod("testConstructor")),
                JUnitTestRef.ofMethod(() -> CargoPlaneTest.class.getDeclaredMethod("testMass"))),
            criterion("Die Methoden loadContainer, hasFreightLoaded und unloadNextContainer sind korrekt implementiert.",
                JUnitTestRef.ofMethod(() -> CargoPlaneTest.class.getDeclaredMethod("testLoadContainer")),
                JUnitTestRef.ofMethod(() -> CargoPlaneTest.class.getDeclaredMethod("testHasFreightLoaded")),
                JUnitTestRef.ofMethod(() -> CargoPlaneTest.class.getDeclaredMethod("testUnloadNextContainer")))
        )
        .build();

    private static final Criterion H5_2_3 = Criterion.builder()
        .shortDescription("H5.2.3 | Kombiniertes Flugzeug")
        .maxPoints(2)
        .addChildCriteria(
            criterion("Die CombinedPlane-Klasse implementiert die Interfaces CarriesPassengers und CarriesCargo korrekt.",
                JUnitTestRef.ofMethod(() -> CombinedPlaneTest.class.getDeclaredMethod("testClassHeader"))),
            criterion("Die Klasse ist vollständig korrekt.",
                JUnitTestRef.ofMethod(() -> CombinedPlaneTest.class.getDeclaredMethod("testClassHeader")),
                JUnitTestRef.ofMethod(() -> CombinedPlaneTest.class.getDeclaredMethod("testConstructor")))
        )
        .build();

    private static final Criterion H5_2 = Criterion.builder()
        .shortDescription("H5.2 | Basis")
        .addChildCriteria(H5_2_1, H5_2_2, H5_2_3)
        .build();

    private static final Criterion H5_3_1 = Criterion.builder()
        .shortDescription("H5.3.1 | Betankung")
        .maxPoints(1)
        .addChildCriteria(
            criterion("Das Refuelling-Interface ist korrekt implementiert.",
                JUnitTestRef.ofMethod(() -> RefuellingTest.class.getDeclaredMethod("testClassHeader")),
                JUnitTestRef.ofMethod(() -> RefuellingTest.class.getDeclaredMethod("testMethodHeaders")))
        )
        .build();

    private static final Criterion H5_3_2 = Criterion.builder()
        .shortDescription("H5.3.2 | Tank")
        .maxPoints(2)
        .addChildCriteria(
            criterion("Die Tank-Klasse ist korrekt deklariert und enthält die richtigen Attribute.",
                JUnitTestRef.ofMethod(() -> TankTest.class.getDeclaredMethod("testClassHeader")),
                JUnitTestRef.ofMethod(() -> TankTest.class.getDeclaredMethod("testFields"))),
            criterion("Die Methode refuelPlane ist korrekt implementiert.",
                JUnitTestRef.ofMethod(() -> TankTest.class.getDeclaredMethod("testRefuelPlane")),
                JUnitTestRef.ofMethod(() -> TankTest.class.getDeclaredMethod("testRefuelPlane_ErrorMessage")))
        )
        .build();

    private static final Criterion H5_3_3 = Criterion.builder()
        .shortDescription("H5.3.3 | Tanker-Flugzeug")
        .maxPoints(3)
        .addChildCriteria(
            criterion("Der Kopf der TankerPlane-Klasse sowie das Attribut availableAmount und der Konstruktor sind korrekt implementiert.",
                JUnitTestRef.ofMethod(() -> TankerPlaneTest.class.getDeclaredMethod("testClassHeader")),
                JUnitTestRef.ofMethod(() -> TankerPlaneTest.class.getDeclaredMethod("testFields")),
                JUnitTestRef.ofMethod(() -> TankerPlaneTest.class.getDeclaredMethod("testConstructor"))),
            criterion("Die Methoden loadFuel und mass sind korrekt implementiert.",
                JUnitTestRef.ofMethod(() -> TankerPlaneTest.class.getDeclaredMethod("testLoadFuel")),
                JUnitTestRef.ofMethod(() -> TankerPlaneTest.class.getDeclaredMethod("testMass")),
                JUnitTestRef.ofMethod(() -> TankerPlaneTest.class.getDeclaredMethod("testRefuelPlane"))),
            criterion("Die Methode refuelPlane ist korrekt implementiert.",
                JUnitTestRef.ofMethod(() -> TankerPlaneTest.class.getDeclaredMethod("testRefuelPlane")))
        )
        .build();

    private static final Criterion H5_3 = Criterion.builder()
        .shortDescription("H5.3 | Tanken")
        .addChildCriteria(H5_3_1, H5_3_2, H5_3_3)
        .build();

    private static final Criterion H5_4 = Criterion.builder()
        .shortDescription("H5.4 | Start- und Landebahn")
        .maxPoints(2)
        .addChildCriteria(
            criterion("Die Runway-Klasse ist korrekt deklariert und enthält die richtigen Attribute.",
                JUnitTestRef.ofMethod(() -> RunwayTest.class.getDeclaredMethod("testClassHeader")),
                JUnitTestRef.ofMethod(() -> RunwayTest.class.getDeclaredMethod("testFields"))),
            criterion("Die Methode land ist korrekt implementiert.",
                JUnitTestRef.ofMethod(() -> RunwayTest.class.getDeclaredMethod("testLand", boolean.class)))
        )
        .build();

    private static final Criterion H5_5 = Criterion.builder()
        .shortDescription("H5.5 | Luftraum")
        .maxPoints(1)
        .addChildCriteria(
            criterion("Die Methode scanAirspace ist korrekt implementiert.",
                JUnitTestRef.ofMethod(() -> AirspaceTest.class.getDeclaredMethod("testScanAirspace_Empty")),
                JUnitTestRef.ofMethod(() -> AirspaceTest.class.getDeclaredMethod("testScanAirspace_CargoPlane")),
                JUnitTestRef.ofMethod(() -> AirspaceTest.class.getDeclaredMethod("testScanAirspace_PassengerPlane")))
        )
        .build();

    private static final Criterion H5_6 = Criterion.builder()
        .shortDescription("H5.6 | Spielwiese")
        .maxPoints(2)
        .addChildCriteria(
            criterion("Der Luftraum ist bis nach dem Start von \"GAG-67\" korrekt.",
                JUnitTestRef.ofMethod(() -> MainTest.class.getDeclaredMethod("testMain_FirstScans", JsonParameterSet.class))),
            criterion("Der Luftraum ist bei jedem Scan korrekt.",
                JUnitTestRef.ofMethod(() -> MainTest.class.getDeclaredMethod("testMain_AllScans", JsonParameterSet.class)))
        )
        .build();

    @Override
    public Rubric getRubric() {
        return Rubric.builder()
            .title("H05 | FOP und Fort: Abheben im Flugzeug")
            .addChildCriteria(H5_1, H5_2, H5_3, H5_4, H5_5, H5_6)
            .build();
    }

    @Override
    public void configure(RubricConfiguration configuration) {
        configuration.addTransformer(() -> new SolutionMergingClassTransformer.Builder("h05")
            .addSolutionClass("h05.Airspace")
            .addSolutionClass("h05.CargoPlane")
            .addSolutionClass("h05.CargoStack")
            .addSolutionClass("h05.CarriesCargo")
            .addSolutionClass("h05.CarriesPassengers")
            .addSolutionClass("h05.CombinedPlane")
            .addSolutionClass("h05.Flying")
            .addSolutionClass("h05.FuelType")
            .addSolutionClass("h05.Main")
            .addSolutionClass("h05.PassengerPlane")
            .addSolutionClass("h05.Plane")
            .addSolutionClass("h05.Refuelling")
            .addSolutionClass("h05.Runway")
            .addSolutionClass("h05.Tank")
            .addSolutionClass("h05.TankerPlane")
            .addSolutionClass("h05.WeatherBalloon")
            .setSimilarity(0.80)
            .build());
    }
}
