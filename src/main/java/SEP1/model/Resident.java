package SEP1.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a resident of Cloverville.
 * A resident can perform communal tasks and green actions, earning
 * points that are stored in their points account.
 */
public class Resident {

  /** Resident's full name */
  private String name;

  /** Resident's email address */
  private String email;

  /** Resident's phone number */
  private String phoneNumber;

  /** Points earned by the resident */
  private int pointsAccount;

  /** List of completed communal tasks */
  private List<CommunalTask> communalTasks;

  /** List of completed green actions */
  private List<GreenAction> greenActions;

  /** Maximum number of points a resident can hold before transfer occurs */
  public static final int MAX_INDIVIDUAL_POINTS = 300;

  /**
   * Constructs a {Resident}.
   * @param name Resident's full name
   * @param email Resident's email address
   * @param phoneNumber Resident's phone number
   */
  public Resident(String name, String email, String phoneNumber) {
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.pointsAccount = 0;
    this.communalTasks = new ArrayList<>();
    this.greenActions = new ArrayList<>();
  }

  /**
   * Records completion of a communal task, adds its points
   * to the resident's points account and checks if the resident
   * reaches the cap.
   *
   * @param task the task done by resident
   * @param pool the communal pool to transfer points to if the cap is reached
   * @return {true} if points were transferred to the communal pool;
   *         {false} otherwise
   */
  public boolean doCommunalTask(CommunalTask task, CommunalPool pool) {

    boolean isFirstEverCommunalTask = communalTasks.isEmpty();
    communalTasks.add(task);

    int pointsToAdd = task.getPoints();

    // Apply +20% only if:
    // 1) task was created when task list was empty
    // 2) this is the resident's first-ever communal task
    if (task.isBonusEligible() && isFirstEverCommunalTask) {
      pointsToAdd = Math.round(pointsToAdd * 1.2f);
    }

    pointsAccount += pointsToAdd;
    return checkAndTransferPointsToCommunity(pool);
  }




  /**
   * A class containing Green Actions
   * @param action the actions done by the resident
   * @param pool the position where all the green actions will be listed
   */
  public void doGreenAction(GreenAction action, CommunalPool pool) {
    greenActions.add(action);
    pool.addGreenAction(action);
  }

  /**
   * Returns  the resident's name.
   * @return  the name
   */
  public String getName() { return name; }

  /**
   * Sets the resident's name.
   * @param name the new name
   */
  public void setName(String name) { this.name = name; }

  /**
   * Returns the resident's email address.
   * @return the email
   */
  public String getEmail() { return email; }

  /**
   * Sets the resident's email address.
   * @param email the new email
   */
  public void setEmail(String email) { this.email = email; }

  /**
   * Returns the resident's phone number.
   * @return the phone number
   */
  public String getPhoneNumber() { return phoneNumber; }

  /**
   * Sets the resident's phone number.
   * @param phoneNumber the new phone number
   */
  public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

  /**
   *
   * Returns the list of completed communal tasks.
   * @return list of communal tasks
   */
  public List<CommunalTask> getCommunalTasks() { return communalTasks; }

  /**
   * Returns the list of completed green actions.
   * @return list of green actions
   */
  public List<GreenAction> getGreenActions() { return greenActions; }

  /**
   * Returns the current points balance of the resident.
   * @return points in the account
   */
  public int getPointsAccount() {
    return pointsAccount;
  }

  /**
   * Adds points to the resident's account.
   * @param amount the number of points to add
   */
  public void addPoints(int amount) {
    pointsAccount += amount;
  }

  /**
   * Checks whether the resident has enough points.
   * @param amount the required number of points
   * @return true if the resident can afford the amount, false otherwise
   */
  public boolean canAfford(int amount) {
    return pointsAccount >= amount;
  }

  /**
   * Spends points from the resident's account if possible.
   * @param amount the number of points to spend
   * @return true if the points were successfully spent, false otherwise
   */
  public boolean spendPoints(int amount) {
    if (pointsAccount >= amount) {
      pointsAccount -= amount;
      return true;
    }
    return false;
  }

  /**
   * Adds a completed communal task to the resident's record.
   * @param task the completed communal task
   */
  public void addCompletedCommunalTask(CommunalTask task) {
    communalTasks.add(task);
  }

  /**
   * Adds a completed green action to the resident's record.
   * @param action the completed green action
   */
  public void addCompletedGreenAction(GreenAction action) {
    greenActions.add(action);
  }

  /**
   * Returns all completed communal tasks.
   * @return list of communal tasks
   */
  public List<CommunalTask> getCompletedCommunalTasks() {
    return communalTasks;
  }

  /**
   * Returns all completed green actions.
   * @return list of green actions
   */
  public List<GreenAction> getCompletedGreenActions() {
    return greenActions;
  }

  /**
   * Checks whether the resident has reached the maximum individual points
   * and transfers all points to the communal pool if so.
   *
   * @param pool the communal pool to receive the transferred points
   * @return {true} if a transfer occurred; {false} otherwise
   */
  public boolean checkAndTransferPointsToCommunity(CommunalPool pool) {
    if (pointsAccount >= MAX_INDIVIDUAL_POINTS) {
      int transferredPoints = pointsAccount;
      pointsAccount = 0;                 // resident loses all points
      pool.addPoints(transferredPoints); // community gains them
      return true;
    }
    return false;
  }


  /**
   * Returns a string representation of the resident.
   * @return resident name and points balance
   */
  @Override
  public String toString() {
    return name + " (" + pointsAccount + " pts)";
  }
}

