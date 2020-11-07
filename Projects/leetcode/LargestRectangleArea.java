package leetcode;

class LargestRectangleArea {
    private static class Bar {
        final int i;
        final int height;

        Bar(int i, int height) {
            this.i = i;
            this.height = height;
        }
    }

    public static int largestRectangleArea(int[] heights) {
        Stack<Bar> stack = new Stack<>();
        stack.push(new Bar(-1, -1));
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int startIndex = i;
            final int height = heights[i];
            while (stack.peek().height >= height) {
                Bar bar = stack.pop();
                startIndex = bar.i;
                maxArea = Math.max(maxArea, bar.height * (i - startIndex));
            }
            stack.push(new Bar(startIndex, height));
        }
        while (!stack.isEmpty()) {
            Bar bar = stack.pop();
            maxArea = Math.max(maxArea, bar.height * (heights.length - bar.i));
        }
        return maxArea;
    }
}
