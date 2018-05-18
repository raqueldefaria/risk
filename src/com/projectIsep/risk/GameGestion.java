package com.projectIsep.risk;

import edu.princeton.cs.introcs.StdDraw;

import java.util.ArrayList;
import java.util.Random;

public class GameGestion {

    public void worldMap(String map, int numberOfPlayers){
        showMap();
        boolean gameOver= false;
        ArrayList<Territory> territoryArrayList = initiateTerritories();
        int it =1; // initialising the number of players
        ArrayList<Player> playerArrayList = new ArrayList<>();
        ArrayList<Region> regionArrayList = new ArrayList<>();
        for (int i=0; i<numberOfPlayers;i++){ // initialisation des joueurs
            int a = (int) Math.floor(Math.random() * 100) + 1; // on tire l'id de mission, pour l'instant de 1 à 100
            Player player = new Player();
            //player.setMission(); //on set la mission d'ID a;
            playerArrayList.add(player);
            territoryInitialisation(player, numberOfPlayers,territoryArrayList); // on affecte ses territoires au joueur
        }
        System.out.println(playerArrayList.get(1));

        while (!gameOver){ // on
            while (it!=numberOfPlayers ){
                boolean pass = false;
                Player player = playerArrayList.get(it); // On sélectionne le joueurs
                StdDraw.show(); // on affiche la map
                it++; // on incrémente
                player.computerReinforcement(); //on calcule le nombre de renforts
                //on permet au joueur de placer ses renforts
                int reinforcement = player.getReinforcement();


                while (!pass){
                    StdDraw.show(); // on refresh la map
                    //une boucle désignation d'attaque/résolution d'attaque
                    // on check si le bouton pour mettre fin à la phase d'attaque est appuyé, puis on update pass

                }

                //mouvement

                //on check si le joueur a gagné
                if (player.getMission().missionAccomplished){
                    gameOver=true;
                    // message de victoire
                    // on ferme la partie
                }
                //message de fin de tour
            }
            it=1;

        }



    }

    public void showMap(){
        // map of the world
        StdDraw.setCanvasSize(1050,737);
        StdDraw.setXscale(0,100);
        StdDraw.setYscale(0,100);
        StdDraw.clear();
        StdDraw.picture(50,50, "img/riskmap.jpg");
        // display and pause for 20 ms
        StdDraw.show();
    }

    public ArrayList<Territory> initiateTerritories(){
        ArrayList<Territory> territoryArrayList = new ArrayList<>();
        String [] territorylist = {"Alaska", "North West Territory", "Alberta", "Ontario", "Quebec", "Groenland","Western United States",
                "Eastern United States", "Central America", "Venezuela", "Brazil", "Peru", "Argentina", "Congo","South Africa", "Madagascar",
                "East Africa","Egypt","Iceland","Western Europe","Northern Europe","Great Britain","Southern Europe","Scandinavia",
                "Ukraine","Middle East","Afghanistan","Ural","Siberia","Yakutsk","Kamchatka","Irkustsk","Mongolia","China",
                "India","Siam","Japan","Indonesia","New Guinea","Western Australia","Eastern Australia"};
        for(int it=0; it<territorylist.length;it++){
            String territoryName= territorylist[it];
            Territory territory = new Territory(it,0, territoryName);
            territoryArrayList.add(territory);
        }
        return territoryArrayList;

    }

