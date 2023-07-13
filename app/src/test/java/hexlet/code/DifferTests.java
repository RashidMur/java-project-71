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
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
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
                    + "  - buy: true\n"
                    + "    developer: The Indie Stone\n"
                    + "  + frightening: sometimes\n"
                    + "  - name: Project Zomboid\n"
                    + "  + name: project zomboid\n"
                    + "  - version: 41.78.16\n"
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
