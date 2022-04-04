package sudoku;

import org.fusesource.jansi.Ansi;

import java.util.function.Consumer;

public class GridPrinterImpl implements GridPrinter {

    /**
     * Wraps the provided {@link String} with a configurable Ansi-sequence terminated with reset.
     */
    @SafeVarargs
    private static Ansi toAnsi(final String string, final Consumer<? super Ansi>... builders) {
        final Ansi ansi = new Ansi();
        for (final Consumer<? super Ansi> builder : builders) {
            builder.accept(ansi);
        }
        return ansi.a(string).reset();
    }

    @Override
    public void print(final Grid grid) {
        printHorizontal(0);
        for (int y = 0; y < 9; y++) {
            System.out.print("┃ ");
            for (int x = 0; x < 9; x++) {
                System.out.print(grid.get(x, y) + " ");
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
