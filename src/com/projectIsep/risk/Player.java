package com.projectIsep.risk;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    private int ID;
    private Mission mission;
    private String namePlayer;
    public ArrayList<Territory> arraylistTerritories= new ArrayList<Territory>();
    public ArrayList<Region> arraylistRegion = new ArrayList<Region>();
    private int reinforcement;
    private int capture;
    private boolean alive;

    // -------------- Constructor --------------  //
    public Player() {
    }

    // -------------- Getters & Setters --------------  //

    public Mission getMission() {
        return mission;
    }



    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public ArrayList<Territory> getArraylistTerritories() {
        return arraylistTerritories;
    }

    public void setArraylistTerritories(ArrayList<Territory> arraylistTerritories) {
        this.arraylistTerritories = arraylistTerritories;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public ArrayList<Region> getArraylistRegion() {
        return arraylistRegion;
    }

    public void setArraylistRegion(ArrayList<Region> arraylistRegion) {
        this.arraylistRegion = arraylistRegion;
    }

    public int getReinforcement() {
        return reinforcement;
    }

    public void setReinforcement(int reinforcement) {
        this.reinforcement = reinforcement;
    }

    public int getCapture() {
        return capture;
    }

    public void setCapture(int capture) {
        this.capture = capture;
    }


    // -------------- Methods --------------  //

    public void computerReinforcement(){
        int amount = 0;
        amount = amount + (int) arraylistTerritories.size()/3; // les renforts des territoires
        amount = amount + this.reinforcementByRegion(); // les renforts des régions
        amount = amount + this.reinforcementByCapture(); // on ajoute les renforts dus aux captures
        if (amount<2){ // si les renforts sont inférieurs à 2
            this.setReinforcement(2); // on les définis comme valant 2
        }
        else {
            this.setReinforcement(amount); // sinon ils valent la valeur calculée
        }


    }

    public int reinforcementByCapture(){ // fonction de calcul des renforts dus aux captures
        Random random = new Random(); //on initialise notre fonction qui va gérer l'aléatoire
        int amoutByCapture=0;
        if (this.capture !=0){
            for (int it = 0; it < capture; it ++){ // cette boucle tourne autant de fois que des territoires ont été capturés
                int num = random.nextInt(2); // on sélectionne un nombre entre 0 et 1 aléatoirement
                if (num == 1){ // si on a tiré 1
                    amoutByCapture++; // on gagne un renfort
                }
            }
        }
        return amoutByCapture;
    }

    public int reinforcementByRegion(){
        int amoutByRegion=0;
        if (arraylistRegion!= null){
            for (int it = 0; it <arraylistRegion.size(); it++){
                Region region = arraylistRegion.get(it); // On sélectionne la région
                amoutByRegion = amoutByRegion + region.computeReinforcementGiven(); // on ajoute aux renforts les renforts lié au contrôle de la région
            }
        }

        return amoutByRegion;
    }




}
