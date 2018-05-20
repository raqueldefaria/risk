package com.projectIsep.risk;

import edu.princeton.cs.introcs.StdDraw;

public class Menu  {
    private int numberOfplayers;


    //------------------Constructor------------------//
    public Menu() {

    }

    //------------------Getters------------------//

    public int getNumberOfplayers() {
        return numberOfplayers;
    }


    //------------------Methods------------------//

    public void startGame(){
        // map of the world
        StdDraw.setCanvasSize(850,350);
        StdDraw.setXscale(0,10);
        StdDraw.setYscale(0,10);
        StdDraw.clear();
        StdDraw.picture(5.1,7, "img/risk_logo.png");
        // Number of players
        StdDraw.text(1.5,3,"Nombre de joueurs : ");
        StdDraw.text(3,3,"2");
        StdDraw.text(4,3,"3");
        StdDraw.text(5,3,"4");
        StdDraw.text(6,3,"5");
        StdDraw.text(7,3,"6");
        StdDraw.text(8,3,"7");
        StdDraw.text(9,3,"8");
        // display and pause for 20 ms
        StdDraw.show();
        //StdDraw.pause(20);

        boolean numberOfplayersChosen = false;
        while (!numberOfplayersChosen){
            // Choosing the number of players
            if(StdDraw.isMousePressed()) {
                //coordinates of the click
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                this.numberOfplayers = numberPlayersChosen(x,y);
                if(this.numberOfplayers!=0){
                    numberOfplayersChosen = true;
                }
                StdDraw.pause(90);

                //y 2.7, 3.4
                //x 2.9, 3.1
            }
        }
        StdDraw.clear();
        StdDraw.text(5,5,"You chose "+this.numberOfplayers+" players");
        StdDraw.pause(2000);
        StdDraw.clear();


    }

    public int numberPlayersChosen(double x, double y){
        if(y>=2.7 && y<=3.4){
            if(x>=2.9 && x<=3.1){
                return 2;
            }
            else if(x>=3.9 && x<=4.1){
                return 3;
            }
            else if(x>=4.9 && x<=5.1){
                return 4;
            }
            else if(x>=5.9 && x<=6.1){
                return 5;
            }
            else if(x>=6.9 && x<=7.1){
                return 6;
            }
            else if(x>=7.9 && x<=8.1){
                return 7;
            }
        }
        return 0;

    }


}
