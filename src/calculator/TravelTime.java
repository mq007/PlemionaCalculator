package calculator;

import java.util.ArrayList;
import java.util.List;

public class TravelTime {
    List<Time> timeOnField = new ArrayList<>();
    private Village toAttack;
    private Time attackTime;
    private double length;

    public TravelTime(Village toAttack){
        timeOnField.add(new Time(0, 18, 0)); // pik
        timeOnField.add(new Time(0, 22, 0)); // miecznik
        timeOnField.add(new Time(0, 18, 0)); // topornik
        timeOnField.add(new Time(0, 18, 0)); // lucznik
        timeOnField.add(new Time(0, 9, 0)); // zwiad
        timeOnField.add(new Time(0, 10, 0)); // lk
        timeOnField.add(new Time(0, 10, 0)); // łk
        timeOnField.add(new Time(0, 11, 0)); // ck
        timeOnField.add(new Time(0, 30, 0)); // taran
        timeOnField.add(new Time(0, 30, 0)); // kat
        timeOnField.add(new Time(0, 10, 0)); // rycek
        timeOnField.add(new Time(0, 35, 0)); // szlachcic
        this.toAttack = toAttack;
    }

    // ================================================ Getters and setters

    public Village getToAttack() {
        return toAttack;
    }

    public void setToAttack(Village toAttack) {
        this.toAttack = toAttack;
    }

    public Time getAttackTime() {
        return attackTime;
    }

    public void setAttackTime(Time attackTime) {
        this.attackTime = attackTime;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    // ================================================ Methods

    public void countLength(Village fromVillage){
        int xA = toAttack.getX();
        int yA = toAttack.getY();
        int xB = fromVillage.getX();
        int yB = fromVillage.getY();
        int x = Math.abs(xA-xB);
        int y = Math.abs(yA-yB);
        length = Math.sqrt(x*x + y*y);
    }

    public void setEstimatedTime(Village fromVillage){
        countLength(fromVillage);
        for(int i=0; i<12; ++i){
            double timeInSeconds = timeOnField.get(i).getMinute()*60*length;
            fromVillage.estimatedTimeToTarget.add(timeInSeconds);
        }
    }

    public void setSendTime(Village fromVillage){
        for(int i=0; i<12; ++i){
            double unitTime = fromVillage.estimatedTimeToTarget.get(i);
            fromVillage.sendTime.add(TimeConverter.countAttackTime(unitTime, attackTime));
        }
    }
}
