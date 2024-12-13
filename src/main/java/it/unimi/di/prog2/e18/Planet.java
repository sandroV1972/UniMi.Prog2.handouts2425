package it.unimi.di.prog2.e18;

import java.util.Objects;

public class Planet extends CelestialBody {
    private Point velocity = Point.ZERO;

    public Planet(String name, Point position) {
        super(name, position);
    }
    
    public void velocity(Point velocity) {
        this.velocity = velocity;
    }

    public Point velocity() {
        return velocity;
    }

    public void updateVelocity(final CelestialBody c) throws IllegalArgumentException {
        if (c == null) {
            throw new IllegalArgumentException("c must not be null");
        }
        Objects.requireNonNull(c);
        this.velocity(this.velocity.sum(Point.signum(c.position().sub(this.position()))));

    }

    @Override
    public void updatePosition() {
        this.position(this.position.sum(this.velocity()));
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
        return position.norm() * velocity.norm();
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
         sb.append("Planet");
         sb.append(", name: " + name); 
         sb.append(", pos: " + position);
         sb.append(", vel: " + velocity);
         return sb.toString();
     }
}
