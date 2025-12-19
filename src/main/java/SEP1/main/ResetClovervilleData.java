package SEP1.main;

import SEP1.model.ClovervilleModel;
import SEP1.utils.DataInitializer;
import SEP1.utils.MyFileHandler;

public class ResetClovervilleData {
  public static void main(String[] args) {
    ClovervilleModel model = DataInitializer.createDefaultModel();
    MyFileHandler.saveModel(model, "cloverville.json");
    System.out.println("Data reset: 0 points, all default green actions and tasks restored.");
  }
}

