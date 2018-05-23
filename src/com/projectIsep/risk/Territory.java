package com.projectIsep.risk;

public class Territory {
    private int idTerritory;
    private int proprietary;
    private int nbSoldier;
    private int nbCavalery;
    private int nbCanon;
    private String nameTerritory;
    private double x;
    private double y;

    // -------------- Constructors --------------  //
    public Territory() {
    }

    public Territory(int idTerritory, int proprietary) {
        this.idTerritory = idTerritory;
        this.proprietary = proprietary;
    }

    public Territory(int idTerritory, int proprietary, String nameTerritory) {
        this.idTerritory = idTerritory;
        this.proprietary = proprietary;
        this.nameTerritory = nameTerritory;
    }

    // -------------- Getters & Setters --------------  //

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

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    // fonction de résolution de combat. En effet les combats se font entre deux territoires


}
