package h02;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.RobotFamily;
import fopbot.World;
import h02.template.InputHandler;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.Optional;

/**
 * The {@link FourWins} class represents the main class of the FourWins game.
 */
public class FourWins {
    private final InputHandler inputHandler = new InputHandler(this);
    /**
     * The width of the game board.
     */
    private final int width;
    /**
     * The height of the game board.
     */
    private final int height;
    /**
     * Indicates whether the game has finished.
     */
    @SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
    private boolean finished = false;

    /**
     * Creates a new {@link FourWins} instance with the given width and height.
     *
     * @param width  the width of the game board
     * @param height the height of the game board
     */
    FourWins(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Starts the game by setting up the world and executing the game loop.
     */
    void startGame() {
        setupWorld();
        gameLoop();
    }

    /**
     * Sets up the world and installs the {@link InputHandler}.
     */
    void setupWorld() {
        World.setSize(width, height);
        World.setDelay(10);
        World.setVisible(true);
        inputHandler.install();
    }


    /**
     * Validates if a given column index is within the bounds of the game board and not fully occupied.
     *
     * @param column The column index to validate.
     * @param stones 2D array representing the game board, where each cell contains a RobotFamily color indicating the
     *               player that has placed a stone in that position.
     * @return true if the column is within bounds and has at least one unoccupied cell; false otherwise.
     */
    @StudentImplementationRequired("H2.2.1")
    public static boolean validateInput(final int column, final RobotFamily[][] stones) {
        // TODO: H2.2.1
        return column >= 0 && column < stones[0].length;
    }


    /**
     * Calculates the next unoccupied row index in the specified column. This row index is the next destination for a
     * falling stone.
     *
     * @param column The column index where the stone is to be dropped.
     * @param stones 2D array representing the game board, where each cell contains a RobotFamily object indicating the
     *               player that has placed a stone in that position.
     * @return Index of the next unoccupied row index in the specified column.
     */
    @StudentImplementationRequired("H2.2.2")
    public static int getDestinationRow(final int column, final RobotFamily[][] stones) {
        // TODO: H2.2.2
        int row = 0;
        while (row < stones.length && stones[row][column] != null) {
            row++;
        }
        return row == stones.length ? -1 : row;
    }

    /**
     * Drops a stone into the specified column of the game board, simulating a falling animation. This method gets the
     * destination row for the stone in the specified column with the `getDestinationRow` method. It creates a new Robot
     * instance to represent the stone with the currentPlayer's RobotFamily in the given column and the destination row.
     * After that it simulates the stone's fall by decrementing its position until it reaches the destination row. Once
     * the  stone reaches its destination, the method updates the stones array (a 2D array of RobotFamily colors) to
     * mark the slot as occupied by the currentPlayer.
     *
     * @param column        The column index where the stone is to be dropped.
     * @param stones        2D array representing the game board, where each cell contains a RobotFamily object
     *                      indicating the player that has placed a stone in that position.
     * @param currentPlayer The RobotFamily object representing the current player dropping the stone.
     */
    @StudentImplementationRequired("H2.2.2")
    public static void dropStone(final int column, final RobotFamily[][] stones, final RobotFamily currentPlayer) {
        // TODO: H2.2.2
        var row = getDestinationRow(column, stones);
        if (row < 0) {
            return;
        }
        var stone = new Robot(column, stones.length - 1, Direction.DOWN, 0, currentPlayer);
        while (stone.getY() > row) {
            stone.move();
        }
        stones[row][column] = stone.getRobotFamily();
    }


    /**
     * Checks if the current player has won by any condition. The conditions can be a horizontal, vertical, diagonal, or
     * anti-diagonal line of at least four stones.
     *
     * @param stones        2D array representing the game board, where each cell contains a RobotFamily color
     *                      indicating the player that has placed a stone in that position.
     * @param currentPlayer The RobotFamily color representing the current player to check for a win.
     * @return true if the current player has formed a horizontal line of at least four stones; false otherwise.
     */
    @StudentImplementationRequired("H2.2.3")
    public static boolean testWinConditions(final RobotFamily[][] stones, final RobotFamily currentPlayer) {
        // TODO: H2.2.3
        return testWinHorizontal(stones, currentPlayer) || testWinVertical(stones, currentPlayer) || testWinDiagonal(stones, currentPlayer);
    }

    /**
     * Checks if the current player has won by forming a horizontal line of at least consecutive four stones.
     *
     * @param stones        2D array representing the game board, where each cell contains a RobotFamily color
     *                      indicating the player that has placed a stone in that position.
     * @param currentPlayer The RobotFamily color representing the current player to check for a win.
     * @return true if the current player has formed a horizontal line of at least four stones; false otherwise.
     */
    @StudentImplementationRequired("H2.2.3")
    public static boolean testWinHorizontal(final RobotFamily[][] stones, final RobotFamily currentPlayer) {
        // TODO: H2.2.3
        for (int row = 0; row < stones.length; row++) {
            int count = 0;
            for (int col = 0; col < stones[row].length; col++) {
                if (stones[row][col] == currentPlayer) {
                    count++;
                } else {
                    count = 0;
                }
                if (count >= 4) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the current player has won by forming a vertical line of at least consecutive four stones.
     *
     * @param stones        2D array representing the game board, where each cell contains a RobotFamily color
     *                      indicating the player that has placed a stone in that position.
     * @param currentPlayer The RobotFamily color representing the current player to check for a win.
     * @return true if the current player has formed a vertical line of at least four stones; false otherwise.
     */
    @StudentImplementationRequired("H2.2.3")
    public static boolean testWinVertical(final RobotFamily[][] stones, final RobotFamily currentPlayer) {
        // TODO: H2.2.3
        for (int col = 0; col < stones[0].length; col++) {
            int count = 0;
            for (int row = 0; row < stones.length; row++) {
                if (stones[row][col] == currentPlayer) {
                    count++;
                } else {
                    count = 0;
                }
                if (count >= 4) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the current player has won by forming a diagonal line of at least consecutive four stones.
     *
     * @param stones        2D array representing the game board, where each cell contains a RobotFamily color
     *                      indicating the player that has placed a stone in that position.
     * @param currentPlayer The RobotFamily color representing the current player to check for a win.
     * @return true if the current player has formed a diagonal line of at least four stones; false otherwise.
     */
    @DoNotTouch
    public static boolean testWinDiagonal(final RobotFamily[][] stones, final RobotFamily currentPlayer) {
        @SuppressWarnings("CheckStyle") final int MAX_STONES = 4;

        @SuppressWarnings("CheckStyle") final int WIDTH = World.getWidth();
        @SuppressWarnings("CheckStyle") final int HEIGHT = World.getHeight();
        int[] direction = new int[]{1, 1};

        // for every field
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {

                // for every direction
                for (int nthDirection = 0; nthDirection < 4; nthDirection++) {
                    final int[] pos = {x, y};

                    // test for consecutive coins
                    int coinCount = 0; // start counting at 0
                    while (pos[0] >= 0 && pos[0] < WIDTH
                        && pos[1] >= 0 && pos[1] < HEIGHT
                        && stones[pos[1]][pos[0]] == currentPlayer) {
                        coinCount++; // count every stone that has currentPlayer's color
                        if (coinCount >= MAX_STONES) {
                            return true;
                        }
                        pos[0] += direction[0];
                        pos[1] += direction[1];
                    }

                    direction = new int[]{direction[1], -direction[0]}; // next direction (rotate by 90 deg)
                }
            }
        }

        return false;
    }


    /**
     * Switches the player for each turn. If the current player is SQUARE_BLUE, SQUARE_RED is returned as the next
     * player. If the current player is SQUARE_RED, SQUARE_BLUE is returned as the next player.
     *
     * @param currentPlayer The player color of the current player.
     * @return The player color of the next player.
     */
    @StudentImplementationRequired("H2.2.4")
    public static RobotFamily nextPlayer(final RobotFamily currentPlayer) {
        // TODO: H2.2.4
        return currentPlayer == RobotFamily.SQUARE_BLUE ? RobotFamily.SQUARE_RED : RobotFamily.SQUARE_BLUE;
    }

    /**
     * Displays a Message in the console and on the game board indicating the game is drawn.
     */
    @StudentImplementationRequired("H2.2.4")
    public void writeDrawMessage() {
        inputHandler.displayDrawStatus();

        // TODO: H2.2.4
        System.out.println("No valid columns found. Hence, game ends with a draw.");
    }

    /**
     * Displays a Message in the console and on the game board indicating the game is won by a player.
     *
     * @param winner {@link RobotFamily} of the winning player
     */
    @StudentImplementationRequired("H2.2.4")
    public void writeWinnerMessage(final RobotFamily winner) {
        inputHandler.displayWinnerStatus(winner);

        // TODO: H2.2.4
        System.out.printf("Player %s wins the game!", winner.getName());
    }

    /**
     * Displays the winner of the game by printing the winning color in the console and filling the whole field with
     * Robots of the winning color.
     *
     * @param winner The RobotFamily color of the winner.
     */
    @StudentImplementationRequired("H2.2.4")
    public static void colorFieldBackground(final RobotFamily winner) {
        // TODO: H2.2.4
        for (int i = 0; i < World.getHeight(); i++) {
            for (int j = 0; j < World.getWidth(); j++) {
                setFieldColor(j, i, winner);
            }
        }
    }

    /**
     * Executes the main game loop, handling player turns, stone drops, and win condition checks. This method
     * initializes the game board as a 2D array of RobotFamily colors, representing the slots that can be filled with
     * players' stones. It starts with a predefined currentPlayer and continues in a loop until a win condition is met.
     * Each iteration of the loop waits for player input to select a column to drop a stone into, switches the current
     * player, drops the stone in the selected column, and checks for win conditions. If a win condition is met, the
     * loop ends, and the winner is displayed.
     */
    @StudentImplementationRequired("H2.2.4")
    void gameLoop() {
        final RobotFamily[][] stones = new RobotFamily[World.getHeight()][World.getWidth()];
        RobotFamily currentPlayer = RobotFamily.SQUARE_BLUE;

        boolean draw = false;
        finished = false;

        while (!finished) {
            // TODO: H2.2.4
            // set next player
            currentPlayer = nextPlayer(currentPlayer);
//            org.tudalgo.algoutils.student.Student.crash("H2.2.4 - Remove if implemented");

            // wait for click in column (DO NOT TOUCH)
            finished = draw = isGameBoardFull(stones);
            if (draw) {
                break;
            }
            final int column = inputHandler.getNextInput(currentPlayer, stones);

            // TODO: H2.2.4
            // let stone drop
            dropStone(column, stones, currentPlayer);
            // test win conditions
            if (testWinConditions(stones, currentPlayer)) {
                finished = true;
            }
        }

        // displaying either draw or winner (DO NOT TOUCH)
        if (draw) {
            writeDrawMessage();
            colorFieldBackground(getDrawnRobotFamily());
        } else {
            writeWinnerMessage(currentPlayer);
            colorFieldBackground(currentPlayer);
        }
    }


    /**
     * Executes the main game loop, handling player turns, stone drops, and win condition checks. Sets the background
     * color of a field at the specified coordinates. The color is derived from the {@link RobotFamily} SQUARE_BLUE or
     * SQUARE_RED.
     *
     * @param x     the x coordinate of the field
     * @param y     the y coordinate of the field
     * @param color the {@link RobotFamily} corresponding to the field color to set
     */
    @DoNotTouch
    public static void setFieldColor(final int x, final int y, final RobotFamily color) {
        World.getGlobalWorld().setFieldColor(x, y, color.getColor());
    }

    /**
     * Returns the {@link RobotFamily} which represents a drawn game.
     *
     * @return the {@link RobotFamily} which represents a drawn game.
     */
    @DoNotTouch
    @SuppressWarnings("UnstableApiUsage")
    protected static RobotFamily getDrawnRobotFamily() {
        return Optional.ofNullable(World.getGlobalWorld().getGuiPanel()).filter(guiPanel -> !guiPanel.isDarkMode()).map(guiPanel -> RobotFamily.SQUARE_ORANGE).orElse(RobotFamily.SQUARE_YELLOW);
    }

    /**
     * Checks if all columns of the game board are fully occupied.
     *
     * @param stones 2D array representing the game board, where each cell contains a RobotFamily
     * @return true if all columns of the game board are fully occupied; false otherwise.
     */
    @DoNotTouch
    public static boolean isGameBoardFull(final RobotFamily[][] stones) {
        for (int x = 0; x < World.getWidth(); x++) {
            if (FourWins.validateInput(x, stones)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns {@code true} when the game is finished, {@code false} otherwise.
     *
     * @return whether the game is finished.
     */
    public boolean isFinished() {
        return finished;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }
}
