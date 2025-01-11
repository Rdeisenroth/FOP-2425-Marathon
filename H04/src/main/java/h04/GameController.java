package h04;

import h04.template.ChessUtils;
import h04.template.GameControllerTemplate;
import h04.chesspieces.King;

public class GameController extends GameControllerTemplate {
    public GameController() {
        super();
        setup();
    }

    @Override
    public boolean checkWinCondition() {
        //TODO H4.1
        return false;
    }
}
