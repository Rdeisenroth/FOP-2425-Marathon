package h05;

public enum FuelType {
    JetA(1),
    JetB(1.2),
    AvGas(.99),
    Biokerosin(1.02);
    private final double consumptionMultiplicator;

    public double getConsumptionMultiplicator() {
        return consumptionMultiplicator;
    }

    FuelType(double consumptionMultiplicator) {
        this.consumptionMultiplicator = consumptionMultiplicator;
    }
}
