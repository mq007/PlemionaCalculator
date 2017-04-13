package calculator;

import java.util.ArrayList;
import java.util.List;

public class Owner {
    private String name;
    private int amountOfVillages;
    private int amountOfAttackingVillages;
    public List<Village> villages = new ArrayList<>();
    public List<Village> attackingVillages = new ArrayList<>();

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

    public int getAmountOfAttackingVillages() {
        return amountOfAttackingVillages;
    }

    public void setAmountOfAttackingVillages(int amountOfAttackingVillages) {
        this.amountOfAttackingVillages = amountOfAttackingVillages;
    }

    public void addVillage(Village v){
        villages.add(v);
    }

    public void addAttackingVillage(Village v){
        attackingVillages.add(v);
    }
}
