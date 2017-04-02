package util.collectionsFramework.set;

import java.util.*;

/**
 * Created by omed on 2017/3/29.
 */
public class distinctAndOrder {
    public static void main(String[] args) {
        String[] s = {"i", "came", "i", "saw", "i", "left"};
        Set<String> hashSet = new HashSet<>(Arrays.asList(s));
        Set<String> treeSet = new TreeSet<>(Arrays.asList(s));
        Set<String> linkedHashSet = new LinkedHashSet<>(Arrays.asList(s));

        System.out.println(hashSet.size() + " hashSet distinct words: " + hashSet);
        System.out.println(treeSet.size() + " treeSet distinct words: " + treeSet);
        System.out.println(linkedHashSet.size() + " linkedHashSet distinct words: " + linkedHashSet);


        Set<String> unique = new HashSet<>();
        Set<String> duplicate = new HashSet<>();
        for (String i : s) {
            if (!unique.add(i)) {
                duplicate.add(i);
            }
        }

        unique.removeAll(duplicate);

        System.out.println("unique words :" + unique);
        System.out.println("duplicate words :" + duplicate);

        Comparator<Integer> compare = Integer::compare;
        Comparator<Integer> reversed = compare.reversed();
    }
}
