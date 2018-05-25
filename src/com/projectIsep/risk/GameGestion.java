package com.projectIsep.risk;

import edu.princeton.cs.introcs.StdDraw;

import java.util.ArrayList;
import java.util.Random;

public class GameGestion {

    public void worldMap(int numberOfPlayers) {
        initiateBackground();

        boolean gameOver = false;

        // arrayList containing all the regions
        ArrayList<Territory> territoryArrayList = initiateTerritories();

        int it = 0; // initialising the number of players
        ArrayList<Player> playerArrayList = new ArrayList<>();
        ;
        ArrayList<Region> regionArrayList = initiateRegions(territoryArrayList);
        for (int i = 0; i < numberOfPlayers; i++) { // initialisation des joueurs
            int a = (int) Math.floor(Math.random() * 100) + 1; // on tire l'id de mission, pour l'instant de 1 à 100
            Player player = new Player();
            //player.setMission(); //on set la mission d'ID a;

            playerArrayList.add(player); // adding a player to the list

            //giving the player his territories
            territoryInitialisation(player, numberOfPlayers, territoryArrayList); // on affecte ses territoires au joueur
        }
        while (!gameOver) {
            initiateBackground();
            StdDraw.pause(400);
            System.out.println("in game");
            int that = 1;
            while (that != numberOfPlayers) {
                System.out.println("in player");


                boolean pass = false;
                Player player = playerArrayList.get(that); // On sélectionne le joueurs
                that++; // on incrémente
                player.computerReinforcement(); //on calcule le nombre de renforts
                //on permet au joueur de placer ses renforts
                int reinforcement = player.getReinforcement();


                while (!pass) {
                    //une boucle désignation d'attaque/résolution d'attaque
                    // on check si le bouton pour mettre fin à la phase d'attaque est appuyé, puis on update pass
                    pass = true;


                }

                //mouvement

//                //on check si le joueur a gagné
//                if (player.getMission().missionAccomplished) {
//                    gameOver = true;
//                    // message de victoire
//                    // on ferme la partie
//                }

                //message de fin de tour
            }

        }
    }

    public void initiateBackground(){
        // map of the world
        StdDraw.setCanvasSize(1050,737);
        StdDraw.setXscale(0,100);
        StdDraw.setYscale(0,100);
        StdDraw.clear();
        StdDraw.picture(50,50, "img/riskmap.jpg");

       // display and pause for 20 ms
        StdDraw.show();
        //StdDraw.pause(20);
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

    public void territoryInitialisation(Player player, int numberOfPlayers, ArrayList<Territory> territories){
        Random random = new Random();
        int numberTerritories = territories.size();
        switch(numberOfPlayers){
            case 2:
                player.setReinforcement(40);
                for (int twoPlayers = 0; twoPlayers < numberTerritories/2; twoPlayers++){
                    int territoryId2 = random.nextInt(numberTerritories);
                    addTerritory(player, territoryId2,territories);
                }
                break;
            case 3:
                player.setReinforcement(35);
                for (int threePlayers = 0; threePlayers < numberTerritories/3; threePlayers++){
                    int territoryId3 = random.nextInt(numberTerritories);
                    addTerritory(player, territoryId3,territories);
                }
                break;
            case 4:
                player.setReinforcement(30);
                for (int fourPlayers = 0; fourPlayers < numberTerritories/4; fourPlayers++){ // il restera deux territoires à distribuer
                    int territoryId4 = random.nextInt(numberTerritories);
                    addTerritory(player, territoryId4,territories);
                }
                break;
            case 5:
                player.setReinforcement(25);
                for (int fivePlayers = 0; fivePlayers < numberTerritories/5; fivePlayers++){ // il restera deux territoires à distribuer
                    int territoryId5 = random.nextInt(numberTerritories);
                    addTerritory(player, territoryId5,territories);
                }
                break;
            case 6:
                player.setReinforcement(20);
                for (int sixPlayers = 0; sixPlayers < numberTerritories/6; sixPlayers++){
                    int territoryId6 = random.nextInt(numberTerritories);
                    addTerritory(player, territoryId6,territories);
                }
                break;
            default:
                player.setReinforcement(0);
        }
    }

    public void addTerritory(Player player, int id, ArrayList<Territory> territoryArrayList){
        Random random = new Random();
        Territory territory = territoryArrayList.get(id);
        if (territory.getProprietary()==0){
            ArrayList<Territory> currentArraylist= null;
            player.getArraylistTerritories().add(0, territory);
            player.setArraylistTerritories(player.getArraylistTerritories());

        }
        else {
            int toAdd= random.nextInt(41)+1;
            addTerritory(player, toAdd, territoryArrayList);

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
