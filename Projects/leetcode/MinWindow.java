package leetcode;

class MinWindow {
    private static Map<Character, Integer> letterMap(String t) {
        final Map<Character, Integer> letterMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            final int deficit = letterMap.getOrDefault(c, 0);
            letterMap.put(c, deficit + 1);
        }
        return letterMap;
    }

    private static class Window implements Comparable<Window> {
        int start;
        int end;

        Window(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Window that) {
            final int thisLength = this.end - this.start;
            final int thatLength = that.end - that.start;
            return (
                thisLength > thatLength ? 1 :
                thisLength == thatLength ? 0 :
                -1
            );
        }
    }

    private static Window initialWindow(String s, Map<Character, Integer> letterMap) {
        int start = -1;
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            if (!letterMap.containsKey(c) && start == -1) continue;
            start = start == -1 ? i : start;
            if (!letterMap.containsKey(c)) continue;
            letterMap.put(c, letterMap.get(c) - 1);
            if (letterMap.values().stream().anyMatch(x -> x > 0)) continue;
            return new Window(start, i + 1);
        }
        return null;
    }

    private static void refineWindow(Window window, String s, Map<Character, Integer> letterMap) {
        while (true) {
            final char c = s.charAt(window.start);
            int deficit = letterMap.getOrDefault(c, Integer.MAX_VALUE);
            if (deficit == 0) return;
            window.start++;
            if (deficit > 0) continue;
            letterMap.put(c, deficit + 1);
        }
    }

    public static String minWindow(String s, String t) {
        final Map<Character, Integer> letterMap = letterMap(t);
        Window curWindow = initialWindow(s, letterMap);
        if (curWindow == null) return "";
        refineWindow(curWindow, s, letterMap);
        Window minWindow = curWindow;
        for (int end = curWindow.end; end < s.length(); end++) {
            final char endC = s.charAt(end);
            if (!letterMap.containsKey(endC)) continue;
            letterMap.put(endC, letterMap.get(endC) - 1);
            curWindow = new Window(curWindow.start, end + 1);
            refineWindow(curWindow, s, letterMap);
            minWindow = minWindow.compareTo(curWindow) == -1 ? minWindow : curWindow;
        }
        return s.substring(minWindow.start, minWindow.end);
    }
}
