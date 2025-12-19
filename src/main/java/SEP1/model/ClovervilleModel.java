package SEP1.model;

/**
 * The central model class for the Cloverville application.
 * This class aggregates and manages all core data structures,
 * including events, trade offers, communal tasks, green actions,
 * residents, and the communal pool.
 */

public class ClovervilleModel {

  /** List of events in the system */
  private EventList eventList;

  /** List of trade offers available to residents */
  private TradeOfferList tradeOfferList;

  /** List of communal tasks */
  private CommunalTaskList communalTaskList;

  /** List of green actions */
  private GreenActionList greenActionList;

  /** List of registered residents */
  private ResidentList residentList;

  /** Communal pool used for shared resources and rewards */
  private CommunalPool communalPool;

  /**
   * Constructs a {ClovervilleModel} with a fixed communal pool value.
   * @param fixedPoolValue the fixed value used to initialize the communal pool
   */
  public ClovervilleModel(int fixedPoolValue) {
    this.eventList = new EventList();
    this.tradeOfferList = new TradeOfferList();
    this.communalTaskList = new CommunalTaskList();
    this.greenActionList = new GreenActionList();
    this.residentList = new ResidentList();
    this.communalPool = new CommunalPool(0, 0, fixedPoolValue);
  }

  /**
   * Returns the list of events.
   * @return the event list
   */
  public EventList getEventList() { return eventList; }

  /**
   * Returns the list of trade offers.
   * @return the trade offer list
   */
  public TradeOfferList getTradeOfferList() { return tradeOfferList; }

  /**
   * Returns the list of communal tasks.
   * @return the communal task list
   */
  public CommunalTaskList getCommunalTaskList() { return communalTaskList; }

  /**
   * Returns the list of green actions.
   * @return the green action list
   */
  public GreenActionList getGreenActionList() { return greenActionList; }

  /**
   * Returns the list of residents.
   * @return the resident list
   */
  public ResidentList getResidentList() { return residentList; }

  /**
   * Returns the communal pool.
   * @return the communal pool
   */
  public CommunalPool getCommunalPool() { return communalPool; }
}


