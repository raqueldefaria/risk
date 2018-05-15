package com.projectIsep.risk;

public class Player {
    private int mission;
    private String namePlayer;
    private int[] territories;
    private int reinforcement;

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

    public int getReinforcement() {
        return reinforcement;
    }

    public void setReinforcement(int armies) {
        this.reinforcement = armies;
    }
}
