package sudoku;

public class RandomGridGenerator implements GridGenerator {

    private final SudokuSolver solver;

    private final double density;

    public RandomGridGenerator(final SudokuSolver solver, final double density) {
        this.solver = solver;
        this.density = density;
    }

    @Override
    public Grid createGrid() {
        final Grid grid = new Grid(new int[9][9]);

        solver.solve(grid);

        final int[][] data = grid.copyData();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Math.random() > density) {
                    data[i][j] = 0;
                }
            }
        }

        return new Grid(data);
    }
}
