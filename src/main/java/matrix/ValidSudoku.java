package matrix;

import java.util.HashSet;

/**
 * <a href="https://leetcode.com/problems/valid-sudoku/description">36. Valid Sudoku</a>
 */
public class ValidSudoku {

    /**
     * Determine if the Sudoku board is correct. It can be correct if it is not completed. If there
     * are any incorrect squares, returns false.
     * @param board a 9x9 matrix with chars. A '.' char indicates a blank space
     * @return true if the Sudoku board contains no incorrect squares
     */
    public boolean isValidSudoku(char[][] board) {
        // Check one 3x3 box at a time to track which numbers have been seen
        for (int boxRow = 0; boxRow < 9; boxRow += 3) {
            for (int boxCol = 0; boxCol < 9; boxCol += 3) {
                HashSet<Character> presentInBox = new HashSet<>();

                for (int row = boxRow; row < boxRow + 3; row++) {
                    for (int col = boxCol; col < boxCol + 3; col++) {
                        if (board[row][col] == '.') {
                            continue;
                        }

                        if (presentInBox.contains(board[row][col])) {
                            return false;
                        } else {
                            presentInBox.add(board[row][col]);
                        }
                        // check whole row for this same digit, only check to the right
                        // since we are moving right
                        for (int rowCheck = row + 1; rowCheck < 9; rowCheck++) {
                            if (board[rowCheck][col] != '.' && board[rowCheck][col] == board[row][col]) {
                                return false;
                            }
                        }
                        // check whole column for this same digit, only check down
                        // since we are moving down
                        for (int colCheck = col + 1; colCheck < 9; colCheck++) {
                            if (board[row][colCheck] != '.' && board[row][colCheck] == board[row][col]) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidSudoku solution = new ValidSudoku();
        char[][] board = {
                {'8','3','.','.','7','.','.','.','.'}
                ,{'6','.','.','1','9','5','.','.','.'}
                ,{'.','9','8','.','.','.','.','6','.'}
                ,{'8','.','.','.','6','.','.','.','3'}
                ,{'4','.','.','8','.','3','.','.','1'}
                ,{'7','.','.','.','2','.','.','.','6'}
                ,{'.','6','.','.','.','.','2','8','.'}
                ,{'.','.','.','4','1','9','.','.','5'}
                ,{'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println(solution.isValidSudoku(board));

        board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(solution.isValidSudoku(board));
    }
}
