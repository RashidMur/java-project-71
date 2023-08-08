package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String getPlain(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder();
        for (Map<String, Object> diffs : differences) {
            switch (diffs.get("status").toString()) {
                case "removed" -> result.append("Property ").append("'")
                        .append(diffs.get("key")).append("'").append(" was removed").append("\n");
                case "added" -> result.append("Property ").append(convertObjectToString(diffs.get("key")))
                        .append(" was added with value: ")
                        .append(convertObjectToString(diffs.get("newValue")))
                        .append("\n");
                case "updated" ->
                        result.append("Property ").append(convertObjectToString(diffs.get("key")))
                                .append(" was updated. From ")
                                .append(convertObjectToString(diffs.get("oldValue"))).append(" to ")
                                .append(convertObjectToString(diffs.get("newValue")))
                                .append("\n");

                default -> result.append("");
            }

        }
        return result.toString().trim();

    }

    public static String convertObjectToString(Object data) {
        if (data == null) {
            return "null";
        } else if (data instanceof Map || data instanceof List<?>) {
            return "[complex value]";
        } else if (data instanceof String) {
            return "'" + data + "'";
        }
        return data.toString();
    }
}
