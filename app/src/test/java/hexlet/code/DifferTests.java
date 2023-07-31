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

    @Test
    public void testPlain() throws Exception {
        var filepath1 = "src/test/resources/fileNumb1.json";
        var filepath2 = "src/test/resources/fileNumb2.json";

        var expected =
                  "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'";

        var actual = Differ.generate(filepath1, filepath2, "plain");

        assertEquals(expected, actual);
    }
    @Test
    public void testPlainYml() throws Exception {
        var filepath1 = "src/test/resources/fileNumb1.yml";
        var filepath2 = "src/test/resources/fileNumb2.yml";

        var expected =
                "Property 'chars2' was updated. From [complex value] to false\n"
                        + "Property 'checked' was updated. From false to true\n"
                        + "Property 'default' was updated. From null to [complex value]\n"
                        + "Property 'id' was updated. From 45 to null\n"
                        + "Property 'key1' was removed\n"
                        + "Property 'key2' was added with value: 'value2'\n"
                        + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                        + "Property 'numbers3' was removed\n"
                        + "Property 'numbers4' was added with value: [complex value]\n"
                        + "Property 'obj1' was added with value: [complex value]\n"
                        + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                        + "Property 'setting2' was updated. From 200 to 300\n"
                        + "Property 'setting3' was updated. From true to 'none'";

        var actual = Differ.generate(filepath1, filepath2, "plain");

        assertEquals(expected, actual);
    }
    @Test
    public void testJsonForm() throws Exception {
        var filepath1 = "src/test/resources/fileNumb1.json";
        var filepath2 = "src/test/resources/fileNumb2.json";

        var expected = "[ {\n"
                + "  \"key\" : \"chars1\",\n"
                + "  \"oldValue\" : [ \"a\", \"b\", \"c\" ],\n"
                + "  \"status\" : \"unchanged\"\n"
                + "}, {\n"
                + "  \"key\" : \"chars2\",\n"
                + "  \"oldValue\" : [ \"d\", \"e\", \"f\" ],\n"
                + "  \"newValue\" : false,\n"
                + "  \"status\" : \"updated\"\n"
                + "}, {\n"
                + "  \"key\" : \"checked\",\n"
                + "  \"oldValue\" : false,\n"
                + "  \"newValue\" : true,\n"
                + "  \"status\" : \"updated\"\n"
                + "}, {\n"
                + "  \"key\" : \"default\",\n"
                + "  \"oldValue\" : null,\n"
                + "  \"newValue\" : [ \"value1\", \"value2\" ],\n"
                + "  \"status\" : \"updated\"\n"
                + "}, {\n"
                + "  \"key\" : \"id\",\n"
                + "  \"oldValue\" : 45,\n"
                + "  \"newValue\" : null,\n"
                + "  \"status\" : \"updated\"\n"
                + "}, {\n"
                + "  \"key\" : \"key1\",\n"
                + "  \"oldValue\" : \"value1\",\n"
                + "  \"status\" : \"removed\"\n"
                + "}, {\n"
                + "  \"key\" : \"key2\",\n"
                + "  \"newValue\" : \"value2\",\n"
                + "  \"status\" : \"added\"\n"
                + "}, {\n"
                + "  \"key\" : \"numbers1\",\n"
                + "  \"oldValue\" : [ 1, 2, 3, 4 ],\n"
                + "  \"status\" : \"unchanged\"\n"
                + "}, {\n"
                + "  \"key\" : \"numbers2\",\n"
                + "  \"oldValue\" : [ 2, 3, 4, 5 ],\n"
                + "  \"newValue\" : [ 22, 33, 44, 55 ],\n"
                + "  \"status\" : \"updated\"\n"
                + "}, {\n"
                + "  \"key\" : \"numbers3\",\n"
                + "  \"oldValue\" : [ 3, 4, 5 ],\n"
                + "  \"status\" : \"removed\"\n"
                + "}, {\n"
                + "  \"key\" : \"numbers4\",\n"
                + "  \"newValue\" : [ 4, 5, 6 ],\n"
                + "  \"status\" : \"added\"\n"
                + "}, {\n"
                + "  \"key\" : \"obj1\",\n"
                + "  \"newValue\" : {\n"
                + "    \"nestedKey\" : \"value\",\n"
                + "    \"isNested\" : true\n"
                + "  },\n"
                + "  \"status\" : \"added\"\n"
                + "}, {\n"
                + "  \"key\" : \"setting1\",\n"
                + "  \"oldValue\" : \"Some value\",\n"
                + "  \"newValue\" : \"Another value\",\n"
                + "  \"status\" : \"updated\"\n"
                + "}, {\n"
                + "  \"key\" : \"setting2\",\n"
                + "  \"oldValue\" : 200,\n"
                + "  \"newValue\" : 300,\n"
                + "  \"status\" : \"updated\"\n"
                + "}, {\n"
                + "  \"key\" : \"setting3\",\n"
                + "  \"oldValue\" : true,\n"
                + "  \"newValue\" : \"none\",\n"
                + "  \"status\" : \"updated\"\n"
                + "} ]";

        var actual = Differ.generate(filepath1, filepath2, "json");

        assertEquals(expected, actual);
    }
}

