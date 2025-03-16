package h09;

import org.sourcegrade.jagr.api.rubric.Criterion;
import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricProvider;

import static org.tudalgo.algoutils.tutor.general.jagr.RubricUtils.criterion;

public class H09_RubricProvider implements RubricProvider {

    public static final Rubric RUBRIC = Rubric.builder()
        .title("H09")
        .addChildCriteria(
            Criterion.builder()
                .shortDescription("H9.1 | StackOfObjects")
                .addChildCriteria(
                    criterion("Der generische Typparameter O wird korrekt deklariert und das Attribut " +
                        "objs wird korrekt initialisiert."),
                    criterion("push wird korrekt mit generischem Typparameter angepasst."),
                    criterion("remove wird korrekt mit generischem Typparameter angepasst."),
                    criterion("get und pop werden korrekt mit generischem Typparameter angepasst."),
                    criterion("of wird korrekt mit generischem Typparameter angepasst.", 2)
                    )
                .build(),
            Criterion.builder()
                .shortDescription("H9.2 | Generische Typen beschränken")
                .addChildCriteria(
                    criterion("Der generische Typparameter von Enclosure wird korrekt deklariert und beschränkt."),
                    criterion("Enclosure::getStack von Enclosure wird korrekt mit generischem Typparameter angepasst."),
                    criterion("Waterenclosure besitzt einen korrekt beschränkten Typparameter und implementiert Enclosure korrekt."),
                    criterion("Waterenclosure::getStack funktioniert korrekt."),
                    criterion("Waterenclosure::feed getStack funktioniert korrekt.", 2),
                    criterion("Waterenclosure::getMeanElevation getStack funktioniert korrekt.")
                )
                .build(),
            Criterion.builder()
                .shortDescription("H9.3 | Bearbeitung von Enclosures mit funktionalen Interfaces")
                .addChildCriteria(
                    criterion("forEach hat korrekt beschränkte Typparameter.", 3),
                    criterion("filterObj hat korrekt beschränkte Typparameter.", 3),
                    criterion("filterFunc hat korrekt beschränkte Typparameter.", 4),
                    criterion("filterFunc gibt korrekt ein neues Enclosure mit gefilterten Tieren zurück.", 2)
                    )
                .build(),
            Criterion.builder()
                .shortDescription("H9.4 | Predicates and Consumer mit Lambda")
                .addChildCriteria(
                    criterion("SWIMS_AT_LOW_ELEVATION funktioniert korrekt und wurde korrekt beschränkt."),
                    criterion("FEED_AND_SLEEP funktioniert korrekt und wurde korrekt beschränkt."),
                    criterion("EAT_AND_SINK() gibt einen korrekten Consumer zurück, welcher korrekt beschränkt ist.", 2)
                )
                .build(),
            Criterion.builder()
                .shortDescription("H9.5 | Enclosure::forEach")
                .addChildCriteria(
                    criterion("Drei Lion Objekte werden erstellt sowie gefüttert und einfache Fehlfunktionen der Methode forEach werden erkannt."),
                    criterion("Zwei Penguin Objekte werden korrekt erstellt, bewegt und gefüttert."),
                    criterion("Einfache Fehlfunktionen der Methode filterFunc werden erkannt.")
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
