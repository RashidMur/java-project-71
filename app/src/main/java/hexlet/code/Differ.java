package hexlet.code;

import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        StringBuilder result = new StringBuilder("\n{"); //создается объект StringBuilder с начальным значением "\n{",
        // который будет использоваться для формирования результата.

        var map1 = Parser.readFile(filepath1); //- содержимое файлов считывается в два объекта типа Map<String, Object>
        var map2 = Parser.readFile(filepath2); // с помощью метода readFile из класса Parser.

        Set<String> keys = new TreeSet<>(map1.keySet()); // создается множество ключей, которые содержатся в 1 файле.
        keys.addAll(map2.keySet()); // дополняем keys ключами из 2 файла

        for (String key : keys) {
            if (!map1.containsKey(key)) { // если ключ есть только во втором файле,
                // то он добавляется в результат с пометкой +
                result.append("\n  + " + key + ": " + map2.get(key));
            } else if (!map2.containsKey(key)) { //если ключ есть только в первом файле,
                // то он добавляется в результат с пометкой -
                result.append("\n  - " + key + ": " + map1.get(key));
            } else if (map1.get(key).equals(map2.get(key))) { // если значение ключей совпадает в обоих файлах,
                // то ключ и его значение добавляются в результат без пометок.
                result.append("\n    " + key + ": " + map1.get(key));
            } else { // если значение ключа отличается в двух файлах,
                // то ключ и его значения добавляются в результат с пометками - и +.
                result.append("\n  - " + key + ": " + map1.get(key) + "\n" + "  + " + key + ": " + map2.get(key));
            }
        }
        result.append("\n}");
        return result.toString();
    }
}
