package h03;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;

import static h03.TestConstants.RANDOM_SEED;

public abstract class TestUtils {

    /**
     * A generator for JSON test data.
     */
    public interface JsonGenerator {
        /**
         * Generates a JSON object node.
         *
         * @param mapper The object mapper to use.
         * @param index  The index of the object node.
         * @param rnd    The random number generator to use.
         * @return The generated JSON object node.
         */
        ObjectNode generateJson(ObjectMapper mapper, int index, Random rnd);
    }

    /**
     * Generates and saves JSON test data.
     *
     * @param generator The generator to use.
     * @param amount    The amount of test data to generate.
     * @param fileName  The file name to save the test data to (without extension).
     * @throws IOException If an I/O error occurs.
     */
    public static void generateJsonTestData(final JsonGenerator generator, final int amount, final String fileName) throws IOException {
        final var seed = RANDOM_SEED;
        final var random = new java.util.Random(seed);
        final ObjectMapper mapper = new ObjectMapper();
        final ArrayNode arrayNode = mapper.createArrayNode();
        System.out.println("Generating test data with seed: " + seed);
        for (int i = 0; i < amount; i++) {
            arrayNode.add(generator.generateJson(mapper, i, random));
        }

        final var path = Paths.get(
            "src",
            "graderPrivate",
            "resources",
            "h03",
            fileName + ".generated.json"
        ).toAbsolutePath();
        System.out.printf("Saving to file: %s%n", path);
        final var file = path.toFile();
        file.createNewFile();
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, arrayNode);
    }
}
