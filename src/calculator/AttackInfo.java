package calculator;

import java.util.ArrayList;
import java.util.List;

public class AttackInfo {
    private int ownersAmount;
    private int fullAmountOfAttackingVillages;
    private String attackTime;
    private String toAttackVillage;
    private List<Owner> ownersWithVillages = new ArrayList<>();

    public int getOwnersAmount() {
        return ownersAmount;
    }

    public void setOwnersAmount(int ownersAmount) {
        this.ownersAmount = ownersAmount;
    }

    public String getAttackTime() {
        return attackTime;
    }

    public void setAttackTime(String attackTime) {
        this.attackTime = attackTime;
    }

    public int getFullAmountOfAttackingVillages() {
        return fullAmountOfAttackingVillages;
    }

    public void setFullAmountOfAttackingVillages(int fullAmountOfAttackingVillages) {
        this.fullAmountOfAttackingVillages = fullAmountOfAttackingVillages;
    }

    public String getToAttackVillage() {
        return toAttackVillage;
    }

    public void setToAttackVillage(String toAttackVillage) {
        this.toAttackVillage = toAttackVillage;
    }

    public void addOwner(String name){
        ownersWithVillages.add(new Owner(name));
    }

    public Owner getOwner(int i){
        return ownersWithVillages.get(i);
    }

    public List<Owner> getOwnersWithVillages() {
        return ownersWithVillages;
    }

    public void setOwnersWithVillages(List<Owner> ownersWithVillages) {
        this.ownersWithVillages = ownersWithVillages;
    }
}
