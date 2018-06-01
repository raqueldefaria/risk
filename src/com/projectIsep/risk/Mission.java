package com.projectIsep.risk;

import java.util.ArrayList;

public class Mission {
    private int id;
    private int missionType; // 1 pour une annihilation de joueur,
    // 2 pour une conquête de tous les territoires
    // 3 pour un contrôle de 3 régions et au moins 18 territoires
    // 4 pour 18 territoires avec 2 armées
    // 5 pour un contrôle de X territoires
    // 6 contrôles la plus grosse région + une autre région
    private int IDtarget; // peut faire référence au nombre de territoires à capturer, à l'id du joueur à tuer
    private String briefing;
    private boolean missionAvalaible;
    public boolean missionAccomplished;

    // -------------- Constructors --------------  //

    public Mission() {
    }

    public Mission(int id, String briefing, boolean missionAvalaible, boolean missionAccomplished) {
        this.id = id;
        this.briefing = briefing;
        this.missionAvalaible = missionAvalaible;
        this.missionAccomplished = missionAccomplished;
    }



    // -------------- Getters & Setters --------------  //


    public int getIDtarget() {
        return IDtarget;
    }

    public void setIDtarget(int IDtarget) {
        this.IDtarget = IDtarget;
    }

    public int getMissionType() {
        return missionType;
    }

    public void setMissionType(int missionType) {
        this.missionType = missionType;
    }

    public boolean isMissionAccomplished() {
        return missionAccomplished;
    }

    public void setMissionAccomplished(boolean missionAccomplished) {
        this.missionAccomplished = missionAccomplished;
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

    public ArrayList<Mission> generateMission(ArrayList<Player> listOfPlayers){
        ArrayList<Mission> listOfMission = new ArrayList<Mission>();
        int numberOfTerritoryToConquier = 30; // nombre te territoires à conquérir pour les missions de type 5


// ---------- Les missions pour deux ou trois joueur du nombre de joueurs  ---------- //
        if(listOfPlayers.size()==2 ||listOfPlayers.size()==3 ){ // si il y a 2 ou 3 joueurs
            numberOfTerritoryToConquier = 30; // on doit conquérir 30 territoires
            Mission mission = new Mission();
            mission.setMissionType(4); // et diriger le monde
            mission.setBriefing("The world is too small for two tyrants, you must rule over all of it !");
            mission.setMissionAccomplished(false);
            mission.setMissionAvalaible(true);
            listOfMission.add(mission);

        }


        if(listOfPlayers.size()>2 ) { // si il y a 3 joueurs ou plus

            for (int it = 0; it < listOfPlayers.size(); it++) { // on crée les mission de type "tuer joueur X"
                Mission mission = new Mission();
                mission.setMissionType(1);
                mission.setIDtarget(it);
                mission.setBriefing("You must eliminate player " + it+1 + "to win");
                listOfMission.add(mission);
            }

            Mission eighteenTerritories = new Mission();
            eighteenTerritories.setMissionType(4);
            eighteenTerritories.setBriefing("You must have at least two reinforcement points on eighteen territories");
            eighteenTerritories.setMissionAccomplished(false);
            eighteenTerritories.setMissionAvalaible(true);
            listOfMission.add(eighteenTerritories);
        }

        if ((listOfPlayers.size() ==4) || (listOfPlayers.size() == 5)){ // conquetes pour 4 ou 5 jouerus
            numberOfTerritoryToConquier = 24;
        }
        if (listOfPlayers.size() ==6){ // conquetes pour 6 joueurs
            numberOfTerritoryToConquier = 21;
        }

        // ---------- Les missions indépendantes du nombre de joueurs  ---------- //
        Mission threeRegions = new Mission();
        threeRegions.setMissionType(3);
        threeRegions.setBriefing("You must control three Regions, and at least eighteen terriroties");
        threeRegions.setMissionAccomplished(false);
        threeRegions.setMissionAvalaible(true);
        listOfMission.add(threeRegions);


        Mission territoryConquest = new Mission();
        territoryConquest.setMissionType(5);
        territoryConquest.setIDtarget(numberOfTerritoryToConquier);
        territoryConquest.setBriefing("You must conquier "+ numberOfTerritoryToConquier + " territories !");
        territoryConquest.setMissionAvalaible(true);
        territoryConquest.setMissionAccomplished(false);
        listOfMission.add(territoryConquest);


        Mission bigTerriroty = new Mission();
        bigTerriroty.setMissionType(6);
        bigTerriroty.setBriefing("You must control the biggest united region, and any other united one");
        bigTerriroty.setMissionAccomplished(false);
        bigTerriroty.setMissionAvalaible(true);
        listOfMission.add(bigTerriroty);


                // ---------- On affecteur leur ID aux missions  ---------- //
        for (int it=0; it<listOfMission.size(); it++){
            listOfMission.get(it).setId(it);
        }



        return listOfMission;
    }

    private void chekcMissionFinished(ArrayList<Player> playerArrayList, Player player){
        if (this.getMissionType()==1){ // si on a une mission de type "tuer un joueur"
            if (playerArrayList.get(this.IDtarget).getArraylistTerritories().size()==0){
                this.missionAccomplished=true;
            }
        }
        if (this.getMissionType()==2){ // si on a une mission de type "conquete totale"
            if (player.getArraylistTerritories().size()==42){
                this.missionAccomplished=true;
            }
        }

        if (this.getMissionType()==3){ // si on a une mission de type "trois régions et 18 territoires"
            if ((player.getArraylistTerritories().size()>=18) && (player.getArraylistRegion().size()>=3)){
                this.missionAccomplished=true;
            }
        if (this.getMissionType()==4){ // 18 territoires avec 2 armées
            if ((playerArrayList.get(this.IDtarget).getArraylistTerritories().size()>=18)){ // si il a au moins 18 territoires
                int territoryWithEnoughArmy = 0;
                for (int it=0; it<(playerArrayList.get(this.IDtarget).getArraylistTerritories().size()); it++){ // on parours la liste des territoires
                    if ((playerArrayList.get(this.IDtarget).getArraylistTerritories().get(it).getNbCavalery()!=0)||(playerArrayList.get(this.IDtarget).getArraylistTerritories().get(it).getNbCanon()!=0)||(playerArrayList.get(this.IDtarget).getArraylistTerritories().get(it).getNbSoldier()>=2)){
                        //pour avoir une valeur d'armée supérieure ou égale à deux, il faut soit 2 soldats, soit au moins un cavalier ou canon
                        territoryWithEnoughArmy = territoryWithEnoughArmy+1;
                    }
                }
                if (territoryWithEnoughArmy>=18){ // si 18 territoire ont une assez grande armée
                    this.missionAccomplished=true; // on gagne
                }

            }
        }
        if (this.getMissionType()==5){ // si on a une mission de type "contrôler X territoires"
            if (player.getArraylistTerritories().size()>= this.getIDtarget()){
                this.missionAccomplished=true;
            }
        }
        if (this.getMissionType()==6) { // si on a une mission de type "Contrôler la plus grosse région + une autre"

            if (player.getArraylistRegion().size() >= 1) { // si on contrôle au moins une région
                boolean validation = true;
                for(int increment = 0; increment<playerArrayList.size(); increment++){
                    if (increment==player.getID()){

                    }
                    else if (playerArrayList.get(increment).getArraylistTerritories().size()>player.getArraylistTerritories().size()){
                        validation=false;
                    }
                }


                    if (validation){
                    this.missionAccomplished = true;
                }
            }
        }

        }
    }
}
