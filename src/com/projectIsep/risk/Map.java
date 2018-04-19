package com.projectIsep.risk;

import edu.princeton.cs.introcs.StdDraw;

public class Map {

    public void worldMap(){
        // map of the world
        StdDraw.setCanvasSize(1210,730);
        StdDraw.setXscale(0,100);
        StdDraw.setYscale(0,100);
        StdDraw.clear();
        StdDraw.picture(50,50, "img/riskmap.jpg");
        // display and pause for 20 ms
        StdDraw.show();
    }
}
