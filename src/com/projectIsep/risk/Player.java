package com.projectIsep.risk;

import java.util.ArrayList;

public class Player {
    private Mission mission;
    private String namePlayer;
    private ArrayList<Territory> arraylistTerritories;
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
