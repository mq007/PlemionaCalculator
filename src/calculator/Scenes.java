package calculator;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Scenes {
    private static AttackInfo attackInfo = new AttackInfo();
    public static Scene setAmountOfOwnersScene(Stage window){
        System.out.println("setAmountOfOwnersScene");
        VBox vbox = new VBox(1);
        vbox.setAlignment(Pos.CENTER);

        HBox attackTime = new HBox();
        attackTime.setPadding(new Insets(10,12,10,12));
        attackTime.setSpacing(10);
        attackTime.setAlignment(Pos.CENTER);

        Label attackTimeLabel = new Label("Godzina ataku: ");
        TextField attackTimeTextField = new TextField();
        attackTime.getChildren().addAll(attackTimeLabel, attackTimeTextField);

        HBox attackTarget = new HBox();
        attackTarget.setPadding(new Insets(10,12,10,12));
        attackTarget.setSpacing(10);
        attackTarget.setAlignment(Pos.CENTER);

        Label attackTargetLabel = new Label("Cel ataku: ");
        TextField attackTargetTextField = new TextField();
        attackTarget.getChildren().addAll(attackTargetLabel, attackTargetTextField);

        HBox owners = new HBox();
        owners.setPadding(new Insets(10,12,10,12));
        owners.setSpacing(10);
        owners.setAlignment(Pos.CENTER);

        Label ownersLabel = new Label("Liczba właścicieli: ");
        TextField ownersAmountTextField = new TextField();
        Button aplyOwnersButton = new Button("Zatwierdź");
        aplyOwnersButton.setPrefSize(100,20);
        aplyOwnersButton.setOnAction(e->{
            try{
                int amountOfOwners = Integer.parseInt(ownersAmountTextField.getText());
                attackInfo.setOwnersAmount(amountOfOwners);
                attackInfo.setAttackTime(attackTimeTextField.getText());
                attackInfo.setToAttackVillage(attackTargetTextField.getText());
                window.setScene(setOwnersNamesAndVillagesAmountScene(window));
            }catch(NumberFormatException error){
                error.printStackTrace();
            }
        });

        ownersAmountTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    aplyOwnersButton.fire();
                }
            }
        });

        owners.getChildren().addAll(ownersLabel, ownersAmountTextField, aplyOwnersButton);

        Label label = new Label("Podaj liczbę właścicieli których wioski uczestniczą w ataku");
        vbox.getChildren().addAll(label, attackTime, attackTarget, owners);

        return new Scene(vbox, 700, 800);
    }

    public static Scene setOwnersNamesAndVillagesAmountScene(Stage window){
        System.out.println("setOwnersNamesAndVillagesAmountScene");
        List<TextField> listOfNames = new ArrayList<>();
        List<TextField> listOfNumbers = new ArrayList<>();
        Label sceneDescription = new Label("Wprowadź nicki właścicieli: ");

        attackInfo.setOwnersWithVillages(new ArrayList<>());

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(sceneDescription);

        HBox nameOfColumn = new HBox();
        nameOfColumn.setPadding(new Insets(50,12,5,12));
        nameOfColumn.setSpacing(100);
        nameOfColumn.setAlignment(Pos.CENTER);

        Label x = new Label("");
        Label name = new Label("nick:");
        Label amount = new Label("ilość wiosek:");
        nameOfColumn.getChildren().addAll(x, name, amount);
        vbox.getChildren().add(nameOfColumn);

        for(int i=1; i<=attackInfo.getOwnersAmount(); ++i){
            HBox villageInfo = new HBox();
            villageInfo.setPadding(new Insets(5,12,5,12));
            villageInfo.setSpacing(10);
            villageInfo.setAlignment(Pos.CENTER);

            Label ownerName = new Label("Właściciel "+i);
            TextField nameField = new TextField();
            TextField amountField = new TextField();

            villageInfo.getChildren().addAll(ownerName, nameField, amountField);
            vbox.getChildren().addAll(villageInfo);
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
            window.setScene(setVillagesCoordsScene(window));
        });

        goBack.setOnAction(e -> {
            window.setScene(setAmountOfOwnersScene(window));
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
        vbox.getChildren().add(settingsButtons);

        return new Scene(vbox, 700, 800);
    }

    public static Scene setVillagesCoordsScene(Stage window){
        System.out.println("setVillagesCoordsScene");
        List<TextField> listOfCoords = new ArrayList<>();
        List<TextField> listOfTypesOfAttacks = new ArrayList<>();
        Label sceneDescription = new Label("Wprowadź dane dotyczące wiosek i rodzaj ataku: ");
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
            window.setScene(setDisplayResultsScene(window));
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
            window.setScene(setOwnersNamesAndVillagesAmountScene(window));
        });

        HBox settingsButtons = new HBox();
        settingsButtons.setAlignment(Pos.CENTER);
        settingsButtons.setSpacing(500);
        settingsButtons.getChildren().addAll(goBack, accept);

        return new Scene(new BorderPane(scroller, sceneDescription, null, settingsButtons, null), 700, 800);
    }

    public static Scene setDisplayResultsScene(Stage window){
        System.out.println("setDisplayResultsScene");
        Label sceneDescription = new Label("Oto Twoje czasy ");
        sceneDescription.setFont(Font.font(null, FontWeight.BOLD, 20));
        VBox vbox = new VBox(1);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(sceneDescription);

        GridPane results = new GridPane();
        results.getColumnConstraints().add(new ColumnConstraints(100));
        results.getColumnConstraints().add(new ColumnConstraints(100));
        results.getColumnConstraints().add(new ColumnConstraints(200));
        results.getColumnConstraints().add(new ColumnConstraints(100));
        results.getColumnConstraints().add(new ColumnConstraints(100));
        results.setAlignment(Pos.CENTER);


        Label ownerLabel = new Label("Właściciel");
        Label coordLabel = new Label("Coordy");
        Label typeOfUnitLabel = new Label("Jednostka");
        Label estimatedTimeLabel = new Label("Czas dojścia");
        Label sendTimeLabel = new Label("Czas wysłania");

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

            for(int j=0; j<owner.getAmountOfVillages(); ++j){

                Village village = owner.villages.get(j);
                TimeDisplay td = new TimeDisplay(village);
                village.setAmountOfAttack(td.checkAttackTypeAndSave());

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
            window.setScene(setGeneratedScene(window));
        });

        goBack.setOnAction(e -> {
            window.setScene(setVillagesCoordsScene(window));
        });

        HBox settingsButtons = new HBox();
        settingsButtons.setAlignment(Pos.CENTER);
        settingsButtons.setSpacing(500);
        settingsButtons.getChildren().addAll(goBack, generate);
        vbox.getChildren().addAll(settingsButtons);

        return new Scene(vbox, 700, 800);
    }

    public static Scene setGeneratedScene(Stage window){
        System.out.println("setGeneratedScene");
        Label sceneDescription = new Label("Wygenerowany kod do wiadomości: ");
        VBox vbox = new VBox(1);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(sceneDescription);

        return new Scene(vbox, 700, 800);
    }
}
