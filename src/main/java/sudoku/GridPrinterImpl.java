package sudoku;

public class GridPrinterImpl implements GridPrinter {

    @Override
    public void print(final int[][] grid) {
        printHorizontal(0);
        for (int y = 0; y < grid.length; y++) {
            System.out.print("┃ ");
            for (int x = 0; x < grid[y].length; x++) {
                System.out.print(grid[y][x] + " ");
                if (x % 3 == 2) {
                    if (x == 8) {
                        System.out.print("┃");
                    } else {
                        System.out.print("┃ ");
                    }
                }
            }
            System.out.println();
            if (y % 3 == 2) {
                printHorizontal(y == 8 ? 2 : 1);
            }
        }
    }

    private void printHorizontal(int num) {
        switch (num) {
            case 0:
                System.out.print('┏');
                break;
            case 1:
                System.out.print('┣');
                break;
            case 2:
                System.out.print('┗');
        }
        for (int x = 1; x < 24; x++) {
            if (x % 8 == 0) {
                switch (num) {
                    case 0:
                        System.out.print('┳');
                        break;
                    case 1:
                        System.out.print('╋');
                        break;
                    case 2:
                        System.out.print('┻');
                }
            } else {
                System.out.print("━");
            }
        }
        switch (num) {
            case 0:
                System.out.print('┓');
                break;
            case 1:
                System.out.print('┫');
                break;
            case 2:
                System.out.print('┛');
        }
        System.out.println();
    }
}
