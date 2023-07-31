package hexlet.code;

import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Json;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatStyle(
            List<Map<String, Object>> differences, String format) throws IOException {
        if (format.equals("stylish")) {
            return Stylish.getStylish(differences);
        } else if (format.equals("plain")) {
            return Plain.getPlain(differences);
        } else if (format.equals("json")) {
            return Json.getJson(differences);
        } else {
            throw new Error("Unknown format: " + format);
        }
    }
}