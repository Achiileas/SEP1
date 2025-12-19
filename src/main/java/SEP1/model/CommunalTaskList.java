package SEP1.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of communal tasks in the Cloverville system.
 * <p>
 * Provides methods to add, remove, search, and retrieve communal tasks.
 */
public class CommunalTaskList {

  /** List of communal tasks */
  private List<CommunalTask> tasks;

  /**
   * No-argument constructor initializing the CommunalTaskList.
   */
  public CommunalTaskList() {
    tasks = new ArrayList<>();
  }

  /**
   * Adds a communal task to the list.
   * @param task the communal task to add
   */
  public void add(CommunalTask task) { tasks.add(task); }

  /**
   * Removes a communal task from the list.
   * @param task the communal task to remove
   */
  public void remove(CommunalTask task) { tasks.remove(task); }

  /**
   * Finds a communal task by name.
   * @param name the name of the communal task
   * @return the matching task, or {@code null} if not found
   */
  public CommunalTask getByName(String name) {
    return tasks.stream()
        .filter(t -> t.getName().equalsIgnoreCase(name))
        .findFirst().orElse(null);
  }

  /**
   * Returns all communal tasks.
   * @return the list of communal tasks
   */
  public List<CommunalTask> getAll() { return tasks; }
}

