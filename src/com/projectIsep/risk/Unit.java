package com.projectIsep.risk;

public class Unit {
    public int cost;
    public int strengthMax;
    public int strengthMin;
    public int attackPriority;
    public int defencePriority;
    public int movement;

    public Unit() {
    }


    public Unit(int cost, int strengthMax, int strengthMin, int attackPriority, int defencePriority, int movement) {
        this.cost = cost;
        this.strengthMax = strengthMax;
        this.strengthMin = strengthMin;
        this.attackPriority = attackPriority;
        this.defencePriority = defencePriority;
        this.movement = movement;
    }

    public int getStrengthMax() {
        return strengthMax;
    }

    public void setStrengthMax(int strengthMax) {
        this.strengthMax = strengthMax;
    }

    public int getStrengthMin() {
        return strengthMin;
    }

    public void setStrengthMin(int strengthMin) {
        this.strengthMin = strengthMin;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


    public int getAttackPriority() {
        return attackPriority;
    }

    public void setAttackPriority(int attackPriority) {
        this.attackPriority = attackPriority;
    }

    public int getDefencePriority() {
        return defencePriority;
    }

    public void setDefencePriority(int defencePriority) {
        this.defencePriority = defencePriority;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    //fonction de résolution de combats


}
