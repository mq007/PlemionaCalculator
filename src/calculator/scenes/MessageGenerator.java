package calculator.scenes;

import calculator.AttackInfo;
import calculator.Owner;
import calculator.TimeDisplay;
import calculator.Village;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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

        TextArea message = new TextArea();
        message.setPrefSize(700, 500);

        message.setText(boldBBCode("Plan działania na wioskę: ") + villageBBCode(attackInfo.getToAttackVillage())+"\n"
            + boldBBCode("czas ataku: ") + boldBBCode(colorBBCode("#ff0e0e", attackInfo.getAttackTime())) + "\n\n");

        for(int i=0; i<attackInfo.getOwnersAmount(); ++i){
            Owner owner = attackInfo.getOwner(i);
            message.setText(message.getText() + boldBBCode("gracz -> ") +playerNameBBCode(owner.getName()) + "\n\n");

            for(int j=0; j<owner.getAmountOfVillages(); ++j){
                Village village = owner.villages.get(j);
                message.setText(message.getText() + villageBBCode(village.getCoords()) + "\n");

                for(int k=0; k<village.getAmountOfAttack(); ++k){
                    TimeDisplay td = village.getTimeDisplayer();
                    message.setText(message.getText() + boldBBCode(td.attackTypeString.get(k)+": ")
                        + td.neededSendTimes.get(k) + " -> wysyłka: " + td.neededEstimatedTimes.get(k) + "\n");
                }
                message.setText(message.getText() + "\n");
            }
        }

        vbox.getChildren().add(message);
        window.setScene(new Scene(vbox, 700, 800));
    }

    private String playerNameBBCode(String name){
        return "[player]" + name + "[/player]";
    }

    private String villageBBCode(String coord){
        return "[coord]" + coord + "[/coord]";
    }

    private String boldBBCode(String text){
        return "[b]" + text + "[/b]";
    }

    private String colorBBCode(String color, String text){
        return "[color=" + color + "]" + text + "[/color]";
    }
}
