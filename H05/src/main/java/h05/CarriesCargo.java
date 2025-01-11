package h05;

public interface CarriesCargo {
    void loadContainer(int cargoWeight);

    /**
     * At least one container loaded
     * @return
     */
    boolean hasFreightLoaded();

    /**
     * Unloads container
     * @return its weight
     */
    int unloadNextContainer();
}
