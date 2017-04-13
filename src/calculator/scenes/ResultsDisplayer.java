package calculator.scenes;

import calculator.AttackInfo;
import calculator.Owner;
import calculator.TimeDisplay;
import calculator.Village;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ResultsDisplayer {

    Stage window;
    AttackInfo attackInfo;

    public ResultsDisplayer(AttackInfo attackInfo, Stage window){
        this.window = window;
        this.attackInfo = attackInfo;
        setScene();
    }

    private void setScene(){
        System.out.println("setDisplayResultsScene");
        Label sceneDescription = new Label("Oto Twoje czasy");
        sceneDescription.setFont(Font.font(null, FontWeight.BOLD, 20));

        VBox vbox = new VBox(1);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(sceneDescription);

        GridPane results = new GridPane();
        results.setPadding(new Insets(40,0,40,0));
        results.getColumnConstraints().add(new ColumnConstraints(100));
        results.getColumnConstraints().add(new ColumnConstraints(100));
        results.getColumnConstraints().add(new ColumnConstraints(200));
        results.getColumnConstraints().add(new ColumnConstraints(100));
        results.getColumnConstraints().add(new ColumnConstraints(100));
        results.setAlignment(Pos.CENTER);


        Label ownerLabel = new Label("Właściciel");
        Label coordLabel = new Label("Coordy");
        Label typeOfUnitLabel = new Label("Jednostka");
        Label estimatedTimeLabel = new Label("Czas wysłania");
        Label sendTimeLabel = new Label("Czas dojścia");

        ownerLabel.setFont(Font.font(null, FontWeight.BOLD, 14));
        coordLabel.setFont(Font.font(null, FontWeight.BOLD, 14));
        typeOfUnitLabel.setFont(Font.font(null, FontWeight.BOLD, 14));
        estimatedTimeLabel.setFont(Font.font(null, FontWeight.BOLD, 14));
        sendTimeLabel.setFont(Font.font(null, FontWeight.BOLD, 14));

        results.add(ownerLabel, 0, 0);
        results.add(coordLabel, 1, 0);
        results.add(typeOfUnitLabel, 2, 0);
        results.add(estimatedTimeLabel, 3, 0);
        results.add(sendTimeLabel, 4, 0);


        int counter = 0;

        for(int i=0; i<attackInfo.getOwnersAmount(); ++i){
            Owner owner = attackInfo.getOwner(i);
            for(int j=0; j<owner.getAmountOfAttackingVillages(); ++j){
                Village village = owner.attackingVillages.get(j);
                TimeDisplay td = new TimeDisplay(village);
                village.setAmountOfAttack(td.checkAttackTypeAndSave());
                village.setTimeDisplayer(td);

                for(int k=0; k<village.getAmountOfAttack(); ++k){
                    counter++;

                    Label ownerName = new Label(owner.getName());
                    Label coords = new Label(owner.villages.get(j).getCoords());
                    Label typeOfUnit = new Label(td.attackTypeString.get(k));
                    Label timeToAttack = new Label(td.neededEstimatedTimes.get(k));
                    Label sendTime = new Label(td.neededSendTimes.get(k));

                    results.add(ownerName, 0, counter);
                    results.add(coords, 1, counter);
                    results.add(typeOfUnit, 2, counter);
                    results.add(timeToAttack, 3, counter);
                    results.add(sendTime, 4, counter);
                }
            }
        }
        vbox.getChildren().add(results);

        Button generate = new Button("Wygeneruj wiadomość");
        Button goBack = new Button("Powrót");

        generate.setOnAction(e -> {
            new MessageGenerator(attackInfo, window);
        });

        goBack.setOnAction(e -> {
            new VillagesCoords(attackInfo, window);
        });

        HBox settingsButtons = new HBox();
        settingsButtons.setAlignment(Pos.CENTER);
        settingsButtons.setSpacing(500);
        settingsButtons.getChildren().addAll(goBack, generate);
        vbox.getChildren().addAll(settingsButtons);

        window.setScene(new Scene(vbox, 700, 800));
    }
}
