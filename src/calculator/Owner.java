package calculator;

import java.util.ArrayList;
import java.util.List;

public class Owner {
    private String name;
    List<Village> villages = new ArrayList<>();

    Owner(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void addVillage(Village v){
        villages.add(v);
    }
}
