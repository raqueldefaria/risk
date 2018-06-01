package com.projectIsep.risk;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameGestion {

    public void worldMap(int numberOfPlayers) {

        boolean gameOver = false;

        // ---------- Initialising players and territories ---------- //
        ArrayList<Territory> territoryArrayList = initiateTerritories();
        int it = 0; // initialising the number of players
        ArrayList<Player> playerArrayList = new ArrayList<>();
        ArrayList<Region> regionArrayList = initiateRegions(territoryArrayList);

        for (int i = 0; i < numberOfPlayers; i++) { // initialisation des joueurs
            int a = (int) Math.floor(Math.random() * 100) + 1; // on tire l'id de mission, pour l'instant de 1 à 100
            Player player = new Player();
            //player.setMission(); //on set la mission d'ID a;

            playerArrayList.add(player); // adding a player to the list

            //giving the player his territories
            territoryInitialisation(player, (i+1),numberOfPlayers, territoryArrayList); // on affecte ses territoires au joueur
        }

        // ---------- Initialising the background---------- //
        initiateBackground(territoryArrayList,numberOfPlayers);

        // ---------- Initialising the background---------- //
        StdDraw.pause(1000);
        StdDraw.clear();
        StdDraw.text(50,50,"Please put your armies in the territories");
        StdDraw.show();
        StdDraw.pause(2000);

        // ---------- Initialising Armies in the different territories ---------- //
        initiateArmy(playerArrayList, territoryArrayList);




        while (!gameOver) {
            StdDraw.disableDoubleBuffering();
            StdDraw.clear();
            StdDraw.text(50,50,"Let the game begin !");
            StdDraw.show();
            StdDraw.pause(1000);
            StdDraw.clear();
            int compteur = 0;
            while (compteur < numberOfPlayers) {
                Player player = playerArrayList.get(compteur); // On sélectionne le joueur
                playing(playerArrayList,territoryArrayList,compteur,numberOfPlayers, player.getReinforcement());
                player.computerReinforcement(); //on calcule le nombre de renforts
                //on permet au joueur de placer ses renforts
                //on check si le joueur a gagné avec sa mission
//                if (player.getMission().missionAccomplished) {
//                    gameOver = true;
//                    // message de victoire
//                    // on ferme la partie
//                }
                //checking if the player has conquered all the territories
                for(int k=0; k<territoryArrayList.size();k++){
                    gameOver = true;
                    if(territoryArrayList.get(k).getProprietary() != (compteur+1)){
                        gameOver = false;
                    }
                }
                compteur++;
                if(compteur == numberOfPlayers){
                    compteur=0;
                }
            }

        }
    }

    public void playing(ArrayList<Player> playerArrayList, ArrayList<Territory> territoryArrayList, int compteur, int numberOfPlayers, int reinforcement){
        Player player = playerArrayList.get(compteur); // On sélectionne le joueur

        // ---------- Player getting reinforcements and placing them ---------- //
        if(reinforcement!=0){
            StdDraw.disableDoubleBuffering();
            StdDraw.clear();
            StdDraw.text(50,50,"Player "+ (compteur+1)+" you have reinforcements. Place them on your territories !");
            StdDraw.show();
            StdDraw.pause(2000);
            StdDraw.clear();
            //getting the map
            updateBackground(territoryArrayList, numberOfPlayers);
            //player positioning its reinforcements
            placingUnits(player, compteur, territoryArrayList, playerArrayList);
        }
        // ---------- Player does not have reinforcements and plays right away ---------- //
        else{
            StdDraw.disableDoubleBuffering();
            StdDraw.clear();
            StdDraw.text(50,50,"Player " + (compteur+1) + " it's your turn to play !");
            StdDraw.pause(2000);
            updateBackground(territoryArrayList,numberOfPlayers);
            boolean actionChosen = false;
            while (!actionChosen){
                if(StdDraw.isMousePressed()){
                    double xAction = StdDraw.mouseX();
                    double yAction = StdDraw.mouseY();
                    if(xAction>=89){
                        // attack
                        if(yAction>=58.7 && yAction<=61.7){
                            StdDraw.disableDoubleBuffering();
                            StdDraw.clear();
                            StdDraw.text(50,55,"You're going to attack !");
                            StdDraw.text(50,52,"First choose one of your territories to attack with");
                            StdDraw.text(50,49,"Then choose the territory that you want to attack");
                            StdDraw.show();
                            StdDraw.pause(1000);
                            StdDraw.clear();
                            updateBackground(territoryArrayList, numberOfPlayers);
                            ArrayList<Territory> listTerritories =  chosingTwoTerritories(player, numberOfPlayers, territoryArrayList,"attack");
                            conflict(listTerritories.get(0),listTerritories.get(1));
                            actionChosen = true;
                            updateBackground(territoryArrayList, numberOfPlayers);
                        }
                        // move
                        else if(yAction>=54.9 && yAction<=57.4){
                            ArrayList<Territory> listTerritories =  chosingTwoTerritories(player, numberOfPlayers, territoryArrayList,"move");
                            int numberOfUnits = listTerritories.get(0).getNbSoldier() + listTerritories.get(0).getNbCavalery() + listTerritories.get(0).getNbCanon();
                            movingUnits(listTerritories.get(0),listTerritories.get(1));
                            updateBackground(territoryArrayList,numberOfPlayers);
                        }
                        // pass
                        else if(yAction>=50.8 && yAction<=53.3){
                            StdDraw.clear();
                            StdDraw.show();
                            return;
                        }
                        // clicking on the mission
                        else if (yAction >= 43.96 && yAction <= 46.96) {
                            StdDraw.disableDoubleBuffering();
                            StdDraw.clear();
                            StdDraw.text(50, 50, "Player " + (compteur + 1) + ", your mission is :  " + player.getMission().getBriefing());
                            StdDraw.show();
                            StdDraw.pause(5000);
                            StdDraw.clear();
                            updateBackground(territoryArrayList, playerArrayList.size());
                        }
                        //clicking on inspection
                        else if(yAction>=39.34 && yAction<=42.47) {
                            StdDraw.disableDoubleBuffering();
                            StdDraw.clear();
                            StdDraw.text(50,50,"Click on a territory to inspect it !");
                            StdDraw.show();
                            StdDraw.pause(2000);
                            updateBackground(territoryArrayList, playerArrayList.size());
                            boolean territoryChecked = false;
                            while (!territoryChecked) {
                                if (StdDraw.isMousePressed()) {
                                    //coordinates of the click
                                    double xTerritory = StdDraw.mouseX();
                                    double yTerritory = StdDraw.mouseY();
                                    StdDraw.pause(200);
                                    for (int u = 0; u < territoryArrayList.size(); u++) {
                                        Territory territory = territoryArrayList.get(u);
                                        if ((xTerritory >= territory.getX() - 2 && xTerritory <= territory.getX() + 2) && (yTerritory >= territory.getY() - 4 && yTerritory <= territory.getY() + 4)) {
                                            StdDraw.disableDoubleBuffering();
                                            StdDraw.clear();
                                            StdDraw.text(50, 60, "Here are the units available in " + territory.getNameTerritory());
                                            StdDraw.text(30, 50, "Number of Soldiers : " + territory.getNbSoldier());
                                            StdDraw.text(50, 50, "Cavalry effective : " + territory.getNbCavalery());
                                            StdDraw.text(70, 50, "Number of Canons : " + territory.getNbCanon());
                                            StdDraw.show();
                                            StdDraw.pause(5000);
                                            territoryChecked = true;
                                            updateBackground(territoryArrayList, playerArrayList.size());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    public void movingUnits(Territory territoryGivingUnits, Territory territoryReceivingUnits){
        boolean unitsChosen = false;
        while (!unitsChosen) {
            int[] xTextSoldier = new int[territoryGivingUnits.getNbSoldier() + 1];
            StdDraw.disableDoubleBuffering();
            StdDraw.clear();
            StdDraw.text(50, 60, "How many soldiers do you want to go from " + territoryGivingUnits.getNameTerritory()+" to " + territoryReceivingUnits.getNameTerritory() + " ?");
            StdDraw.text(40, 55, "Number of soldiers : ");
            for (int it = 0; it <= territoryGivingUnits.getNbSoldier(); it++) {
                StdDraw.text((45 + it * 2) + 2, 55, String.valueOf(it));
                xTextSoldier[it] = (45 + it * 2) + 2;
            }
            StdDraw.show();
            StdDraw.pause(5000);
            boolean numberOfSoldiersChosen = false;
            int nbSoldierAttacker = 0;
            while (!numberOfSoldiersChosen) {
                if (StdDraw.isMousePressed()) {
                    double xSoldier = StdDraw.mouseX();
                    double ySoldier = StdDraw.mouseY();
                    if (ySoldier >= 53.7 && ySoldier <= 56.3) {
                        for (int k = 0; k <= territoryGivingUnits.getNbSoldier(); k++) {
                            if (xSoldier >= xTextSoldier[k] - 1.1 && xSoldier <= xTextSoldier[k] + 1.1) {
                                nbSoldierAttacker = k;
                                numberOfSoldiersChosen = true;
                            }
                        }
                    }
                    StdDraw.pause(90);
                }
            }



            int[] xTextCavalier = new int[territoryGivingUnits.getNbCavalery() + 1];
            StdDraw.disableDoubleBuffering();
            StdDraw.clear();
            StdDraw.text(50, 60, "How many cavaliers do you want to go from " + territoryGivingUnits.getNameTerritory()+" to " + territoryReceivingUnits.getNameTerritory() + " ?");
            StdDraw.text(40, 55, "Number of cavaliers : ");
            for (int it = 0; it <= territoryGivingUnits.getNbCavalery(); it++) {
                StdDraw.text((45 + it * 2) + 2, 55, String.valueOf(it));
                xTextCavalier[it] = (45 + it * 2) + 2;
            }
            StdDraw.show();
            StdDraw.pause(5000);
            boolean numberOfCavaliersChosen = false;
            int nbCavaleryAttacker = 0;
            while (!numberOfCavaliersChosen) {
                if (StdDraw.isMousePressed()) {
                    double xCavalery = StdDraw.mouseX();
                    double yCavalery = StdDraw.mouseY();

                    if (yCavalery >= 53.7 && yCavalery <= 56.3) {
                        for (int k = 0; k <= territoryGivingUnits.getNbCavalery(); k++) {
                            if (xCavalery >= xTextCavalier[k] - 1.1 && xCavalery <= xTextCavalier[k] + 1.1) {
                                nbCavaleryAttacker = k;
                                numberOfCavaliersChosen = true;
                            }
                        }
                    }
                    StdDraw.pause(90);
                }
            }



            int[] xTextCanon = new int[territoryGivingUnits.getNbCanon() + 1];
            StdDraw.disableDoubleBuffering();
            StdDraw.clear();
            StdDraw.text(50, 60, "How many canons do you want to go from " + territoryGivingUnits.getNameTerritory()+" to " + territoryReceivingUnits.getNameTerritory() + " ?");
            StdDraw.text(40, 55, "Number of canons : ");
            for (int it = 0; it <= territoryGivingUnits.getNbCanon(); it++) {
                StdDraw.text((45 + it * 2) + 2, 55, String.valueOf(it));
                xTextCanon[it] = (45 + it * 2) + 2;
            }
            StdDraw.show();
            StdDraw.pause(5000);
            boolean numberOfCanonChosen = false;
            int nbCanonAttacker = 0;
            while (!numberOfCanonChosen) {
                if (StdDraw.isMousePressed()) {
                    double xCanon = StdDraw.mouseX();
                    double yCanon = StdDraw.mouseY();
                    if (yCanon >= 53.7 && yCanon <= 56.3) {
                        for (int k = 0; k <= territoryGivingUnits.getNbCanon(); k++) {
                            if (xCanon >= xTextCanon[k] - 1.1 && xCanon <= xTextCanon[k] + 1.1) {
                                nbCanonAttacker = k;
                                numberOfCanonChosen = true;
                            }
                        }
                    }
                    StdDraw.pause(90);
                }
            }

            int unitsTerritory = territoryGivingUnits.getNbSoldier() + territoryGivingUnits.getNbCanon() + territoryGivingUnits.getNbCavalery();
            int units = nbSoldierAttacker + nbCavaleryAttacker + nbCanonAttacker;

            if((nbCanonAttacker + nbCavaleryAttacker + nbSoldierAttacker !=0) && (unitsTerritory>units)){
                // giving units
                territoryGivingUnits.setNbSoldier(territoryGivingUnits.getNbSoldier()-nbSoldierAttacker);
                territoryGivingUnits.setNbCavalery(territoryGivingUnits.getNbCavalery()-nbCavaleryAttacker);
                territoryGivingUnits.setNbCanon(territoryGivingUnits.getNbCanon()-nbCanonAttacker);
                //receiving units
                territoryReceivingUnits.setNbSoldier(territoryReceivingUnits.getNbSoldier()+nbSoldierAttacker);
                territoryReceivingUnits.setNbCavalery(territoryReceivingUnits.getNbCavalery()+nbCavaleryAttacker);
                territoryReceivingUnits.setNbCanon(territoryReceivingUnits.getNbCanon()+nbCanonAttacker);
                unitsChosen = true;
            }
            // no mouvement
            if(nbCanonAttacker + nbCavaleryAttacker + nbSoldierAttacker ==0){
                StdDraw.disableDoubleBuffering();
                StdDraw.clear();
                StdDraw.text(50,50,"No units were moved !");
                StdDraw.show();
                StdDraw.pause(1500);
                return;
            }

            if(unitsTerritory<=units){
                StdDraw.disableDoubleBuffering();
                StdDraw.clear();
                StdDraw.text(50,50,"At least one unit needs to remain in " + territoryGivingUnits.getNameTerritory());
                StdDraw.show();
                StdDraw.pause(1500);
            }
        }
    }

    public ArrayList<Territory> chosingTwoTerritories(Player player, int numberOfPlayers, ArrayList<Territory> territories, String button){
        ArrayList<Territory> territoryArrayList = new ArrayList<>();
        boolean attackingTerritoryChosen = false;
        while (!attackingTerritoryChosen) {
            StdDraw.disableDoubleBuffering();
            if (StdDraw.isMousePressed()) {
                double xAttackingTerritory = StdDraw.mouseX();
                double yAttackingTerritory = StdDraw.mouseY();
                StdDraw.pause(200);
                if (xAttackingTerritory < 89) {
                    for (int a = 0; a < player.getArraylistTerritories().size(); a++) {
                        Territory territory = player.getArraylistTerritories().get(a);
                        if ((xAttackingTerritory >= territory.getX() - 2 && xAttackingTerritory <= territory.getX() + 2) && (yAttackingTerritory >= territory.getY() - 4 && yAttackingTerritory <= territory.getY() + 4)) {
                            Territory attackingTerritory = territory;
                            attackingTerritoryChosen = true;
                            StdDraw.clear();
                            if(button.equals("attack")){
                                StdDraw.text(50,50,"You chose to attack with " + attackingTerritory.getNameTerritory());
                                StdDraw.show();

                            }
                            else if(button.equals("move")){
                                StdDraw.text(50,50,"You chose to move units from " + attackingTerritory.getNameTerritory());
                                StdDraw.show();

                            }
                            StdDraw.pause(2000);
                            updateBackground(territories,numberOfPlayers);
                            boolean defendingTerritoryChosen = false;
                            while (!defendingTerritoryChosen) {
                                if (StdDraw.isMousePressed()) {
                                    double xDefendingTerritory = StdDraw.mouseX();
                                    double yDefendingTerritory = StdDraw.mouseY();
                                    StdDraw.pause(200);
                                    if (xDefendingTerritory < 89) {
                                        for (a = 0; a < territories.size(); a++) {
                                            Territory territory2 = territories.get(a);
                                            if ((xDefendingTerritory >= territory2.getX() - 2 && xDefendingTerritory <= territory2.getX() + 2) && (yDefendingTerritory >= territory2.getY() - 4 && yDefendingTerritory <= territory2.getY() + 4)) {
                                                Territory defendingTerritory = territory2;
                                                if(button.equals("attack")){
                                                    // verifying that the defending territory is next to the attacking one
                                                    boolean frontier = false;
                                                    for(int d=0; d<attackingTerritory.getFrontier().length;d++){
                                                        if(defendingTerritory.getIdTerritory() == attackingTerritory.getFrontier()[d]){
                                                            frontier = true;
                                                        }
                                                    }
                                                    // verifying that the it isn't the same territory and that it belongs to two different players
                                                    if(territory2 != territory && territory2.getProprietary() != territory.getProprietary() && frontier){
                                                        defendingTerritoryChosen = true;
                                                        StdDraw.clear();
                                                        StdDraw.text(50,50,"You chose to attack " + defendingTerritory.getNameTerritory());
                                                        StdDraw.show();
                                                        StdDraw.pause(1000);
                                                        updateBackground(territories,numberOfPlayers);
                                                        territoryArrayList.add(0, attackingTerritory);
                                                        territoryArrayList.add(1, defendingTerritory);
                                                    }
                                                    else{
                                                        StdDraw.clear();
                                                        StdDraw.text(50,50,"Please choose another territory");
                                                        StdDraw.show();
                                                        StdDraw.pause(1000);
                                                        updateBackground(territories, numberOfPlayers);
                                                    }
                                                }
                                                else if(button.equals("move")){
                                                    // verifying that the defending territory is next to the attacking one
                                                    boolean frontier = false;
                                                    for(int d=0; d<attackingTerritory.getFrontier().length;d++){
                                                        if(defendingTerritory.getIdTerritory() == attackingTerritory.getFrontier()[d]){
                                                            frontier = true;
                                                        }
                                                    }
                                                    if(territory2 != territory && territory2.getProprietary() == territory.getProprietary() && frontier){
                                                        defendingTerritoryChosen = true;
                                                        StdDraw.clear();
                                                        StdDraw.text(50,50,"You chose to move your units to " + defendingTerritory.getNameTerritory());
                                                        StdDraw.show();
                                                        StdDraw.pause(1000);
                                                        updateBackground(territories,numberOfPlayers);
                                                        territoryArrayList.add(0, attackingTerritory);
                                                        territoryArrayList.add(1, defendingTerritory);
                                                    }
                                                    else{
                                                        StdDraw.clear();
                                                        StdDraw.text(50,50,"Please choose another territory");
                                                        StdDraw.show();
                                                        StdDraw.pause(1000);
                                                        updateBackground(territories, numberOfPlayers);
                                                    }
                                                }

                                            }
                                        }
                                    }

                                }
                            }

                        }
                    }
                }
            }
        }
        return territoryArrayList;

    }


    // ---------- Initialising Armies in the different territories ---------- //
    public void initiateArmy(ArrayList<Player> playerArrayList, ArrayList<Territory> territoryArrayList) {

        updateBackground(territoryArrayList, playerArrayList.size());

        for (int k = 0; k<playerArrayList.size();k++){
            Player player = playerArrayList.get(k);

            //updating player's reinforcements with 1 unit in each territory
            player.setReinforcement(player.getReinforcement()-player.getArraylistTerritories().size());

            // placing units
            placingUnits(player, k, territoryArrayList, playerArrayList);

        }
    }

    // placing the player's units
    public void placingUnits(Player player, int k, ArrayList<Territory> territoryArrayList, ArrayList<Player> playerArrayList){
        while (player.getReinforcement()!=0) {
            StdDraw.disableDoubleBuffering();
            // going through the player's territories when there is a click
            if (StdDraw.isMousePressed()) {
                //coordinates of the click
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                StdDraw.pause(200);
                // clicked on the menu panel
                if(x>=89) {
                    // clicking on the mission
                    if (y >= 43.96 && y <= 46.96) {
                        StdDraw.clear();
                        StdDraw.text(50, 50, "Player " + (k + 1) + ", your mission is :  " + player.getMission().getBriefing());
                        StdDraw.pause(5000);
                        StdDraw.clear();
                        updateBackground(territoryArrayList, playerArrayList.size());
                    }
                    //clicking on inspection
                    else if(y>=39.34 && y<=42.47) {
                        StdDraw.clear();
                        StdDraw.text(50,50,"Click on a territory to inspect it !");
                        StdDraw.pause(2000);
                        updateBackground(territoryArrayList, playerArrayList.size());
                        boolean territoryChecked = false;
                        while (!territoryChecked) {
                            if (StdDraw.isMousePressed()) {
                                //coordinates of the click
                                double xTerritory = StdDraw.mouseX();
                                double yTerritory = StdDraw.mouseY();
                                StdDraw.pause(200);
                                for (int u = 0; u < territoryArrayList.size(); u++) {
                                    Territory territory = territoryArrayList.get(u);
                                    if ((xTerritory >= territory.getX() - 2 && xTerritory <= territory.getX() + 2) && (yTerritory >= territory.getY() - 4 && yTerritory <= territory.getY() + 4)) {
                                        StdDraw.clear();
                                        StdDraw.text(50, 60, "Here are the units available in " + territory.getNameTerritory());
                                        StdDraw.text(30, 50, "Number of Soldiers : " + territory.getNbSoldier());
                                        StdDraw.text(50, 50, "Cavalry effective : " + territory.getNbCavalery());
                                        StdDraw.text(70, 50, "Number of Canons : " + territory.getNbCanon());
                                        StdDraw.pause(5000);
                                        territoryChecked = true;
                                        updateBackground(territoryArrayList, playerArrayList.size());
                                    }
                                }
                            }
                        }
                    }
                }
                //clicked on the map
                else {
                    for (int u = 0; u < player.getArraylistTerritories().size(); u++) {
                        Territory territory = player.getArraylistTerritories().get(u);
                        if ((x >= territory.getX() - 2 && x <= territory.getX() + 2) && (y >= territory.getY() - 4 && y <= territory.getY() + 4)) {
                            boolean unitChosen = false;
                            StdDraw.clear();
                            StdDraw.setPenColor(Color.BLACK);
                            StdDraw.text(50, 90, "Please choose what type of unit you want to add to " + territory.getNameTerritory());
                            StdDraw.text(50, 85, "You have " + player.getReinforcement() + " points of reinforcement left");
                            StdDraw.text(20, 35, "Points of reinforcement : 3");
                            StdDraw.text(50, 35, "Points of reinforcement : 1");
                            StdDraw.text(80, 35, "Points of reinforcement : 7");
                            StdDraw.picture(20, 50, "img/cavalery.png");
                            StdDraw.picture(50, 50, "img/soldier.png");
                            StdDraw.picture(80, 50, "img/canon.png");
                            while (!unitChosen) {
                                if (StdDraw.isMousePressed()) {
                                    //coordinates of the click
                                    double xUnit = StdDraw.mouseX();
                                    double yUnit = StdDraw.mouseY();
                                    StdDraw.pause(200);
                                    if (yUnit >= 40 && yUnit <= 60) {
                                        if ((xUnit >= 13 && xUnit <= 26) & player.getReinforcement() >= 3) {
                                            territory.setNbCavalery(territory.getNbCavalery() + 1);
                                            player.setReinforcement(player.getReinforcement() - 3);
                                            unitChosen = true;
                                        } else if ((xUnit >= 44 && xUnit <= 57) && player.getReinforcement() >= 1) {
                                            territory.setNbSoldier(territory.getNbSoldier() + 1);
                                            player.setReinforcement(player.getReinforcement() - 1);
                                            unitChosen = true;
                                        } else if ((xUnit >= 73 && xUnit <= 87) && player.getReinforcement() >= 7) {
                                            territory.setNbCanon(territory.getNbCanon() + 1);
                                            player.setReinforcement(player.getReinforcement() - 7);
                                            unitChosen = true;
                                        }
                                    }
                                }
                            }
                            updateBackground(territoryArrayList, playerArrayList.size());
                        }

                    }
                }

            }
        }
    }

    // initialising the game's background
    public void initiateBackground(ArrayList<Territory> territoryArrayList, int numberOfPlayers ){
        // map of the world
        StdDraw.setCanvasSize(1200,737);
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0,100);
        StdDraw.setYscale(0,100);
        StdDraw.clear();
        StdDraw.picture(50,50, "img/riskmap.jpg");
        for(int a=0;a<numberOfPlayers;a++){
            if(a==0) {
                StdDraw.setPenColor(Color.RED);
                StdDraw.filledSquare(91,85,1);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.text(95,85, " - Player " + (a+1));
            }
            else if(a==1) {
                StdDraw.setPenColor(Color.GREEN);
                StdDraw.filledSquare(91,81,1);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.text(95,81, " - Player " + (a+1));
            }
            else if(a==2) {
                StdDraw.setPenColor(Color.BLUE);
                StdDraw.filledSquare(91,77,1);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.text(95,77, " - Player " + (a+1));
            }
            else if(a==3) {
                StdDraw.setPenColor(Color.MAGENTA);
                StdDraw.filledSquare(91,73,1);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.text(95,73, " - Player " + (a+1));
            }
            else if(a==4) {
                StdDraw.setPenColor(Color.ORANGE);
                StdDraw.filledSquare(91,69,1);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.text(95,69, " - Player " + (a+1));
            }
            else if(a==5) {
                StdDraw.setPenColor(Color.GRAY);
                StdDraw.filledSquare(91,65,1);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.text(95,65, " - Player " + (a+1));
            }

        }
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(94,60,3.5,1.5);
        StdDraw.text(94,60,"Attack");
        StdDraw.rectangle(94,56,3.5,1.5);
        StdDraw.text(94,56,"Move");
        StdDraw.rectangle(94,52,3.5,1.5);
        StdDraw.text(94,52,"Pass");
        StdDraw.rectangle(94,45,3.5,1.5);
        StdDraw.text(94,45,"Mission");
        StdDraw.rectangle(94,41,3.5,1.5);
        StdDraw.text(94,41,"Inspection");
        double [] xList = {2.75, 14.666666666666666, 12.666666666666666, 17.166666666666668, 23.666666666666668, 29.333333333333332, 12.333333333333334, 22.0, 11.333333333333334,
                18.333333333333332, 27.0, 18.916666666666668, 20.833333333333332, 48.25, 47.416666666666664, 58.666666666666664, 52.583333333333336, 44.5, 39.916666666666664,
                37.083333333333336, 38.25, 41.083333333333336, 37.333333333333336, 45.666666666666664, 43.666666666666664, 51.333333333333336, 53.833333333333336, 59.333333333333336,
                61.583333333333336, 66.25, 70.66666666666667, 78.58333333333333, 70.25, 74.91666666666667, 74.08333333333333, 64.83333333333333, 73.91666666666667, 82.0,
                74.08333333333333, 82.83333333333333, 77.58333333333333, 85.0};
        double [] yList = {80.32564450474898, 89.41655359565807, 72.86295793758481, 77.06919945725916, 77.88331071913161, 86.29579375848033, 61.601085481682496, 64.4504748982361,
                49.11804613297151, 40.43419267299864, 30.80054274084125, 31.343283582089555, 15.739484396200808, 21.03120759837178, 8.005427408412487, 10.447761194029852,
                25.915875169606508, 42.33378561736771, 42.06241519674356, 83.85345997286296, 51.01763907734057, 63.229308005427406, 65.40027137042063, 53.595658073270016,
                75.30529172320217, 69.74219810040705, 45.59023066485753, 59.15875169606513, 71.09905020352781, 80.1899592944369, 84.93894165535957, 84.53188602442334,
                72.59158751696066, 68.24966078697422, 54.274084124830395, 44.36906377204885, 40.298507462686565, 67.57123473541384, 30.664857530529176, 34.32835820895522,
                17.77476255088196, 10.854816824966079};

        // creating the circles in the map
        for(int it=0;it<xList.length;it++){
            //adding coordinates to the territory
            territoryArrayList.get(it).setX(xList[it]);
            territoryArrayList.get(it).setY(yList[it]);
            // coordinates of the circles
            double x = xList[it];
            double y = yList[it];
            StdDraw.setPenColor(Color.white);
            StdDraw.filledEllipse(x,y,1,2);
            StdDraw.setPenRadius(0.005);
            //color of the player
            if(territoryArrayList.get(it).getProprietary()==1){
                StdDraw.setPenColor(Color.RED);
            }
            else if(territoryArrayList.get(it).getProprietary()==2){
                StdDraw.setPenColor(Color.GREEN);
            }
            else if(territoryArrayList.get(it).getProprietary()==3){
                StdDraw.setPenColor(Color.BLUE);
            }
            else if(territoryArrayList.get(it).getProprietary()==4){
                StdDraw.setPenColor(Color.MAGENTA);
            }
            else if(territoryArrayList.get(it).getProprietary()==5){
                StdDraw.setPenColor(Color.ORANGE);
            }
            else if(territoryArrayList.get(it).getProprietary()==6){
                StdDraw.setPenColor(Color.GRAY);
            }
            else{
                StdDraw.setPenColor(Color.BLACK);
            }
            StdDraw.ellipse(x,y,1,2);
            StdDraw.setPenColor(Color.black);
            //giving one soldier to each territory
            territoryArrayList.get(it).setNbSoldier(1);
            // number of armies
            int numberCanons = territoryArrayList.get(it).getNbCanon();
            int numberCavalery = territoryArrayList.get(it).getNbCavalery();
            int numberSoldier = territoryArrayList.get(it).getNbSoldier();
            int numberArmies = 7*numberCanons + 3*numberCavalery + numberSoldier;
            // showing the number of armies in each territory
            StdDraw.text(x,y, String.valueOf(numberArmies));
        }

        // display and pause for 20 ms
        StdDraw.show();
        //StdDraw.pause(20);


    }

    //updating the game's background
    public void updateBackground(ArrayList<Territory> territoryArrayList, int numberOfPlayers){
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();
        StdDraw.picture(50,50, "img/riskmap.jpg");
        for(int a=0;a<numberOfPlayers;a++){
            if(a==0) {
                StdDraw.setPenColor(Color.RED);
                StdDraw.filledSquare(91,85,1);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.text(95,85, " - Player " + (a+1));
            }
            else if(a==1) {
                StdDraw.setPenColor(Color.GREEN);
                StdDraw.filledSquare(91,81,1);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.text(95,81, " - Player " + (a+1));
            }
            else if(a==2) {
                StdDraw.setPenColor(Color.BLUE);
                StdDraw.filledSquare(91,77,1);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.text(95,77, " - Player " + (a+1));
            }
            else if(a==3) {
                StdDraw.setPenColor(Color.MAGENTA);
                StdDraw.filledSquare(91,73,1);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.text(95,73, " - Player " + (a+1));
            }
            else if(a==4) {
                StdDraw.setPenColor(Color.ORANGE);
                StdDraw.filledSquare(91,69,1);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.text(95,69, " - Player " + (a+1));
            }
            else if(a==5) {
                StdDraw.setPenColor(Color.GRAY);
                StdDraw.filledSquare(91,65,1);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.text(95,65, " - Player " + (a+1));
            }

        }
        StdDraw.rectangle(94,60,3.5,1.5);
        StdDraw.text(94,60,"Attack");
        StdDraw.rectangle(94,56,3.5,1.5);
        StdDraw.text(94,56,"Move");
        StdDraw.rectangle(94,52,3.5,1.5);
        StdDraw.text(94,52,"Pass");
        StdDraw.rectangle(94,45,3.5,1.5);
        StdDraw.text(94,45,"Mission");
        StdDraw.rectangle(94,41,3.5,1.5);
        StdDraw.text(94,41,"Inspection");
        double [] xList = {2.75, 14.666666666666666, 12.666666666666666, 17.166666666666668, 23.666666666666668, 29.333333333333332, 12.333333333333334, 22.0, 11.333333333333334,
                18.333333333333332, 27.0, 18.916666666666668, 20.833333333333332, 48.25, 47.416666666666664, 58.666666666666664, 52.583333333333336, 44.5, 39.916666666666664,
                37.083333333333336, 38.25, 41.083333333333336, 37.333333333333336, 45.666666666666664, 43.666666666666664, 51.333333333333336, 53.833333333333336, 59.333333333333336,
                61.583333333333336, 66.25, 70.66666666666667, 78.58333333333333, 70.25, 74.91666666666667, 74.08333333333333, 64.83333333333333, 73.91666666666667, 82.0,
                74.08333333333333, 82.83333333333333, 77.58333333333333, 85.0};
        double [] yList = {80.32564450474898, 89.41655359565807, 72.86295793758481, 77.06919945725916, 77.88331071913161, 86.29579375848033, 61.601085481682496, 64.4504748982361,
                49.11804613297151, 40.43419267299864, 30.80054274084125, 31.343283582089555, 15.739484396200808, 21.03120759837178, 8.005427408412487, 10.447761194029852,
                25.915875169606508, 42.33378561736771, 42.06241519674356, 83.85345997286296, 51.01763907734057, 63.229308005427406, 65.40027137042063, 53.595658073270016,
                75.30529172320217, 69.74219810040705, 45.59023066485753, 59.15875169606513, 71.09905020352781, 80.1899592944369, 84.93894165535957, 84.53188602442334,
                72.59158751696066, 68.24966078697422, 54.274084124830395, 44.36906377204885, 40.298507462686565, 67.57123473541384, 30.664857530529176, 34.32835820895522,
                17.77476255088196, 10.854816824966079};

        // creating the circles in the map
        for(int it=0;it<xList.length;it++){
            // coordinates of the circles
            double x = xList[it];
            double y = yList[it];
            StdDraw.setPenColor(Color.white);
            StdDraw.filledEllipse(x,y,1,2);
            StdDraw.setPenRadius(0.005);
            //color of the player
            if(territoryArrayList.get(it).getProprietary()==1){
                StdDraw.setPenColor(Color.RED);
            }
            else if(territoryArrayList.get(it).getProprietary()==2){
                StdDraw.setPenColor(Color.GREEN);
            }
            else if(territoryArrayList.get(it).getProprietary()==3){
                StdDraw.setPenColor(Color.BLUE);
            }
            else if(territoryArrayList.get(it).getProprietary()==4){
                StdDraw.setPenColor(Color.MAGENTA);
            }
            else if(territoryArrayList.get(it).getProprietary()==5){
                StdDraw.setPenColor(Color.ORANGE);
            }
            else if(territoryArrayList.get(it).getProprietary()==6){
                StdDraw.setPenColor(Color.PINK);
            }
            else if(territoryArrayList.get(it).getProprietary()==7){
                StdDraw.setPenColor(Color.GRAY);
            }
            else if(territoryArrayList.get(it).getProprietary()==8){
                StdDraw.setPenColor(Color.YELLOW);
            }
            else{
                StdDraw.setPenColor(Color.BLACK);
            }
            StdDraw.ellipse(x,y,1,2);
            StdDraw.setPenColor(Color.black);
            // number of armies
            int numberCanons = territoryArrayList.get(it).getNbCanon();
            int numberCavalery = territoryArrayList.get(it).getNbCavalery();
            int numberSoldier = territoryArrayList.get(it).getNbSoldier();
            int numberArmies = 7*numberCanons + 3*numberCavalery + numberSoldier;
            // showing the number of armies in each territory
            StdDraw.text(x,y, String.valueOf(numberArmies));
        }

        // display and pause for 20 ms
        StdDraw.show();
        //StdDraw.pause(20);

    }

    public ArrayList<Territory> initiateTerritories(){
        ArrayList<Territory> territoryArrayList = new ArrayList<>();
        String [] territorylist = {"Alaska", "North West Territory", "Alberta", "Ontario", "Quebec", "Groenland","Western United States",
                "Eastern United States", "Central America", "Venezuela", "Brazil", "Peru", "Argentina", "Congo","South Africa", "Madagascar",
                "East Africa","Egypt","North Africa","Iceland","Western Europe","Northern Europe","Great Britain","Southern Europe","Scandinavia",
                "Ukraine","Middle East","Afghanistan","Ural","Siberia","Yakutsk","Kamchatka","Irkustsk","Mongolia","China",
                "India","Siam","Japan","Indonesia","New Guinea","Western Australia","Eastern Australia"};

        ArrayList<int [][]> listoffrontiers = new ArrayList<int [][]>();

        int [] [] listfrontiers = {{2, 3, 32}, // Alaska id 1
                {1, 3, 4, 6}, //"North West Territory", 2
                {1, 2, 4, 7},//"Alberta", 3
                {2, 3, 5, 6}, //"Ontario", 4
                {4, 6, 8},//"Quebec", 5
                {2, 4, 20},//"Groenland",6
                {3, 4, 8, 9},// "Western United States", 7
                {4, 5, 7, 9},//"Eastern United States", 8
                {7, 8, 10},//"Central America", 9
                {9, 11, 12},//"Venezuela", 10
                {10, 12, 13},//"Brazil", 11
                {10, 11, 13},//"Peru", 12
                {11, 12},//"Argentina", 13
                {15, 17, 19},//"Congo", 14
                {14, 16, 17},//"South Africa", 15
                {15, 17},//"Madagascar",    16
                {14, 15, 16, 18, 19}, //"East Africa"17
                {17, 19, 24, 27},//"Egypt",18
                {11, 14, 17, 18, 21, 24},//"North Africa",19
                {6, 23, 25},//"Iceland", 20
                {19, 22, 23, 24},//"Western Europe","21
                {21, 23, 24, 25},//Northern Europe",22
                {20, 21, 22, 25},//"Great Britain","23
                {18, 19, 21, 22, 26},//Southern Europe",24
                {20, 22, 23, 26},//"Scandinavia",25
                {22, 24, 25, 27, 28, 29},//"Ukraine","26
                {17, 18, 24, 26, 28, 36},//Middle East","27
                {26, 27, 29, 35, 36},//Afghanistan",28
                {26, 28, 30, 35},//"Ural","29
                {29, 31, 33, 34, 35},//Siberia",30
                {30, 32, 33},//"Yakutsk",31
                {1, 31, 33, 34, 38},//"Kamchatka",32
                {30, 31, 32, 34},//"Irkustsk",33
                {30, 32, 33, 35, 38},//"Mongolia",34
                {28, 29, 30, 34, 36, 37},//"China",35
                {27, 28, 35, 37},//"India",36
                {35, 36, 39},//"Siam", 37
                {32, 34},//"Japan",38
                {37, 40, 41},//"Indonesia",39
                {39, 40, 41},//"New Guinea",40
                {39,40,42},//"Western Australia"41
                {40,41},//, "Eastern Australia"42
        };
        listoffrontiers.add(listfrontiers);

        for(int it=1; it<=territorylist.length;it++){
            String territoryName= territorylist[it-1];
            int[] territoryFrontiers= listfrontiers[it-1];
            Territory territory = new Territory(it,0, territoryName, territoryFrontiers);
            territoryArrayList.add(territory);
        }

        return territoryArrayList;

    }

    public ArrayList<Region> initiateRegions(ArrayList<Territory> territoryArrayList){

        ArrayList<Region> regionArrayList = new ArrayList<Region>();

        ArrayList<Territory> territoryArrayListNorthamerica = new ArrayList<Territory>();
        for (int it=0; it<9; it++){
            territoryArrayListNorthamerica.add(territoryArrayList.get(it)); // on ajoute à la liste des territoires americains les 9 premiers elements
        }
        Region northAmerica = new Region(1, "North America", 0, territoryArrayListNorthamerica);
        regionArrayList.add(northAmerica);

        ArrayList<Territory> territoryArrayListSouthamerica = new ArrayList<Territory>();
        for (int it=9; it<13; it++){
            territoryArrayListSouthamerica.add(territoryArrayList.get(it)); // on ajoute à la liste des territoires sud americains les 4 elements suivants
        }
        Region southAmerica = new Region(1, "South America", 0, territoryArrayListSouthamerica);
        regionArrayList.add(southAmerica);

        ArrayList<Territory> territoryArrayListAfrica = new ArrayList<Territory>();
        for (int it=14; it<19; it++){
            territoryArrayListAfrica.add(territoryArrayList.get(it)); // on ajoute à la liste des territoires africains les 6 elements suivants
        }
        Region africa = new Region(1, "Africa", 0, territoryArrayListAfrica);
        regionArrayList.add(africa);

        ArrayList<Territory> territoryArrayListEurope = new ArrayList<Territory>();
        for (int it=19; it<26; it++){
            territoryArrayListEurope.add(territoryArrayList.get(it)); // on ajoute à la liste des territoires europées les 7 elements suivants
        }
        Region europe = new Region(1, "Europe", 0, territoryArrayListNorthamerica);
        regionArrayList.add(europe);

        ArrayList<Territory> territoryArrayListAsia = new ArrayList<Territory>();
        for (int it=26; it<37; it++){
            territoryArrayListAsia.add(territoryArrayList.get(it)); // on ajoute à la liste des territoires americains les 11 elements suivants
        }
        Region asia = new Region(1, "Asia", 0, territoryArrayListAsia);
        regionArrayList.add(asia);

        ArrayList<Territory> territoryArrayListOceania = new ArrayList<Territory>();
        for (int it=37; it<41; it++){
            territoryArrayListNorthamerica.add(territoryArrayList.get(it)); // on ajoute à la liste des territoires americains les 4 elements suivants
        }
        Region oceania = new Region(1, "Oceania", 0, territoryArrayListNorthamerica);
        regionArrayList.add(oceania);

        return regionArrayList;

    }

    public void territoryInitialisation(Player player, int playerId, int numberOfPlayers, ArrayList<Territory> territories){
        Random random = new Random();
        int numberTerritories = territories.size();
        switch(numberOfPlayers){
            case 2:
                player.setReinforcement(40);
                for (int twoPlayers = 0; twoPlayers < numberTerritories/2; twoPlayers++){
                    int territoryId2 = random.nextInt(numberTerritories);
                    addTerritory(player, playerId,territoryId2,territories);
                }
                break;
            case 3:
                player.setReinforcement(35);
                for (int threePlayers = 0; threePlayers < numberTerritories/3; threePlayers++){
                    int territoryId3 = random.nextInt(numberTerritories);
                    addTerritory(player, playerId,territoryId3,territories);
                }
                break;
            case 4:
                player.setReinforcement(30);
                for (int fourPlayers = 0; fourPlayers < numberTerritories/4; fourPlayers++){ // il restera deux territoires à distribuer
                    int territoryId4 = random.nextInt(numberTerritories);
                    addTerritory(player, playerId,territoryId4,territories);
                }
                break;
            case 5:
                player.setReinforcement(25);
                for (int fivePlayers = 0; fivePlayers < numberTerritories/5; fivePlayers++){ // il restera deux territoires à distribuer
                    int territoryId5 = random.nextInt(numberTerritories);
                    addTerritory(player, playerId,territoryId5,territories);
                }
                break;
            case 6:
                player.setReinforcement(20);
                for (int sixPlayers = 0; sixPlayers < numberTerritories/6; sixPlayers++){
                    int territoryId6 = random.nextInt(numberTerritories);
                    addTerritory(player, playerId,territoryId6,territories);
                }
                break;
            default:
                player.setReinforcement(0);
        }
    }

    public void addTerritory(Player player, int playerId, int id, ArrayList<Territory> territoryArrayList){
        Random random = new Random();
        Territory territory = territoryArrayList.get(id);
        if (territory.getProprietary()==0){
            ArrayList<Territory> currentArraylist= null;
            player.getArraylistTerritories().add(0, territory);
            player.setArraylistTerritories(player.getArraylistTerritories());
            territory.setProprietary(playerId);

        }
        else {
            int toAdd= random.nextInt(territoryArrayList.size());
            addTerritory(player, playerId, toAdd, territoryArrayList);

        }
    }

    public void conflict(Territory attackerTerritory, Territory defenderTerritory){

        // ------------------- On crée l'armée attaquante -------------------//
        Army attacker = new Army();
        attacker.setAttacker(true);
        attacker.setTerritory(attackerTerritory);
        if (!attacker.generateAttacker()){ // si l'armée n'est pas générée correctement
            conflict(attackerTerritory,defenderTerritory); // on reboot
        }

        // ------------------- On crée l'armée défenseuse -------------------//
        Army defender = new Army();
        defender.setAttacker(false);
        defender.setTerritory(defenderTerritory);
        if (!defender.generateDefender()){ // si l'armée n'est pas générée correctement
            conflict(attackerTerritory,defenderTerritory); // on reboot
        }

        // ------------------- On résout un round de combat -------------------//

        attacker.battle(defender);

        // ------------------- On vérifie que les territoires sont voisins -------------------//
        boolean frontierValidation = false;
        for(int it=0; it<attackerTerritory.getFrontier().length; it++){
            if (attackerTerritory.getFrontier()[it]== defenderTerritory.getIdTerritory()){ // si on trouve l'ID du territoire attaqué dans la liste des voisins de l'attaquand
                frontierValidation = true; // l'attaque est validée
            }
        }
        if (!frontierValidation){ // sinon on stoppe la fonction
            return;
        }

    }
}
