package calculator;

import calculator.scenes.AttackInformation;
import calculator.scenes.MessageGenerator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Stack;


public class Main extends Application {

    Stack<Scene> scenes = new Stack<Scene>();
    Stage window;
    String attackTarget;
    AttackInfo attackInfo;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        attackInfo = new AttackInfo();
        window = primaryStage;
        window.setTitle("Plemiona Calculator");
        //window.setScene(Scenes.setAmountOfOwnersScene(window));
        new AttackInformation(attackInfo, window);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
