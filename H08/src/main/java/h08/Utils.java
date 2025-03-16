package h08;

import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class Utils {
    @FunctionalInterface
    public interface BetterRunnable {
        void run() throws Throwable;
    }

    public record TryResult(boolean success, @Nullable Throwable e) {
        boolean wasSuccessful() {
            return success;
        }

        TryResult orElseTry(BetterRunnable r) {
            return wasSuccessful() ? this : tryBool(r);
        }

        public <X extends Throwable> TryResult orElseThrow(Function<Throwable, ? extends X> exceptionSupplier) throws X {
            if (wasSuccessful()) {
                return this;
            } else {
                throw exceptionSupplier.apply(e);
            }
        }
    }

    public static <T> TryResult tryBool(BetterRunnable r) {
        try {
            r.run();
            return new TryResult(true, null);
        } catch (Throwable e) {
            return new TryResult(false, e);
        }
    }
}
