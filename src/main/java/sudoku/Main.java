package sudoku;

/**
 * Main entry point in executing the program.
 */
public class Main {

    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        var gridPrinter = new GridPrinterImpl();
        var gridGenerator = new RandomGridGenerator(0.2);
        var sudokuSolver = new SudokuSolverImpl();

        for (int i = 0; i < 1; i++) {
            var grid = gridGenerator.createGrid();

            gridPrinter.print(grid);
            sudokuSolver.solve(grid);
            gridPrinter.print(grid);
        }
    }
}
