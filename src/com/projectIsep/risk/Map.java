package com.projectIsep.risk;

import edu.princeton.cs.introcs.StdDraw;

public class Map {

    public void worldMap(String map, int numberOfPlayers){
        System.out.println(map);
        System.out.println(numberOfPlayers);
        // map of the world
        StdDraw.setCanvasSize(1050,737);
        StdDraw.setXscale(0,100);
        StdDraw.setYscale(0,100);
        StdDraw.clear();
        StdDraw.picture(50,50, "img/risk.png");
        // display and pause for 20 ms
        StdDraw.show();
    }

    //nbre territoires : 42
}
