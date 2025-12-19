package SEP1.model;

/**
 * Represents a communal task that residents can complete
 * to earn points.
 * <p>
 * Each communal task has a name, a description, and a
 * point value awarded upon completion.
 */
public class CommunalTask {

  /** Name of the communal task */
  private String name;

  /** Description of the communal task */
  private String description;

  /** Points awarded for completing the task */
  private int points;

  /** Indicates whether the task is eligible for a bonus reward */
  private boolean bonusEligible;

  /**
   * Constructs a new {CommunalTask}.
   * @param name the name of the task
   * @param description a short description of the task
   * @param points the number of points awarded
   * Also sets the bonusEligible to false
   */
  public CommunalTask(String name, String description, int points) {
    this.name = name;
    this.description = description;
    this.points = points;
    this.bonusEligible = false;
  }

  /**
   * Returns the number of points awarded for this task.
   * @return the task's point value
   */
  public int getPoints() { return points; }

  /**
   * Returns the description of the task.
   * @return the task description
   */
  public String getDescription() { return description; }

  /**
   * Returns the name of the task.
   * @return the task name
   */
  public String getName() { return name; }

  /**
   * Indicates whether the task is eligible for a bonus.
   *
   * @return {true} if the task is bonus-eligible;
   *         {false} otherwise
   */
  public boolean isBonusEligible() { return bonusEligible; }

  /**
   * Sets whether the task is eligible for a bonus.
   *
   * @param bonusEligible {true} if the task should be bonus-eligible;
   *                      {false} otherwise
   */
  public void setBonusEligible(boolean bonusEligible) { this.bonusEligible = bonusEligible; }

  /**
   * Returns a string representation of the communal task.
   * @return task name and point value
   */
  @Override
  public String toString() {
    return name + " (" + points + " pts)";
  }
}

