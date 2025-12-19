package SEP1.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import SEP1.model.ClovervilleModelManager;
import SEP1.model.Resident;
import SEP1.utils.MyFileHandler;

public class ResidentViewController {

  private final ClovervilleModelManager manager;
  private final BorderPane root;

  private final ListView<Resident> residentListView;
  private final TextField nameField;
  private final TextField emailField;
  private final TextField phoneField;
  private final Label pointsLabel;

  public ResidentViewController(ClovervilleModelManager manager) {
    this.manager = manager;
    this.root = new BorderPane();

    residentListView = new ListView<>();
    nameField = new TextField();
    emailField = new TextField();
    phoneField = new TextField();
    pointsLabel = new Label("Select a resident to see points.");

    buildView();
    refreshResidents();
  }

  private void buildView() {
    residentListView.getSelectionModel().selectedItemProperty().addListener(
        (obs, oldV, newV) -> {
          if (newV != null) {
            pointsLabel.setText(newV.getName() + " has "
                + newV.getPointsAccount() + " points.");
          } else {
            pointsLabel.setText("Select a resident to see points.");
          }
        });

    root.setLeft(residentListView);
    BorderPane.setMargin(residentListView, new Insets(10));

    nameField.setPromptText("Name");
    emailField.setPromptText("Email");
    phoneField.setPromptText("Phone (8 digits)");

    Button addBtn = new Button("Add Resident");
    addBtn.setOnAction(e -> addResident());

    Button removeBtn = new Button("Remove Selected");
    removeBtn.setOnAction(e -> removeSelected());

    HBox form = new HBox(10, nameField, emailField, phoneField, addBtn, removeBtn);
    form.setPadding(new Insets(10));
    form.setAlignment(Pos.CENTER_LEFT);

    root.setTop(form);

    Button saveBtn = new Button("Save to JSON");
    saveBtn.setOnAction(e -> save());

    HBox bottom = new HBox(20, pointsLabel, saveBtn);
    bottom.setPadding(new Insets(10));
    bottom.setAlignment(Pos.CENTER_LEFT);

    root.setBottom(bottom);
  }

  private void addResident() {
    String name = nameField.getText().trim();
    String email = emailField.getText().trim();
    String phone = phoneField.getText().trim();

    if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
      showAlert("All fields are required.");
      return;
    }

    Resident r = new Resident(name, email, phone);
    manager.addResident(r);
    refreshResidents();
    clearForm();
    save();
  }

  private void removeSelected() {
    Resident selected = residentListView.getSelectionModel().getSelectedItem();
    if (selected == null) {
      showAlert("Select a resident to remove.");
      return;
    }
    manager.removeResident(selected);
    refreshResidents();
    save();
  }

  private void refreshResidents() {
    residentListView.getItems().setAll(manager.getAllResidents());
  }

  private void clearForm() {
    nameField.clear();
    emailField.clear();
    phoneField.clear();
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

