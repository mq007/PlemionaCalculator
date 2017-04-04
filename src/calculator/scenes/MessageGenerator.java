package calculator.scenes;

import calculator.AttackInfo;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MessageGenerator {

    Stage window;
    AttackInfo attackInfo;

    public MessageGenerator(AttackInfo attackInfo, Stage window){
        this.window = window;
        this.attackInfo = attackInfo;
        setScene();
    }

    private void setScene(){
        System.out.println("setGeneratedScene");
        Label sceneDescription = new Label("Wygenerowany kod do wiadomości");
        sceneDescription.setFont(Font.font(null, FontWeight.BOLD, 20));

        VBox vbox = new VBox(1);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(sceneDescription);

        window.setScene(new Scene(vbox, 700, 800));
    }
}
