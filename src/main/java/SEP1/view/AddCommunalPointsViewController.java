package SEP1.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.StringConverter;
import SEP1.model.ClovervilleModelManager;
import SEP1.model.GreenAction;
import SEP1.model.Resident;
import SEP1.utils.MyFileHandler;

import java.util.stream.Collectors;

public class AddCommunalPointsViewController {

  private final ClovervilleModelManager manager;
  private final VBox root;

  private final ComboBox<Resident> residentBox;
  private final ComboBox<GreenAction> actionBox;
  private final Label poolLabel;

  public AddCommunalPointsViewController(ClovervilleModelManager manager) {
    this.manager = manager;
    this.root = new VBox(15);

    residentBox = new ComboBox<>();
    actionBox = new ComboBox<>();
    poolLabel = new Label();

    buildView();
    refreshResidents();
    updatePoolLabel();
  }

  private void buildView() {
    root.setPadding(new Insets(10));
    root.setAlignment(Pos.TOP_LEFT);

    // Show only the resident's NAME in this ComboBox (no points)
    residentBox.setCellFactory(cb -> new ListCell<>() {
      @Override
      protected void updateItem(Resident item, boolean empty) {
        super.updateItem(item, empty);
        setText(empty || item == null ? null : item.getName());
      }
    });
    residentBox.setButtonCell(new ListCell<>() {
      @Override
      protected void updateItem(Resident item, boolean empty) {
        super.updateItem(item, empty);
        setText(empty || item == null ? null : item.getName());
      }
    });

    // When resident changes, refresh available actions for that resident
    residentBox.valueProperty().addListener((obs, oldV, newV) -> updateActionsForResident());

    // You can keep default toString for actions (name + points)
    actionBox.setConverter(new StringConverter<>() {
      @Override
      public String toString(GreenAction ga) {
        return (ga == null) ? "" : ga.getName() + " (" + ga.getPointsGiven() + " pts)";
      }
      @Override
      public GreenAction fromString(String string) { return null; }
    });

    HBox row = new HBox(10,
        new Label("Resident:"), residentBox,
        new Label("Green Action:"), actionBox);
    row.setAlignment(Pos.CENTER_LEFT);

    Button completeBtn = new Button("Complete Green Action");
    completeBtn.setOnAction(e -> completeAction());

    root.getChildren().addAll(row, completeBtn, poolLabel);
  }

  private void refreshResidents() {
    residentBox.getItems().setAll(manager.getAllResidents());
  }

  /** Only show actions this resident has NOT already done (filterNames). */
  private void updateActionsForResident() {
    Resident r = residentBox.getValue();
    if (r == null) {
      actionBox.getItems().clear();
      return;
    }

    var available = manager.getAllGreenActions().stream()
        .filter(a -> !a.hasFilter(r.getName())) // hide already-done actions
        .collect(Collectors.toList());

    actionBox.getItems().setAll(available);
    actionBox.getSelectionModel().clearSelection();
  }

  private void updatePoolLabel() {
    poolLabel.setText(
        "Communal points: " + manager.getCommunalPool().getTotalPoints() +
            " (Green actions done: " + manager.getCommunalPool().getGreenActionsDone() +
            ", threshold: " + manager.getCommunalPool().getFixedValue() + " pts)"
    );
  }

    private void completeAction() {
      Resident r = residentBox.getValue();
      GreenAction a = actionBox.getValue();

      if (r == null || a == null) {
        showAlert("Please select a resident and a green action.");
        return;
      }

      r.addCompletedGreenAction(a);

      r.doGreenAction(a, manager.getCommunalPool());

      manager.removeGreenAction(a);

      MyFileHandler.saveModel(manager.getModel(), "cloverville.json");

      updateActionsForResident();
      updatePoolLabel();
    }


    private void showAlert(String msg) {
    Alert a = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
    a.showAndWait();
  }

  public void refresh() {
    refreshResidents();
    updateActionsForResident();
    updatePoolLabel();
  }


  public Parent getRoot() {
    return root;
  }
}


