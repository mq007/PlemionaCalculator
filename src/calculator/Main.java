package calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Village A = new Village("641|423");
        Village B = new Village("635|420");
        TravelTime tt = new TravelTime(B);
        tt.setAttackTime(new Time(21, 0, 0));
        tt.countLength(A);
        tt.setEstimatedTime(A);
        tt.setSendTime(A);
        A.getAllTimes();

        //launch(args);
    }
}
