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
    private boolean IA;

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

    public boolean getIA() {
        return IA;
    }

    public void setIA(boolean IA) {
        this.IA = IA;
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
        if (this.getCapture()!=0){
            for (int it = 0; it < capture; it ++){ // cette boucle tourne autant de fois que des territoires ont été capturés
                int num = random.nextInt(2); // on sélectionne un nombre entre 0 et 1 aléatoirement
                if (num == 1){ // si on a tiré 1
                    amoutByCapture++; // on gagne un renfort
                }
            }
        }
        this.setCapture(0);
        return amoutByCapture;
    }

    public int reinforcementByRegion(){
        int amoutByRegion=0;

        if (this.arraylistRegion!= null){
            for (int it = 0; it <arraylistRegion.size(); it++){
                Region region = arraylistRegion.get(it); // On sélectionne la région
                amoutByRegion = amoutByRegion + region.computeReinforcementGiven(); // on ajoute aux renforts les renforts lié au contrôle de la région
            }
        }

        return amoutByRegion;
    }

    //-----------------------IA----------------------//

    public void IAReinforcement(ArrayList<Territory> territoryTotal) {

        int currentReinforcement = this.getReinforcement();
        for(int i =0; i<this.getArraylistTerritories().size(); i++){ // pour chaque territoire du joueur
            for(int e=0; e<this.getArraylistTerritories().get(i).getFrontier().length; e++){
                boolean ennemy=false;
                Territory frontierTerritory = territoryTotal.get(this.getArraylistTerritories().get(i).getFrontier()[e]-1);
                if(frontierTerritory.getProprietary().getID()!=this.getID()){
                    ennemy = true;
                }
                if(this.getReinforcement()>0){
                    if (ennemy){ // si il y a un territoire ennemi
                        if(this.getArraylistTerritories().get(i).getNbSoldier()< frontierTerritory.getNbSoldier() + 3*frontierTerritory.getNbCavalery() + 7*frontierTerritory.getNbCanon() ){ // si le territoire ennemi a plus de troupes
                            this.getArraylistTerritories().get(i).setNbSoldier(this.getArraylistTerritories().get(i).getNbSoldier()+1);
                            this.setReinforcement(this.getReinforcement()-1);
                        }
                    }
                }

            }
        }

        // si jamais on a des renforts qui restent
        Random random = new Random();
        int idTerritory = random.nextInt(this.getArraylistTerritories().size());
        while (this.getReinforcement()>0){
            this.getArraylistTerritories().get(idTerritory).setNbSoldier(this.getArraylistTerritories().get(idTerritory).getNbSoldier()+1);
            this.setReinforcement(this.getReinforcement()-1);
            idTerritory = random.nextInt(this.getArraylistTerritories().size());
        }

    }

    public ArrayList<Territory> IAListOfPossibleConflicts( ArrayList<Territory> territoryTotal){
        ArrayList<Territory> conflictTerritories = new ArrayList<Territory>();
        for(int i =0; i<this.getArraylistTerritories().size(); i++){ // pour chaque territoire du joueur

            for(int e=0; e<this.getArraylistTerritories().get(i).getFrontier().length; e++){
                boolean ennemy=false;
                Territory frontierTerritory = territoryTotal.get(this.getArraylistTerritories().get(i).getFrontier()[e]-1);
                if(frontierTerritory.getProprietary().getID()!=this.getID()){
                    ennemy = true;
                }
                if (ennemy){ // si il y a un territoire ennemi
                    if(this.getArraylistTerritories().get(i).getNbSoldier()> frontierTerritory.getNbSoldier() + 3*frontierTerritory.getNbCavalery() + 7*frontierTerritory.getNbCanon() ){ // si le territoire de l'IA a plus de troupes
                        conflictTerritories.add(this.getArraylistTerritories().get(i));
                        conflictTerritories.add(frontierTerritory);

                    }
                }
            }

        }
        return conflictTerritories; // on dispose d'une arraylist contenant une alternance de territoires attaquants/attaqués

    }

    public void IAMovement(ArrayList<Territory> territoryTotal){
        for(int i =0; i<this.getArraylistTerritories().size(); i++){ // pour chaque territoire du joueur

            for(int e=0; e<this.getArraylistTerritories().get(i).getFrontier().length; e++){
                boolean ennemy=false;
                Territory frontierTerritory = territoryTotal.get(this.getArraylistTerritories().get(i).getFrontier()[e]-1);
                if(frontierTerritory.getProprietary().getID()!=this.getID()){
                    ennemy = true;
                }
                if (!ennemy){ // si il y a un territoire allié
                    while((this.getArraylistTerritories().get(i).getNbSoldier()> frontierTerritory.getNbSoldier())&&(this.getArraylistTerritories().get(i).getNbSoldier()>2)){ // si le territoire allié a moins de troupes, on équilibre
                        this.getArraylistTerritories().get(i).setNbSoldier(this.getArraylistTerritories().get(i).getNbSoldier()-1); // un soldat part du premier territoire
                        frontierTerritory.setNbSoldier(frontierTerritory.getNbSoldier()+1); // et va dans le second
                    }
                }
            }

        }
    }




}
