package sudoku;

import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricForSubmission;
import org.sourcegrade.jagr.api.rubric.RubricProvider;

@RubricForSubmission("sudoku")
public class HSudoku_RubricProvider implements RubricProvider {

    public static final Rubric RUBRIC = Rubric.builder()
        .title("HSudoku")
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }
}
