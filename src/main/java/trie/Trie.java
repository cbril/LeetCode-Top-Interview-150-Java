package trie;

/** 208. Implement Trie https://leetcode.com/problems/implement-trie-prefix-tree */
class Trie {
    private static class Node {
        private boolean isEndOfWord;
        private final Node[] childNodes;

        private Node() {
            // The index of the array represents the following letter. There are 26 letters in the
            // alphabet.
            // e.g. index 0 = 'a', index 1 = 'b', index 25 = 'z'
            childNodes = new Node[26];
        }
    }

    private final Node rootNode;

    public Trie() {
        rootNode = new Node();
    }

    /**
     * @param c lowercase character between 'a' and 'z'
     * @return index of the character, 'a' being 0 and 'z' being 25
     */
    private static int indexOfChar(char c) {
        return c - 'a';
    }

    /**
     * Makes the string lowercase and without spaces. LeetCode constraints said that input will only
     * be lowercase English letters, but LeetCode gave test cases with capital letters.
     *
     * @param s a string
     * @return the string to lowercase and with any character other than a-z removed
     */
    private static String sanitizeString(String s) {
        return s.toLowerCase().replaceAll("[^a-z]", "");
    }

    /**
     * @param word a string of lowercase letters
     * @return The node if it is present in the trie, otherwise null
     */
    private Node findNode(String word) {
        Node currentNode = rootNode;
        for (char letter : word.toCharArray()) {
            int index = indexOfChar(letter);
            if (currentNode.childNodes[index] == null) return null;
            currentNode = currentNode.childNodes[index];
        }
        return currentNode;
    }

    /**
     * Insert the word into the trie.
     *
     * @param word a word with 1 or more lowercase letters
     */
    public void insert(String word) {
        word = sanitizeString(word);
        Node currentNode = rootNode;
        for (char letter : word.toCharArray()) {
            int index = indexOfChar(letter);
            if (currentNode.childNodes[index] == null) {
                currentNode.childNodes[index] = new Node();
            }
            currentNode = currentNode.childNodes[index];
        }
        // Once out of the loop, currentNode represents the last letter
        currentNode.isEndOfWord = true;
    }

    /**
     * Search the trie for the given word.
     *
     * @param word a word with 1 or more lowercase letters
     * @return true if the word has been inserted into the trie
     */
    public boolean search(String word) {
        Node node = findNode(sanitizeString(word));
        if (node == null) return false;
        return node.isEndOfWord;
    }

    /**
     * Search the trie for the given prefix.
     *
     * @param prefix a string of between 0 and 2000 lowercase letters
     * @return true if any words with the given prefix have been inserted into the trie
     */
    public boolean startsWith(String prefix) {
        Node node = findNode(sanitizeString(prefix));
        return node != null;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("Trie");
        trie.insert("insert");
        trie.insert("search");
        trie.insert("search");
        trie.insert("searching");
        trie.insert("startsWith");
        trie.search("nope");
        trie.search("search");
        trie.startsWith("int");
        trie.startsWith("ins");
        trie.startsWith("insert");
    }
}
