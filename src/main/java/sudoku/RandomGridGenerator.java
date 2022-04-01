package sudoku;

import java.util.Random;

public class RandomGridGenerator implements GridGenerator {

    private final Random random = new Random();

    private final SudokuSolver solver = new SudokuSolverImpl();

    private int[][] grid = null;

    @Override
    public int[][] createGrid() {
        grid = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (random.nextDouble() < 0.1) {
                    if (!fillCell(i, j)) {
                        grid[i][j] = 0;
                    }
                }
            }
        }

        return grid;
    }

    private boolean fillCell(int i, int j) {
        var offset = random.nextInt(10);

        for (int k = 0; k < 9; k++) {
            grid[i][j] = 1 + (k + offset) % 9;

            if (isSolvable()) {
                return true;
            }
        }

        return false;
    }

    private boolean isSolvable() {
        var copy = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                copy[i][j] = grid[i][j];
            }
        }

        solver.solve(copy);
        return !hasZeros(copy);
    }

    private boolean hasZeros(int[][] copy) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (copy[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
