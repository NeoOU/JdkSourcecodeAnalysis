package util.collectionsFramework.map;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by demo on 17-4-3.
 */
public class Frequency {
    public static void main(String[] args) {
        String words = "if it is to be it is up to me to delegate";
        String[] strings = words.split(",");
        List<String> s = Arrays.asList(strings);
        Map<String, List<String>> collect = s.stream().collect(Collectors.groupingBy(String::toString));

    }
}
