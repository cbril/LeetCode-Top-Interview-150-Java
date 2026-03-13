package bitmanipulation;

import lombok.NoArgsConstructor;

/** 67. Add Binary https://leetcode.com/problems/add-binary */
@NoArgsConstructor
public class AddBinary {
    /**
     * Given two binary strings a and b, return their sum as a binary string.
     *
     * @param a binary number as a string
     * @param b binary number as a string
     * @return sum of a and b as binary string
     */
    public String addBinary(String a, String b) {
        // My approach is to do it the way I would do arithmetic on paper
        StringBuilder sb = new StringBuilder();
        int idxA = a.length() - 1;
        int idxB = b.length() - 1;
        boolean carryOver = false;

        while (idxA >= 0 || idxB >= 0) {
            int addend1 = 0;
            if (idxA >= 0) {
                addend1 = a.charAt(idxA--) == '1' ? 1 : 0;
            }
            int addend2 = 0;
            if (idxB >= 0) {
                addend2 = b.charAt(idxB--) == '1' ? 1 : 0;
            }

            int sum = addend1 + addend2;
            if (carryOver) {
                sum += 1;
                carryOver = false;
            }
            if (sum > 1) {
                carryOver = true;
                sb.insert(0, sum - 2);
            } else {
                sb.insert(0, sum);
            }
        }
        if (carryOver) {
            sb.insert(0, "1");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AddBinary solution = new AddBinary();
        String a = "101";
        String b = "1";
        System.out.printf("%s + %s = %s\n", a, b, solution.addBinary(a, b));

        a = "1111111111111";
        b = "100001";
        System.out.printf("%s + %s = %s\n", a, b, solution.addBinary(a, b));

        a = "0";
        b = "0";
        System.out.printf("%s + %s = %s\n", a, b, solution.addBinary(a, b));
    }
}
