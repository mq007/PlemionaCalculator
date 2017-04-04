package calculator.scenes;

import calculator.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class VillagesCoords {

    Stage window;
    AttackInfo attackInfo;

    public VillagesCoords(AttackInfo attackInfo, Stage window){
        this.window = window;
        this.attackInfo = attackInfo;
        setScene();
    }

    private void setScene(){
        System.out.println("setVillagesCoordsScene");
        List<TextField> listOfCoords = new ArrayList<>();
        List<TextField> listOfTypesOfAttacks = new ArrayList<>();

        Label sceneDescription = new Label("Wprowadź dane dotyczące wiosek i rodzaj ataku");
        sceneDescription.setAlignment(Pos.CENTER);
        sceneDescription.setFont(Font.font(null, FontWeight.BOLD, 20));

        VBox vbox = new VBox(1);
        vbox.setAlignment(Pos.CENTER);
        ScrollPane scroller = new ScrollPane(vbox);
        scroller.setFitToWidth(true);

        for(int i=0; i<attackInfo.getOwnersAmount(); ++i){
            for(int j=0; j<attackInfo.getOwner(i).getAmountOfVillages(); ++j){
                HBox villageInfo = new HBox();
                villageInfo.setPadding(new Insets(5,12,5,12));
                villageInfo.setSpacing(10);
                villageInfo.setAlignment(Pos.CENTER);

                Label ownerName = new Label(attackInfo.getOwner(i).getName());
                TextField villageCoord = new TextField();
                TextField typeOfAttack = new TextField();

                villageInfo.getChildren().addAll(ownerName, villageCoord, typeOfAttack);
                vbox.getChildren().add(villageInfo);
                listOfCoords.add(villageCoord);
                listOfTypesOfAttacks.add(typeOfAttack);
            }
        }

        Button accept = new Button("Oblicz");
        Button goBack = new Button("Powrót");

        accept.setOnAction(e ->{
            int counter = 0;
            TravelTime tt = new TravelTime(new Village(attackInfo.getToAttackVillage()));
            tt.setAttackTime(new Time(TimeConverter.convertStringToTable(attackInfo.getAttackTime())));
            for(int i=0; i<attackInfo.getOwnersAmount(); ++i){
                for(int j=0; j<attackInfo.getOwner(i).getAmountOfVillages(); ++j){
                    Village village = new Village(listOfCoords.get(counter).getText());
                    village.setTypesOfAttack(listOfTypesOfAttacks.get(counter).getText());
                    attackInfo.getOwner(i).addVillage(village);
                    tt.setEstimatedTime(village);
                    tt.setSendTime(village);
                    counter++;
                }
            }
            attackInfo.setFullAmountOfAttackingVillages(counter);
            new ResultsDisplayer(attackInfo, window);
        });

        listOfTypesOfAttacks.get(listOfTypesOfAttacks.size()-1).setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    accept.fire();
                }
            }
        });

        goBack.setOnAction(e ->{
            new AttackingOwnersAndVillages(attackInfo, window);
        });

        HBox settingsButtons = new HBox();
        settingsButtons.setAlignment(Pos.CENTER);
        settingsButtons.setSpacing(500);
        settingsButtons.getChildren().addAll(goBack, accept);

        window.setScene(new Scene(vbox, 700, 800));
    }
}
