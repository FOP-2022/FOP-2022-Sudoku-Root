package sudoku;

import org.jetbrains.annotations.Nullable;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board {

    private static final Pattern INPUT_PATTERN = Pattern.compile("\\s*(?<x>\\d),\\s*(?<y>\\d):\\s*(?<value>\\d)");

    private static void checkPos(final int pos) {
        if (0 > pos || pos >= 9) {
            throw new IllegalArgumentException(String.format("Position %d must be in range [0, 8]", pos));
        }
    }

    private static void checkValue(final int value) {
        if (0 > value || value >= 9) {
            throw new IllegalArgumentException(String.format("Value %d must be in range [1, 9]", value));
        }
    }

    // 9x9 grid
    private final int[][] grid;
    private final GridChecker gridChecker;
    private final GridPrinter gridPrinter;

    private static class UpdateRequest {
        final int x;
        final int y;
        final int value;

        public UpdateRequest(final int x, final int y, final int value) {
            checkPos(x);
            checkPos(y);
            checkPos(y - 1); // adjust by one, inputs are [1,9] not [0,8]
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    public Board(
        final GridGenerator gridGenerator,
        final GridChecker gridChecker,
        final GridPrinter gridPrinter
    ) {
        grid = gridGenerator.createGrid();
        this.gridChecker = gridChecker;
        this.gridPrinter = gridPrinter;
    }

    public void run() {
        final Scanner in = new Scanner(System.in);
        while (true) {
            if (checkGridFinished()) {
                checkGrid();
                break;
            }
            gridPrinter.print(grid);
            final @Nullable UpdateRequest next = nextRequest(in);
            if (next == null) {
                break;
            }
            grid[next.y][next.x] = next.value;
        }
    }

    private @Nullable UpdateRequest nextRequest(final Scanner in) {
        System.out.println("Enter next position in format 'x,y:value' or 'quit'");
        final UpdateRequest next;
        try {
            final String nextLine = in.nextLine();
            if ("quit".equals(nextLine)) {
                System.out.println("Goodbye");
                return null;
            }
            next = nextPos(nextLine);
            checkRequest(next);
        } catch (Exception e) {
            System.err.println("Could not parse next position :: " + e.getMessage());
            return nextRequest(in);
        }
        return next;
    }

    private UpdateRequest nextPos(final String nextLine) {
        final Matcher matcher = INPUT_PATTERN.matcher(nextLine);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Must be in format 'x,y:value'");
        }
        return new UpdateRequest(
            Integer.parseInt(matcher.group("x")),
            Integer.parseInt(matcher.group("y")),
            Integer.parseInt(matcher.group("value"))
        );
    }

    private void checkRequest(final UpdateRequest request) {
        if (grid[request.y][request.x] != 0) {
            throw new IllegalArgumentException(
                String.format("Field at position (%d, %d) already has a value", request.x, request.y));
        }
    }

    private boolean checkGridFinished() {
        return Stream.of(grid).flatMapToInt(IntStream::of).noneMatch(i -> i == 0);
    }

    private void checkGrid() {
        try {
            gridChecker.check(grid);
            System.out.println("Grid ok!");
        } catch (Exception e) {
            System.err.println("Grid not ok: " + e.getMessage());
        }
    }
}
