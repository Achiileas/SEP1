package SEP1.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import SEP1.model.ClovervilleModelManager;
import SEP1.model.Event;
import SEP1.utils.MyFileHandler;

public class EventsViewController {

  private final ClovervilleModelManager manager;
  private final VBox root;

  private final ListView<Event> eventListView;
  private final TextField nameField;
  private final TextField descriptionField;
  private final ComboBox<Event> thresholdBox;
  private final Label thresholdLabel;

  public EventsViewController(ClovervilleModelManager manager) {
    this.manager = manager;
    this.root = new VBox(10);

    eventListView = new ListView<>();
    nameField = new TextField();
    descriptionField = new TextField();
    thresholdBox = new ComboBox<>();
    thresholdLabel = new Label();

    buildView();
    refreshEvents();
    updateThresholdLabel();
  }

  private void buildView() {
    root.setPadding(new Insets(10));
    root.setAlignment(Pos.TOP_LEFT);

    // List on top
    eventListView.setPrefHeight(200);

    // Add/remove controls
    nameField.setPromptText("Event name");
    descriptionField.setPromptText("Description");

    Button addBtn = new Button("Add Event");
    addBtn.setOnAction(e -> addEvent());

    Button removeBtn = new Button("Remove Selected");
    removeBtn.setOnAction(e -> removeSelected());

    HBox buttonRow = new HBox(10, addBtn, removeBtn);
    buttonRow.setAlignment(Pos.CENTER_LEFT);

    // Threshold selection
    HBox thresholdRow = new HBox(10,
        new Label("Event when communal points reach 200:"), thresholdBox);
    thresholdRow.setAlignment(Pos.CENTER_LEFT);

    Button setThresholdBtn = new Button("Set Threshold Event");
    setThresholdBtn.setOnAction(e -> setThresholdEvent());

    root.getChildren().addAll(
        new Label("Events:"),
        eventListView,
        nameField,
        descriptionField,
        buttonRow,
        new Separator(),
        thresholdRow,
        setThresholdBtn,
        thresholdLabel
    );
  }

  private void refreshEvents() {
    eventListView.getItems().setAll(manager.getAllEvents());
    thresholdBox.getItems().setAll(manager.getAllEvents());
  }

  private void addEvent() {
    String name = nameField.getText().trim();
    String desc = descriptionField.getText().trim();

    if (name.isEmpty() || desc.isEmpty()) {
      showAlert("Name and description required.");
      return;
    }

    Event e = new Event(name, desc);
    manager.addEvent(e);
    refreshEvents();
    clearForm();
    save();
  }

  private void removeSelected() {
    Event e = eventListView.getSelectionModel().getSelectedItem();
    if (e == null) {
      showAlert("Select an event to remove.");
      return;
    }
    manager.removeEvent(e);
    refreshEvents();
    save();
  }

  private void clearForm() {
    nameField.clear();
    descriptionField.clear();
  }

  private void setThresholdEvent() {
    Event e = thresholdBox.getValue();
    if (e == null) {
      showAlert("Select an event to set as threshold event.");
      return;
    }
    manager.getCommunalPool().setThresholdEvent(e);
    updateThresholdLabel();
    save();
  }

  private void updateThresholdLabel() {
    Event e = manager.getCommunalPool().getThresholdEvent();
    if (e == null) {
      thresholdLabel.setText("No threshold event set yet (200 points).");
    } else {
      thresholdLabel.setText("Threshold event (200 points): " + e.getName());
    }
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
