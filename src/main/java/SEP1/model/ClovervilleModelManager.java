package SEP1.model;

import java.util.List;

/**
 * Acts as a manager for the {ClovervilleModel}.
 *
 * This class provides a simplified interface for accessing and modifying
 * the core model data such as residents, green actions, communal tasks,
 * events, trade offers, and the communal pool.
 */

public class ClovervilleModelManager {

  /** The underlying Cloverville model */
  private ClovervilleModel model;

  /**
   * Constructs a new {ClovervilleModelManager}.
   * @param model the Cloverville model to manage
   */
  public ClovervilleModelManager(ClovervilleModel model) {
    this.model = model;
  }

  /**
   * Returns the underlying Cloverville model.
   * @return the managed model
   */
  public ClovervilleModel getModel() { return model; }

  /**
   * Returns a list of all residents.
   * @return a list of residents
   */
  public List<Resident> getAllResidents() { return model.getResidentList().getAllResidents(); }

  /**
   * Adds a resident to the model.
   * @param resident the resident to add
   */
  public void addResident(Resident resident) { model.getResidentList().addResident(resident); }

  /**
   * Removes a resident from the model.
   * @param resident the resident to remove
   */
  public void removeResident(Resident resident) { model.getResidentList().removeResident(resident); }

  /**
   * Returns all available green actions.
   * @return a list of green actions
   */
  public List<GreenAction> getAllGreenActions() { return model.getGreenActionList().getAll(); }

  /**
   * Adds a green action to the model.
   * @param action the green action to add
   */
  public void addGreenAction(GreenAction action) { model.getGreenActionList().add(action); }

  /**
   * Removes a green action from the model.
   * @param action the green action to remove
   */
  public void removeGreenAction(GreenAction action) { model.getGreenActionList().remove(action); }

  /**
   * Returns all communal tasks.
   * @return a list of communal tasks
   */
  public List<CommunalTask> getAllCommunalTasks() { return model.getCommunalTaskList().getAll(); }

  /**
   * Adds a communal task to the model.
   * @param task the communal task to add
   */
  public void addCommunalTask(CommunalTask task) {
    // If the communal task list is currently empty, this newly added task becomes bonus-eligible
    boolean wasEmpty = model.getCommunalTaskList().getAll().isEmpty();
    if (wasEmpty) {
      task.setBonusEligible(true);
    }
    model.getCommunalTaskList().add(task);
  }

  /**
   * Removes a communal task from the model.
   * @param task the communal task to remove
   */
  public void removeCommunalTask(CommunalTask task) { model.getCommunalTaskList().remove(task); }

  /**
   * Returns all events.
   * @return a list of events
   */
  public List<Event> getAllEvents() { return model.getEventList().getAll(); }

  /**
   * Adds an event to the model.
   * @param e the event to add
   */
  public void addEvent(Event e) { model.getEventList().add(e); }

  /**
   * Removes an event from the model.
   * @param e the event to remove
   */
  public void removeEvent(Event e) { model.getEventList().remove(e); }


  /**
   * Returns all trade offers.
   * @return a list of trade offers
   */
  public List<TradeOffer> getAllTradeOffers() {
    return model.getTradeOfferList().getAll();
  }

  /**
   * Adds a trade offer to the model.
   * @param offer the trade offer to add
   */
  public void addTradeOffer(TradeOffer offer) {
    model.getTradeOfferList().add(offer);
  }

  /**
   * Removes a trade offer from the model.
   * @param offer the trade offer to remove
   */
  public void removeTradeOffer(TradeOffer offer) {
    model.getTradeOfferList().remove(offer);
  }


  /**
   * Returns the communal pool.
   * @return the communal pool
   */
  public CommunalPool getCommunalPool() { return model.getCommunalPool(); }
}


