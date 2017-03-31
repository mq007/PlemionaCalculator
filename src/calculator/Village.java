package calculator;

import java.util.ArrayList;
import java.util.List;

public class Village {
    private int x;
    private int y;

    List<Time> sendTime = new ArrayList<>();
    List<Double> estimatedTimeToTarget = new ArrayList<>();

    Village(String coord){
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

    public void getAllTimes(){
        for(int i=0; i<12; ++i){
            System.out.print(getEstimatedTime(i));
            System.out.println(" -> "+getSendTime(i));
        }
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