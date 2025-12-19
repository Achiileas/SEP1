package SEP1.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of green actions in the Cloverville system.
 * <p>
 * Provides methods to add, remove, search, and retrieve green actions.
 */
public class GreenActionList {

  /** List of green actions */
  private List<GreenAction> actions;

  /**
   * No-argument constructor initializing the GreenActionList.
   */
  public GreenActionList() {
    actions = new ArrayList<>();
  }

  /**
   * Adds a green action to the list.
   * @param action the green action to add
   */
  public void add(GreenAction action) { actions.add(action); }

  /**
   * Removes a green action from the list.
   * @param action the green action to remove
   */
  public void remove(GreenAction action) { actions.remove(action); }

  /**
   * Finds a green action by name.
   * @param name the name of the green action
   * @return the matching action, or {@code null} if not found
   */
  public GreenAction getByName(String name) {
    return actions.stream()
        .filter(a -> a.getName().equalsIgnoreCase(name))
        .findFirst().orElse(null);
  }

  /**
   * Returns all green actions.
   * @return the list of green actions
   */
  public List<GreenAction> getAll() { return actions; }
}

