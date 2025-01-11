package h01;

import fopbot.Direction;
import org.sourcegrade.jagr.api.rubric.*;
import static org.tudalgo.algoutils.tutor.general.jagr.RubricUtils.criterion;

public class H01_RubricProvider implements RubricProvider {

    private static final Criterion H1_1 = Criterion.builder()
            .shortDescription("H1.1 | Steuerung von Pacman")
            .maxPoints(4)
            .addChildCriteria(
                criterion("Pacman kann sich nach links, rechts, oben und unten bewegen.", 2,
                    JUnitTestRef.ofMethod(() -> PacmanTest.class.getDeclaredMethod("testBasicMovement", Direction.class))),
                criterion("Pacman kann sich nicht durch Wände bewegen.",
                    JUnitTestRef.ofMethod(() -> PacmanTest.class.getDeclaredMethod("testMovementWithWalls", Direction.class))),
                criterion("Pacman sammelt Münzen auf, wenn er sich auf ein Feld mit einer Münze bewegt.",
                    JUnitTestRef.ofMethod(() -> PacmanTest.class.getDeclaredMethod("testMovementWithCoins", Direction.class))))
            .build();

    private static final Criterion H1_2_1 = Criterion.builder()
            .shortDescription("H1.2.1 | Blaues Gespenst")
            .maxPoints(2)
            .addChildCriteria(
                criterion("Das blaue Gespenst dreht sich nach rechts.",
                    JUnitTestRef.ofMethod(() -> BlueGhostTest.class.getDeclaredMethod("testRightTurn"))),
                criterion("Wenn eine Wand vor dem blauen Gespenst ist, dreht es sich nach links, bis es keine Wand mehr vor sich hat.",
                    JUnitTestRef.ofMethod(() -> BlueGhostTest.class.getDeclaredMethod("testLeftTurns"))),
                criterion("Das blaue Gespenst bewegt sich ein Feld nach vorne.",
                    JUnitTestRef.ofMethod(() -> BlueGhostTest.class.getDeclaredMethod("testMove"))))
            .build();

    private static final Criterion H1_2_2 = Criterion.builder()
            .shortDescription("H1.2.2 | Pinkes Gespenst")
            .maxPoints(3)
            .addChildCriteria(
                criterion("Das pinke Gespenst wählt einen zufälligen Weg aus den freien Wegen.",
                    JUnitTestRef.ofMethod(() -> PinkGhostTest.class.getDeclaredMethod("testPicksRandomFreePath"))),
                criterion("Das pinke Gespenst dreht sich zu dem gewählten freien Weg.",
                    JUnitTestRef.ofMethod(() -> PinkGhostTest.class.getDeclaredMethod("testTurnToFreePath"))),
                criterion("Das pinke Gespenst bewegt sich ein Feld nach vorne.",
                    JUnitTestRef.ofMethod(() -> PinkGhostTest.class.getDeclaredMethod("testMove"))))
            .build();

    private static final Criterion H1_2_3 = Criterion.builder()
            .shortDescription("H1.2.3 | Oranges Gespenst")
            .maxPoints(4)
            .addChildCriteria(
                criterion("Das orange Gespenst läuft solange nach vorne, bis eine Wand vor ihm ist.",
                    JUnitTestRef.ofMethod(() -> OrangeGhostTest.class.getDeclaredMethod("testMoveForward"))),
                criterion("Das orange Gespenst dreht sich nach dem ersten Aufruf rechts herum.",
                    JUnitTestRef.ofMethod(() -> OrangeGhostTest.class.getDeclaredMethod("testTurnsRight"))),
                criterion("Das orange Gespenst kehrt am des Aufrufs von doMove() seine Drehrichtung um.", 2,
                    JUnitTestRef.ofMethod(() -> OrangeGhostTest.class.getDeclaredMethod("testSwitchTurning"))))
            .build();

    private static final Criterion H1_2_4 = Criterion.builder()
            .shortDescription("H1.2.4 | Rotes Gespenst")
            .maxPoints(3)
            .addChildCriteria(
                criterion("Das rote Gespenst dreht sich in die Richtung in der sich Pacman befindet.",
                    JUnitTestRef.ofMethod(() -> RedGhostTest.class.getDeclaredMethod("testTurnToPacman"))),
                criterion("Das rote Gespenst dreht weiter nach links, bis es keine Wand mehr vor sich hat.",
                    JUnitTestRef.ofMethod(() -> RedGhostTest.class.getDeclaredMethod("testTurnLeft"))),
                criterion("Das rote Gespenst bewegt sich ein Feld nach vorne.",
                    JUnitTestRef.ofMethod(() -> RedGhostTest.class.getDeclaredMethod("testMove"))))
            .build();

    private static final Criterion H1_2 = Criterion.builder()
            .shortDescription("H1.2 | Die Gespenster kommen")
            .addChildCriteria(
                    H1_2_1, H1_2_2, H1_2_3, H1_2_4)
            .build();

    public static final Rubric RUBRIC = Rubric.builder()
            .title("H01 | Pacman")
            .addChildCriteria(
                    H1_1,
                    H1_2)
            .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }
}
