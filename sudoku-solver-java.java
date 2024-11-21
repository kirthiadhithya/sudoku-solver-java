import java.util.Scanner;

public class SudokuSolver {

    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] board = new int[GRID_SIZE][GRID_SIZE];

        System.out.println("Enter the Sudoku puzzle row by row (use 0 for empty cells):");

        // Read the Sudoku board
        for (int i = 0; i < GRID_SIZE; i++) {
            System.out.println("Enter row " + (i + 1) + ": ");
            for (int j = 0; j < GRID_SIZE; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Solving the Sudoku puzzle...");

        if (solveSudoku(board)) {
            System.out.println("Solved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("This Sudoku puzzle cannot be solved.");
        }

        scanner.close();
    }

    private static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) { // Find an empty cell
                    for (int num = 1; num <= 9; num++) {
                        if (isValidPlacement(board, num, row, col)) {
                            board[row][col] = num;

                            // Recursively attempt to solve the rest
                            if (solveSudoku(board)) {
                                return true;
                            }

                            // Backtrack
                            board[row][col] = 0;
                        }
                    }

                    return false; // No valid number found, so backtrack
                }
            }
        }
        return true; // All cells are filled
    }

    private static boolean isValidPlacement(int[][] board, int number, int row, int col) {
        // Check the row
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return false;
            }
        }

        // Check the column
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == number) {
                return false;
            }
        }

        // Check the 3x3 subgrid
        int subgridRowStart = row - row % 3;
        int subgridColStart = col - col % 3;
        for (int i = subgridRowStart; i < subgridRowStart + 3; i++) {
            for (int j = subgridColStart; j < subgridColStart + 3; j++) {
                if (board[i][j] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int col = 0; col < GRID_SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }
}
