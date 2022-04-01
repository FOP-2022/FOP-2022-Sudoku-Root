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
        var gridGenerator = new StandardGridGenerator();
        var sudokuSolver = new SudokuSolverImpl(new GridCheckerImpl());

        var grid = gridGenerator.createGrid();

        gridPrinter.print(grid);
        sudokuSolver.solve(grid);
        gridPrinter.print(grid);
    }
}
