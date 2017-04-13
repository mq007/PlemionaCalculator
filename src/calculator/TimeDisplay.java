package calculator;

import java.util.ArrayList;
import java.util.List;

public class TimeDisplay {
    private Village village;
    public List<String> neededEstimatedTimes = new ArrayList<>();
    public List<String> neededSendTimes = new ArrayList<>();
    public List<String> attackTypeString = new ArrayList<>();

    public TimeDisplay(Village village){
        this.village = village;
    }

    public int checkAttackTypeAndSave(){
        System.out.println("Village: " + village.getCoords());
        System.out.println("types: " + village.getTypesOfAttack());
        String [] types = village.getTypesOfAttack().split(";");
        for(String type : types){
            setAttackTypeAndNeededTime(type);
        }
        return types.length;
    }

    public void setAttackTypeAndNeededTime(String type){
        int out = 0;
        String nameOfUnit = "";
        if(type.equals("p") || type.equals("P")){
            nameOfUnit = "pikinier";
            out = 0;
        }else if(type.equals("m") || type.equals("M")){
            nameOfUnit = "miecznik";
            out = 1;
        }else if(type.equals("t") || type.equals("T")){
            nameOfUnit = "topornik";
            out = 2;
        }else if(type.equals("l") || type.equals("L")){
            nameOfUnit = "łucznik";
            out = 3;
        }else if(type.equals("z") || type.equals("Z")){
            nameOfUnit = "zwiadowca";
            out = 4;
        }else if(type.equals("lk") || type.equals("LK")){
            nameOfUnit = "lekki kawalerzysta";
            out = 5;
        }else if(type.equals("luk") || type.equals("LUK")){
            nameOfUnit = "łucznik na koniu";
            out = 6;
        }else if(type.equals("ck") || type.equals("CK")){
            nameOfUnit = "ciężki kawalerzysta";
            out = 7;
        }else if(type.equals("tar") || type.equals("TAR")){
            nameOfUnit = "taran";
            out = 8;
        }else if(type.equals("k") || type.equals("K")){
            nameOfUnit = "katapulta";
            out = 9;
        }else if(type.equals("r") || type.equals("R")){
            nameOfUnit = "rycerz";
            out = 10;
        }else if(type.equals("s") || type.equals("S")){
            nameOfUnit = "szlachcic";
            out = 11;
        }
        neededEstimatedTimes.add(village.getSendTime(out));
        neededSendTimes.add(village.getEstimatedTime(out));
        attackTypeString.add(nameOfUnit);
    }
}