package SEP1.main;

import SEP1.model.ClovervilleModel;
import SEP1.utils.DataInitializer;
import SEP1.utils.MyFileHandler;

public class RunClovervilleApplication {

  public static void main(String[] args) {

    // Create a fresh model with 0 points everywhere
    ClovervilleModel model = DataInitializer.createDefaultModel();

    // Save it to JSON
    MyFileHandler.saveModel(model, "cloverville.json");

    System.out.println("Fresh model saved with 0 communal points and all residents at 0 points.");
  }
}



