package calculator;

public class TimeConverter {

    private static int dayInSeconds = 24*60*60;

    public static String convertTimeToString(int [] time){
        String tmp = "";
        for(int i=0; i<3; ++i){
            if(time[i] < 10){
                tmp += "0" + time[i];
            }else{
                tmp += time[i];
            }
            if(i != 2){
                tmp += ":";
            }
        }
        return tmp;
    }

    public static int convertTimeToSeconds(Time time){
        return time.getSecond() + time.getMinute()*60 + time.getHour()*60*60;
    }

    public static int [] convertTimeToTable(Time time){
        return new int[]{time.getHour(), time.getMinute(), time.getSecond()};
    }

    public static int [] convertSecondsToTime(int seconds){

        int [] time = new int[4];
        int secondsToAdd = seconds;
        int minutesToAdd = secondsToAdd / 60;
        secondsToAdd = secondsToAdd - minutesToAdd*60;
        int hoursToAdd = minutesToAdd / 60;
        minutesToAdd = minutesToAdd  - hoursToAdd*60;
        int daysToAdd = hoursToAdd / 24;
        hoursToAdd = hoursToAdd - daysToAdd*24;

        time[0] = hoursToAdd;
        time[1] = minutesToAdd;
        time[2] = secondsToAdd;
        time[3] = daysToAdd;

        return time;
    }

    public static int [] convertSecondsToTime(double seconds){

        int [] time = new int[4];
        int secondsToAdd = round(seconds);
        int minutesToAdd = secondsToAdd / 60;
        secondsToAdd = secondsToAdd - minutesToAdd*60;
        int hoursToAdd = minutesToAdd / 60;
        minutesToAdd = minutesToAdd  - hoursToAdd*60;
        int daysToAdd = hoursToAdd / 24;
        hoursToAdd = hoursToAdd - daysToAdd*24;

        time[0] = hoursToAdd;
        time[1] = minutesToAdd;
        time[2] = secondsToAdd;
        time[3] = daysToAdd;

        return time;
    }

    public static int [] convertStringToTable(String time){
        String [] tmp = time.split(":");
        int [] t = new int[4];
        t[0] = Integer.parseInt(tmp[0]);
        t[1] = Integer.parseInt(tmp[1]);
        t[2] = Integer.parseInt(tmp[2]);

        return t;
    }
    private static int round(double number){
        int out = (int) number;
        number = number - (int)number;
        if(number > 0.50){
            out++;
        }
        return out;
    }

    public static Time countAttackTime(double unitTime, Time attackTime){
        double secondsAttackTime = (double) convertTimeToSeconds(attackTime);
        if(unitTime > secondsAttackTime){
            secondsAttackTime += dayInSeconds;
        }
        double totalSeconds = secondsAttackTime - unitTime;
        int [] time = convertSecondsToTime(totalSeconds);

        return new Time(time);
    }

}
