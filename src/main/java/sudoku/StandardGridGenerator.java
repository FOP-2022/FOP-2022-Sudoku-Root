package sudoku;

public class StandardGridGenerator implements GridGenerator {
    @Override
    public int[][] createGrid() {
        return new int[][] {
            {0, 0, 3,   0, 0, 0,    6, 0, 0},
            {6, 5, 0,   0, 0, 3,    4, 0, 8},
            {0, 0, 0,   0, 1, 6,    0, 9, 0},

            {5, 0, 2,   7, 0, 8,    1, 0, 0},
            {3, 0, 7,   0, 6, 0,    5, 0, 2},
            {0, 0, 8,   3, 0, 5,    9, 0, 7},

            {0, 3, 0,   1, 4, 0,    0, 0, 0},
            {4, 0, 1,   8, 0, 0,    0, 7, 6},
            {0, 0, 5,   0, 0, 0,    8, 0, 0},
        };
    }
}
