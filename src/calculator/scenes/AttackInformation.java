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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AttackInformation {

    private Stage window;
    private Scene scene;
    private AttackInfo attackInfo;

    public AttackInformation(AttackInfo attackInfo, Stage window){
        this.window = window;
        this.attackInfo = attackInfo;
        setScene();
    }

    public AttackInformation(AttackInfo attackInfo, Scene scene){
        this.scene = scene;
        this.attackInfo = attackInfo;
        setScene();
    }

    private void setScene(){
        System.out.println("setAmountOfOwnersScene");
        Label sceneDescription = new Label("Podaj liczbę właścicieli których wioski uczestniczą w ataku");
        sceneDescription.setFont(Font.font(null, FontWeight.BOLD, 20));
        VBox vbox = new VBox(1);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(sceneDescription);

        GridPane attackDescription = new GridPane();
        attackDescription.setPadding(new Insets(40,10,40,10));
        attackDescription.getColumnConstraints().add(new ColumnConstraints(200));
        attackDescription.getColumnConstraints().add(new ColumnConstraints(200));
        attackDescription.setAlignment(Pos.CENTER);
        attackDescription.setVgap(10);
        attackDescription.setHgap(10);

        Label attackTimeLabel = new Label("Godzina ataku: ");
        TextField attackTimeTextField = new TextField();
        attackDescription.addRow(0, attackTimeLabel, attackTimeTextField);

        Label attackTargetLabel = new Label("Cel ataku: ");
        TextField attackTargetTextField = new TextField();
        attackDescription.addRow(1, attackTargetLabel, attackTargetTextField);

        Label ownersLabel = new Label("Liczba osób atakujących: ");
        TextField ownersAmountTextField = new TextField();
        attackDescription.addRow(2, ownersLabel, ownersAmountTextField);

        Button aplyOwnersButton = new Button("Zatwierdź");
        aplyOwnersButton.setPrefSize(100,20);
        attackDescription.add(aplyOwnersButton, 1, 3);
        GridPane.setHalignment(aplyOwnersButton, HPos.RIGHT);

        aplyOwnersButton.setOnAction(e->{
            try{
                int amountOfOwners = Integer.parseInt(ownersAmountTextField.getText());
                attackInfo.setOwnersAmount(amountOfOwners);
                attackInfo.setAttackTime(attackTimeTextField.getText());
                attackInfo.setToAttackVillage(attackTargetTextField.getText());
                new AttackingOwnersAndVillages(attackInfo, scene);
            }catch(NumberFormatException error){
                error.printStackTrace();
            }
        });

        ownersAmountTextField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                aplyOwnersButton.fire();
            }
        });

        vbox.getChildren().add(attackDescription);

        scene.setRoot(vbox);
        //window.setScene(new Scene(vbox, 700, 800));
    }
}

