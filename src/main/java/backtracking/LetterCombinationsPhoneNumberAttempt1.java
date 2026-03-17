package backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 17. Letter Combinations of a Phone Number
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number
 */
class LetterCombinationsPhoneNumberAttempt1 {

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
     * For a telephone number, find all the possible letter combinations it could represent. Uses an
     * iterative approach rather than backtracking.
     *
     * @param digits A string containing between 1 and 4 digits ranging from 2-9
     * @return All possible letter combinations the numbers could represent
     */
    public List<String> letterCombinations(String digits) {
        List<String> priorCombinations = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            char digit = digits.charAt(i);
            if (!digitLetters.containsKey(digit)) continue;
            if (priorCombinations.isEmpty()) {
                priorCombinations.addAll(digitLetters.get(digit));
                continue;
            }
            List<String> newCombinations = new ArrayList<>();
            for (String priorCombination : priorCombinations) {
                for (String letter : digitLetters.get(digit)) {
                    newCombinations.add(priorCombination + letter);
                }
            }
            priorCombinations = newCombinations;
        }
        return priorCombinations;
    }

    public static void main(String[] args) {
        LetterCombinationsPhoneNumberAttempt1 solution =
                new LetterCombinationsPhoneNumberAttempt1();

        String digits = "9";
        List<String> combinations = solution.letterCombinations(digits);
        System.out.printf(
                "Digits [%s] have %s possible combinations:\n%s\n\n",
                digits, combinations.size(), combinations);

        digits = "32";
        combinations = solution.letterCombinations(digits);
        System.out.printf(
                "Digits [%s] have %s possible combinations:\n%s\n\n",
                digits, combinations.size(), combinations);

        digits = "666";
        combinations = solution.letterCombinations(digits);
        System.out.printf(
                "Digits [%s] have %s possible combinations:\n%s\n\n",
                digits, combinations.size(), combinations);

        digits = "7345";
        combinations = solution.letterCombinations(digits);
        System.out.printf(
                "Digits [%s] have %s possible combinations:\n%s\n\n",
                digits, combinations.size(), combinations);

        digits = "22438";
        combinations = solution.letterCombinations(digits);
        System.out.printf(
                "Digits [%s] have %s possible combinations:\n%s\n\n",
                digits, combinations.size(), combinations);

        digits = "295532";
        combinations = solution.letterCombinations(digits);
        System.out.printf(
                "Digits [%s] have %s possible combinations:\n%s\n\n",
                digits, combinations.size(), combinations);
    }
}
