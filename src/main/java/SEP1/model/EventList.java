package SEP1.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of events in the Cloverville system.
 * <p>
 * Provides methods to add, remove, search, and retrieve events.
 */
public class EventList {

  /** List of events */
  private List<Event> events;

  /**
   * No-argument constructor initializing the EventList.
   */
  public EventList() {
    events = new ArrayList<>();
  }

  /**
   * Adds an event to the list.
   * @param event the event to add
   */
  public void add(Event event) { events.add(event); }

  /**
   * Removes an event from the list.
   * @param event the event to remove
   */
  public void remove(Event event) { events.remove(event); }

  /**
   * Finds an event by name.
   * @param name the name of the event
   * @return the matching event, or {@code null} if not found
   */
  public Event getByName(String name) {
    return events.stream()
        .filter(e -> e.getName().equalsIgnoreCase(name))
        .findFirst().orElse(null);
  }

  /**
   * Returns all events.
   * @return the list of events
   */
  public List<Event> getAll() { return events; }
}

