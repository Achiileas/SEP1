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

public class RegisterTradeOfferViewController {

  private final ClovervilleModelManager manager;
  private final BorderPane root;

  private final ListView<TradeOffer> offerListView;
  private final ComboBox<Resident> providerBox;
  private final TextField nameField;
  private final TextField pointCostField;
  private final TextArea descriptionArea;

  public RegisterTradeOfferViewController(ClovervilleModelManager manager) {
    this.manager = manager;
    this.root = new BorderPane();

    offerListView = new ListView<>();
    providerBox = new ComboBox<>();
    nameField = new TextField();
    pointCostField = new TextField();
    descriptionArea = new TextArea();

    buildView();
    refreshData();
  }

  private void buildView() {
    root.setPadding(new Insets(10));

    root.setLeft(offerListView);
    BorderPane.setMargin(offerListView, new Insets(0, 10, 0, 0));

    providerBox.setPromptText("Resident providing the offer");

    nameField.setPromptText("Offer name");
    pointCostField.setPromptText("Point cost");
    descriptionArea.setPromptText("Description");
    descriptionArea.setPrefRowCount(3);

    Button addBtn = new Button("Register Offer");
    addBtn.setOnAction(e -> registerOffer());

    Button removeBtn = new Button("Remove Selected");
    removeBtn.setOnAction(e -> removeSelected());

    Button saveBtn = new Button("Save");
    saveBtn.setOnAction(e -> save());

    HBox buttons = new HBox(10, addBtn, removeBtn, saveBtn);
    buttons.setAlignment(Pos.CENTER_LEFT);

    VBox right = new VBox(8,
        new Label("Provider:"), providerBox,
        new Label("Offer name:"), nameField,
        new Label("Point cost:"), pointCostField,
        new Label("Description:"), descriptionArea,
        buttons
    );
    right.setPadding(new Insets(0, 0, 0, 10));

    root.setCenter(right);
  }

  private void refreshData() {
    providerBox.getItems().setAll(manager.getAllResidents());
    offerListView.getItems().setAll(manager.getAllTradeOffers());
  }

  private void registerOffer() {
    Resident provider = providerBox.getValue();
    String name = nameField.getText().trim();
    String costText = pointCostField.getText().trim();
    String desc = descriptionArea.getText().trim();

    if (provider == null || name.isEmpty() || costText.isEmpty() || desc.isEmpty()) {
      showAlert("Select a provider and fill all fields.");
      return;
    }

    int cost;
    try {
      cost = Integer.parseInt(costText);
    } catch (NumberFormatException e) {
      showAlert("Point cost must be an integer.");
      return;
    }

    TradeOffer offer = new TradeOffer(
        name,
        cost,
        desc,
        provider.getName(),
        null
    );

    manager.addTradeOffer(offer);
    refreshData();
    clearForm();
    save();
  }

  private void removeSelected() {
    TradeOffer selected = offerListView.getSelectionModel().getSelectedItem();
    if (selected == null) {
      showAlert("Select an offer to remove.");
      return;
    }
    manager.removeTradeOffer(selected);
    refreshData();
    save();
  }

  private void clearForm() {
    nameField.clear();
    pointCostField.clear();
    descriptionArea.clear();
    providerBox.getSelectionModel().clearSelection();
  }

  private void save() {
    MyFileHandler.saveModel(manager.getModel(), "cloverville.json");
  }

  private void showAlert(String msg) {
    Alert a = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
    a.showAndWait();
  }

  public Parent getRoot() {
    return root;
  }
}

