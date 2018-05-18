package com.projectIsep.risk;

public class Mission {
    private int id;
    private String briefing;
    private boolean missionAvalaible = true;
    public boolean missionAccomplished = false;

    public Mission() {
    }

    public Mission(int id, String briefing, boolean missionAvalaible) {
        this.id = id;
        this.briefing = briefing;
        this.missionAvalaible = missionAvalaible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBriefing() {
        return briefing;
    }

    public void setBriefing(String briefing) {
        this.briefing = briefing;
    }

    public boolean isMissionAvalaible() {
        return missionAvalaible;
    }

    public void setMissionAvalaible(boolean missionAvalaible) {
        this.missionAvalaible = missionAvalaible;
    }

    // fonction de génération des missions
}
