package com.projectIsep.risk;

public class Main{

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.startGame();
        GameGestion gestion = new GameGestion();
        gestion.worldMap(menu.getNumberOfplayers(), menu.getIA());
    }

}
