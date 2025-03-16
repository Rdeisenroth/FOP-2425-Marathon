package h06;

import h06.problems.*;
import h06.ui.DrawInstruction;
import h06.ui.FractalVisualizer;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * Main entry point in executing the program.
 */
public class Main {
    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    @StudentImplementationRequired
    public static void main(String[] args) {
        DrawInstruction[] dragonCurveInstructions = Fractals.dragonCurve(14);
        DrawInstruction[] kochSnowflakeInstructions = Fractals.kochSnowflake(5);

        FractalVisualizer fracVis = new FractalVisualizer(dragonCurveInstructions, 90);
        fracVis.setVisible(true);

        FractalVisualizer fracVis2 = new FractalVisualizer(kochSnowflakeInstructions, 60);
        fracVis2.setVisible(true);
    }
}
