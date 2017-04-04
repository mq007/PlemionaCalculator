package calculator.scenes;

import calculator.AttackInfo;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AttackingOwnersAndVillages {

    Stage window;
    AttackInfo attackInfo;

    public AttackingOwnersAndVillages(AttackInfo attackInfo, Stage window){
        this.window = window;
        this.attackInfo = attackInfo;
        setScene();
    }

    private void setScene(){
        System.out.println("setOwnersNamesAndVillagesAmountScene");
        List<TextField> listOfNames = new ArrayList<>();
        List<TextField> listOfNumbers = new ArrayList<>();
        Label sceneDescription = new Label("Wprowadź nicki właścicieli");
        sceneDescription.setFont(Font.font(null, FontWeight.BOLD, 20));

        attackInfo.setOwnersWithVillages(new ArrayList<>());

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(sceneDescription);

        GridPane ownersDescription = new GridPane();
        ownersDescription.setPadding(new Insets(40,10,40,10));
        ownersDescription.getColumnConstraints().add(new ColumnConstraints(100));
        ownersDescription.getColumnConstraints().add(new ColumnConstraints(200));
        ownersDescription.getColumnConstraints().add(new ColumnConstraints(200));
        ownersDescription.setAlignment(Pos.CENTER);

        Label name = new Label("nick:");
        Label amount = new Label("ilość wiosek:");
        ownersDescription.add(name, 1, 0);
        ownersDescription.add(amount, 2, 0);
        ownersDescription.setHalignment(name, HPos.CENTER);
        ownersDescription.setHalignment(amount, HPos.CENTER);
        ownersDescription.setVgap(10);
        ownersDescription.setHgap(10);

        for(int i=1; i<=attackInfo.getOwnersAmount(); ++i){
            Label ownerName = new Label("Właściciel "+i);
            TextField nameField = new TextField();
            TextField amountField = new TextField();

            ownersDescription.addRow(i, ownerName, nameField, amountField);

            listOfNames.add(nameField);
            listOfNumbers.add(amountField);
        }

        Button accept = new Button("Dalej");
        Button goBack = new Button("Powrót");


        accept.setOnAction(e ->{
            for(int i=0; i<listOfNames.size(); ++i){
                attackInfo.addOwner(listOfNames.get(i).getText());
                int amountOfVillages = Integer.parseInt(listOfNumbers.get(i).getText());
                attackInfo.getOwner(i).setAmountOfVillages(amountOfVillages);
            }
            new VillagesCoords(attackInfo, window);
        });

        goBack.setOnAction(e -> {
            new AttackInformation(attackInfo, window);
        });

        listOfNumbers.get(listOfNumbers.size()-1).setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    accept.fire();
                }
            }
        });

        HBox settingsButtons = new HBox();
        settingsButtons.setAlignment(Pos.CENTER);
        settingsButtons.setSpacing(500);
        settingsButtons.getChildren().addAll(goBack, accept);
        vbox.getChildren().addAll(ownersDescription, settingsButtons);

        window.setScene(new Scene(vbox, 700, 800));
    }
}
