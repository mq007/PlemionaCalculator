package calculator;

public class Time {
    private int hour;
    private int minute;
    private int second;
    private int day;

    Time(int hour, int minute, int second){
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.day = 0;
    }
    Time(int hour, int minute, int second, int day){
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.day = day;
    }
    Time(int [] time){
        this.hour = time[0];
        this.minute = time[1];
        this.second = time[2];
        this.day = time[3];
    }

    public int getHour(){
        return hour;
    }

    public int getMinute(){
        return minute;
    }

    public int getSecond(){
        return second;
    }

    public int getDays(){
        return day;
    }
}
