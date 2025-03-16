package h06.ui;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;

import javax.swing.*;
import java.awt.*;

/*
 * A class to visualize fractals, given their drawing instructions.
 *
 * @author Manuel Peters
 */
@DoNotTouch
public class FractalVisualizer extends JFrame {

    /*
     * Constructor for this class.
     *
     * @param drawInstructions the instructions to draw the fractal
     * @param angle the angle in degrees for a turn
     */
    public FractalVisualizer(DrawInstruction[] drawInstructions, int angle) {
        super("Fractal Visualizer");
        setSize(new Dimension(800,800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1,1));

        FractalDrawer fractalDrawer = new FractalDrawer(drawInstructions, angle);
        add(fractalDrawer);
    }
}
