package sudoku;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuSolverImpl implements SudokuSolver {

    @Override
    public void solve(int[][] grid) {
        try {
            solve0(grid);
        } catch (ThreadDeath ignore) {
        }
    }

    public void solve0(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    var placements = getPlacements(grid, i, j);

                    for (int k : placements) {
                        grid[i][j] = k;
                        solve0(grid);
                        grid[i][j] = 0;
                    }
                    return;
                }
            }
        }

        throw new ThreadDeath();
    }

    @NotNull
    private List<Integer> getPlacements(int[][] grid, int i, int j) {
        var placements = IntStream
            .rangeClosed(1, 9)
            .filter(k ->
                isUwuPlacement(grid, i, j, k))
            .boxed()
            .collect(Collectors.toList());
        Collections.shuffle(placements);
        return placements;
    }

    private boolean isUwuPlacement(int[][] grid, int i, int j, int k) {
        for (int l = 0; l < 9; l++) {
            if (grid[i][l] == k || grid[l][j] == k) {
                return false;
            }
        }

        var x0 = (i/3) * 3;
        var y0 = (j/3) * 3;

        for (int l = 0; l < 3; l++) {
            for (int m = 0; m < 3; m++) {
                if (grid[x0+l][y0+m] == k) {
                    return false;
                }
            }
        }

        return true;
    }
}
