package SEP1.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import SEP1.model.ClovervilleModelManager;
import SEP1.model.CommunalTask;
import SEP1.model.Resident;
import SEP1.utils.MyFileHandler;

public class AddIndividualPointsViewController {

  private final ClovervilleModelManager manager;
  private final VBox root;

  private final ComboBox<Resident> residentBox;
  private final ComboBox<CommunalTask> taskBox;
  private final Label infoLabel;

  public AddIndividualPointsViewController(ClovervilleModelManager manager) {
    this.manager = manager;
    this.root = new VBox(15);

    residentBox = new ComboBox<>();
    taskBox = new ComboBox<>();
    infoLabel = new Label("Select resident and task.");

    buildView();
    refreshData();
  }

  private void buildView() {
    root.setPadding(new Insets(10));
    root.setAlignment(Pos.TOP_LEFT);

    HBox topRow = new HBox(10,
        new Label("Resident:"), residentBox,
        new Label("Communal Task:"), taskBox);
    topRow.setAlignment(Pos.CENTER_LEFT);

    Button completeBtn = new Button("Complete Task");
    completeBtn.setOnAction(e -> completeTask());

    root.getChildren().addAll(topRow, completeBtn, infoLabel);
  }

  private void refreshData() {
    residentBox.getItems().setAll(manager.getAllResidents());
    taskBox.getItems().setAll(manager.getAllCommunalTasks());
  }

  private void completeTask() {
    Resident r = residentBox.getValue();
    CommunalTask t = taskBox.getValue();

    if (r == null || t == null) {
      showAlert("Please select a resident and a task.");
      return;
    }

    // Give points (with newcomer bonus + transfer check)
    boolean transferred = r.doCommunalTask(t, manager.getCommunalPool());

    // Remove from available communal tasks
    manager.removeCommunalTask(t);

    // Save state
    MyFileHandler.saveModel(manager.getModel(), "cloverville.json");

    // Refresh dropdowns / lists
    refreshData();

    if (transferred) {
      infoLabel.setText(
          r.getName() +
              " reached 300 points. Points were transferred to the communal pool. " +
              r.getName() + " now has 0 points."
      );
    } else {
      infoLabel.setText(
          r.getName() +
              " now has " + r.getPointsAccount() + " points."
      );
    }
  }

  public void refresh() {
    refreshData();
  }


  private void showAlert(String msg) {
    Alert a = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
    a.showAndWait();
  }

  public Parent getRoot() {
    return root;
  }
}

