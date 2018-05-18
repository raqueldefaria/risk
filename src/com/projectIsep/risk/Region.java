package com.projectIsep.risk;

import java.util.ArrayList;

public class Region {
    private int idRegion;
    private String nameRegion;
    private int ruler;
    private ArrayList<Territory> Territories;

    // -------------- Constructors --------------  //

    public Region() {
    }

    public Region(int idRegion, String nameRegion, int ruler, ArrayList<Territory> Territories) {
        this.idRegion = idRegion;
        this.nameRegion = nameRegion;
        this.ruler = ruler;
        this.Territories = Territories;
    }

    // -------------- Getters & Setters --------------  //

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

    public ArrayList<Territory> getIdTerritories() {
        return Territories;
    }

    public void setTerritories(ArrayList<Territory> Territories) {
        this.Territories = Territories;
    }

    // -------------- Methods --------------  //

    public int computeReinforcementGiven(){
        int a = this.Territories.size();
        int regionReinforcement = (int)a/2;
        return a;
    }
}
