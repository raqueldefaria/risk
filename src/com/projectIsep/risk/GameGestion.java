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
        StdDraw.pause(2000);

        // ---------- Initialising Armies in the different territories ---------- //
        initiateArmy(playerArrayList, territoryArrayList);


        while (!gameOver) {
            StdDraw.pause(400);
            System.out.println("in game");
            int compteur = 1;
            while (compteur != numberOfPlayers) {
                System.out.println("in player");


                boolean pass = false;
                Player player = playerArrayList.get(compteur); // On sélectionne le joueurs
                compteur++; // on incrémente
                player.computerReinforcement(); //on calcule le nombre de renforts
                //on permet au joueur de placer ses renforts
                int reinforcement = player.getReinforcement();


                while (!pass) {
                    //une boucle désignation d'attaque/résolution d'attaque
                    // on check si le bouton pour mettre fin à la phase d'attaque est appuyé, puis on update pass
                    pass = true;


                }

                //mouvement

                //on check si le joueur a gagné
                if (player.getMission().missionAccomplished) {
                    gameOver = true;
                    // message de victoire
                    // on ferme la partie
                }

                //message de fin de tour
            }

        }
    }

    // ---------- Initialising Armies in the different territories ---------- //
    public void initiateArmy(ArrayList<Player> playerArrayList, ArrayList<Territory> territoryArrayList) {

        updateBackground(territoryArrayList, playerArrayList.size());

        for (int k = 0; k<playerArrayList.size();k++){
            Player player = playerArrayList.get(k);

            //updating player's reinforcements with 1 unit in each territory
            player.setReinforcement(player.getReinforcement()-player.getArraylistTerritories().size());

            while (player.getReinforcement()!=0) {
                // going through the player's territories when there is a click
                if (StdDraw.isMousePressed()) {
                    //coordinates of the click
                    double x = StdDraw.mouseX();
                    double y = StdDraw.mouseY();
                    StdDraw.pause(200);
                    System.out.println(x);
                    System.out.println(y);
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
    }

    public void initiateBackground(ArrayList<Territory> territoryArrayList, int numberOfPlayers ){
        // map of the world
        StdDraw.setCanvasSize(1200,737);
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

    public void updateBackground(ArrayList<Territory> territoryArrayList, int numberOfPlayers){
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
        for(int it=0; it<territorylist.length;it++){
            String territoryName= territorylist[it];
            Territory territory = new Territory(it,0, territoryName);
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

    }
}
