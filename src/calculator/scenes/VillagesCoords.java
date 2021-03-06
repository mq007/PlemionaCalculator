package calculator.scenes;

import calculator.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

class VillagesCoords {

    private Scene scene;
    private AttackInfo attackInfo;
    private List<String> allVillagesNames = new ArrayList<>();
    private List<String> allVillagesCoords = new ArrayList<>();


    VillagesCoords(AttackInfo attackInfo, Scene scene){
        this.scene = scene;
        this.attackInfo = attackInfo;
        setScene();
    }


    private void setScene(){
        System.out.println("setVillagesCoordsScene");
        List<ComboBox<String>> listOfCoords = new ArrayList<>();
        List<TextField> listOfTypesOfAttacks = new ArrayList<>();

        Label sceneDescription = new Label("Wprowadź dane dotyczące wiosek i rodzaj ataku");
        sceneDescription.setAlignment(Pos.CENTER);
        sceneDescription.setFont(Font.font(null, FontWeight.BOLD, 20));
        sceneDescription.setPadding(new Insets(40,10,40,10));

        VBox vbox = new VBox(1);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(sceneDescription);

        GridPane units = new GridPane();
        units.setPadding(new Insets(20,10,20,10));
        units.getColumnConstraints().add(new ColumnConstraints(200));
        units.getColumnConstraints().add(new ColumnConstraints(200));
        units.getColumnConstraints().add(new ColumnConstraints(200));
        units.setAlignment(Pos.CENTER);


        Label unitsDescription1 = new Label("p/P - pikinier\n"
                                            + "m/M - miecznik\n"
                                            + "t/T - topornik\n"
                                            + "ł/Ł - łucznik\n");
        Label unitsDescription2 = new Label("z - zwiadowca\n"
                                            + "lk/LK - lekka kaw.\n"
                                            + "łuk/ŁUK - łucznik na koniu\n"
                                            + "ck/CK - ciężka kaw.\n");
        Label unitsDescription3 = new Label("tar/TAR - taran\n"
                                            + "k/K - katapulta\n"
                                            + "r/R - rycerz\n"
                                            + "s/S - szlachcic\n");

        unitsDescription1.setFont(Font.font(null, FontWeight.BOLD, 14));
        unitsDescription2.setFont(Font.font(null, FontWeight.BOLD, 14));
        unitsDescription3.setFont(Font.font(null, FontWeight.BOLD, 14));
        units.addRow(0, unitsDescription1, unitsDescription2, unitsDescription3);

        vbox.getChildren().add(units);

        GridPane villageInfo = new GridPane();
        villageInfo.setPadding(new Insets(40,10,40,10));
        villageInfo.getColumnConstraints().add(new ColumnConstraints(150));
        villageInfo.getColumnConstraints().add(new ColumnConstraints(200));
        villageInfo.getColumnConstraints().add(new ColumnConstraints(200));
        villageInfo.setAlignment(Pos.CENTER);
        villageInfo.setVgap(10);
        villageInfo.setHgap(10);

        Label ownerNameLabel = new Label("Właściciel");
        ownerNameLabel.setFont(Font.font(null, FontWeight.BOLD, 14));
        Label villageCoordLabel = new Label("Coordy");
        villageCoordLabel.setFont(Font.font(null, FontWeight.BOLD, 14));
        Label typeOfAttackLabel = new Label("Typ ataku");
        typeOfAttackLabel.setFont(Font.font(null, FontWeight.BOLD, 14));

        villageInfo.addRow(0, ownerNameLabel, villageCoordLabel, typeOfAttackLabel);

        ScrollPane scroller = new ScrollPane(vbox);
        scroller.setFitToWidth(true);

        int count = 0;

        for(int i=0; i<attackInfo.getOwnersAmount(); ++i){
            for(int j=0; j<attackInfo.getOwner(i).villages.size(); ++j){
                allVillagesNames.add(attackInfo.getOwner(i).villages.get(j).getName());
                allVillagesCoords.add(attackInfo.getOwner(i).villages.get(j).getCoords());
            }
        }

        List<String> villageName = new ArrayList<>();

        for(int i=0; i<attackInfo.getOwnersAmount(); ++i){
            villageName.clear();
            for(int j=0; j<attackInfo.getOwner(i).villages.size(); ++j){
                villageName.add(attackInfo.getOwner(i).villages.get(j).getName());
            }
            for(int j=0; j<attackInfo.getOwner(i).getAmountOfAttackingVillages(); ++j){
                count++;
                Label ownerName = new Label(attackInfo.getOwner(i).getName());
                TextField typeOfAttack = new TextField();

                ComboBox<String> villageCoordsBox = new ComboBox<>();
                villageCoordsBox.getItems().addAll(villageName);
                villageCoordsBox.setValue("Wybierz wioske...");

                villageInfo.addRow(count, ownerName, villageCoordsBox, typeOfAttack);

                listOfCoords.add(villageCoordsBox);
                listOfTypesOfAttacks.add(typeOfAttack);
            }
        }

        vbox.getChildren().add(villageInfo);

        Button accept = new Button("Oblicz");
        Button goBack = new Button("Powrót");

        accept.setOnAction(e ->{
            int counter = 0;
            TravelTime tt = new TravelTime(new Village(attackInfo.getToAttackVillage()));
            tt.setAttackTime(new Time(TimeConverter.convertStringToTable(attackInfo.getAttackTime())));
            for(int i=0; i<attackInfo.getOwnersAmount(); ++i){
                for(int j=0; j<attackInfo.getOwner(i).getAmountOfAttackingVillages(); ++j){
                    String nameOfVillage = listOfCoords.get(counter).getSelectionModel().getSelectedItem();
                    Village village = new Village(nameOfVillage, getCoordFromComboBox(nameOfVillage));
                    village.setTypesOfAttack(listOfTypesOfAttacks.get(counter).getText());
                    attackInfo.getOwner(i).addAttackingVillage(village);
                    tt.setEstimatedTime(village);
                    tt.setSendTime(village);
                    counter++;
                }
            }
            attackInfo.setFullAmountOfAttackingVillages(counter);
            new ResultsDisplayer(attackInfo, scene);
        });

        listOfTypesOfAttacks.get(listOfTypesOfAttacks.size()-1).setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                accept.fire();
            }
        });

        goBack.setOnAction(e -> new AttackingOwnersAndVillages(attackInfo, scene));

        HBox settingsButtons = new HBox();
        settingsButtons.setAlignment(Pos.CENTER);
        settingsButtons.setSpacing(500);
        settingsButtons.getChildren().addAll(goBack, accept);

        vbox.getChildren().add(settingsButtons);


        scene.setRoot(scroller);
    }

    private String getCoordFromComboBox(String name){
        for(int i=0; i<allVillagesNames.size(); ++i){
            if(allVillagesNames.get(i).equals(name)){
                return allVillagesCoords.get(i);
            }
        }
        return "";
    }
}
