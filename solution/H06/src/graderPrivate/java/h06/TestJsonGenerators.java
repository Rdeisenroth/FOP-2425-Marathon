package h06;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import h06.problems.Fractals;
import h06.ui.DrawInstruction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static h06.TestConstants.TEST_ITERATIONS;

@DisabledIf("org.tudalgo.algoutils.tutor.general.Utils#isJagrRun()")
public class TestJsonGenerators {

    @Test
    public void generateLinearSearchDataSet() throws IOException {
        TestUtils.generateJsonTestData(
            (mapper, index, rnd) -> {
                boolean containsTarget = rnd.nextBoolean();
                int target = rnd.nextInt();
                List<Integer> arr = rnd.ints()
                    .distinct()
                    .filter(i -> i != target)
                    .limit(rnd.nextInt(10, 20))
                    .boxed()
                    .collect(Collectors.toList());
                int expectedIndex;
                if (containsTarget) {
                    expectedIndex = rnd.nextInt(arr.size());
                    arr.set(expectedIndex, target);
                } else {
                    expectedIndex = -1;
                }

                ArrayNode arrayNode = mapper.createArrayNode();
                arr.forEach(arrayNode::add);
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("target", target);
                objectNode.set("arr", arrayNode);
                objectNode.put("expectedIndex", expectedIndex);

                return objectNode;
            },
            TEST_ITERATIONS,
            "LinearSearchDataSet"
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"First", "Second", "Both"})
    public void generateFractalsConcatDataSet(String concatType) throws IOException {
        DrawInstruction[] drawInstructions = DrawInstruction.values();
        BiFunction<String, Random, Integer> arr1Length = (s, rnd) -> s.equals("Second") ? 0 : rnd.nextInt(1, 10);
        BiFunction<String, Random, Integer> arr2Length = (s, rnd) -> s.equals("First") ? 0 : rnd.nextInt(1, 10);

        TestUtils.generateJsonTestData(
            (mapper, index, rnd) -> {
                int arr1Size = arr1Length.apply(concatType, rnd);
                List<String> arr1 = new ArrayList<>();
                for (int i = 0; i < arr1Size; i++) {
                    arr1.add(drawInstructions[rnd.nextInt(drawInstructions.length)].name());
                }
                int arr2Size = arr2Length.apply(concatType, rnd);
                List<String> arr2 = new ArrayList<>();
                for (int i = 0; i < arr2Size; i++) {
                    arr2.add(drawInstructions[rnd.nextInt(drawInstructions.length)].name());
                }

                ArrayNode arr1ArrayNode = mapper.createArrayNode();
                arr1.forEach(arr1ArrayNode::add);
                ArrayNode arr2ArrayNode = mapper.createArrayNode();
                arr2.forEach(arr2ArrayNode::add);
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.set("arr1", arr1ArrayNode);
                objectNode.set("arr2", arr2ArrayNode);
                return objectNode;
            },
            TEST_ITERATIONS,
            "FractalsConcatDataSet" + concatType
        );
    }

    @Test
    public void generateFractalsReplaceAtIndexDataSet() throws IOException {
        DrawInstruction[] drawInstructions = DrawInstruction.values();

        TestUtils.generateJsonTestData(
            (mapper, index, rnd) -> {
                int arrLength = rnd.nextInt(1, 10);
                List<String> arr = new ArrayList<>();
                for (int i = 0; i < arrLength; i++) {
                    arr.add(drawInstructions[rnd.nextInt(drawInstructions.length)].name());
                }
                int idx = rnd.nextInt(arr.size());
                String elem = drawInstructions[rnd.nextInt(drawInstructions.length)].name();

                ArrayNode arrArrayNode = mapper.createArrayNode();
                arr.forEach(arrArrayNode::add);
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.set("arr", arrArrayNode);
                objectNode.put("idx", idx);
                objectNode.put("elem", elem);
                return objectNode;
            },
            TEST_ITERATIONS,
            "FractalsReplaceAtIndexDataSet"
        );
    }

    @Test
    public void generateFractalsDragonCurveDataSet() throws IOException {
        AtomicInteger atomicN = new AtomicInteger(2);

        TestUtils.generateJsonTestData(
            (mapper, index, rnd) -> {
                int n = atomicN.getAndIncrement();
                DrawInstruction[] dragonCurveInstructions = Fractals.dragonCurve(n);
                List<String> expected = new ArrayList<>();
                for (int i = 0; i < dragonCurveInstructions.length; i++) {
                    expected.add(dragonCurveInstructions[i].name());
                }

                ArrayNode arrayNode = mapper.createArrayNode();
                expected.forEach(arrayNode::add);
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("n", n);
                objectNode.set("expected", arrayNode);
                return objectNode;
            },
            8,
            "FractalsDragonCurveDataSet"
        );
    }

    @Test
    public void generateFractalsKochSnowflakeDataSet() throws IOException {
        AtomicInteger atomicN = new AtomicInteger(2);

        TestUtils.generateJsonTestData(
            (mapper, index, rnd) -> {
                int n = atomicN.getAndIncrement();
                DrawInstruction[] kochSnowflakeInstructions = Fractals.kochSnowflake(n);
                List<String> expected = new ArrayList<>();
                for (int i = 0; i < kochSnowflakeInstructions.length; i++) {
                    expected.add(kochSnowflakeInstructions[i].name());
                }

                ArrayNode arrayNode = mapper.createArrayNode();
                expected.forEach(arrayNode::add);
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("n", n);
                objectNode.set("expected", arrayNode);
                return objectNode;
            },
            4,
            "FractalsKochSnowflakeDataSet"
        );
    }
}
