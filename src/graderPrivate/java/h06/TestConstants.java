package h06;

import java.util.concurrent.ThreadLocalRandom;

public class TestConstants {
    public static long RANDOM_SEED = ThreadLocalRandom.current().nextLong();

    public static final int TEST_TIMEOUT_IN_SECONDS = 30;

    public static final int TEST_ITERATIONS = 30;

    public static final boolean SKIP_AFTER_FIRST_FAILED_TEST = true;
}
