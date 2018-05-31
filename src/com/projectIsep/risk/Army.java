package com.projectIsep.risk;

import edu.princeton.cs.introcs.StdDraw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Army {
    public int nbSoldier;
    public int nbCavalery;
    public int nbCanon;
    public boolean attacker;
    public Territory territory;

    public Army() {
    }

    public Army(int nbSoldier, int nbCavalery, int nbCanon, boolean attacker,Territory territory) {
        this.nbSoldier = nbSoldier;
        this.nbCavalery = nbCavalery;
        this.nbCanon = nbCanon;
        this.attacker = attacker;
        this.territory = territory;
    }

    public int getNbSoldier() {
        return nbSoldier;
    }

    public void setNbSoldier(int nbSoldier) {
        this.nbSoldier = nbSoldier;
    }

    public int getNbCavalery() {
        return nbCavalery;
    }

    public void setNbCavalery(int nbCavalery) {
        this.nbCavalery = nbCavalery;
    }

    public int getNbCanon() {
        return nbCanon;
    }

    public void setNbCanon(int nbCanon) {
        this.nbCanon = nbCanon;
    }

    public boolean Attacker() {
        return attacker;
    }

    public void setAttacker(boolean attacker) {
        this.attacker = attacker;
    }

    public Territory getTerritory() {
        return territory;
    }

    public void setTerritory(Territory territory) {
        this.territory = territory;
    }

    public boolean generateAttacker(){
        boolean unitsChosen = false;
        while (!unitsChosen){
            int [] xTextSoldier = new int[this.getTerritory().getNbSoldier()+1];
            StdDraw.clear();
            StdDraw.text(50,60,"How many soldiers do you want to attack ?");
            StdDraw.text(40,55,"Number of soldiers : ");
            for(int it=0;it<=this.getTerritory().getNbSoldier();it++){
                StdDraw.text((45+it*2)+2,55, String.valueOf(it));
                xTextSoldier[it] = (45+it*2)+2;
            }
            StdDraw.pause(5000);
            boolean numberOfSoldiersChosen = false;
            int nbSoldierAttacker = 0;
            while (!numberOfSoldiersChosen){
                if (StdDraw.isMousePressed()){
                    double xSoldier = StdDraw.mouseX();
                    double ySoldier = StdDraw.mouseY();
                    StdDraw.pause(200);
                    if(ySoldier>=53.7 && ySoldier<=56.3){
                        for (int k=0; k<=this.getTerritory().getNbSoldier();k++){
                            System.out.println("nombre : "+xTextSoldier[k]);
                            if(xSoldier>=xTextSoldier[k]-1.1 && xSoldier<=xTextSoldier[k]+1.1){
                                nbSoldierAttacker = k;
                                numberOfSoldiersChosen = true;
                            }
                        }
                    }
                }
            }

            if (this.getTerritory().getNbSoldier()<nbSoldierAttacker){
                StdDraw.clear();
                StdDraw.text(50,50,"Not enough soldiers");
                StdDraw.pause(1500);
            }
            this.setNbSoldier(nbSoldierAttacker);



            int [] xTextCavalier = new int[this.getTerritory().getNbCavalery()+1];
            StdDraw.clear();
            StdDraw.text(50,60,"How many cavaliers do you want to attack ?");
            StdDraw.text(40,55,"Number of cavaliers : ");
            for(int it=0;it<=this.getTerritory().getNbCavalery();it++){
                StdDraw.text((45+it*2)+2,55, String.valueOf(it));
                xTextCavalier[it] = (45+it*2)+2;
            }
            StdDraw.pause(5000);
            boolean numberOfCavaliersChosen = false;
            int nbCavaleryAttacker = 0;
            while (!numberOfCavaliersChosen){
                if (StdDraw.isMousePressed()){
                    double xCavalery = StdDraw.mouseX();
                    double yCavalery = StdDraw.mouseY();
                    StdDraw.pause(200);
                    if(yCavalery>=53.7 && yCavalery<=56.3){
                        for (int k=0; k<=this.getTerritory().getNbCavalery();k++){
                            if(xCavalery>=xTextCavalier[k]-1.1 && xCavalery<=xTextCavalier[k]+1.1){
                                nbCavaleryAttacker = k;
                                numberOfCavaliersChosen = true;
                            }
                        }
                    }
                }
            }

            if (this.getTerritory().getNbCavalery()<nbCavaleryAttacker){
                StdDraw.clear();
                StdDraw.text(50,50,"Not enough cavaliers");
                StdDraw.pause(1500);
            }
            this.setNbCavalery(nbCavaleryAttacker);



            int [] xTextCanon = new int[this.getTerritory().getNbCanon()+1];
            StdDraw.clear();
            StdDraw.text(50,60,"How many canons do you want to attack ?");
            StdDraw.text(40,55,"Number of canons : ");
            for(int it=0;it<=this.getTerritory().getNbCanon();it++){
                StdDraw.text((45+it*2)+2,55, String.valueOf(it));
                xTextCanon[it] = (45+it*2)+2;
            }
            StdDraw.pause(5000);
            boolean numberOfCanonChosen = false;
            int nbCanonAttacker = 0;
            while (!numberOfCanonChosen){
                if (StdDraw.isMousePressed()){
                    double xCanon = StdDraw.mouseX();
                    double yCanon = StdDraw.mouseY();
                    StdDraw.pause(200);
                    if(yCanon>=53.7 && yCanon<=56.3){
                        for (int k=0; k<=this.getTerritory().getNbCanon();k++){
                            if(xCanon>=xTextCanon[k]-1.1 && xCanon<=xTextCanon[k]+1.1){
                                nbCanonAttacker = k;
                                numberOfCanonChosen = true;
                            }
                        }
                    }
                }
            }

            if (this.getTerritory().getNbCanon()<nbCanonAttacker){
                StdDraw.clear();
                StdDraw.text(50,50,"Not enough canons");
                StdDraw.pause(1500);
            }
            this.setNbCanon(nbCanonAttacker);


            if ((this.getNbSoldier() + this.getNbCavalery() + this.getNbCanon()) ==0){
                StdDraw.clear();
                StdDraw.text(50,50,"You need to at least chose one unit ");
                StdDraw.pause(1500);
            }

            else if ((this.getNbSoldier() + this.getNbCavalery() + this.getNbCanon()) > 3){
                StdDraw.clear();
                StdDraw.text(50,50,"You cannot choose more than three units ");
                StdDraw.pause(1500);
            }

            else if ((this.getNbSoldier() + this.getNbCavalery() + this.getNbCanon()) >= (this.getTerritory().getNbCanon()+this.getTerritory().getNbSoldier()+this.getTerritory().getNbCavalery() )){
                StdDraw.clear();
                StdDraw.text(50,50,"At least one unit needs to stay in the territory ");
                StdDraw.pause(1500);
            }
            else{
                unitsChosen = true;
                this.territory.setNbSoldier(this.territory.getNbSoldier()-nbSoldierAttacker); // on retire les troupes qui partent au combat de leur territoire de base
                this.territory.setNbCavalery(this.territory.getNbCavalery()-nbCavaleryAttacker);
                this.territory.setNbCanon(this.territory.getNbCanon()-nbCanonAttacker);
            }

        }

        return unitsChosen;

    }

    public boolean generateDefender(){

        // ---------------------- Generating army -----------------//
        Scanner scannerdef = new Scanner(System.in);
        System.out.println("How many soliers to defend?");
        int nbSoldierDefender = scannerdef.nextInt();
        if (this.getTerritory().getNbSoldier()<nbSoldierDefender){
            System.out.println("Not enough soldiers");
            return false;
        }
        this.setNbSoldier(nbSoldierDefender);


        System.out.println("How many canons to defend?");
        int nbCanonDefender = scannerdef.nextInt();
        if (this.getNbCanon()<nbCanonDefender){
            System.out.println("Not enough canons");
            return false;
        }
        this.setNbCanon(nbCanonDefender);
        System.out.println("How many cavalieries to defend?");
        int nbCavaleryDefender = scannerdef.nextInt();
        if (this.getNbCavalery()<nbCavaleryDefender){
            System.out.println("Not enough cavaleries");
            return false;
        }
        this.setNbCavalery(nbCavaleryDefender);

        // ---------------------- Army checking -----------------//

        if ((this.getNbSoldier() + this.getNbCavalery() + this.getNbCanon()) ==0){
            System.out.println("il faut au moins un défenseur");
            return false;
        }

        if ((this.getNbSoldier() + this.getNbCavalery() + this.getNbCanon()) > 2){
            System.out.println("On ne peut pas défendre avec plus de 2 unités");
            return false;
        }

        this.territory.setNbSoldier(this.territory.getNbSoldier()-nbSoldierDefender); // on retire les troupes qui partent au combat de leur territoire de base
        this.territory.setNbCavalery(this.territory.getNbCavalery()-nbCavaleryDefender);
        this.territory.setNbCanon(this.territory.getNbCanon()-nbCanonDefender);
        return true;
    }

    public void battle(Army defender){

        ArrayList <Unit>  attackerList = this.generateAttackerList(); //On prépare l'ordre des troupes attaquantes

        ArrayList <Unit> defenderList = defender.generateDefenderList();//  On prépare l'ordre des troupes défenseuses

        ArrayList<Integer> attackerResults = generateDice(attackerList);// On lance les dés pour l'attaquant

        ArrayList<Integer> defenderResults = generateDice(defenderList);// On lance les dés pour le défenseur

        ArrayList<Boolean> results = compareResult(attackerResults, defenderResults); // On compare les résultats

        supressLosses(results, attackerList, this, defenderList, defender); // on supprime les pertes

        battleConclusion(this,defender); // et on conclut le combat

    }

    public ArrayList<Unit> generateAttackerList(){ // l'ordre d'ajout des troupes à la liste permet de gérer les priorités de combats (les premiers meurent en premier, "et les premiers seront les derniers" ne s'applique pas à un tir de canon
        ArrayList<Unit> unitList= new ArrayList<>();
        int cavaleryAttacker = this.getNbCavalery();
        while (cavaleryAttacker!=0){
            Cavalery cavalery = new Cavalery();
            unitList.add(cavalery);
            cavaleryAttacker --;
        }
        int soldierAttacker = this.getNbSoldier();
        while (soldierAttacker!=0){
            Soldier soldier = new Soldier();
            unitList.add(soldier);
            soldierAttacker --;
        }
        int canonAttacker =this.getNbCanon();
        while (canonAttacker!=0){
            Canon canon = new Canon();
            unitList.add(canon);
            canonAttacker --;
        }
        return unitList;
    }

    public ArrayList<Unit> generateDefenderList(){
        ArrayList<Unit> unitList= new ArrayList<>();
        int soldierAttacker = this.getNbSoldier();
        while (soldierAttacker!=0){
            Soldier soldier = new Soldier();
            unitList.add(soldier);
            soldierAttacker --;
        }

        int canonAttacker =this.getNbCanon();
        while (canonAttacker!=0){
            Canon canon = new Canon();
            unitList.add(canon);
            canonAttacker --;
        }
        int cavaleryAttacker = this.getNbCavalery();
        while (cavaleryAttacker!=0){
            Cavalery cavalery = new Cavalery();
            unitList.add(cavalery);
            cavaleryAttacker --;
        }


        return unitList;
    }

    public ArrayList<Integer> generateDice (ArrayList<Unit> army){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (Unit anArmy : army) {
            Random random = new Random();
            int value = random.nextInt(anArmy.strengthMax) + anArmy.strengthMin;
            result.add(value);
        }
        return result;
    }

    public ArrayList<Boolean> compareResult(ArrayList<Integer> attackerResults, ArrayList<Integer> defenderResults){
        ArrayList<Boolean> results = new ArrayList<Boolean>();
        int numberOfRounds= Math.min(attackerResults.size(), defenderResults.size());
        int it = 0;
        while (it< numberOfRounds ){
           Integer attack = Collections.max(attackerResults); // on prend le plus grand résultat des attaquants
            attackerResults.remove(attack);
           Integer defense = Collections.max(defenderResults);// on prend le plus grand résultat des défenseurs
            attackerResults.remove(defense);

            if(attack <= defense){ // on si le défenseur gagne
                results.add(false); // l'attaquant perdra sa it-ième unité
            }
            else if(attack> defense){ //sinon le défenseur la perdra
                results.add(true);
            }
           it++;
        }
        return results;

    }

    public void supressLosses(ArrayList<Boolean> result, ArrayList <Unit>  attackerList, Army attacker,  ArrayList <Unit> defenderList, Army defender ){
        for (int it=0; it<result.size(); it++ ){ // pour chaque round de combats
            if (result.get(it)){ // si le it-ième terme de la liste est true, donc que l'attaquant a remporté le round
                deleteUnit(it, defenderList, defender);    //on supprime dans son armée l'unité qu'un faut

            }
            else if (!result.get(it)){ // si le it-ième terme de la liste est false, donc que l'attaquant a perdu le round
                deleteUnit(it, attackerList, attacker); //on supprime dans son armée l'unité qu'un faut
            }

        }
    }

    public void deleteUnit(int it, ArrayList <Unit>  armyList, Army army ){
        Unit casulatie = armyList.get(it); // on sélectionne le it-ième terme de la liste de troupes de l'armée
        int cost = casulatie.getCost(); // on regarde le cout de l'unité
        if(cost == 1){ // si le prix est 1 alors c'est un soldat
            army.setNbSoldier(army.getNbSoldier()-1); // on supprime un soldat dans l'armée
        }
        if(cost == 3){ // si le prix est 1 alors c'est un cavalier
            army.setNbCavalery(army.getNbCavalery()-1); // on supprime un cavalier dans l'armée
        }
        if(cost == 7){ // si le prix est 1 alors c'est un canon
            army.setNbCanon(army.getNbCanon()-1); // on supprime un canon dans l'armée
        }
    }

    public void battleConclusion(Army attacker, Army defender){
        if (((defender.getNbSoldier() + defender.getNbCavalery() + defender.getNbCanon()) ==0) && (defender.territory.getNbSoldier() + defender.territory.getNbCavalery() + defender.territory.getNbCanon()) ==0){ //si les troupes du défenseur sont toutes décimées
            defender.territory.setNbSoldier(attacker.getNbSoldier()); // l'armée du gagnant occupe le territoire
            defender.territory.setNbCavalery(attacker.getNbCavalery());
            defender.territory.setNbCanon(attacker.getNbCanon());
            defender.territory.setProprietary(attacker.territory.getProprietary()); // le vainqueur prend possession du territoire

        }
        else{ // sinon les survivants rentrent chez eux
            defender.territory.setNbSoldier(defender.territory.getNbSoldier() +defender.getNbSoldier()); // les troupes du défenseur retournent dans leur territoire
            defender.territory.setNbCavalery(defender.territory.getNbCavalery() +defender.getNbCavalery());
            defender.territory.setNbCanon(defender.territory.getNbCanon() +defender.getNbCanon());
            attacker.territory.setNbSoldier(attacker.territory.getNbSoldier() +attacker.getNbSoldier()); // idem pour les attaquants
            attacker.territory.setNbCavalery(attacker.territory.getNbCavalery() +attacker.getNbCavalery());
            attacker.territory.setNbCanon(attacker.territory.getNbCanon() +attacker.getNbCanon());
        }

    }


}
