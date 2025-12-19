package SEP1.view;

import javafx.application.Application;
import javafx.stage.Stage;
import SEP1.model.ClovervilleModel;
import SEP1.model.ClovervilleModelManager;
import SEP1.utils.DataInitializer;
import SEP1.utils.MyFileHandler;

public class StartGUI extends Application {

  @Override
  public void start(Stage primaryStage) {

    ClovervilleModel model = MyFileHandler.loadModel("cloverville.json");
    if (model == null) {
      model = DataInitializer.createDefaultModel();
    }

    ClovervilleModelManager manager = new ClovervilleModelManager(model);

    ViewHandler viewHandler = new ViewHandler(primaryStage, manager);
    viewHandler.openMainView();
  }

  public static void main(String[] args) {
    launch(args);
  }
}

