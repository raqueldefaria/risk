package com.projectIsep.risk;

public class Territory {
    private int idTerritory;
    private int proprietary;
    private int nbSoldier;
    private int nbCavalery;
    private int nbCanon;
    private String nameTerritory;

    public Territory() {
    }

    public Territory(int idTerritory, int proprietary, String nameTerritory) {
        this.idTerritory = idTerritory;
        this.proprietary = proprietary;
        this.nameTerritory = nameTerritory;
    }

    public int getIdTerritory() {
        return idTerritory;
    }

    public void setIdTerritory(int idTerritory) {
        this.idTerritory = idTerritory;
    }

    public int getProprietary() {
        return proprietary;
    }

    public void setProprietary(int proprietary) {
        this.proprietary = proprietary;
    }

    public int getNbSoldier() {
        return nbSoldier;
    }

    public void setNbSoldier(int nbSoldier) {
        this.nbSoldier = nbSoldier;
    }

    public int getNbCavalery() {
        return nbCavalery;
    }

    public void setNbCavalery(int nbCavalery) {
        this.nbCavalery = nbCavalery;
    }

    public int getNbCanon() {
        return nbCanon;
    }

    public void setNbCanon(int nbCanon) {
        this.nbCanon = nbCanon;
    }

    public String getNameTerritory() {
        return nameTerritory;
    }

    public void setNameTerritory(String nameTerritory) {
        this.nameTerritory = nameTerritory;
    }

    // fonction de résolution de combat. En effet les combats se font entre deux territoires


}
