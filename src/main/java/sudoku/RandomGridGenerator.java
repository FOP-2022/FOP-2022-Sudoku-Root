package sudoku;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.IntUnaryOperator;

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
                    fillCell(i, j);
                }
            }
        }

        return grid;
    }

    private void fillCell(int i, int j) {
        var offset = random.nextInt(10);

        for (int k = 0; k < 9; k++) {
            grid[i][j] = 1 + (k + offset) % 9;

            if (isSolvable()) {
                return;
            }
        }

        grid[i][j] = 0;
    }

    private boolean isSolvable() {
        if (!isValid()) {
            return false;
        }

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

    private boolean isValid() {
        for (int i = 0; i < 9; i++) {
            if (!checkColumn(grid, i)
                || !checkRow(grid, i)
                || !check3by3(grid, (i / 3) * 3, (i % 3) * 3)) {
                return false;
            }
        }
        return true;
    }

    private boolean check3by3(final int[][] grid, final int posX, final int posY) {
        return isValid(grid, i -> i % 3, i -> i / 3);
    }

    private boolean checkRow(final int[][] grid, final int posY) {
        return isValid(grid, i -> i, i -> posY);
    }

    private boolean checkColumn(final int[][] grid, final int posX) {
        return isValid(grid, i -> posX, i -> i);
    }

    private boolean isValid(final int[][] grid, final IntUnaryOperator xGenerator, final IntUnaryOperator yGenerator) {
        final Set<Integer> nums = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            final int x = xGenerator.applyAsInt(i);
            final int y = yGenerator.applyAsInt(i);
            final int num = grid[y][x];
            if (!nums.add(num)) {
                return false;
            }
        }
        return true;
    }
}
