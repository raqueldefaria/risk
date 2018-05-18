package com.projectIsep.risk;

public class Soldier extends Unit {

    public Soldier() {
    }

    public Soldier(int cost, int strengthMax, int strengthMin, int attackPriority, int defencePriority, int movement) {
        super(cost, strengthMax, strengthMin, attackPriority, defencePriority, movement);
        this.cost = 1;
        this.strengthMax = 6;
        this.strengthMin = 1;
        this.attackPriority = 2;
        this.defencePriority = 1;
        this.movement = 2;
    }
}