    public void initiateRegions(ArrayList<Region> regionArrayList, ArrayList<Territory> territoryArrayList){
        ArrayList<Territory> territoryArrayListNorthamerica = new ArrayList<Territory>();
        for (int it=0; it<9; it++){
            territoryArrayListNorthamerica.add(territoryArrayList.get(it)); // on ajoute à la liste des territoires americains les 9 premiers elements
        }
        Region northAmerica = new Region(1, "North America", 0, territoryArrayListNorthamerica);

        ArrayList<Territory> territoryArrayListSouthamerica = new ArrayList<Territory>();
        for (int it=9; it<13; it++){
            territoryArrayListSouthamerica.add(territoryArrayList.get(it)); // on ajoute à la liste des territoires sud americains les 4 elements suivants
        }
        Region southAmerica = new Region(1, "South America", 0, territoryArrayListSouthamerica);

        ArrayList<Territory> territoryArrayListAfrica = new ArrayList<Territory>();
        for (int it=14; it<19; it++){
            territoryArrayListAfrica.add(territoryArrayList.get(it)); // on ajoute à la liste des territoires africains les 6 elements suivants
        }
        Region africa = new Region(1, "Africa", 0, territoryArrayListAfrica);

        ArrayList<Territory> territoryArrayListEurope = new ArrayList<Territory>();
        for (int it=19; it<26; it++){
            territoryArrayListEurope.add(territoryArrayList.get(it)); // on ajoute à la liste des territoires europées les 7 elements suivants
        }
        Region europe = new Region(1, "Europe", 0, territoryArrayListNorthamerica);

        ArrayList<Territory> territoryArrayListAsia = new ArrayList<Territory>();
        for (int it=26; it<37; it++){
            territoryArrayListNorthamerica.add(territoryArrayList.get(it)); // on ajoute à la liste des territoires americains les 11 elements suivants
        }
        Region NortAmerica = new Region(1, "Asia", 0, territoryArrayListNorthamerica);

        ArrayList<Territory> territoryArrayListOceania = new ArrayList<Territory>();
        for (int it=37; it<41; it++){
            territoryArrayListNorthamerica.add(territoryArrayList.get(it)); // on ajoute à la liste des territoires americains les 4 elements suivants
        }
        Region oceania = new Region(1, "Oceania", 0, territoryArrayListNorthamerica);

    }

    public void territoryInitialisation(Player player, int numberOfPlayers, ArrayList<Territory> territories){
        Random random = new Random();
        int numberTerritories = territories.size();
        switch(numberOfPlayers){
            case 2:
                player.setReinforcement(40);
                for (int twoPlayers = 0; twoPlayers < numberTerritories/2; twoPlayers++){
                    int num2 = random.nextInt(numberTerritories);
                    addTerritory(player, num2,territories);
                }
                break;
            case 3:
                player.setReinforcement(35);
                for (int threePlayers = 0; threePlayers < numberTerritories/3; threePlayers++){
                    int num3 = random.nextInt(numberTerritories);
                    addTerritory(player, num3,territories);
                }
                break;
            case 4:
                player.setReinforcement(30);
                for (int fourPlayers = 0; fourPlayers < numberTerritories/4; fourPlayers++){ // il restera deux territoires à distribuer
                    int num4 = random.nextInt(numberTerritories);
                    addTerritory(player, num4,territories);
                }
                break;
            case 5:
                player.setReinforcement(25);
                for (int fivePlayers = 0; fivePlayers < numberTerritories/5; fivePlayers++){ // il restera deux territoires à distribuer
                    int num5 = random.nextInt(numberTerritories);
                    addTerritory(player, num5,territories);
                }
                break;
            case 6:
                player.setReinforcement(20);
                for (int sixPlayers = 0; sixPlayers < numberTerritories/6; sixPlayers++){
                    int num6 = random.nextInt(numberTerritories);
                    addTerritory(player, num6,territories);
                }
                break;
            default:
                player.setReinforcement(0);
        }
    }

    public void addTerritory(Player player, int id, ArrayList<Territory> territoryArrayList){
        Random random = new Random();
        Territory territory = territoryArrayList.get(id);// trouver le territoire de l'id cherché
        if (territory.getProprietary()==0){
            player.getArraylistTerritories().add(territory);
        }
        else {
            int toAdd= random.nextInt(42)+1;
            while (toAdd==id){
                toAdd= random.nextInt(42)+1;
            }
            player.getArraylistTerritories().add(territory);
            //addTerritory(player, toAdd, territoryArrayList);
        }
    }
}
