package sudoku;

import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricForSubmission;
import org.sourcegrade.jagr.api.rubric.RubricProvider;

@RubricForSubmission("sudoku")
public class Sudoku_RubricProvider implements RubricProvider {

    public static final Rubric RUBRIC = Rubric.builder()
        .title("Sudoku")
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }
}
