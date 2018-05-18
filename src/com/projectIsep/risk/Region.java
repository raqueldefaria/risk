package com.projectIsep.risk;

public class Region {
    private int idRegion;
    private String nameRegion;
    private int ruler;
    private Territory [] idTerritories;

    public Region() {
    }

    public Region(int idRegion, String nameRegion, int ruler, Territory[] idTerritories) {
        this.idRegion = idRegion;
        this.nameRegion = nameRegion;
        this.ruler = ruler;
        this.idTerritories = idTerritories;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public String getNameRegion() {
        return nameRegion;
    }

    public void setNameRegion(String nameRegion) {
        this.nameRegion = nameRegion;
    }

    public int getRuler() {
        return ruler;
    }

    public void setRuler(int ruler) {
        this.ruler = ruler;
    }

    public Territory[] getIdTerritories() {
        return idTerritories;
    }

    public void setIdTerritories(Territory[] idTerritories) {
        this.idTerritories = idTerritories;
    }

    public int computeReinforcementGiven(){
        int a = this.idTerritories.length;
        int regionReinforcement = (int)a/2;
        return a;
    }
}
