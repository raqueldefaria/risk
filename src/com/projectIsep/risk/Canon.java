package com.projectIsep.risk;

public class Canon extends Unit{
    public Canon(int cost, int strengthMax, int strengthMin, int attackPriority, int defencePriority, int movement) {
        super(cost, strengthMax, strengthMin, attackPriority, defencePriority, movement);
//        this.cost = 7;
//        this.strengthMax = 9;
//        this.strengthMin = 4;
//        this.attackPriority = 3;
//        this.defencePriority = 2;
//        this.movement = 1;

    }

    public Canon() {
        this.cost = 7;
        this.strengthMax = 9;
        this.strengthMin = 4;
        this.attackPriority = 3;
        this.defencePriority = 2;
        this.movement = 1;
    }
}
