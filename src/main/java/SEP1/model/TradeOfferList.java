package SEP1.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of trade offers in the Cloverville system.
 * <p>
 * Provides methods to add, remove, search, and retrieve trade offers.
 */
public class TradeOfferList {

  /** List of available trade offers */
  private List<TradeOffer> offers;

  /**
   *  No-argument constructor initializing the TradeOfferList.
   */
  public TradeOfferList() {
    offers = new ArrayList<>();
  }

  /**
   * Adds a trade offer to the list.
   * @param offer the trade offer to add
   */
  public void add(TradeOffer offer) { offers.add(offer); }

  /**
   * Removes a trade offer from the list.
   * @param offer the trade offer to remove
   */
  public void remove(TradeOffer offer) { offers.remove(offer); }

  /**
   * Finds a trade offer by name.
   * @param name the name of the trade offer
   * @return the matching trade offer, or {@code null} if not found
   */
  public TradeOffer getByName(String name) {
    return offers.stream()
        .filter(o -> o.getName().equalsIgnoreCase(name))
        .findFirst().orElse(null);
  }

  /**
   * Returns all trade offers.
   * @return the list of trade offers
   */
  public List<TradeOffer> getAll() { return offers; }
}


