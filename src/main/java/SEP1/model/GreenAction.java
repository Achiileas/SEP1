package SEP1.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a green action that residents can perform.
 * A green action has a name, description, point value, approval status,
 * and keeps track of residents who have already completed it.
 */
public class GreenAction {

  /** Name of the green action */
  private String name;

  /** Description of the green action */
  private String description;

  /** Number of points awarded for completing the action */
  private int pointsGiven;

  /** Whether the action has been approved */
  private boolean approved;

  /** Names of residents who have already completed this action */
  private List<String> filterNames;

  /**
   * Constructor initializing a {GreenAction}.
   *
   * @param name the name of the action
   * @param description a short description of the action
   * @param pointsGiven points awarded for completing the action
   * @param approved whether the action is approved
   */
  public GreenAction(String name, String description, int pointsGiven, boolean approved) {
    this.name = name;
    this.description = description;
    this.pointsGiven = pointsGiven;
    this.approved = approved;
    this.filterNames = new ArrayList<>();
  }

  /**
   * Checks if a resident has already completed this action.
   *
   * @param residentName the name of the resident
   * @return true if the resident has completed the action, false otherwise
   */
  public boolean hasFilter(String residentName) {
    return filterNames.contains(residentName);
  }

  /**
   * Adds a resident name to the list of residents
   * who have completed this action.
   *
   * @param residentName the name of the resident
   */
  public void addFilterName(String residentName) {
    if (!filterNames.contains(residentName)) {
      filterNames.add(residentName);
    }
  }

  /**
   * Marks this action as completed by a resident.
   * @param residentName the name of the resident
   */
  public void markDoneBy(String residentName) {
    addFilterName(residentName);
  }

  /**
   * Approves this green action.
   */
  public void approve() { this.approved = true; }

  /**
   * Returns whether this action is approved.
   * @return true if approved, false otherwise
   */
  public boolean isApproved() { return approved; }

  /**
   * Returns the number of points given for this action
   * @return points awarded
   */
  public int getPointsGiven() { return pointsGiven; }

  /**
   * Returns the description of the action.
   * @return action description
   */
  public String getDescription() { return description; }

  /**
   * Returns the name of the action.
   * @return action name
   */
  public String getName() { return name; }

  /**
   * Returns a string representation of the action.
   *
   * @return formatted action name and points
   */
  @Override
  public String toString() {
    return name + " (" + pointsGiven + " pts)";
  }
}


