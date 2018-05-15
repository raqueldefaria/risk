package com.projectIsep.risk;

public class Player {
    private int mission;
    private int[] territories;
    private int armies;

    // -------------- Constructor --------------  //
    public Player() {
    }

    // -------------- Getters & Setters --------------  //

    public int getMission() {
        return mission;
    }

    public void setMission(int mission) {
        this.mission = mission;
    }

    public int[] getTerritories() {
        return territories;
    }

    public void setTerritories(int[] territories) {
        this.territories = territories;
    }

    public int getArmies() {
        return armies;
    }

    public void setArmies(int armies) {
        this.armies = armies;
    }
}
