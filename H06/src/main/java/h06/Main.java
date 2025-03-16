package h06;

import h06.problems.Fractals;
import h06.ui.FractalVisualizer;

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
        var fv = new FractalVisualizer(Fractals.dragonCurve(20), 90);
        fv.setVisible(true);
    }
}
