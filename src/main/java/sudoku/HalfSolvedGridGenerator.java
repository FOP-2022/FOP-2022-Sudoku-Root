package sudoku;

public class HalfSolvedGridGenerator implements GridGenerator {
    @Override
    public Grid createGrid() {
        return new Grid(new int[][]{
            {0, 0, 4,    6, 0, 8,    9, 0, 0},
            {0, 0, 2,    0, 9, 0,    0, 4, 8},
            {0, 9, 0,    0, 0, 2,    5, 0, 7},

            {8, 0, 9,    0, 6, 0,    4, 2, 0},
            {0, 0, 6,    8, 0, 3,    7, 0, 1},
            {7, 1, 3,    0, 2, 4,    0, 5, 6},

            {0, 0, 1,    5, 3, 7,    0, 8, 0},
            {2, 0, 7,    4, 0, 9,    6, 0, 5},
            {3, 0, 0,    2, 8, 6,    0, 7, 0},
        });
    }
}
