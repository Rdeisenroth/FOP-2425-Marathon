package h09;

import org.sourcegrade.jagr.api.rubric.Criterion;
import org.sourcegrade.jagr.api.rubric.JUnitTestRef;
import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricProvider;

import java.util.List;

import static org.tudalgo.algoutils.tutor.general.jagr.RubricUtils.criterion;

public class H09_RubricProvider implements RubricProvider {

    public static final Rubric RUBRIC = Rubric.builder()
        .title("H09")
        .addChildCriteria(
            Criterion.builder()
                .shortDescription("H9.1 | StackOfObjects")
                .addChildCriteria(
                    criterion(
                        "Der generische Typparameter O wird korrekt deklariert und das Attribut objs wird korrekt initialisiert.",
                        JUnitTestRef.and(
                            JUnitTestRef.ofMethod(() -> StackOfObjectsTest.class.getMethod("testClassParameter")),
                            JUnitTestRef.ofMethod(() -> StackOfObjectsTest.class.getMethod("testObjsType"))
                        )
                    ),
                    criterion(
                        "push wird korrekt mit generischem Typparameter angepasst.",
                        JUnitTestRef.ofMethod(() -> StackOfObjectsTest.class.getMethod("testPushParameter"))
                    ),
                    criterion(
                        "remove wird korrekt mit generischem Typparameter angepasst.",
                        JUnitTestRef.ofMethod(() -> StackOfObjectsTest.class.getMethod("testRemoveParameter"))
                    ),
                    criterion(
                        "get und pop werden korrekt mit generischem Typparameter angepasst.",
                        JUnitTestRef.and(
                            JUnitTestRef.ofMethod(() -> StackOfObjectsTest.class.getMethod("testGetParameter")),
                            JUnitTestRef.ofMethod(() -> StackOfObjectsTest.class.getMethod("testPopParameter"))
                        )
                    ),
                    criterion(
                        "of wird korrekt mit generischem Typparameter angepasst.",
                        JUnitTestRef.ofMethod(() -> StackOfObjectsTest.class.getMethod("testOfParameter"))
                        , 2
                    )
                )
                .build(),
            Criterion.builder()
                .shortDescription("H9.2 | Generische Typen beschränken")
                .addChildCriteria(
                    criterion(
                        "Der generische Typparameter von Enclosure wird korrekt deklariert und beschränkt.",
                        JUnitTestRef.ofMethod(() -> EnclosureTestPrivate.class.getMethod("testClassParameter"))
                    ),
                    criterion(
                        "Enclosure::getStack von Enclosure wird korrekt mit generischem Typparameter angepasst.",
                        JUnitTestRef.ofMethod(() -> EnclosureTestPrivate.class.getMethod("testGetStack_ReturnType"))
                    ),
                    criterion(
                        "Waterenclosure besitzt einen korrekt beschränkten Typparameter und implementiert Enclosure korrekt.",
                        JUnitTestRef.and(
                            JUnitTestRef.ofMethod(() -> WaterEnclosureTest.class.getMethod("testClassParameter")),
                            JUnitTestRef.ofMethod(() -> WaterEnclosureTest.class.getMethod("testExtends"))
                        )
                    ),
                    criterion(
                        "Waterenclosure::getStack funktioniert korrekt.",
                        JUnitTestRef.and(
                            JUnitTestRef.ofMethod(() -> WaterEnclosureTest.class.getMethod("testGetStack_field")),
                            JUnitTestRef.ofMethod(() -> WaterEnclosureTest.class.getMethod("testGetStack_value")),
                            JUnitTestRef.ofMethod(() -> WaterEnclosureTest.class.getMethod("testGetStack_ReturnType")),
                            JUnitTestRef.ofMethod(() -> WaterEnclosureTest.class.getMethod("testAnimals_Type"))
                        )
                    ),
                    criterion(
                        "Waterenclosure::feed funktioniert korrekt.",
                        JUnitTestRef.ofMethod(() -> WaterEnclosureTest.class.getMethod("testFeed"))
                        , 2
                    ),
                    criterion(
                        "Waterenclosure::getMeanElevation funktioniert korrekt.",
                        JUnitTestRef.ofMethod(() -> WaterEnclosureTest.class.getMethod("testGetMeanElevation", List.class))
                    )
                )
                .build(),
            Criterion.builder()
                .shortDescription("H9.3 | Bearbeitung von Enclosures mit funktionalen Interfaces")
                .addChildCriteria(
                    criterion(
                        "forEach hat korrekt beschränkte Typparameter.",
                        JUnitTestRef.ofMethod(() -> EnclosureTestPrivate.class.getMethod("testForEach_Parameter"))
                        , 3
                    ),
                    criterion(
                        "filterObj hat korrekt beschränkte Typparameter.",
                        JUnitTestRef.ofMethod(() -> EnclosureTestPrivate.class.getMethod("testFilterObj_Parameter"))
                        , 3
                    ),
                    criterion(
                        "filterFunc hat korrekt beschränkte Typparameter.",
                        JUnitTestRef.and(
                            JUnitTestRef.ofMethod(() -> EnclosureTestPrivate.class.getMethod("testFilterFunc_Parameter")),
                            JUnitTestRef.ofMethod(() -> EnclosureTestPrivate.class.getMethod(
                                "testFilterFunc_ParameterDefinition")),
                            JUnitTestRef.ofMethod(() -> EnclosureTestPrivate.class.getMethod("testFilterFunc_ReturnType"))
                        )
                        , 4
                    ),
                    criterion(
                        "filterFunc gibt korrekt ein neues Enclosure mit gefilterten Tieren zurück.",
                        JUnitTestRef.ofMethod(() -> EnclosureTestPrivate.class.getMethod(
                            "testFilterFunc_Implementation",
                            int.class,
                            List.class
                        ))
                        , 2
                    )
                )
                .build(),
            Criterion.builder()
                .shortDescription("H9.4 | Predicates and Consumer mit Lambda")
                .addChildCriteria(
                    criterion(
                        "SWIMS_AT_LOW_ELEVATION funktioniert korrekt und wurde korrekt beschränkt.",
                        JUnitTestRef.and(
                            JUnitTestRef.ofMethod(() -> EnclosureTestPrivate.class.getMethod("testSWIMS_AT_LOW_ELEVATION_Type")),
                            JUnitTestRef.ofMethod(() -> EnclosureTestPrivate.class.getMethod(
                                "testSWIMS_AT_LOW_ELEVATION_Implementation",
                                float.class,
                                boolean.class
                            ))
                        )
                    ),
                    criterion(
                        "FEED_AND_SLEEP funktioniert korrekt und wurde korrekt beschränkt.",
                        JUnitTestRef.and(
                            JUnitTestRef.ofMethod(() -> EnclosureTestPrivate.class.getMethod("testFEED_AND_SLEEP_Type")),
                            JUnitTestRef.ofMethod(() -> EnclosureTestPrivate.class.getMethod("testFEED_AND_SLEEP_Implementation"))
                        )
                    ),
                    criterion(
                        "EAT_AND_SINK() gibt einen korrekten Consumer zurück, welcher korrekt beschränkt ist.",
                        JUnitTestRef.and(
                            JUnitTestRef.ofMethod(() -> EnclosureTestPrivate.class.getMethod("testEAT_AND_SINK_DefinedType")),
                            JUnitTestRef.ofMethod(() -> EnclosureTestPrivate.class.getMethod("testEAT_AND_SINK_Implementation")),
                            JUnitTestRef.ofMethod(() -> EnclosureTestPrivate.class.getMethod("testEAT_AND_SINK_ReturnType"))
                        )
                        , 2
                    )
                )
                .build(),
            Criterion.builder()
                .shortDescription("H9.5 | Enclosure::forEach")
                .addChildCriteria(
                    criterion(
                        "Drei Lion Objekte werden erstellt sowie gefüttert. Die Methode forEach wird grundlegend getestet.",
                        JUnitTestRef.ofMethod(() -> EnclosureTestTest.class.getMethod("testTestForEach"))
                    ),
                    criterion(
                        "Zwei Penguin Objekte werden korrekt erstellt, bewegt und gefüttert.",
                        JUnitTestRef.ofMethod(() -> EnclosureTestTest.class.getMethod("testTestFilter_behaviour"))
                    ),
                    criterion(
                        "Einfache Fehlfunktionen der Methode filterFunc werden erkannt.",
                        JUnitTestRef.ofMethod(() -> EnclosureTestTest.class.getMethod("testTestFilter_test"))
                    )
                )
                .build()
        )
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }

//    @Override
//    public void configure(final RubricConfiguration configuration) {
//        configuration.addTransformer(new AccessTransformer());
//    }
}
