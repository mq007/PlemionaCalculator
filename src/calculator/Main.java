package calculator;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Main extends Application {

    Stack<Scene> scenes = new Stack<Scene>();
    Stage window;
    String attackTarget;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window = primaryStage;
        window.setTitle("Plemiona Calculator");
        window.setScene(Scenes.setAmountOfOwnersScene(window));
        window.show();
    }

    public static void main(String[] args) {
        /*
        Village A = new Village("628|427");
        Village B = new Village("636|423");
        TravelTime tt = new TravelTime(B);
        tt.setAttackTime(new Time(22, 9, 0));
        tt.countLength(A);
        tt.setEstimatedTime(A);
        tt.setSendTime(A);
        A.getAllTimes();
        */

        launch(args);
    }
}
