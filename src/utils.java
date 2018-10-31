import java.util.*;
import java.util.stream.Collectors;

/**
 * class for storing general purpose functions
 */
class utils {

    /**
     * sort the provided map by value comparison and orders by descending then returns the result with 10 limit.
     *
     * @param tagMap provided map with all tag data
     * @return top 10 tags by value descending order
     * <p>
     * reference:  1) https://stackoverflow.com/a/23846961/3796452
     * 2) https://stackoverflow.com/a/109389/3796452
     */
    static <K, V> Map<K, V> sortMapByValue(Map<K, V> tagMap) {
        List<Map.Entry<K, V>> entryList = new LinkedList<>(tagMap.entrySet());
        entryList.sort((Comparator<Object>) (o2, o1) -> ((Comparable<V>) ((Map.Entry<K, V>) (o1)).getValue())
                .compareTo(((Map.Entry<K, V>) (o2)).getValue()));

        return entryList.stream()
                .limit(10)              // limits output result to 10
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));
    }
}
