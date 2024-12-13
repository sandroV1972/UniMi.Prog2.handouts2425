/*

Copyright 2024 Massimo Santini

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

package it.unimi.di.prog2.e17;

import java.util.Objects;

/** A mutable class representing a rectangle with integer valued base and height. */
public class Rectangle {

  private int base;
  private int height;

  /*-
    // RI: base > 0 && height > 0
    // AF: un rettangolo è una figura geometrica formata da 4 lati con una base ed una altezza e lati opposti paraqlleli e angoli interni di 90 gradi

    Check the specification, possibly adding missing exceptions.

    Finish the implementation of the class.
  */

  /**
   * Creates a rectangle of given base and height.
   *
   * @param base the base of the rectangle.
   * @param height height of the rectangle.
   */
  public Rectangle(int base, int height) {
    this.base = base;
    this.height = height;
    repOk();
  }

  /**
   * Returns the base of the rectangle.
   *
   * @return the base of the rectangle.
   */
  public int base() {
    return base;
  }

  /**
   * Sets the base of the rectangle.
   *
   * @param base the new base of the rectangle.
   */
  public void base(int base) {
    this.base = base;
    repOk();
  }

  /**
   * Returns the height of the rectangle.
   *
   * @return the height of the rectangle.
   */
  public int height() {
    return height;
  }

  /**
   * Sets the height of the rectangle.
   *
   * @param height the new height of the rectangle.
   */
  public void height(int height) {
    this.height = height;
    repOk();
  }

  @Override
  public String toString() {
    return "Rectangle[base=" + base + ", height=" + height + "]";
  }

  public void repOk() {
    assert base > 0;
    assert height > 0;
  }

      /**
     * Verifica se questo rettangolo è uguale a un altro oggetto.
     *
     * @param o l'oggetto da confrontare
     * @return true se questo rettangolo è uguale a o, false altrimenti
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return base == rectangle.base && height == rectangle.height;
    }

    /**
     * Calcola l'hash code del rettangolo.
     *
     * @return l'hash code del rettangolo
     */
    @Override
    public int hashCode() {
        return Objects.hash(base, height);
    }
}
