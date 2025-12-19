package SEP1.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import SEP1.model.ClovervilleModelManager;
import SEP1.model.GreenAction;
import SEP1.utils.MyFileHandler;

public class GreenActionViewController {

  private final ClovervilleModelManager manager;
  private final BorderPane root;

  private final ListView<GreenAction> actionListView;
  private final TextField nameField;
  private final TextField descriptionField;
  private final TextField pointsField;

  public GreenActionViewController(ClovervilleModelManager manager) {
    this.manager = manager;
    this.root = new BorderPane();

    actionListView = new ListView<>();
    nameField = new TextField();
    descriptionField = new TextField();
    pointsField = new TextField();

    buildView();
    refreshActions();
  }

  private void buildView() {
    root.setLeft(actionListView);
    BorderPane.setMargin(actionListView, new Insets(10));

    nameField.setPromptText("Name");
    descriptionField.setPromptText("Description");
    pointsField.setPromptText("Points (20-50)");

    Button addBtn = new Button("Add Green Action");
    addBtn.setOnAction(e -> addAction());

    Button removeBtn = new Button("Remove Selected");
    removeBtn.setOnAction(e -> removeSelected());

    Button saveBtn = new Button("Save");
    saveBtn.setOnAction(e -> save());

    HBox buttons = new HBox(10, addBtn, removeBtn, saveBtn);
    buttons.setAlignment(Pos.CENTER_LEFT);

    VBox right = new VBox(10, nameField, descriptionField, pointsField, buttons);
    right.setPadding(new Insets(10));

    root.setCenter(right);
  }

  private void refreshActions() {
    actionListView.getItems().setAll(manager.getAllGreenActions());
  }

  public void refresh() {
    refreshActions();
  }

  private void addAction() {
    String name = nameField.getText().trim();
    String desc = descriptionField.getText().trim();
    String ptsText = pointsField.getText().trim();

    if (name.isEmpty() || desc.isEmpty() || ptsText.isEmpty()) {
      showAlert("All fields are required.");
      return;
    }

    int pts;
    try {
      pts = Integer.parseInt(ptsText);
    } catch (NumberFormatException e) {
      showAlert("Points must be an integer.");
      return;
    }

    GreenAction action = new GreenAction(name, desc, pts, false);
    manager.addGreenAction(action);
    refreshActions();
    clearForm();
    save();
  }

  private void removeSelected() {
    GreenAction selected = actionListView.getSelectionModel().getSelectedItem();
    if (selected == null) {
      showAlert("Select an action to remove.");
      return;
    }
    manager.removeGreenAction(selected);
    refreshActions();
    save();
  }

  private void clearForm() {
    nameField.clear();
    descriptionField.clear();
    pointsField.clear();
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

