package it.unimi.di.prog2.e18;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

public class AstronomicalSystem implements Iterable<CelestialBody> {

    List<CelestialBody> celestialBodies;


    public AstronomicalSystem(List<CelestialBody> celestialBodies) {
        this.celestialBodies = new ArrayList<>(celestialBodies);
    }

    public Iterator<CelestialBody> iterator() {
        return celestialBodies.iterator();
    }
    
    /**
     * Simulates one step of the simulation.
     */
    public void simulateStep() {
        for (int i = 0; i < celestialBodies.size(); i++) {
            // 
            if (celestialBodies.get(i) instanceof Planet) {
                for (int j = 0; j < celestialBodies.size(); j++) {
                    if (i != j) {
                        celestialBodies.get(i).updateVelocity(celestialBodies.get(j));
                    }
                } 
            }
           
        }

        for (int i = 0; i < celestialBodies.size(); i++) {
            if (celestialBodies.get(i) instanceof Planet) {
                celestialBodies.get(i).updatePosition();
            }
        }
    }

    public int  totalEnergy() {
        int totalEnergy = 0;
        for(CelestialBody c : celestialBodies) {
            totalEnergy += c.energy();
        }
        return totalEnergy;
    }
   /**
     * Ordina la lista di corpi celesti in base al tipo e poi al nome.
     *
     * REQUIRES: nessuno
     * MODIFIES: this
     * EFFECTS: ordina la lista di corpi celesti in base al tipo e poi al nome
     */
    public void sortByName() {
        celestialBodies.sort(Comparator.comparing(CelestialBody::name));
    }

    //
    //
    @Override
    public String toString(){
        StringJoiner sj = new StringJoiner("\n");
        sortByName();
        for (CelestialBody c : celestialBodies) sj.add(c.toString());
        sj.add("Total Energy: " + totalEnergy());
        return sj.toString();
    }
    
}
