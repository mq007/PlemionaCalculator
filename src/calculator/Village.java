package calculator;

import java.util.ArrayList;
import java.util.List;

public class Village {
    private int x;
    private int y;
    private String name;
    private String coords;
    private String typesOfAttack;
    private int amountOfAttack;
    private TimeDisplay timeDisplayer;

    List<Time> sendTime = new ArrayList<>();
    List<Double> estimatedTimeToTarget = new ArrayList<>();

    public Village(String coord){
        this.coords = coord;
        String tmp [] = coord.split("\\|");
        this.x = Integer.parseInt(tmp[0]);
        this.y = Integer.parseInt(tmp[1]);
    }

    public Village(String name, String coord){
        this.name = name;
        this.coords = coord;
        String tmp [] = coord.split("\\|");
        this.x = Integer.parseInt(tmp[0]);
        this.y = Integer.parseInt(tmp[1]);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimeDisplay getTimeDisplayer() {
        return timeDisplayer;
    }

    public void setTimeDisplayer(TimeDisplay timeDisplayer) {
        this.timeDisplayer = timeDisplayer;
    }

    public int getAmountOfAttack() {
        return amountOfAttack;
    }

    public void setAmountOfAttack(int amountOfAttack) {
        this.amountOfAttack = amountOfAttack;
    }

    public String getCoords() {
        return coords;
    }

    public void setCoords(String coords) {
        this.coords = coords;
    }

    public String getTypesOfAttack() {
        return typesOfAttack;
    }

    public void setTypesOfAttack(String typesOfAttack) {
        this.typesOfAttack = typesOfAttack;
    }

    public String getSendTime(int num){
        int hour = sendTime.get(num).getHour();
        int minute = sendTime.get(num).getMinute();
        int second = sendTime.get(num).getSecond();

        int [] time = {hour, minute, second};
        String out = TimeConverter.convertTimeToString(time);

        return out;
    }

    public String getEstimatedTime(int num){
        int [] time = TimeConverter.convertSecondsToTime(estimatedTimeToTarget.get(num));
        return TimeConverter.convertTimeToString(time);
    }
}