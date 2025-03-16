package h06.ui;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for drawing fractals, given their drawing instructions.
 *
 * @author Manuel Peters
 */
@DoNotTouch
public class FractalDrawer extends JPanel {
    private final DrawInstruction[] drawInstructions;
    private final List<Line2D> lines;

    /**
     * The angle in radians for a turn.
     * This angle determines how far a left or right turn will go.
     */
    private final double angle;

    /**
     * Constructor for this class.
     *
     * @param drawInstructions the instructions to draw the fractal
     * @param angle the angle in degrees for a turn
     */
    public FractalDrawer(DrawInstruction[] drawInstructions, int angle) {
        this.drawInstructions = drawInstructions;
        this.lines = new ArrayList<>();
        this.angle = Math.toRadians(angle);
    }

    /**
     * Draws on the canvas by following the drawing instructions
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);

        Graphics2D g2d = (Graphics2D) g;

        // Set the initial position and direction
        double x = 0;
        double y = 0;
        // The first line is drawn to the right
        double currentAngle = 0;

        double minX = x;
        double minY = y;
        double maxX = x;
        double maxY = y;

        // Store the lines based on instructions
        for (DrawInstruction instruction : drawInstructions) {
            if (instruction.equals(DrawInstruction.DRAW_LINE)) {
                double x2 = x + Math.cos(currentAngle);
                double y2 = y + Math.sin(currentAngle);
                lines.add(new Line2D.Double(x, y, x2, y2));
                x = x2;
                y = y2;

                // Update Bounding Box values
                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);
            } else if (instruction.equals(DrawInstruction.TURN_LEFT)) {
                currentAngle -= angle;
            } else if (instruction.equals(DrawInstruction.TURN_RIGHT)) {
                currentAngle += angle;
            }
        }

        // Define Bounding Box
        Rectangle2D bounds = new Rectangle2D.Double(minX - 1, minY - 1, maxX - minX + 2, maxY - minY + 2);

        // Calculate scaling factor to fit all the lines within the panel
        double scaleX = getWidth() / bounds.getWidth();
        double scaleY = getHeight() / bounds.getHeight();
        double scale = Math.min(scaleX, scaleY);

        // Calculate the initial position to center the fractal
        double offsetX = (getWidth() - bounds.getWidth() * scale) / 2 - bounds.getX() * scale;
        double offsetY = (getHeight() - bounds.getHeight() * scale) / 2 - bounds.getY() * scale;

        // Draw the stored lines with scaling and offset
        for (Line2D line : lines) {
            double x1 = offsetX + line.getX1() * scale;
            double y1 = offsetY + line.getY1() * scale;
            double x2 = offsetX + line.getX2() * scale;
            double y2 = offsetY + line.getY2() * scale;
            g2d.draw(new Line2D.Double(x1, y1, x2, y2));
        }
    }
}
