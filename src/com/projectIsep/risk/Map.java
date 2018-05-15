package com.projectIsep.risk;

import edu.princeton.cs.introcs.StdDraw;

import java.util.Random;

public class Map {

    public void worldMap(String map, int numberOfPlayers){
        System.out.println(map);
        System.out.println(numberOfPlayers);
        // map of the world
        StdDraw.setCanvasSize(1050,737);
        StdDraw.setXscale(0,100);
        StdDraw.setYscale(0,100);
        StdDraw.clear();
        StdDraw.picture(50,50, "img/risk-prof.png");
        // display and pause for 20 ms
        StdDraw.show();

    }

    //nbre territoires : 42

    public void gameInitialisation(int numberOfPlayers){
        Random rand = new Random();
        //list of all territories. There are a total of 42
        int [] territories = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,36,36,37,38,39,40,41,42};

        for(int it=0; it<numberOfPlayers; it++){

            //creating a new player
            Player player = new Player();

            // defining the number of armies for each player
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
}
