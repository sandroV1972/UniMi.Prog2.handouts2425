package it.unimi.di.prog2.e18;

public class Star extends CelestialBody {    

    public Star(String name, Point position) {
        super(name, position);
    }


        /**
     * Calcola e restituisce l'energia del corpo celeste.
     *
     * REQUIRES: nessuno
     * MODIFIES: nessuno
     * EFFECTS: restituisce l'energia del corpo celeste, calcolata come il prodotto della norma della posizione e della norma della velocità
     *
     * @return l'energia del corpo celeste
     */
    public int energy() {
        return 0;
    }
        /**
     * Restituisce una rappresentazione stringa del corpo celeste.
     *
     * REQUIRES: nessuno
     * MODIFIES: nessuno
     * EFFECTS: restituisce una rappresentazione stringa del corpo celeste
     *
     * @return una rappresentazione stringa del corpo celeste
     */

     @Override
     public String toString() {
         StringBuilder sb = new StringBuilder();
         sb.append("Star");
         sb.append(", name: " + name); 
         sb.append(", pos: " + position);
         return sb.toString();
     }


        @Override
        public void updateVelocity(CelestialBody c) throws IllegalArgumentException {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'updateVelocity'");
        }

        @Override
        public void updatePosition() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'updatePosition'");
        }
    
}
