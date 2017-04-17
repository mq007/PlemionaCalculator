package calculator;

import calculator.scenes.AttackInformation;
import calculator.scenes.MessageGenerator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
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

    Stack<Scene> scenes = new Stack<>();
    private Stage window;
    String attackTarget;
    private AttackInfo attackInfo;

    String text = "";

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        attackInfo = new AttackInfo();
        window = primaryStage;
        window.setTitle("Plemiona Calculator");
        Scene scene = new Scene(new VBox(), 700, 800);
        window.setScene(scene);
        new AttackInformation(attackInfo, scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
