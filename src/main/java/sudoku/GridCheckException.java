package sudoku;

public class GridCheckException extends RuntimeException {
    public GridCheckException(final int posX, final int posY, final String extra) {
        super(String.format("Incorrect field at position (%d,%d) :: %s", posX, posY, extra));
    }
}
