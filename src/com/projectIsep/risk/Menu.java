package com.projectIsep.risk;

import edu.princeton.cs.introcs.StdDraw;

public class Menu  {
    private int numberOfplayers;
    private int IA;


    //------------------Constructor------------------//
    public Menu() {

    }

    //------------------Getters------------------//

    public int getNumberOfplayers() {
        return numberOfplayers;
    }

    public int getIA() {
        return IA;
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
        StdDraw.setPenRadius(0.004);
        StdDraw.text(2.5,3,"Number of players : ");
        StdDraw.text(4,3,"2");
        StdDraw.ellipse(4,3.05,0.12,0.3);
        StdDraw.text(5,3,"3");
        StdDraw.ellipse(5,3.05,0.12,0.3);
        StdDraw.text(6,3,"4");
        StdDraw.ellipse(6,3.05,0.12,0.3);
        StdDraw.text(7,3,"5");
        StdDraw.ellipse(7,3.05,0.12,0.3);
        StdDraw.text(8,3,"6");
        StdDraw.ellipse(8,3.05,0.12,0.3);
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
        StdDraw.picture(5.1,7, "img/risk_logo.png");
        // Number of players
        StdDraw.text(1.5,3,"Number of IAs :");
        int [] numberIACoordinates = new int[7];
        for(int k=0; k<=this.numberOfplayers; k++){
            StdDraw.text(2+(k)+1,3, String.valueOf((k)));
            StdDraw.ellipse(2+(k)+1,3.05,0.12,0.3);
            numberIACoordinates[k] = 3+k;
        }

        // display and pause for 20 ms
        StdDraw.show();
        //StdDraw.pause(20);

        boolean numberOfIAChosen = false;
        while (!numberOfIAChosen){
            // Choosing the number of players
            if(StdDraw.isMousePressed()) {
                //coordinates of the click
                double xIA = StdDraw.mouseX();
                double yIA = StdDraw.mouseY();
                this.IA = numberOfIAChosen(xIA,yIA, numberIACoordinates);
                if(this.IA!=-1){
                    numberOfIAChosen = true;
                }
                StdDraw.pause(90);
            }
        }

        StdDraw.clear();
        StdDraw.text(5,5,"You chose "+this.numberOfplayers+" players with " + this.IA +" AI playing.");
        StdDraw.pause(2000);
        StdDraw.clear();


    }

    public int numberPlayersChosen(double x, double y){
        if(y>=2.7 && y<=3.4){
            if(x>=3.9 && x<=4.1){
                return 2;
            }
            else if(x>=4.9 && x<=5.1){
                return 3;
            }
            else if(x>=5.9 && x<=6.1){
                return 4;
            }
            else if(x>=6.9 && x<=7.1){
                return 5;
            }
            else if(x>=7.9 && x<=8.1){
                return 6;
            }

        }
        return 0;

    }

    public int numberOfIAChosen(double xIA, double yIA, int [] numberIACoordinates){
        int variable = -1;
        if((yIA>=2.7 && yIA<=3.4)){
            for (int k=0; k<=this.getNumberOfplayers();k++){
                if((xIA>=numberIACoordinates[k]-0.1) && (xIA<=numberIACoordinates[k]+0.1)){
                    variable = k;
                }
            }
        }
        return variable;
    }


}
