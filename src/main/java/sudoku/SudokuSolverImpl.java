package sudoku;

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
                    for (int k = 1; k <= 9; k++) {
                        if (isUwuPlacement(grid, i, j, k)) {
                            grid[i][j] = k;
                            solve0(grid);
                            grid[i][j] = 0;
                        }
                    }
                    return;
                }
            }
        }

        throw new ThreadDeath();
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
