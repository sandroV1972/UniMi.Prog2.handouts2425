/*

Copyright 2024 ALESSANDRO VALENTI

This file is part of "Programmazione 2 @ UniMI" teaching material.

This is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This material is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this file.  If not, see <https://www.gnu.org/licenses/>.

*/

package it.unimi.di.prog2.e15;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * La classe IntRange rappresenta un intervallo di numeri interi.
 * Permette di definire un intervallo con un valore iniziale (from), un valore finale (to) e un passo (step).
 *
 * AF(c) = un intervallo di numeri interi che inizia da c.from, termina a c.to e ha un passo di c.step.
 *         L'intervallo include tutti i numeri interi i tali che c.from <= i <= c.to e i è incrementato di c.step.
 *
 * RI(c) = se c.from <= c.to allora c.step >= 0
 *         se c.from >= c.to allora c.step <= 0
 *         c.from, c.to e c.step sono valori interi validi.
 */
public class IntRange implements Iterable<Integer> {
    private int from;
    private int to;
    private int step;

    /**
     * Costruttore di default che inizializza l'intervallo con i valori predefiniti:
     * from = Integer.MIN_VALUE, to = Integer.MAX_VALUE, step = 1.
     *
     * REQUIRES: nessuno
     * MODIFIES: this
     * EFFECTS: inizializza l'intervallo con i valori predefiniti
     */
    public IntRange() {
        this.from = Integer.MIN_VALUE;
        this.to = Integer.MAX_VALUE;
        this.step = 1;
        assert repOk();
    }

    /**
     * Costruttore che permette di specificare i valori iniziali, finali e il passo dell'intervallo.
     *
     * REQUIRES: from <= to, step > 0
     * MODIFIES: this
     * EFFECTS: inizializza l'intervallo con i valori specificati
     *
     * @param from il valore iniziale dell'intervallo
     * @param to il valore finale dell'intervallo
     * @param step il passo dell'intervallo
     */
    public IntRange(int from, int to, int step) {
        this.from = from;
        this.to = to;
        this.step = step;
        assert repOk();
    }

    /**
     * Restituisce il valore iniziale dell'intervallo.
     *
     * REQUIRES: nessuno
     * MODIFIES: nessuno
     * EFFECTS: restituisce il valore iniziale dell'intervallo
     *
     * @return il valore iniziale dell'intervallo
     */
    public int getGrom() {
        return from;
    }

    /**
     * Restituisce il valore finale dell'intervallo.
     *
     * REQUIRES: nessuno
     * MODIFIES: nessuno
     * EFFECTS: restituisce il valore finale dell'intervallo
     *
     * @return il valore finale dell'intervallo
     */
    public int getTo() {
        return to;
    }

    /**
     * Restituisce il passo dell'intervallo.
     *
     * REQUIRES: nessuno
     * MODIFIES: nessuno
     * EFFECTS: restituisce il passo dell'intervallo
     *
     * @return il passo dell'intervallo
     */
    public int getStep() {
        return step;
    }

    /**
     * Imposta il valore iniziale dell'intervallo.
     *
     * REQUIRES: nessuno
     * MODIFIES: this
     * EFFECTS: imposta il valore iniziale dell'intervallo
     *
     * @param from il nuovo valore iniziale dell'intervallo
     */
    public void from(int from) {
        this.from = from;
    }

    /**
     * Imposta il valore finale dell'intervallo.
     *
     * REQUIRES: nessuno
     * MODIFIES: this
     * EFFECTS: imposta il valore finale dell'intervallo
     *
     * @param to il nuovo valore finale dell'intervallo
     */
    public void to(int to) {
        this.to = to;
    }

    /**
     * Imposta il passo dell'intervallo.
     *
     * REQUIRES: step > 0
     * MODIFIES: this
     * EFFECTS: imposta il passo dell'intervallo
     *
     * @param step il nuovo passo dell'intervallo
     */
    public void step(int step) {
        this.step = step;
        assert repOk();
    }

    /**
     * Restituisce un iteratore per l'intervallo di numeri interi.
     *
     * REQUIRES: nessuno
     * MODIFIES: nessuno
     * EFFECTS: restituisce un iteratore per l'intervallo di numeri interi
     *
     * @return un iteratore per l'intervallo di numeri interi
     */
    @Override
    public Iterator<Integer> iterator() {
        return new IntRangeIterator();
    }

    private class IntRangeIterator implements Iterator<Integer> {
        private int current = from;

        /**
         * Verifica se ci sono altri elementi nell'intervallo.
         *
         * REQUIRES: nessuno
         * MODIFIES: nessuno
         * EFFECTS: restituisce true se ci sono altri elementi nell'intervallo, false altrimenti
         *
         * @return true se ci sono altri elementi nell'intervallo, false altrimenti
         */
        @Override
        public boolean hasNext() {

            if (step > 0) {
                return current < to;
            }   
            return current > to;
        }

        /**
         * Restituisce il prossimo elemento nell'intervallo.
         *
         * REQUIRES: hasNext() == true
         * MODIFIES: this
         * EFFECTS: restituisce il prossimo elemento nell'intervallo
         *
         * @return il prossimo elemento nell'intervallo
         * @throws NoSuchElementException se non ci sono altri elementi nell'intervallo
         */
        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            final int value = current;
            if (step > 0 && value >= Integer.MAX_VALUE - step) current = Integer.MAX_VALUE;
            else if (step < 0 && value <= Integer.MIN_VALUE - step) current = Integer.MIN_VALUE;
            else current += step;
            return value;
        }
    }

    /**
     * Verifica che l'invariante di rappresentazione sia valido.
     *
     * REQUIRES: nessuno
     * MODIFIES: nessuno
     * EFFECTS: restituisce true se l'invariante di rappresentazione è valido, false altrimenti
     *
     * @return true se l'invariante di rappresentazione è valido, false altrimenti
     */
    private boolean repOk() {
        if (from>to) {
            return step <= 0;
        }
        if (from<to) {
            return step >= 0;   
        }
        return step == 0;
    }

        /**
     * Restituisce una rappresentazione stringa dell'intervallo.
     *
     * REQUIRES: nessuno
     * MODIFIES: nessuno
     * EFFECTS: restituisce una rappresentazione stringa dell'intervallo
     *
     * @return una rappresentazione stringa dell'intervallo
     */
    @Override
    public String toString() {
        return "{" +
                "from:" + from +
                ", to:" + to +
                ", step:" + step +
                '}';
    }

    /**
     * Calcola l'hash code dell'intervallo.
     *
     * REQUIRES: nessuno
     * MODIFIES: nessuno
     * EFFECTS: restituisce l'hash code dell'intervallo
     *
     * @return l'hash code dell'intervallo
     */
    @Override
    public int hashCode() {
        return Objects.hash(from, to, step);
    }

    /**
     * Verifica se questo intervallo è uguale a un altro oggetto.
     *
     * REQUIRES: nessuno
     * MODIFIES: nessuno
     * EFFECTS: restituisce true se questo intervallo è uguale a un altro oggetto, false altrimenti
     *
     * @param o l'oggetto da confrontare
     * @return true se questo intervallo è uguale a o, false altrimenti
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntRange intRange = (IntRange) o;
        return from == intRange.from && to == intRange.to && step == intRange.step;
    }
}
