package h03.robots;

public class VersatileRobot extends HackingRobot {
    public VersatileRobot(int x, int y, boolean order, boolean exchange) {
        super(exchange ? y : x, exchange ? x : y, order);
//        super(x, y, order);
        if (getType() == MovementType.DIAGONAL) {
            setY(getX());
        }
    }

    @Override
    public boolean shuffle(final int itNr) {
        var res = super.shuffle(itNr);
        if (getType() == MovementType.DIAGONAL) {
            setY(getX());
        }
        return res;
    }

    @Override
    public void shuffle() {
        super.shuffle();
        if (getType() == MovementType.DIAGONAL) {
            setY(getX());
        }
    }
}
