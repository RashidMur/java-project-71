package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DifferTests {
    private static final String PATH1 = "src/test/resources/fileNumb1.";
    private static final String PATH2 = "src/test/resources/fileNumb2.";
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;
    private static String readString(String toNormalize) throws IOException {
        return Files.readString(Paths.get(toNormalize).toAbsolutePath().normalize());
    }
    @BeforeAll
    public static void initExpected() throws IOException {
        expectedStylish = readString("src/test/resources/expectedStylish");
        expectedPlain = readString("src/test/resources/expectedPlain");
        expectedJson = readString("src/test/resources/expectedJson");
    }
    @ParameterizedTest(name = "Input {0} files - output wrong - format")
    @ValueSource (strings = {"json", "yml"})
    public void wrongFilepath(String format) {
        String testFile1 = PATH1 + format;
        var thrown = catchThrowable(
                () -> Differ.generate(testFile1, "")
        );
        assertThat(thrown).isInstanceOf(Exception.class);
    }
    @ParameterizedTest(name = "Input {0} files - output stylish format")
    @ValueSource (strings = {"json", "yml"})
    public void testStylish(String format) throws Exception {
        String testFile1 = PATH1 + format;
        String testFile2 = PATH2 + format;
        assertEquals(expectedStylish, Differ.generate(testFile1, testFile2, "stylish"));
    }

    @ParameterizedTest(name = "Input {0} files - output plain format")
    @ValueSource (strings = {"json", "yml"})
    public void testPlain(String format) throws Exception {
        String testFile1 = PATH1 + format;
        String testFile2 = PATH2 + format;
        assertEquals(expectedPlain, Differ.generate(testFile1, testFile2, "plain"));
    }

    @ParameterizedTest(name = "Input {0} files - output json format")
    @ValueSource (strings = {"json", "yml"})
    public void testJson(String format) throws Exception {
        String testFile1 = PATH1 + format;
        String testFile2 = PATH2 + format;
        assertEquals(expectedJson, Differ.generate(testFile1, testFile2, "json"));
    }
    @ParameterizedTest(name = "Input {0} files - output default format")
    @ValueSource (strings = {"json", "yml"})
    public void testGenerateDefault(String format) throws Exception {
        String testFile1 = PATH1 + format;
        String testFile2 = PATH2 + format;
        assertEquals(expectedStylish, Differ.generate(testFile1, testFile2));
    }
}

