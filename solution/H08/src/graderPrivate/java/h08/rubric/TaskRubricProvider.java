package h08.rubric;

import h08.rubric.task.Task;
import org.sourcegrade.jagr.api.rubric.Criterion;
import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricProvider;

import java.util.List;

/**
 * A TaskRubricProvider is a RubricProvider that provides a Rubric for an assignment and allows for customizations.
 *
 * @author Nhan Huynh
 */
public abstract class TaskRubricProvider implements RubricProvider {

    /**
     * The task number of the assignment.
     */
    private final int taskNumber;

    /**
     * The title of the assignment.
     */
    private final String title;

    /**
     * Whether the tests are public or private.
     */
    private final boolean privateTests;

    /**
     * Creates a new TaskRubricProvider with the given task number, title, and whether the tests are public or private.
     *
     * @param taskNumber   the task number of the assignment
     * @param title        the title of the assignment
     * @param privateTests whether the tests are public or private
     */
    public TaskRubricProvider(int taskNumber, String title, boolean privateTests) {
        this.taskNumber = taskNumber;
        this.title = title;
        this.privateTests = privateTests;
    }

    /**
     * Gets the tasks for the assignment.
     *
     * @return the tasks for the assignment
     */
    public abstract List<Task> getTasks();

    @Override
    public Rubric getRubric() {
        return Rubric.builder()
            .title("H%02d | %s - %s Tests".formatted(taskNumber, title, privateTests ? "Private" : "Public"))
            .addChildCriteria(
                getTasks().stream()
                    .map(Task::getCriterion)
                    .toArray(Criterion[]::new)
            ).build();
    }
}
