package hexlet.code;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatStyle(
            List<Map<String, Object>> differences, String format) throws IOException {
        switch (format) {
            case "stylish":
                return Stylish.formatStylish(differences);
            default:
                throw new Error("Unknown format: " + format);
        }
    }
}