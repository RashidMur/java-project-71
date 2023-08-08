package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Json;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatStyle(
            List<Map<String, Object>> differences, String format) throws JsonProcessingException {
        switch (format) {
            case "stylish":
                return Stylish.getStylish(differences);
            case "plain":
                return Plain.getPlain(differences);
            case "json":
                return Json.getJson(differences);
            default:
                throw new RuntimeException("Unknown format: " + format);
        }
    }
}
