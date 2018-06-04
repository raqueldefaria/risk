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

    public ArrayList<Territory> IAListOfPossibleConflicts( ArrayList<Territory> territoryOfIa, ArrayList<Territory> territoryTotal){
        ArrayList<Territory> conflictTerritories = new ArrayList<Territory>();
        for(int i =0; i<territoryOfIa.size(); i++){ // pour chaque territoire du joueur

            for(int e=0; e<territoryOfIa.get(i).getFrontier().length; e++){
                boolean ennemy=false;
                Territory frontierTerritory = territoryTotal.get(territoryOfIa.get(i).getFrontier()[e]);
                if(frontierTerritory.getProprietary().getID()!=this.getID()){
                    ennemy = true;
                }
                if (ennemy){ // si il y a un territoire ennemi
                    if(territoryOfIa.get(i).getNbSoldier()> frontierTerritory.getNbSoldier() + 3*frontierTerritory.getNbCavalery() + 7*frontierTerritory.getNbCanon() ){ // si le territoire de l'IA a plus de troupes
                        conflictTerritories.add(territoryOfIa.get(i));
                        conflictTerritories.add(frontierTerritory);

                    }
                }
            }

        }
        return conflictTerritories; // on dispose d'une arraylist contenant une alternance de territoires attaquants/attaqués

    }

    public void IAMovement(ArrayList<Territory> territoryOfIA, ArrayList<Territory> territoryTotal){
        for(int i =0; i<territoryOfIA.size(); i++){ // pour chaque territoire du joueur

            for(int e=0; e<territoryOfIA.get(i).getFrontier().length; e++){
                boolean ennemy=false;
                Territory frontierTerritory = territoryTotal.get(territoryOfIA.get(i).getFrontier()[e]);
                if(frontierTerritory.getProprietary().getID()!=this.getID()){
                    ennemy = true;
                }
                if (!ennemy){ // si il y a un territoire allié
                    while(territoryOfIA.get(i).getNbSoldier()< frontierTerritory.getNbSoldier()){ // si le territoire allié a plus de troupes, on équilibre
                        territoryOfIA.get(i).setNbSoldier(territoryOfIA.get(i).getNbSoldier()-1); // un soldat part du premier territoire
                        frontierTerritory.setNbSoldier(frontierTerritory.getNbSoldier()+1); // et va dans le second
                    }
                }
            }

        }
    }


}
