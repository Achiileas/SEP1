package SEP1.model;

import java.util.ArrayList;
import java.util.List;

/** Represents an Event that will take place in Cloverville.
 * <p>
 * Once a fixed point value is reached in the community pool,
 * then the event takes place.
 */
public class Event {

  /** The name of the event */
  private String name;

  /** A short description of the event */
  private String description;

  /** List of residents participating in the event */
  private List<Resident> participants;

  /**
   *Constructs creating a new {Event} with a name and description.
   * @param name the name of the event
   * @param description a short description of the event
   */
  public Event(String name, String description) {
    this.name = name;
    this.description = description;
    this.participants = new ArrayList<>();
  }

  /**
   * Registers a resident as a participant in the event.
   * The resident is only added if they are not already registered.
   *
   * @param resident the resident to register
   */
  public void registerParticipant(Resident resident) {
    if (!participants.contains(resident)) {
      participants.add(resident);
    }
  }

  /**
   * Returns the list of residents participating in the event.
   * @return list of participants
   */
  public List<Resident> getParticipants() { return participants; }

  /**
   * Returns the event description.
   * @return the description of the event
   */
  public String getDescription() { return description; }

  /**
   * Returns the name of the event.
   * @return the event name
   */
  public String getName() { return name; }

  /**
   * Returns the event name as a string.
   * Used for displaying the event.
   *
   * @return event name
   */
  @Override
  public String toString() {
    return name;
  }


}

