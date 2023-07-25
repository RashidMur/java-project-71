package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DifferTests {
    @Test
    public void testGenerate() throws Exception {
        var filepath1 = "src/test/resources/fileNumb1.json";
        var filepath2 = "src/test/resources/fileNumb2.json";

        var expected = "\n" + "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}";
        var actual = Differ.generate(filepath1, filepath2);

        assertEquals(expected, actual);
    }

    @Test
    public void testIfExists() {
        var filepath1 = "src/test/resources/fileNumb3.json";
        var filepath2 = "src/test/resources/fileNumb2.json";

        var thrown = catchThrowable(
                () -> Differ.generate(filepath1, filepath2)
        );
        assertThat(thrown).isInstanceOf(Exception.class);
    }

    @Test
    public void testEmptyFile() throws Exception {
        var filepath1 = "src/test/resources/fileNumb1.json";
        var filepath2 = "src/test/resources/fileEmpty.json";

        var thrown = catchThrowable(
                () -> Differ.generate(filepath1, filepath2)
        );
        assertThat(thrown).isInstanceOf(Exception.class);
    }

    @Test
    public void wrongFilepath() throws Exception {
        var filepath1 = "src/test/resources/fileNumb1.json";
        var filepath2 = "file2";

        var thrown = catchThrowable(
                () -> Differ.generate(filepath1, filepath2)
        );
        assertThat(thrown).isInstanceOf(Exception.class);
    }

        @Test
        public void testYmlFiles() throws Exception {
            var filepath1 = "src/test/resources/fileNumb1.yml";
            var filepath2 = "src/test/resources/fileNumb2.yml";

            var expected = "\n" + "{\n"
                    + "    chars1: [a, b, c]\n"
                    + "  - chars2: [d, e, f]\n"
                    + "  + chars2: false\n"
                    + "  - checked: false\n"
                    + "  + checked: true\n"
                    + "  - default: null\n"
                    + "  + default: [value1, value2]\n"
                    + "  - id: 45\n"
                    + "  + id: null\n"
                    + "  - key1: value1\n"
                    + "  + key2: value2\n"
                    + "    numbers1: [1, 2, 3, 4]\n"
                    + "  - numbers2: [2, 3, 4, 5]\n"
                    + "  + numbers2: [22, 33, 44, 55]\n"
                    + "  - numbers3: [3, 4, 5]\n"
                    + "  + numbers4: [4, 5, 6]\n"
                    + "  + obj1: {nestedKey=value, isNested=true}\n"
                    + "  - setting1: Some value\n"
                    + "  + setting1: Another value\n"
                    + "  - setting2: 200\n"
                    + "  + setting2: 300\n"
                    + "  - setting3: true\n"
                    + "  + setting3: none\n"
                    + "}";
            var actual = Differ.generate(filepath1, filepath2);

            assertEquals(expected, actual);
    }
    @Test
    public void wrongYmlFilepath() throws Exception {
        var filepath1 = "src/test/resources/fileNumb1.yml";
        var filepath2 = "";

        var thrown = catchThrowable(
                () -> Differ.generate(filepath1, filepath2)
        );
        assertThat(thrown).isInstanceOf(Exception.class);
    }
}
