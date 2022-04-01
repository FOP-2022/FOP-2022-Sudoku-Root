package sudoku;

import java.util.HashSet;
import java.util.Set;
import java.util.function.IntUnaryOperator;

public class GridCheckerImpl implements GridChecker {
    @Override
    public void check(final int[][] grid) {
        checkZeros(grid);
        for (int i = 0; i < 9; i++) {
            checkColumn(grid, i);
            checkRow(grid, i);
            check3by3(grid, (i / 3) * 3, (i % 3) * 3);
        }
    }

    private void checkZeros(final int[][] grid) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (grid[y][x] == 0) {
                    throw new GridCheckException(x, y, "Zero");
                }
            }
        }
    }

    private void check3by3(final int[][] grid, final int posX, final int posY) {
        check(grid, i -> i % 3, i -> i / 3);
    }

    private void checkRow(final int[][] grid, final int posY) {
        check(grid, i -> i, i -> posY);
    }

    private void checkColumn(final int[][] grid, final int posX) {
        check(grid, i -> posX, i -> i);
    }

    private void check(final int[][] grid, final IntUnaryOperator xGenerator, final IntUnaryOperator yGenerator) {
        final Set<Integer> nums = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            final int x = xGenerator.applyAsInt(i);
            final int y = yGenerator.applyAsInt(i);
            final int num = grid[y][x];
            if (!nums.add(num)) {
                throw new GridCheckException(x, y, "Duplicate number " + num);
            }
        }
    }
}
