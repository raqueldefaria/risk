package com.projectIsep.risk;

public class Unit {
    private int cost;
    private int strength;
    private int attackPriority;
    private int defencePriority;
    private int movement;

    public Unit() {
    }

    public Unit(int cost, int strength, int attackPriority, int defencePriority, int movement) {
        this.cost = cost;
        this.strength = strength;
        this.attackPriority = attackPriority;
        this.defencePriority = defencePriority;
        this.movement = movement;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
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
