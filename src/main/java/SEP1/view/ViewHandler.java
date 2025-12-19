package SEP1.view;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import SEP1.model.ClovervilleModelManager;

public class ViewHandler {

  private final Stage stage;
  private final ClovervilleModelManager manager;

  public ViewHandler(Stage stage, ClovervilleModelManager manager) {
    this.stage = stage;
    this.manager = manager;
  }

  public void openMainView() {

    ResidentViewController residentVC = new ResidentViewController(manager);
    AddIndividualPointsViewController individualVC =
        new AddIndividualPointsViewController(manager);
    AddCommunalPointsViewController communalVC =
        new AddCommunalPointsViewController(manager);

    GreenActionViewController greenVC =
        new GreenActionViewController(manager);
    CommunalTaskViewController taskVC =
        new CommunalTaskViewController(manager);
    EventsViewController eventsVC =
        new EventsViewController(manager);
    RegisterTradeOfferViewController registerOfferVC =
        new RegisterTradeOfferViewController(manager);
    BuyTradeOfferViewController buyOfferVC =
        new BuyTradeOfferViewController(manager);

    TabPane tabPane = new TabPane();

    Tab residentTab = new Tab("Residents");
    residentTab.setContent(residentVC.getRoot());
    residentTab.setClosable(false);

    Tab individualTab = new Tab("Individual Points");
    individualTab.setContent(individualVC.getRoot());
    individualTab.setClosable(false);

    individualTab.setOnSelectionChanged(e -> {
      if (individualTab.isSelected()) {
        individualVC.refresh();
      }
    });


    Tab communalTab = new Tab("Communal Points"); // renamed
    communalTab.setContent(communalVC.getRoot());
    communalTab.setClosable(false);

    communalTab.setOnSelectionChanged(e -> {
      if (communalTab.isSelected()) {
        communalVC.refresh();
      }
    });

    Tab greenTab = new Tab("Green Actions");
    greenTab.setContent(greenVC.getRoot());
    greenTab.setClosable(false);

    greenTab.setOnSelectionChanged(e -> {
      if (greenTab.isSelected()) {
        greenVC.refresh();
      }
    });

    Tab taskTab = new Tab("Communal Tasks");
    taskTab.setContent(taskVC.getRoot());
    taskTab.setClosable(false);

    taskTab.setOnSelectionChanged(e -> {
      if (taskTab.isSelected()) {
        taskVC.refresh();
      }
    });


    Tab registerOfferTab = new Tab("Register Offers");
    registerOfferTab.setContent(registerOfferVC.getRoot());
    registerOfferTab.setClosable(false);

    Tab buyOfferTab = new Tab("Buy Offers");
    buyOfferTab.setContent(buyOfferVC.getRoot());
    buyOfferTab.setClosable(false);

    Tab eventsTab = new Tab("Events");
    eventsTab.setContent(eventsVC.getRoot());
    eventsTab.setClosable(false);

    buyOfferTab.setOnSelectionChanged(e -> {
      if (buyOfferTab.isSelected()) {
        buyOfferVC.refresh();
      }
    });


    tabPane.getTabs().addAll(
        residentTab,
        individualTab,
        communalTab,
        greenTab,
        taskTab,
        eventsTab,
        registerOfferTab,
        buyOfferTab
    );


    Scene scene = new Scene(tabPane, 1000, 650);
    stage.setScene(scene);
    stage.setTitle("Cloverville");
    stage.show();
  }
}


