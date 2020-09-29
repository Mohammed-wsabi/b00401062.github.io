package codechef;

public static class BST<K extends Comparable<K>> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;
    private class Node {
        private K key;
        private Node left, right;
        private boolean color;
        private int size;

        public Node(K key, boolean color, int size) {
            this.key = key;
            this.color = color;
            this.size = size;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(K key) {
        return contains(root, key);
    }

    public boolean contains(Node x, K key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return true;
        }
        return false;
    }

    public void add(K key) {
        if (key == null) throw new IllegalArgumentException();
        root = add(root, key);
        root.color = BLACK;
    }

    private Node add(Node h, K key) {
        if (h == null) return new Node(key, RED, 1);
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = add(h.left, key);
        else if (cmp > 0) h.right = add(h.right, key);
        else return h;
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    public void removeMin() {
        if (isEmpty()) throw new NoSuchElementException();
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = removeMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node removeMin(Node h) {
        if (h.left == null)
            return null;
        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);
        h.left = removeMin(h.left);
        return balance(h);
    }

    public void removeMax() {
        if (isEmpty()) throw new NoSuchElementException();
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = removeMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node removeMax(Node h) {
        if (isRed(h.left))
            h = rotateRight(h);
        if (h.right == null)
            return null;
        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);
        h.right = removeMax(h.right);
        return balance(h);
    }

    public void remove(K key) {
        if (key == null) throw new IllegalArgumentException();
        if (!contains(key)) return;
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = remove(root, key);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node remove(Node h, K key) {
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = remove(h.left, key);
        } else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.right = removeMin(h.right);
            } else h.right = remove(h.right, key);
        }
        return balance(h);
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    private Node balance(Node h) {
        if (isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public K min() {
        if (isEmpty()) throw new NoSuchElementException();
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    public K max() {
        if (isEmpty()) throw new NoSuchElementException();
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

    public K floor(K key) {
        if (key == null) throw new IllegalArgumentException();
        if (isEmpty()) throw new NoSuchElementException();
        Node x = floor(root, key);
        if (x == null) throw new NoSuchElementException();
        else return x.key;
    }

    private Node floor(Node x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public K ceiling(K key) {
        if (key == null) throw new IllegalArgumentException();
        if (isEmpty()) throw new NoSuchElementException();
        Node x = ceiling(root, key);
        if (x == null) throw new NoSuchElementException();
        else return x.key;
    }

    private Node ceiling(Node x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else return x;
    }

    public K select(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new IllegalArgumentException();
        }
        return select(root, rank);
    }

    private K select(Node x, int rank) {
        if (x == null) return null;
        int leftSize = size(x.left);
        if (leftSize > rank) return select(x.left, rank);
        else if (leftSize < rank) return select(x.right, rank - leftSize - 1);
        else return x.key;
    }

    public int rank(K key) {
        if (key == null) throw new IllegalArgumentException();
        return rank(key, root);
    }

    private int rank(K key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    public Iterable<K> keys() {
        if (isEmpty()) return new ArrayDeque<K>();
        return keys(min(), max(), true);
    }

    public Iterable<K> keys(K lo, K hi) {
        if (lo == null) throw new IllegalArgumentException();
        if (hi == null) throw new IllegalArgumentException();
        Queue<K> queue = new ArrayDeque<K>();
        keys(root, queue, lo, hi);
        return queue;
    }

    public Iterable<K> keys(K lo, K hi, boolean inclusive) {
        if (lo == null) throw new IllegalArgumentException();
        if (hi == null) throw new IllegalArgumentException();
        Queue<K> queue = new ArrayDeque<K>();
        keys(root, queue, lo, hi);
        if (inclusive) queue.add(hi);
        return queue;
    }

    private void keys(Node x, Queue<K> queue, K lo, K hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi > 0) queue.add(x.key);
        if (cmphi >= 0) keys(x.right, queue, lo, hi);
    }

    public int size(K lo, K hi) {
        if (lo == null) throw new IllegalArgumentException();
        if (hi == null) throw new IllegalArgumentException();
        if (lo.compareTo(hi) > 0) return 0;
        else return rank(hi) - rank(lo);
    }

    private boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node x, K min, K max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.size != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (K key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }

    private boolean is23() {
        return is23(root);
    }

    private boolean is23(Node x) {
        if (x == null) return true;
        if (isRed(x.right)) return false;
        if (x != root && isRed(x) && isRed(x.left))
            return false;
        return is23(x.left) && is23(x.right);
    }

    private boolean isBalanced() {
        int black = 0;
        Node x = root;
        while (x != null) {
            if (!isRed(x)) black++;
            x = x.left;
        }
        return isBalanced(root, black);
    }

    private boolean isBalanced(Node x, int black) {
        if (x == null) return black == 0;
        if (!isRed(x)) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }
}
