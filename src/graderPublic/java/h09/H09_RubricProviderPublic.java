package h09;

import org.sourcegrade.jagr.api.rubric.Criterion;
import org.sourcegrade.jagr.api.rubric.JUnitTestRef;
import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricProvider;

import java.util.List;

import static org.tudalgo.algoutils.tutor.general.jagr.RubricUtils.criterion;
import static org.tudalgo.algoutils.tutor.general.jagr.RubricUtils.graderPrivateOnly;

public class H09_RubricProviderPublic implements RubricProvider {

    public static final Rubric RUBRIC = Rubric.builder()
        .title("H09")
        .addChildCriteria(
            Criterion.builder()
                .shortDescription("H9.1 | StackOfObjects")
                .addChildCriteria(
                    criterion(
                        "Der generische Typparameter O wird korrekt deklariert und das Attribut objs wird korrekt initialisiert.",
                        JUnitTestRef.and(
                            JUnitTestRef.ofMethod(() -> StackOfObjectsTestPublic.class.getMethod("testClassParameter")),
                            JUnitTestRef.ofMethod(() -> StackOfObjectsTestPublic.class.getMethod("testObjsType"))
                        )
                    ),
                    criterion(
                        "push wird korrekt mit generischem Typparameter angepasst.",
                        JUnitTestRef.ofMethod(() -> StackOfObjectsTestPublic.class.getMethod("testPushParameter"))
                    ),
                    criterion(
                        "remove wird korrekt mit generischem Typparameter angepasst.",
                        JUnitTestRef.ofMethod(() -> StackOfObjectsTestPublic.class.getMethod("testRemoveParameter"))
                    ),
                    criterion(
                        "get und pop werden korrekt mit generischem Typparameter angepasst.",
                        JUnitTestRef.and(
                            JUnitTestRef.ofMethod(() -> StackOfObjectsTestPublic.class.getMethod("testGetParameter")),
                            JUnitTestRef.ofMethod(() -> StackOfObjectsTestPublic.class.getMethod("testPopParameter"))
                        )
                    ),
                    criterion(
                        "of wird korrekt mit generischem Typparameter angepasst.",
                        JUnitTestRef.ofMethod(() -> StackOfObjectsTestPublic.class.getMethod("testOfParameter"))
                        , 2
                    )
                )
                .build(),
            Criterion.builder()
                .shortDescription("H9.2 | Generische Typen beschränken")
                .addChildCriteria(
                    criterion(
                        "Der generische Typparameter von Enclosure wird korrekt deklariert und beschränkt.",
                        JUnitTestRef.ofMethod(() -> EnclosureTestPublic.class.getMethod("testClassParameter"))
                    ),
                    criterion(
                        "Enclosure::getStack von Enclosure wird korrekt mit generischem Typparameter angepasst.",
                        JUnitTestRef.ofMethod(() -> EnclosureTestPublic.class.getMethod("testGetStack_ReturnType"))
                    ),
                    privateCriterion(
                        "Waterenclosure besitzt einen korrekt beschränkten Typparameter und implementiert Enclosure korrekt.",
                        0,
                        1
                    ),
                    privateCriterion("Waterenclosure::getStack funktioniert korrekt.", 0, 1),
                    criterion(
                        "Waterenclosure::feed funktioniert korrekt.",
                        JUnitTestRef.ofMethod(() -> WaterEnclosureTestPublic.class.getMethod("testFeed"))
                        , 2
                    ),
                    criterion(
                        "Waterenclosure::getMeanElevation funktioniert korrekt.",
                        JUnitTestRef.ofMethod(() -> WaterEnclosureTestPublic.class.getMethod(
                            "testGetMeanElevation",
                            List.class,
                            double.class
                        ))
                    )
                )
                .build(),
            Criterion.builder()
                .shortDescription("H9.3 | Bearbeitung von Enclosures mit funktionalen Interfaces")
                .addChildCriteria(
                    criterion(
                        "forEach hat korrekt beschränkte Typparameter.",
                        JUnitTestRef.ofMethod(() -> EnclosureTestPublic.class.getMethod("testForEach_Parameter")),
                        3
                    ),
                    privateCriterion("filterObj hat korrekt beschränkte Typparameter.", 0, 3),
                    privateCriterion("filterFunc hat korrekt beschränkte Typparameter.", 0, 4),
                    privateCriterion("filterFunc gibt korrekt ein neues Enclosure mit gefilterten Tieren zurück.", 0, 2)
                )
                .build(),
            Criterion.builder()
                .shortDescription("H9.4 | Predicates and Consumer mit Lambda")
                .addChildCriteria(
                    criterion(
                        "SWIMS_AT_LOW_ELEVATION funktioniert korrekt und wurde korrekt beschränkt.",
                        JUnitTestRef.and(
                            JUnitTestRef.ofMethod(() -> EnclosureTestPublic.class.getMethod("testSWIMS_AT_LOW_ELEVATION_Type")),
                            JUnitTestRef.ofMethod(() -> EnclosureTestPublic.class.getMethod(
                                "testSWIMS_AT_LOW_ELEVATION_Implementation",
                                float.class,
                                boolean.class
                            ))
                        )
                    ),
                    criterion(
                        "FEED_AND_SLEEP funktioniert korrekt und wurde korrekt beschränkt.",
                        JUnitTestRef.and(
                            JUnitTestRef.ofMethod(() -> EnclosureTestPublic.class.getMethod("testFEED_AND_SLEEP_Type")),
                            JUnitTestRef.ofMethod(() -> EnclosureTestPublic.class.getMethod("testFEED_AND_SLEEP_Implementation"))
                        )
                    ),
                    privateCriterion("EAT_AND_SINK() gibt einen korrekten Consumer zurück, welcher korrekt beschränkt ist.", 0, 2)
                )
                .build(),
            Criterion.builder()
                .shortDescription("H9.5 | Enclosure::forEach")
                .addChildCriteria(
                    privateCriterion(
                        "Drei Lion Objekte werden erstellt sowie gefüttert. Die Methode forEach wird grundlegend getestet.",
                        0,
                        1
                    ),
                    privateCriterion("Zwei Penguin Objekte werden korrekt erstellt, bewegt und gefüttert.", 0, 1),
                    privateCriterion("Einfache Fehlfunktionen der Methode filterFunc werden erkannt.", 0, 1)
                )
                .build()
        )
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }

    public static Criterion privateCriterion(String message, int min, int max) {
        return Criterion.builder()
            .shortDescription(message)
            .grader(graderPrivateOnly(max))
            .minPoints(min)
            .maxPoints(max)
            .build();
    }

//    @Override
//    public void configure(final RubricConfiguration configuration) {
//        configuration.addTransformer(new AccessTransformer());
//    }
}
