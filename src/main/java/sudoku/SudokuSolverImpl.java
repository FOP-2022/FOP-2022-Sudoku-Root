package sudoku;

public class SudokuSolverImpl implements SudokuSolver {

    private final GridChecker checker;

    public SudokuSolverImpl(GridChecker checker) {
        this.checker = checker;
    }

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
                        grid[i][j] = k;
                        if (checker.check(grid)) {
                            solve0(grid);
                        }
                        grid[i][j] = 0;
                    }
                }
            }
        }

        throw new ThreadDeath();
    }
}
