package sudoku;

public class RandomGridGenerator implements GridGenerator {

    private final SudokuSolver solver = new SudokuSolverImpl();

    private final double density;

    public RandomGridGenerator(double density) {
        this.density = density;
    }

    @Override
    public int[][] createGrid() {
        var grid = new int[9][9];

        solver.solve(grid);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Math.random() > density) {
                    grid[i][j] = 0;
                }
            }
        }

        return grid;
    }
}
