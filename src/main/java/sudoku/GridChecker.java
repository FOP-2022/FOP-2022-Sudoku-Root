package sudoku;

public interface GridChecker {

    void check(int[][] grid);

    default boolean checkCatching(int[][] grid) {
        try {
            check(grid);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
