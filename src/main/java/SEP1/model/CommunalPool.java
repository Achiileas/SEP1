package SEP1.model;

/**
 * Represents the communal pool of points shared by all residents in Cloverville.
 * <p>
 * The pool tracks the total number of points earned from green actions,
 * the number of green actions performed, and triggers a threshold event
 * once a fixed point value is reached.
 */
public class CommunalPool {

  /** The total number of points currently in the communal pool */
  private int totalPoints;

  /** The total number of green actions performed */
  private int greenActionsDone;

  /**
   * The threshold value.
   * When {totalPoints >= fixedValue}, the threshold event may be triggered.
   */
  private int fixedValue;

  /** Event that will happen when threshold is reached
   * (chosen in Events GUI)
   */
  private Event thresholdEvent;

  /** Indicates whether the threshold has already been reached */
  private boolean thresholdReached;

  /**
   * Constructs a new {CommunalPool}.
   * @param totalPoints the initial total points in the pool
   * @param greenActionsDone the initial number of green actions performed
   * @param fixedValue the point threshold required to trigger the event
   */
  public CommunalPool(int totalPoints, int greenActionsDone, int fixedValue) {
    this.totalPoints = totalPoints;
    this.greenActionsDone = greenActionsDone;
    this.fixedValue = fixedValue;
    this.thresholdReached = false;
  }

  /**
   * Adds a completed green action to the communal pool.
   *
   * This increases both the total number of green actions performed
   * and the total points in the pool.
   *
   * @param action the green action that was completed
   */
  public void addGreenAction(GreenAction action) {
    greenActionsDone++;
    totalPoints += action.getPointsGiven();
  }

  /**
   * Called after adding points.
   *
   * @return {true} the first time the threshold is reached;
   *         {false} otherwise
   */
  public boolean checkThreshold() {
    if (!thresholdReached && fixedValue > 0 && totalPoints >= fixedValue && thresholdEvent != null) {
      thresholdReached = true;
      return true;
    }
    return false;
  }

  /**
   * Sets the event to be triggered when the threshold is reached.
   *
   * If the pool already meets or exceeds the threshold when the event
   * is set, the threshold check is performed immediately.
   *
   * @param thresholdEvent the event to trigger at the threshold
   */
  public void setThresholdEvent(Event thresholdEvent) {
    this.thresholdEvent = thresholdEvent;
    // if we already have enough points and just selected an event, trigger immediately
    checkThreshold();
  }

  /**
   * Returns the event that is triggered when the threshold is reached.
   * @return the threshold event
   */
  public Event getThresholdEvent() {
    return thresholdEvent;
  }

  /**
   * Returns the total number of green actions performed.
   * @return the number of green actions done
   */
  public int getGreenActionsDone() {
    return greenActionsDone;
  }

  /**
   * Returns the total number of points in the communal pool.
   * @return the total points
   */
  public int getTotalPoints() {
    return totalPoints;
  }

  /**
   * Returns the fixed point threshold value.
   * @return the threshold value
   */
  public int getFixedValue() {
    return fixedValue;
  }

  /**
   * Adds the specified number of points directly to the communal pool.
   * @param points the number of points to add
   */
  public void addPoints(int points) {
    totalPoints += points;
  }


  /**
   * Indicates whether the threshold has already been reached.
   * @return {@code true} if the threshold has been reached;
   *         {@code false} otherwise
   */
  public boolean isThresholdReached() {
    return thresholdReached;
  }
}


