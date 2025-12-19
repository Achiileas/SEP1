package SEP1.model;

/**
 * Represents a trade offer in Cloverville.
 * <p>
 * A trade offer has a name, a point cost, a description, and tracks
 * which resident made the offer and which resident (if any)
 * has bought it.
 */
public class TradeOffer {

  /** The name of the trade offer */
  private String name;

  /** The point cost required to buy this offer */
  private int pointCost;

  /** A description of what the trade offer provides */
  private String description;

  /** Name of resident providing the offer */
  private String offerMade;

  /** Name of the resident that bought the offer */
  private String offerBought;

  /**
   * Constructs a new {TradeOffer}.
   * @param name the name of the trade offer
   * @param pointCost the point cost of the offer
   * @param description a description of the offer
   * @param offerMade the name of the resident who made the offer
   * @param offerBought the name of the resident who bought the offer,
   *                    or {@code null} / empty if not yet bought
   */
  public TradeOffer(String name, int pointCost, String description,
      String offerMade, String offerBought) {
    this.name = name;
    this.pointCost = pointCost;
    this.description = description;
    this.offerMade = offerMade;
    this.offerBought = offerBought;
  }

  /**
   * Returns the name of the trade offer.
   * @return the offer name
   */
  public String getName() { return name; }

  /**
   * Returns the point cost of the trade offer.
   * @return the point cost
   */
  public int getPointCost() { return pointCost; }

  /**
   * Returns the description of the trade offer.
   * @return the offer description
   */
  public String getDescription() { return description; }

  /**
   * Returns the name of the resident who made the offer.
   * @return the resident who made the offer
   */
  public String getOfferMade() { return offerMade; }

  /**
   * Sets the name of the resident who made the offer.
   * @param offerMade the resident who made the offer
   */
  public void setOfferMade(String offerMade) { this.offerMade = offerMade; }

  /**
   * Returns the name of the resident who bought the offer.
   * @return the resident who bought the offer, or {@code null} if not bought
   */
  public String getOfferBought() { return offerBought; }

  /**
   * Sets the name of the resident who bought the offer.
   * @param offerBought the resident who bought the offer
   */
  public void setOfferBought(String offerBought) { this.offerBought = offerBought; }

  /**
   * Returns a short string representation of the trade offer.
   * @return a string containing the offer name and point cost
   */
  @Override
  public String toString() {
    return name + " (" + pointCost + " pts)";
  }
}

