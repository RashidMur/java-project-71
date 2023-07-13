package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> readFile(String filepath) throws Exception {
        Map<String, Object> map = new HashMap<>();

        Path path = Paths.get(filepath)// создается объект Path, который представляет путь к файлу, указанному в аргументе метода.
                .toAbsolutePath() // Метод toAbsolutePath() возвращает абсолютный путь к файлу,
                .normalize(); // а метод normalize() приводит путь к каноническому виду.
        String content = Files.readString(path); // читаем файл path и записываем в строку content
        // в зависимости от расширения файла, создается объект ObjectMapper класса Jackson, который будет использоваться
        // для десериализации содержимого файла. Если файл имеет расширение ".json", то создается объект ObjectMapper,
        // а если расширение ".yml", то создается объект YAMLMapper.

        if (filepath.endsWith("json")) {
            ObjectMapper mapper = new ObjectMapper();
            map = mapper.readValue(content, new TypeReference<Map<String, Object>>() { });
        } else if (filepath.endsWith("yml")) {
            ObjectMapper mapper = new YAMLMapper();
            map  = mapper.readValue(content, new TypeReference<Map<String, Object>>() { });
        }
        return map;
    }
}