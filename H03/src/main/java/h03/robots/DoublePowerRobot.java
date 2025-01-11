package h03.robots;

public class DoublePowerRobot extends HackingRobot {
    public MovementType[] doublePowerTypes;

    public DoublePowerRobot(int x, int y, boolean order) {
        super(x, y, order);
        doublePowerTypes = new MovementType[]{getType(), getNextType()};
    }

    @Override
    public boolean shuffle(final int itNr) {
        var res = super.shuffle(itNr);
        doublePowerTypes[0] = getType();
        doublePowerTypes[1] = getNextType();
        return res;
    }

    @Override
    public void shuffle() {
        super.shuffle();
        doublePowerTypes[0] = getType();
        doublePowerTypes[1] = getNextType();
    }
}
