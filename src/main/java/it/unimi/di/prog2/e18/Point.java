package it.unimi.di.prog2.e18;

import java.util.Objects;

/**
 * La classe Point rappresenta un punto in uno spazio tridimensionale.
 *
 * AF(c) = un punto con coordinate (c.x, c.y, c.z) nello spazio tridimensionale.
 *
 * RI(c) = c.x, c.y e c.z sono numeri interi validi.
 */
public class Point {
    private int x;
    private int y;
    private int z;

    public static final Point ZERO = new Point(0, 0, 0);

    /**
     * Costruttore che inizializza un punto con le coordinate specificate.
     *
     * @param x la coordinata x del punto
     * @param y la coordinata y del punto
     * @param z la coordinata z del punto
     */
    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        assert repOk();
    }

    /**
     * Restituisce la coordinata x del punto.
     *
     * @return la coordinata x del punto
     */
    public int x() {
        return x;
    }

    /**
     * Imposta la coordinata x del punto.
     *
     * @param x la nuova coordinata x del punto
     */
    public void x(int x) {
        this.x = x;
        assert repOk();
    }

    /**
     * Restituisce la coordinata y del punto.
     *
     * @return la coordinata y del punto
     */
    public int y() {
        return y;
    }

    /**
     * Imposta la coordinata y del punto.
     *
     * @param y la nuova coordinata y del punto
     */
    public void y(int y) {
        this.y = y;
        assert repOk();
    }

    /**
     * Restituisce la coordinata z del punto.
     *
     * @return la coordinata z del punto
     */
    public int z() {
        return z;
    }

    /**
     * Imposta la coordinata z del punto.
     *
     * @param z la nuova coordinata z del punto
     */
    public void z(int z) {
        this.z = z;
        assert repOk();
    }

    public void set(Point p) {
        x = p.x;
        y = p.y;
        z = p.z;    
    }
    public Point sum(Point p) {
        return new Point(x + p.x, y + p.y, z + p.z);
    }

    public Point sub(Point p) {
        return new Point(x - p.x, y - p.y, z - p.z);
    }

    public static Point signum(Point p) {
        return new Point((int) Math.signum(p.x), (int) Math.signum(p.y), (int) Math.signum(p.z));
    }

    /**
     * Restituisce la norma 1 del punto.
     *
     * @return la norma 1 del punto
     */
    public int norm() {
        return (x > 0 ? x : -x) + (y > 0 ? y : -y) + (z > 0 ? z : -z);
    }

    /**
     * Verifica che l'invariante di rappresentazione sia valido.
     *
     * @return true se l'invariante di rappresentazione è valido, false altrimenti
     */
    private boolean repOk() {
        // In questo caso, non ci sono condizioni specifiche da verificare oltre al fatto che x, y e z siano numeri interi validi.
        return true;
    }

    /**
     * Restituisce una rappresentazione stringa del punto.
     *
     * @return una rappresentazione stringa del punto
     */
    @Override
    public String toString() {
        return "(" + x +
                ", " + y +
                ", " + z +
                ')';
    }


    /**
     * Verifica se questo punto è uguale a un altro oggetto.
     *
     * @param o l'oggetto da confrontare
     * @return true se questo punto è uguale a o, false altrimenti
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y &&
                z == point.z;
    }

    /**
     * Calcola l'hash code del punto.
     *
     * @return l'hash code del punto
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}