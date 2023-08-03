package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DifferTests {
    private static final String PATH1_JSON = "src/test/resources/fileNumb1.json";
    private static final String PATH2_JSON = "src/test/resources/fileNumb2.json";
    private static final String PATH3_YML = "src/test/resources/fileNumb1.yml";
    private static final String PATH4_YML = "src/test/resources/fileNumb2.yml";
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
    @Test
    public void wrongFilepath() throws Exception {
        var thrown = catchThrowable(
                () -> Differ.generate(PATH1_JSON, "")
        );
        assertThat(thrown).isInstanceOf(Exception.class);
    }
    @Test
    public void testStylish1() throws Exception {
        assertEquals(expectedStylish, Differ.generate(PATH1_JSON, PATH2_JSON, "stylish"));
    }

    @Test
    public void testStylish2() throws Exception {
        assertEquals(expectedStylish, Differ.generate(PATH3_YML, PATH4_YML, "stylish"));
    }

    @Test
    public void testPlain1() throws Exception {
        assertEquals(expectedPlain, Differ.generate(PATH1_JSON, PATH2_JSON, "plain"));
    }

    @Test
    public void testPlain2() throws Exception {
        assertEquals(expectedPlain, Differ.generate(PATH3_YML, PATH4_YML, "plain"));
    }

    @Test
    public void testJson1() throws Exception {
        assertEquals(expectedJson, Differ.generate(PATH1_JSON, PATH2_JSON, "json"));
    }

    @Test
    public void testJson2() throws Exception {
        assertEquals(expectedJson, Differ.generate(PATH3_YML, PATH4_YML, "json"));
    }
    @Test
    public void testGenerateStylishFromYaml() throws Exception {
        assertEquals(expectedStylish, Differ.generate(PATH3_YML, PATH4_YML));
    }
    @Test
    public void testGenerateStylishFromJson() throws Exception {
        assertEquals(expectedStylish, Differ.generate(PATH1_JSON, PATH2_JSON));
    }
}

