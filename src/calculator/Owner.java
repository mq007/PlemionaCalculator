package calculator;

import java.util.ArrayList;
import java.util.List;

public class Owner {
    private String name;
    private int amountOfVillages;
    public List<Village> villages = new ArrayList<>();

    Owner(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmountOfVillages() {
        return amountOfVillages;
    }

    public void setAmountOfVillages(int amountOfVillages) {
        this.amountOfVillages = amountOfVillages;
    }

    public void addVillage(Village v){
        villages.add(v);
    }
}
