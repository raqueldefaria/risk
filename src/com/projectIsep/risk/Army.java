package com.projectIsep.risk;

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
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many soliers to atack?");
        int nbSoldierAttacker = scanner.nextInt();
        if (this.getTerritory().getNbSoldier()<nbSoldierAttacker){
            System.out.println("Not enough soldiers");
            return false;
        }
        this.setNbSoldier(nbSoldierAttacker);



        System.out.println("How many cavalieries to atack?");
        int nbCavaleryAttacker = scanner.nextInt();

        if (this.getTerritory().getNbCavalery()<nbCavaleryAttacker){
            System.out.println("Not enough cavaleries");
            return false;
        }
        this.setNbCavalery(nbCavaleryAttacker);



        System.out.println("How many canons to atack?");
        int nbCanonAttacker = scanner.nextInt();
        if (this.getTerritory().getNbCanon()<nbCanonAttacker){
            System.out.println("Not enough canons");
            return false;
        }
        this.setNbCanon(nbCanonAttacker);


        if ((this.getNbSoldier() + this.getNbCavalery() + this.getNbCanon()) ==0){
            System.out.println("il faut au moins un attaquant");
            return false;
        }

        if ((this.getNbSoldier() + this.getNbCavalery() + this.getNbCanon()) > 3){
            System.out.println("On ne peut pas attaquer avec plus de 3 unités");
            return false;
        }

        if ((this.getNbSoldier() + this.getNbCavalery() + this.getNbCanon()) >= (this.getTerritory().getNbCanon()+this.getTerritory().getNbSoldier()+this.getTerritory().getNbCavalery() )){
            System.out.println("Au moins un soldat doit rester défendre");
            return false;
        }
        this.territory.setNbSoldier(this.territory.getNbSoldier()-nbSoldierAttacker); // on retire les troupes qui partent au combat de leur territoire de base
        this.territory.setNbCavalery(this.territory.getNbCavalery()-nbCavaleryAttacker);
        this.territory.setNbCanon(this.territory.getNbCanon()-nbCanonAttacker);
        return true;
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



        System.out.println("How many cavalieries to defend?");
        int nbCavaleryDefender = scannerdef.nextInt();
        if (this.getNbCavalery()<nbCavaleryDefender){
            System.out.println("Not enough cavaleries");
            return false;
        }
        this.setNbCavalery(nbCavaleryDefender);

        System.out.println("How many canons to defend?");
        int nbCanonDefender = scannerdef.nextInt();
        if (this.getNbCanon()<nbCanonDefender){
            System.out.println("Not enough canons");
            return false;
        }
        this.setNbCanon(nbCanonDefender);

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

        ArrayList <Unit>  attackerList = this.generateList(); //On prépare l'ordre des troupes attaquantes

        ArrayList <Unit> defenderList = defender.generateList();//  On prépare l'ordre des troupes défenseuses

        ArrayList<Integer> attackerResults = generateDice(attackerList);// On lance les dés pour l'attaquant

        ArrayList<Integer> defenderResults = generateDice(defenderList);// On lance les dés pour le défenseur

        ArrayList<Boolean> results = compareResult(attackerResults, defenderResults); // On compare les résultats

        supressLosses(results, attackerList, this, defenderList, defender); // on supprime les pertes

        battleConclusion(this,defender); // et on conclut le combat

    }

    public ArrayList<Unit> generateList(){
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

    public ArrayList<Integer> generateDice (ArrayList<Unit> army){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int it=0; it< army.size(); it ++){
            Random random = new Random();
            Unit currentUnit = army.get(it);
            int value = random.nextInt(currentUnit.strengthMax)+currentUnit.strengthMin;
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
