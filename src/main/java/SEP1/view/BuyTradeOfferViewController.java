package SEP1.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import SEP1.model.ClovervilleModelManager;
import SEP1.model.Resident;
import SEP1.model.TradeOffer;
import SEP1.utils.MyFileHandler;

import java.util.List;
import java.util.stream.Collectors;

public class BuyTradeOfferViewController {

  private final ClovervilleModelManager manager;
  private final VBox root;

  private final ComboBox<TradeOffer> offerBox;
  private final ComboBox<Resident> buyerBox;
  private final Label buyerPointsLabel;
  private final ListView<String> historyListView;

  public BuyTradeOfferViewController(ClovervilleModelManager manager) {
    this.manager = manager;
    this.root = new VBox(10);

    offerBox = new ComboBox<>();
    buyerBox = new ComboBox<>();
    buyerPointsLabel = new Label();
    historyListView = new ListView<>();

    buildView();
    refreshOffers();
    refreshHistory();
  }

  private void buildView() {
    root.setPadding(new Insets(10));
    root.setAlignment(Pos.TOP_LEFT);

    offerBox.setPromptText("Choose trade offer");
    buyerBox.setPromptText("Choose buyer");

    // when offer changes, update list of buyers who can afford it
    offerBox.valueProperty().addListener((obs, oldV, newV) -> updateBuyersForOffer());

    // when buyer changes, update label
    buyerBox.valueProperty().addListener((obs, oldV, newV) -> updateBuyerLabel());

    HBox row1 = new HBox(10,
        new Label("Offer:"), offerBox);
    row1.setAlignment(Pos.CENTER_LEFT);

    HBox row2 = new HBox(10,
        new Label("Buyer:"), buyerBox, buyerPointsLabel);
    row2.setAlignment(Pos.CENTER_LEFT);

    Button buyBtn = new Button("Buy Offer");
    buyBtn.setOnAction(e -> buyOffer());

    Label historyLabel = new Label("Trade history (made -> bought):");

    root.getChildren().addAll(row1, row2, buyBtn, historyLabel, historyListView);
  }

  public void refresh() {
    refreshOffers();
    refreshHistory();
  }


  private void refreshOffers() {
    List<TradeOffer> available = manager.getAllTradeOffers().stream()
        .filter(o -> o.getOfferBought() == null || o.getOfferBought().isEmpty())
        .collect(Collectors.toList());

    offerBox.getItems().setAll(available);
    offerBox.getSelectionModel().clearSelection();
    buyerBox.getItems().clear();
    buyerPointsLabel.setText("");
  }

  private void updateBuyersForOffer() {
    TradeOffer offer = offerBox.getValue();
    if (offer == null) {
      buyerBox.getItems().clear();
      buyerPointsLabel.setText("");
      return;
    }

    int cost = offer.getPointCost();

    List<Resident> possibleBuyers = manager.getAllResidents().stream()
        .filter(r -> r.canAfford(cost))
        .collect(Collectors.toList());

    buyerBox.getItems().setAll(possibleBuyers);
    buyerBox.getSelectionModel().clearSelection();
    buyerPointsLabel.setText("");
  }

  private void updateBuyerLabel() {
    Resident buyer = buyerBox.getValue();
    if (buyer == null) {
      buyerPointsLabel.setText("");
    } else {
      buyerPointsLabel.setText("Points: " + buyer.getPointsAccount());
    }
  }

  private void buyOffer() {
    TradeOffer offer = offerBox.getValue();
    Resident buyer = buyerBox.getValue();

    if (offer == null || buyer == null) {
      showAlert("Select both an offer and a buyer.");
      return;
    }

    int cost = offer.getPointCost();
    if (!buyer.canAfford(cost)) {
      showAlert("This resident does not have enough points.");
      updateBuyersForOffer();
      return;
    }

    // find provider (may be null if resident was removed)
    Resident provider = manager.getAllResidents().stream()
        .filter(r -> r.getName().equalsIgnoreCase(offer.getOfferMade()))
        .findFirst().orElse(null);

    // transfer points
    buyer.spendPoints(cost);
    if (provider != null) {
      provider.addPoints(cost);
    }

    // mark offer as bought
    offer.setOfferBought(buyer.getName());

    // save and refresh GUI
    MyFileHandler.saveModel(manager.getModel(), "cloverville.json");
    refreshOffers();
    refreshHistory();
  }

  private void refreshHistory() {
    historyListView.getItems().clear();
    for (TradeOffer o : manager.getAllTradeOffers()) {
      String made = (o.getOfferMade() == null || o.getOfferMade().isEmpty())
          ? "unknown"
          : o.getOfferMade();
      String bought = (o.getOfferBought() == null || o.getOfferBought().isEmpty())
          ? "not bought yet"
          : o.getOfferBought();
      String line = o.getName() + " (" + o.getPointCost() + " pts) - "
          + made + "  ->  " + bought;
      historyListView.getItems().add(line);
    }
  }

  private void showAlert(String msg) {
    Alert a = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
    a.showAndWait();
  }

  public Parent getRoot() {
    return root;
  }
}


