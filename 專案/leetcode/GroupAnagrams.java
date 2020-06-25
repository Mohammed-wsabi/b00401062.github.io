package leetcode;

import java.util.*;

class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<Integer, List<String>> groups = new HashMap<>();
        for (String str : strs) {
            HashMap<Character, Integer> counter = new HashMap<>();
            for (Character chr : str.toCharArray()) {
                int count = counter.getOrDefault(chr, 0) + 1;
                counter.put(chr, count);
            }
            int hash = getHashCode(counter);
            List<String> group = groups.getOrDefault(hash, new ArrayList<>());
            group.add(str);
            groups.put(hash, group);
        }
        return groups.values().stream().collect(Collectors.toList());
    }
}
