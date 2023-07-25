package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        String data1 = getData(filePath1);
        String data2 = getData(filePath2);

        String fileType1 = getFType(filePath1);
        String fileType2 = getFType(filePath2);

        Map<String, Object> map1 = Parser.readFile(data1, fileType1);
        Map<String, Object> map2 = Parser.readFile(data2, fileType2);

        List<Map<String, Object>> result = ComparatorFinder.differ(map1, map2);
        return Formatter.formatStyle(result, format);
    }
    public static String generate(String pathfile1, String pathfile2) throws Exception {
        return generate(pathfile1, pathfile2, "stylish");
    }
    public static String getData(String filepath) throws Exception {
        Path path = Paths.get(filepath.substring(filepath.indexOf("src")));
        return Files.readString(path);

    }
    public static String getFType(String filepath) {

        return filepath.substring(filepath.lastIndexOf(".") + 1);
    }
}
