package backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 17. Letter Combinations of a Phone Number
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number
 */
class LetterCombinationsPhoneNumberAttempt2 {

    private static final Map<Character, List<String>> digitLetters =
            Map.of(
                    '2', List.of("a", "b", "c"),
                    '3', List.of("d", "e", "f"),
                    '4', List.of("g", "h", "i"),
                    '5', List.of("j", "k", "l"),
                    '6', List.of("m", "n", "o"),
                    '7', List.of("p", "q", "r", "s"),
                    '8', List.of("t", "u", "v"),
                    '9', List.of("w", "x", "y", "z"));

    /**
     * Backtracking exhaustive search to find all possible combinations of digits represented as
     * letters.
     *
     * @param digits The string containing 1-4 digits in the range of 2-9
     * @param index The index of the character of the string being explored
     * @param sb StringBuilder to build combinations of letters
     * @param combinations Storage of all complete letter combinations found so far
     */
    private static void exploreOptions(
            String digits, int index, StringBuilder sb, List<String> combinations) {
        if (index == digits.length()) {
            // this is the end of the decision tree
            combinations.add(sb.toString());
            return;
        }
        char digit = digits.charAt(index);
        for (String letter : digitLetters.get(digit)) {
            sb.append(letter);
            exploreOptions(digits, index + 1, sb, combinations);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * For a telephone number, find all the possible letter combinations it could represent. Using a
     * backtracking approach as LeetCode intended.
     *
     * @param digits A string containing between 1 and 4 digits ranging from 2-9
     * @return All possible letter combinations the numbers could represent
     */
    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) return List.of();
        StringBuilder sb = new StringBuilder();
        List<String> combinations = new ArrayList<>();
        exploreOptions(digits, 0, sb, combinations);
        return combinations;
    }

    public static void main(String[] args) {
        String digits = "9";
        List<String> combinations =
                LetterCombinationsPhoneNumberAttempt2.letterCombinations(digits);
        System.out.printf(
                "Digits [%s] have %s possible combinations:\n%s\n\n",
                digits, combinations.size(), combinations);

        digits = "32";
        combinations = LetterCombinationsPhoneNumberAttempt2.letterCombinations(digits);
        System.out.printf(
                "Digits [%s] have %s possible combinations:\n%s\n\n",
                digits, combinations.size(), combinations);

        digits = "666";
        combinations = LetterCombinationsPhoneNumberAttempt2.letterCombinations(digits);
        System.out.printf(
                "Digits [%s] have %s possible combinations:\n%s\n\n",
                digits, combinations.size(), combinations);

        digits = "7345";
        combinations = LetterCombinationsPhoneNumberAttempt2.letterCombinations(digits);
        System.out.printf(
                "Digits [%s] have %s possible combinations:\n%s\n\n",
                digits, combinations.size(), combinations);

        digits = "22438";
        combinations = LetterCombinationsPhoneNumberAttempt2.letterCombinations(digits);
        System.out.printf(
                "Digits [%s] have %s possible combinations:\n%s\n\n",
                digits, combinations.size(), combinations);

        digits = "295532";
        combinations = LetterCombinationsPhoneNumberAttempt2.letterCombinations(digits);
        System.out.printf(
                "Digits [%s] have %s possible combinations:\n%s\n\n",
                digits, combinations.size(), combinations);
    }
}
