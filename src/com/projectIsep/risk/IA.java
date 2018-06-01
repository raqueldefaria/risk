package com.projectIsep.risk;

import java.util.ArrayList;

public class IA extends Player {

    public IA() {
    }

    public void IAReinforcement(int reinforcement, ArrayList<Territory> territoryOfIa, ArrayList<Territory> territoryTotal) {

        int currentReinforcement = reinforcement;
        for(int i =0; i<territoryOfIa.size(); i++){ // pour chaque territoire du joueur

            for(int e=0; e<territoryOfIa.get(i).getFrontier().length; e++){
                boolean ennemy=false;
                Territory frontierTerritory = territoryTotal.get(territoryOfIa.get(i).getFrontier()[e]);
                if(frontierTerritory.getProprietary().getID()!=this.getID()){
                    ennemy = true;
                }
                if (ennemy){ // si il y a un territoire ennemi
                    if(territoryOfIa.get(i).getNbSoldier()< frontierTerritory.getNbSoldier() + 3*frontierTerritory.getNbCavalery() + 7*frontierTerritory.getNbCanon() ){ // si le territoire ennemi a plus de troupes
                        territoryOfIa.get(i).setNbSoldier(territoryOfIa.get(i).getNbSoldier()+1);
                        currentReinforcement=currentReinforcement-1;
                    }
                }
            }

        }

    }


}
