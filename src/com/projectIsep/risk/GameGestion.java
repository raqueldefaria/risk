package com.projectIsep.risk;

import edu.princeton.cs.introcs.StdDraw;

import java.util.ArrayList;
import java.util.Random;

public class GameGestion {

    public void worldMap(String map, int numberOfPlayers){
        showMap();
        boolean gameOver= false;
        int it =1;
        ArrayList<Player> playerArrayList = new ArrayList<>();
        for (int i=0; i<numberOfPlayers;i++){ // initialisation des joueurs
            int a = (int) Math.floor(Math.random() * 100) + 1; // on tire l'id de mission, pour l'instant de 1 à 100
            Player player = new Player();
            //player.setMission(); //on set la mission d'ID a;
            playerArrayList.add(player);
            territoryInitialisation(player, numberOfPlayers); // on affecte ses territoires au joueur
        }

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
        StdDraw.picture(50,50, "img/riskmap");
        // display and pause for 20 ms
        StdDraw.show();
    }
    public void initiateTerritories(){
        Territory alaska = new Territory(1, 0, "Alaska");
        Territory northWestTerritory = new Territory(2, 0, "North West Territory");
        Territory alberta = new Territory(3, 0, "Alberta");
        Territory ontario = new Territory(4, 0, "Ontario");
        Territory quebec = new Territory(5, 0, "Quebec");
        Territory groenland = new Territory(6, 0, "Groenland");
        Territory westernUnatedStates = new Territory(7, 0, "Western Unated States");
        Territory easternUnatedStates = new Territory(8, 0, "Eastern Unated States");
        Territory centralAmerica = new Territory(9, 0, "Central America");
        Territory venezuela = new Territory(10, 0, "Venezuela");
        Territory brazil = new Territory(11, 0, "Brazil");
        Territory peru = new Territory(12, 0, "Peru");
        Territory argentina = new Territory(13, 0, "Argentina");
        Territory northAfrica = new Territory(14, 0, "Congo");
        Territory congo = new Territory(15, 0, "Congo");
        Territory southAfrica = new Territory(16, 0, "South Africa");
        Territory madagascar = new Territory(14, 0, "Madagascar");
        Territory eastAfrica = new Territory(18, 0, "East Africa");
        Territory egypt = new Territory(19, 0, "Egypt");
        Territory iceland = new Territory(20, 0, "Iceland");
        Territory westernEurope = new Territory(21, 0, "Western Europe");
        Territory nothernEurope = new Territory(22, 0, "Nothern Europe");
        Territory greatBritain = new Territory(23, 0, "Great Britain");
        Territory southernEurope = new Territory(24, 0, "Southern Europe");
        Territory scandinava = new Territory(25, 0, "Scandinavia");
        Territory ukraine = new Territory(26, 0, "Ukraine");
        Territory middleEast = new Territory(27, 0, "Middle East");
        Territory afghanistan = new Territory(28, 0, "Afghanistan");
        Territory ural = new Territory(29, 0, "Ural");
        Territory siberia = new Territory(30, 0, "Siberia");
        Territory yakutsk = new Territory(31, 0, "Yakutsk");
        Territory kamchatka = new Territory(32, 0, "Kamchatka");
        Territory irkustsk = new Territory(33, 0, "Irkustsk");
        Territory mongolia = new Territory(34, 0, "Mongolia");
        Territory china = new Territory(35, 0, "China");
        Territory india = new Territory(36, 0, "India");
        Territory siam = new Territory(37, 0, "Siam");
        Territory japan = new Territory(38, 0, "Japan");
        Territory indonesia = new Territory(39, 0, "Indonesia");
        Territory newGuinea = new Territory(40, 0, "New Guinea");
        Territory westernAutralia = new Territory(41, 0, "Western Australia");
        Territory easternAustralia = new Territory(42, 0, "Estern AUstralia");


    }

    public void initiateRegions(){

    }

    //nbre territoires : 42

    public void gameInitialisation(int numberOfPlayers){
        Random rand = new Random();
        //list of all territories. There are a total of 42
        int [] territories = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,36,36,37,38,39,40,41,42};

        for(int it=0; it<numberOfPlayers; it++){

            //generer un autre joueur
            Player player = new Player();

            // definition des renforts en fonction du nombre de joueurs
            switch(numberOfPlayers){
                case 2:
                    player.setReinforcement(40);
                    break;
                case 3:
                    player.setReinforcement(35);
                    break;
                case 4:
                    player.setReinforcement(30);
                    break;
                case 5:
                    player.setReinforcement(25);
                    break;
                case 6:
                    player.setReinforcement(20);
                    break;
                default:
                    player.setReinforcement(0);
            }

            int pickedTerritory = rand.nextInt(42);

        }
    }

    public void territoryInitialisation(Player player, int numberOfPlayers){
        Random random = new Random();
        int [] territories = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,36,36,37,38,39,40,41,42};
        switch(numberOfPlayers){
            case 2:
                player.setReinforcement(40);
                for (int twoPlayers = 0; twoPlayers < 21; twoPlayers++){
                    int num2 = random.nextInt(42);
                    addTerritory(player, num2);
                }
                break;
            case 3:
                player.setReinforcement(35);
                for (int threePlayers = 0; threePlayers < 14; threePlayers++){
                    int num3 = random.nextInt(42);
                    addTerritory(player, num3);
                }
                break;
            case 4:
                player.setReinforcement(30);
                for (int fourPlayers = 0; fourPlayers < 10; fourPlayers++){ // il restera deux territoires à distribuer
                    int num4 = random.nextInt(42);
                    addTerritory(player, num4);
                }
                break;
            case 5:
                player.setReinforcement(25);
                for (int fivePlayers = 0; fivePlayers < 8; fivePlayers++){ // il restera deux territoires à distribuer
                    int num5 = random.nextInt(42);
                    addTerritory(player, num5);
                }
                break;
            case 6:
                player.setReinforcement(20);
                for (int sixPlayers = 0; sixPlayers < 7; sixPlayers++){
                    int num6 = random.nextInt(42);
                    addTerritory(player, num6);
                }
                break;
            default:
                player.setReinforcement(0);
        }
    }

    public void addTerritory(Player player, int id){
       /* int [] territories = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,36,36,37,38,39,40,41,42};
        Random random = new Random();
        Territory territory =// trouver le territoire de l'id cherché
        if (territory.getProprietary()==0){
            player.setArraylistTerritories(player.getArraylistTerritories().add(territory));
        }
        else {

            int toAdd= random.nextInt(42);
            addTerritory(player, toAdd);
        }*/
    }
}
