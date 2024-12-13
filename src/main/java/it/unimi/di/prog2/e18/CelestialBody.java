package it.unimi.di.prog2.e18;

import java.util.Objects;

/**
 * La classe CelestialBody rappresenta un corpo celeste nello spazio.
 *
 * AF(c) = un corpo celeste di tipo c.type con nome c.name e posizione c.position.
 *
 * RI(c) = c.type, c.name non sono nulli e non sono stringhe vuote.
 *         c.position non è nulla.
 */
public abstract class CelestialBody {
    protected final String name;
    protected Point position;



    /**
     * Costruttore che inizializza un corpo celeste con il tipo, il nome e la posizione specificati.
     *
     * REQUIRES: type != null && !type.isEmpty(), name != null && !name.isEmpty(), position != null
     * MODIFIES: this
     * EFFECTS: inizializza un corpo celeste con il tipo, il nome e la posizione specificati
     *
     * @param name il nome del corpo celeste
     * @param position la posizione del corpo celeste
     */
    public CelestialBody(String name, Point position) {
        if (name == null || name.isEmpty() || position == null) {
            throw new IllegalArgumentException("Type, name, and position must not be null or empty");
        }
        this.name = name;
        this.position = position;
    }


    /**
     * Restituisce il nome del corpo celeste.
     *
     * REQUIRES: nessuno
     * MODIFIES: nessuno
     * EFFECTS: restituisce il nome del corpo celeste
     *
     * @return il nome del corpo celeste
     */
    public String name() {
        return name;
    }

    /**
     * Restituisce la posizione del corpo celeste.
     *
     * REQUIRES: nessuno
     * MODIFIES: nessuno
     * EFFECTS: restituisce la posizione del corpo celeste
     *
     * @return la posizione del corpo celeste
     */
    public Point position() {
        return position;
    }

    /**
     * Imposta la posizione del corpo celeste.
     *
     * REQUIRES: position != null
     * MODIFIES: this
     * EFFECTS: imposta la posizione del corpo celeste
     *
     * @param position la nuova posizione del corpo celeste
     */
    public void position(Point position) {
        if (position == null) {
            throw new IllegalArgumentException("Position must not be null");
        }
        this.position = position;
    }

    /**
     * Imposta la velocità del corpo celeste.
     *
     * REQUIRES: velocity != null
     * MODIFIES: this
     * EFFECTS: imposta la velocità del corpo celeste
     *
     * @param velocity la nuova velocità del corpo celeste
     */
    //public abstract void velocity(Point velocity) throws IllegalArgumentException;

    public abstract void updateVelocity(final CelestialBody c) throws IllegalArgumentException ;
    public abstract void updatePosition();
    public abstract int energy();


    /**
     * Verifica se questo corpo celeste è uguale a un altro oggetto.
     *
     * REQUIRES: nessuno
     * MODIFIES: nessuno
     * EFFECTS: restituisce true se questo corpo celeste è uguale a o, false altrimenti
     *
     * @param o l'oggetto da confrontare
     * @return true se questo corpo celeste è uguale a o, false altrimenti
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CelestialBody that = (CelestialBody) o;
        return name.equals(that.name) && position.equals(that.position);
    }

    /**
     * Calcola l'hash code del corpo celeste.
     *
     * REQUIRES: nessuno
     * MODIFIES: nessuno
     * EFFECTS: restituisce l'hash code del corpo celeste
     *
     * @return l'hash code del corpo celeste
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }
}
