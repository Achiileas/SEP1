package SEP1.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of residents in the Cloverville system.
 * <p>
 * Provides methods to add, remove, retrieve, and list residents.
 */
public class ResidentList {

  /** List of all residents */
  private List<Resident> residents;

  /**
   * No-argument constructor initializing the ResidentList.
   */
  public ResidentList() {
    residents = new ArrayList<>();
  }

  /**
   * Adds a resident to the list.
   * @param resident the resident to add
   */
  public void addResident(Resident resident) { residents.add(resident); }

  /**
   * Removes a resident from the list.
   * @param resident the resident to remove
   */
  public void removeResident(Resident resident) { residents.remove(resident); }

  /**
   * Finds a resident by name.
   *
   * @param name the name of the resident
   * @return the matching resident, or {@code null} if not found
   */
  public Resident getResident(String name) {
    return residents.stream()
        .filter(r -> r.getName().equalsIgnoreCase(name))
        .findFirst().orElse(null);
  }

  /**
   * Returns a list of all residents.
   * @return the list of residents
   */
  public List<Resident> getAllResidents() { return residents; }
}

