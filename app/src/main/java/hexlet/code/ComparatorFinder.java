package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

public class ComparatorFinder {
    public static List<Map<String, Object>> differ(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> result = new ArrayList<>();
        Set<String> keysSet = new TreeSet<String>(map1.keySet());
        keysSet.addAll(map2.keySet());
        for (String key: keysSet) {
            Map<String, Object> map = new HashMap<>();
            map.put("key", key);
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                map.put("oldValue", map1.get(key));
                map.put("status", "removed");
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                map.put("newValue", map2.get(key));
                map.put("status", "added");
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                map.put("oldValue", map1.get(key));
                map.put("newValue", map2.get(key));
                map.put("status", "updated");
            } else {
                map.put("oldValue", map1.get(key));
                map.put("status", "unchanged");
            }
            result.add(map);
        }
        return result;
    }
}
