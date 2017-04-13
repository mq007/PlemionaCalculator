package calculator;

import calculator.scenes.AttackInformation;
import calculator.scenes.MessageGenerator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.ElementType;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;




public class Main extends Application {

    Stack<Scene> scenes = new Stack<Scene>();
    Stage window;
    String attackTarget;
    AttackInfo attackInfo;

    String text = "";

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
        //HTMLVillages htmlVillages = new HTMLVillages("mq007");
        //htmlVillages.prepareVillageInfo();
        launch(args);
    }
}
