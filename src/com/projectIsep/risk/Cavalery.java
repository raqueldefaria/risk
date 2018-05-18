package com.projectIsep.risk;

public class Cavalery extends Unit {

    public Cavalery(int cost, int strengthMax, int strengthMin, int attackPriority, int defencePriority, int movement) {
        super(cost, strengthMax, strengthMin, attackPriority, defencePriority, movement);
        this.cost = 3;
        this.strengthMax = 7;
        this.strengthMin = 2;
        this.attackPriority = 1;
        this.defencePriority = 3;
        this.movement = 3;
    }

    public Cavalery() {
    }
}
